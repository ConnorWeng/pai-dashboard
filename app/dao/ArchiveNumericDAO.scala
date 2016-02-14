package dao

import java.sql.Date

import com.google.inject.Inject
import models.Visitors
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

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
      rows.foreach { row =>
        row._2 match {
          case "pageview" => pageViews = row._3.getOrElse(0.0).toInt
          case "uniquevisitor" => uniqueVisitor = row._3.getOrElse(0.0).toInt
        }
      }
      Visitors(appId, pageViews, 0, 0, uniqueVisitor, 0)
    }
  }
}
