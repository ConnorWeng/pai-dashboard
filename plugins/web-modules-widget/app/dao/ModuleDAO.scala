package dao.webmodules

import com.google.inject.Inject
import models.webmodules.WebModule
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by ConnorWeng on 2016/1/5.
  */
class ModuleDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  def all() = {
    val query = for {
      md <- Tables.ModuleDaily
      mmd <- Tables.ModuleMachineDaily if mmd.moduleDailyId === md.moduleDailyId
    } yield (md.appName, md.moduleName, md.moduleView, md.duration, mmd.machineName)

    import play.api.libs.concurrent.Execution.Implicits.defaultContext

    db.run(query.result).map { result =>
      result.groupBy(t => (t._1, t._2, t._3, t._4)).map { case (keys, values) =>
        WebModule(keys._1, keys._2, values.map(_._5.get).toList, keys._3, keys._4)
      }
    }
  }
}
