package dao

import com.google.inject.Inject
import models.Visitors
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by ConnorWeng on 2016/1/27.
  */
class VisitorDailyDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  def all(appId: Int, startDate: Int, endDate: Int) = {
    val query = Tables.VisitorDaily
      .filter(_.appId === appId)
      .filter(_.dayId >= startDate)
      .filter(_.dayId <= endDate)
      .groupBy(_.appId)
      .map { case (appId, fields) =>
        (appId, fields.map(_.pageViews).sum, fields.map(_.sessions).sum, fields.map(_.bounceRate).sum,
          fields.map(_.uniqueVisitors).sum, 0)
      }

    import play.api.libs.concurrent.Execution.Implicits.defaultContext

    db.run(query.result).map { rows =>
      rows.map { row =>
        Visitors(row._1, row._2.getOrElse(0), row._3.getOrElse(0), row._4.getOrElse(0), row._5.getOrElse(0), row._6)
      }.headOption
    }
  }

}
