package dao

import com.google.inject.Inject
import models.WebModule
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * Created by ConnorWeng on 2016/1/5.
  */
class ModuleDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  def all(): Future[Iterable[WebModule]] = all(1, 20000101, 29991231)

  def all(appId: Int, startDate: Int, endDate: Int): Future[Iterable[WebModule]] = {
    val query = for {
      md <- Tables.ModuleDaily if md.dayId >= startDate && md.dayId <= endDate && md.appId === appId
      mmd <- Tables.ModuleMachineDaily if mmd.moduleDailyId === md.moduleDailyId
    } yield (md.moduleDailyId, md.appId, md.appName, md.moduleId, md.moduleName, mmd.machineName, md.moduleView, md.duration, md.dayId)

    import play.api.libs.concurrent.Execution.Implicits.defaultContext

    db.run(query.result).map { result =>
      result.groupBy(t => t._1).map { case (moduleDailyId, fields) =>
        val head = fields.head
        WebModule(moduleDailyId, head._2, head._3, head._4, head._5, fields.map(_._6.get).toList, head._7, head._8, head._9)
      }
    }
  }
}
