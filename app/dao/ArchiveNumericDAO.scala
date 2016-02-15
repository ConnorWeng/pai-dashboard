package dao

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar

import com.google.inject.Inject
import models.Visitors
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.collection.mutable

/**
  * Created by Connor on 2/14/16.
  */
class ArchiveNumericDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def all(appId: Int, startDate: String, endDate: String) = {

    val query = Tables.ArchiveNumeric
      .filter(_.appId === appId)
      .filter(_.date1 >= Date.valueOf(startDate))
      .filter(_.date2 <= Date.valueOf(endDate))
      .groupBy(r => (r.appId, r.name))
      .map { case (key, values) =>
        (key._1, key._2, values.map(_.value).sum)
      }

    import play.api.libs.concurrent.Execution.Implicits.defaultContext

    db.run(query.result).map { rows =>
      var pageViews = 0
      var uniqueVisitor = 0
      var session = 0
      rows.foreach { row =>
        row._2 match {
          case "pageview" => pageViews = row._3.getOrElse(0.0).toInt
          case "uniquevisitor" => uniqueVisitor = row._3.getOrElse(0.0).toInt
          case "session" => session = row._3.getOrElse(0.0).toInt
        }
      }
      Visitors(appId, pageViews, session, 0, uniqueVisitor, 0)
    }
  }

  def visitorOverviewDaily(appId: Int, startDate: String, endDate: String) = {
    val query = Tables.ArchiveNumeric
      .filter(_.appId === appId)
      .filter(_.period === 1.toByte)
      .filter(_.date1 >= Date.valueOf(startDate))
      .filter(_.date2 <= Date.valueOf(endDate))

    import play.api.libs.concurrent.Execution.Implicits.defaultContext

    db.run(query.result).map { rows =>
      val dates = dateList(startDate, endDate)
      val pageViewsDaily = new Array[Int](dates.size)
      val uniqueVisitorDaily = new Array[Int](dates.size)
      val sessionDaily = new Array[Int](dates.size)
      val dateFormat = new SimpleDateFormat("yyyyMMdd")

      rows.foreach { row =>
        val dateString = dateFormat.format(row.date1.get)
        row.name match {
          case "pageview" => pageViewsDaily(dates.indexOf(dateString)) = row.value.getOrElse(0.0).toInt
          case "uniquevisitor" => uniqueVisitorDaily(dates.indexOf(dateString)) = row.value.getOrElse(0.0).toInt
          case "session" => sessionDaily(dates.indexOf(dateString)) = row.value.getOrElse(0.0).toInt
        }
      }

      (pageViewsDaily.toList, uniqueVisitorDaily.toList, sessionDaily.toList)
    }
  }

  def dateList(startDate: String, endDate: String) = {
    val startCalendar = new Calendar.Builder().setInstant(Date.valueOf(startDate)).build()
    val endCalendar = new Calendar.Builder().setInstant(Date.valueOf(endDate)).build()
    val dates = mutable.Buffer[String]()
    val dateFormat = new SimpleDateFormat("yyyyMMdd")
    while (startCalendar.compareTo(endCalendar) < 0) {
      dates += dateFormat.format(startCalendar.getTime)
      startCalendar.add(Calendar.DAY_OF_YEAR, 1)
    }
    dates.toList
  }
}
