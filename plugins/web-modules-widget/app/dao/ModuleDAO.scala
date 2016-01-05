package dao

import com.google.inject.Inject
import models.{ModuleMachineDaily, ModuleDaily}
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.lifted.ProvenShape

/**
  * Created by ConnorWeng on 2016/1/5.
  */
class ModuleDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  val ModuleDailies = TableQuery[ModuleDailyTable]
  val ModuleMachineDailies = TableQuery[ModuleMachineDailyTable]

  def all() = db.run((ModuleDailies join ModuleMachineDailies on (_.moduleDailyId === _.moduleDailyId)).result)

  class ModuleDailyTable(tag: Tag) extends Table[ModuleDaily](tag, "module_daily") {
    def moduleDailyId = column[Int]("module_daily_id", O.PrimaryKey)
    def appId = column[Int]("app_id")
    def appName = column[String]("app_name")
    def moduleId = column[Int]("module_id")
    def moduleName = column[String]("module_name")
    def duration = column[Long]("duration")
    def dayId = column[Int]("day_id")

    override def * : ProvenShape[ModuleDaily] = (moduleDailyId, appId, appName, moduleId, moduleName, duration, dayId) <> (ModuleDaily.tupled, ModuleDaily.unapply)
  }

  class ModuleMachineDailyTable(tag: Tag) extends Table[ModuleMachineDaily](tag, "module_machine_daily") {
    def moduleDailyId = column[Int]("module_daily_id", O.PrimaryKey)
    def machineId = column[Int]("machine_id", O.PrimaryKey)
    def machineName = column[String]("machine_name")

    override def * : ProvenShape[ModuleMachineDaily] = (moduleDailyId, machineId, machineName) <> (ModuleMachineDaily.tupled, ModuleMachineDaily.unapply)
  }
}
