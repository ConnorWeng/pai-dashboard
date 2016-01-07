package dao.webmodules

/**
  * Created by ConnorWeng on 2016/1/7.
  */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction

  import slick.jdbc.{GetResult => GR}

  /** Entity class storing rows of table ModuleDaily
   *  @param moduleDailyId Database column module_daily_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param appId Database column app_id SqlType(INT UNSIGNED)
   *  @param appName Database column app_name SqlType(VARCHAR), Length(45,true)
   *  @param moduleId Database column module_id SqlType(INT UNSIGNED)
   *  @param moduleName Database column module_name SqlType(VARCHAR), Length(45,true)
   *  @param moduleView Database column module_view SqlType(INT UNSIGNED)
   *  @param duration Database column duration SqlType(BIGINT UNSIGNED)
   *  @param dayId Database column day_id SqlType(INT UNSIGNED) */
  case class ModuleDailyRow(moduleDailyId: Int, appId: Int, appName: String, moduleId: Int, moduleName: String, moduleView: Int, duration: Long, dayId: Int)
  /** GetResult implicit for fetching ModuleDailyRow objects using plain SQL queries */
  implicit def GetResultModuleDailyRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Long]): GR[ModuleDailyRow] = GR{
    prs => import prs._
    ModuleDailyRow.tupled((<<[Int], <<[Int], <<[String], <<[Int], <<[String], <<[Int], <<[Long], <<[Int]))
  }
  /** Table description of table module_daily. Objects of this class serve as prototypes for rows in queries. */
  class ModuleDaily(_tableTag: Tag) extends Table[ModuleDailyRow](_tableTag, "module_daily") {
    def * = (moduleDailyId, appId, appName, moduleId, moduleName, moduleView, duration, dayId) <> (ModuleDailyRow.tupled, ModuleDailyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(moduleDailyId), Rep.Some(appId), Rep.Some(appName), Rep.Some(moduleId), Rep.Some(moduleName), Rep.Some(moduleView), Rep.Some(duration), Rep.Some(dayId)).shaped.<>({r=>import r._; _1.map(_=> ModuleDailyRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column module_daily_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val moduleDailyId: Rep[Int] = column[Int]("module_daily_id", O.AutoInc, O.PrimaryKey)
    /** Database column app_id SqlType(INT UNSIGNED) */
    val appId: Rep[Int] = column[Int]("app_id")
    /** Database column app_name SqlType(VARCHAR), Length(45,true) */
    val appName: Rep[String] = column[String]("app_name", O.Length(45,varying=true))
    /** Database column module_id SqlType(INT UNSIGNED) */
    val moduleId: Rep[Int] = column[Int]("module_id")
    /** Database column module_name SqlType(VARCHAR), Length(45,true) */
    val moduleName: Rep[String] = column[String]("module_name", O.Length(45,varying=true))
    /** Database column module_view SqlType(INT UNSIGNED) */
    val moduleView: Rep[Int] = column[Int]("module_view")
    /** Database column duration SqlType(BIGINT UNSIGNED) */
    val duration: Rep[Long] = column[Long]("duration")
    /** Database column day_id SqlType(INT UNSIGNED) */
    val dayId: Rep[Int] = column[Int]("day_id")

    /** Index over (dayId) (database name module_daily_day_idx) */
    val index1 = index("module_daily_day_idx", dayId)
  }
  /** Collection-like TableQuery object for table ModuleDaily */
  lazy val ModuleDaily = new TableQuery(tag => new ModuleDaily(tag))

  /** Entity class storing rows of table ModuleMachineDaily
   *  @param moduleDailyId Database column module_daily_id SqlType(INT UNSIGNED)
   *  @param machineId Database column machine_id SqlType(INT UNSIGNED)
   *  @param machineName Database column machine_name SqlType(VARCHAR), Length(45,true), Default(None) */
  case class ModuleMachineDailyRow(moduleDailyId: Int, machineId: Int, machineName: Option[String] = None)
  /** GetResult implicit for fetching ModuleMachineDailyRow objects using plain SQL queries */
  implicit def GetResultModuleMachineDailyRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[ModuleMachineDailyRow] = GR{
    prs => import prs._
    ModuleMachineDailyRow.tupled((<<[Int], <<[Int], <<?[String]))
  }
  /** Table description of table module_machine_daily. Objects of this class serve as prototypes for rows in queries. */
  class ModuleMachineDaily(_tableTag: Tag) extends Table[ModuleMachineDailyRow](_tableTag, "module_machine_daily") {
    def * = (moduleDailyId, machineId, machineName) <> (ModuleMachineDailyRow.tupled, ModuleMachineDailyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(moduleDailyId), Rep.Some(machineId), machineName).shaped.<>({r=>import r._; _1.map(_=> ModuleMachineDailyRow.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column module_daily_id SqlType(INT UNSIGNED) */
    val moduleDailyId: Rep[Int] = column[Int]("module_daily_id")
    /** Database column machine_id SqlType(INT UNSIGNED) */
    val machineId: Rep[Int] = column[Int]("machine_id")
    /** Database column machine_name SqlType(VARCHAR), Length(45,true), Default(None) */
    val machineName: Rep[Option[String]] = column[Option[String]]("machine_name", O.Length(45,varying=true), O.Default(None))

    /** Primary key of ModuleMachineDaily (database name module_machine_daily_PK) */
    val pk = primaryKey("module_machine_daily_PK", (moduleDailyId, machineId))

    /** Foreign key referencing ModuleDaily (database name module_machine_daily_module_daily_fk) */
    lazy val moduleDailyFk = foreignKey("module_machine_daily_module_daily_fk", moduleDailyId, ModuleDaily)(r => r.moduleDailyId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ModuleMachineDaily */
  lazy val ModuleMachineDaily = new TableQuery(tag => new ModuleMachineDaily(tag))
}
