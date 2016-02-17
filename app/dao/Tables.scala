package dao
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(App.schema, ArchiveBlob.schema, ArchiveNumeric.schema, Base.schema, Day.schema, Department.schema, Machine.schema, Module.schema, Month.schema, Page.schema, PageEvent.schema, PlayEvolutions.schema, Week.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table App
   *  @param appId Database column app_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param appName Database column app_name SqlType(VARCHAR), Length(45,true)
   *  @param appUrl Database column app_url SqlType(VARCHAR), Length(255,true)
   *  @param departmentId Database column department_id SqlType(INT UNSIGNED) */
  case class AppRow(appId: Int, appName: String, appUrl: String, departmentId: Int)
  /** GetResult implicit for fetching AppRow objects using plain SQL queries */
  implicit def GetResultAppRow(implicit e0: GR[Int], e1: GR[String]): GR[AppRow] = GR{
    prs => import prs._
    AppRow.tupled((<<[Int], <<[String], <<[String], <<[Int]))
  }
  /** Table description of table app. Objects of this class serve as prototypes for rows in queries. */
  class App(_tableTag: Tag) extends Table[AppRow](_tableTag, "app") {
    def * = (appId, appName, appUrl, departmentId) <> (AppRow.tupled, AppRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(appId), Rep.Some(appName), Rep.Some(appUrl), Rep.Some(departmentId)).shaped.<>({r=>import r._; _1.map(_=> AppRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column app_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val appId: Rep[Int] = column[Int]("app_id", O.AutoInc, O.PrimaryKey)
    /** Database column app_name SqlType(VARCHAR), Length(45,true) */
    val appName: Rep[String] = column[String]("app_name", O.Length(45,varying=true))
    /** Database column app_url SqlType(VARCHAR), Length(255,true) */
    val appUrl: Rep[String] = column[String]("app_url", O.Length(255,varying=true))
    /** Database column department_id SqlType(INT UNSIGNED) */
    val departmentId: Rep[Int] = column[Int]("department_id")

    /** Index over (departmentId) (database name department_id_idx) */
    val index1 = index("department_id_idx", departmentId)
  }
  /** Collection-like TableQuery object for table App */
  lazy val App = new TableQuery(tag => new App(tag))

  /** Entity class storing rows of table ArchiveBlob
   *  @param archiveId Database column archive_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(255,true)
   *  @param appId Database column app_id SqlType(INT UNSIGNED)
   *  @param date1 Database column date1 SqlType(DATE), Default(None)
   *  @param date2 Database column date2 SqlType(DATE), Default(None)
   *  @param period Database column period SqlType(TINYINT UNSIGNED), Default(None)
   *  @param tsArchived Database column ts_archived SqlType(DATETIME), Default(None)
   *  @param value Database column value SqlType(TEXT), Default(None) */
  case class ArchiveBlobRow(archiveId: Int, name: String, appId: Int, date1: Option[java.sql.Date] = None, date2: Option[java.sql.Date] = None, period: Option[Byte] = None, tsArchived: Option[java.sql.Timestamp] = None, value: Option[String] = None)
  /** GetResult implicit for fetching ArchiveBlobRow objects using plain SQL queries */
  implicit def GetResultArchiveBlobRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[java.sql.Date]], e3: GR[Option[Byte]], e4: GR[Option[java.sql.Timestamp]], e5: GR[Option[String]]): GR[ArchiveBlobRow] = GR{
    prs => import prs._
    ArchiveBlobRow.tupled((<<[Int], <<[String], <<[Int], <<?[java.sql.Date], <<?[java.sql.Date], <<?[Byte], <<?[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table archive_blob. Objects of this class serve as prototypes for rows in queries. */
  class ArchiveBlob(_tableTag: Tag) extends Table[ArchiveBlobRow](_tableTag, "archive_blob") {
    def * = (archiveId, name, appId, date1, date2, period, tsArchived, value) <> (ArchiveBlobRow.tupled, ArchiveBlobRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(archiveId), Rep.Some(name), Rep.Some(appId), date1, date2, period, tsArchived, value).shaped.<>({r=>import r._; _1.map(_=> ArchiveBlobRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column archive_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val archiveId: Rep[Int] = column[Int]("archive_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column app_id SqlType(INT UNSIGNED) */
    val appId: Rep[Int] = column[Int]("app_id")
    /** Database column date1 SqlType(DATE), Default(None) */
    val date1: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date1", O.Default(None))
    /** Database column date2 SqlType(DATE), Default(None) */
    val date2: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date2", O.Default(None))
    /** Database column period SqlType(TINYINT UNSIGNED), Default(None) */
    val period: Rep[Option[Byte]] = column[Option[Byte]]("period", O.Default(None))
    /** Database column ts_archived SqlType(DATETIME), Default(None) */
    val tsArchived: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("ts_archived", O.Default(None))
    /** Database column value SqlType(TEXT), Default(None) */
    val value: Rep[Option[String]] = column[Option[String]]("value", O.Default(None))
  }
  /** Collection-like TableQuery object for table ArchiveBlob */
  lazy val ArchiveBlob = new TableQuery(tag => new ArchiveBlob(tag))

  /** Entity class storing rows of table ArchiveNumeric
   *  @param archiveId Database column archive_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(255,true)
   *  @param appId Database column app_id SqlType(INT UNSIGNED)
   *  @param date1 Database column date1 SqlType(DATE), Default(None)
   *  @param date2 Database column date2 SqlType(DATE), Default(None)
   *  @param period Database column period SqlType(TINYINT UNSIGNED), Default(None)
   *  @param tsArchived Database column ts_archived SqlType(DATETIME), Default(None)
   *  @param value Database column value SqlType(DOUBLE), Default(None) */
  case class ArchiveNumericRow(archiveId: Int, name: String, appId: Int, date1: Option[java.sql.Date] = None, date2: Option[java.sql.Date] = None, period: Option[Byte] = None, tsArchived: Option[java.sql.Timestamp] = None, value: Option[Double] = None)
  /** GetResult implicit for fetching ArchiveNumericRow objects using plain SQL queries */
  implicit def GetResultArchiveNumericRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[java.sql.Date]], e3: GR[Option[Byte]], e4: GR[Option[java.sql.Timestamp]], e5: GR[Option[Double]]): GR[ArchiveNumericRow] = GR{
    prs => import prs._
    ArchiveNumericRow.tupled((<<[Int], <<[String], <<[Int], <<?[java.sql.Date], <<?[java.sql.Date], <<?[Byte], <<?[java.sql.Timestamp], <<?[Double]))
  }
  /** Table description of table archive_numeric. Objects of this class serve as prototypes for rows in queries. */
  class ArchiveNumeric(_tableTag: Tag) extends Table[ArchiveNumericRow](_tableTag, "archive_numeric") {
    def * = (archiveId, name, appId, date1, date2, period, tsArchived, value) <> (ArchiveNumericRow.tupled, ArchiveNumericRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(archiveId), Rep.Some(name), Rep.Some(appId), date1, date2, period, tsArchived, value).shaped.<>({r=>import r._; _1.map(_=> ArchiveNumericRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column archive_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val archiveId: Rep[Int] = column[Int]("archive_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column app_id SqlType(INT UNSIGNED) */
    val appId: Rep[Int] = column[Int]("app_id")
    /** Database column date1 SqlType(DATE), Default(None) */
    val date1: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date1", O.Default(None))
    /** Database column date2 SqlType(DATE), Default(None) */
    val date2: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date2", O.Default(None))
    /** Database column period SqlType(TINYINT UNSIGNED), Default(None) */
    val period: Rep[Option[Byte]] = column[Option[Byte]]("period", O.Default(None))
    /** Database column ts_archived SqlType(DATETIME), Default(None) */
    val tsArchived: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("ts_archived", O.Default(None))
    /** Database column value SqlType(DOUBLE), Default(None) */
    val value: Rep[Option[Double]] = column[Option[Double]]("value", O.Default(None))
  }
  /** Collection-like TableQuery object for table ArchiveNumeric */
  lazy val ArchiveNumeric = new TableQuery(tag => new ArchiveNumeric(tag))

  /** Entity class storing rows of table Base
   *  @param baseId Database column base_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param baseName Database column base_name SqlType(VARCHAR), Length(45,true) */
  case class BaseRow(baseId: Int, baseName: String)
  /** GetResult implicit for fetching BaseRow objects using plain SQL queries */
  implicit def GetResultBaseRow(implicit e0: GR[Int], e1: GR[String]): GR[BaseRow] = GR{
    prs => import prs._
    BaseRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table base. Objects of this class serve as prototypes for rows in queries. */
  class Base(_tableTag: Tag) extends Table[BaseRow](_tableTag, "base") {
    def * = (baseId, baseName) <> (BaseRow.tupled, BaseRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(baseId), Rep.Some(baseName)).shaped.<>({r=>import r._; _1.map(_=> BaseRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column base_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val baseId: Rep[Int] = column[Int]("base_id", O.AutoInc, O.PrimaryKey)
    /** Database column base_name SqlType(VARCHAR), Length(45,true) */
    val baseName: Rep[String] = column[String]("base_name", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table Base */
  lazy val Base = new TableQuery(tag => new Base(tag))

  /** Entity class storing rows of table Day
   *  @param dayId Database column day_id SqlType(INT UNSIGNED), PrimaryKey
   *  @param dayName Database column day_name SqlType(VARCHAR), Length(10,true)
   *  @param dayNumInWeek Database column day_num_in_week SqlType(BIT)
   *  @param weekday Database column weekday SqlType(BIT)
   *  @param holiday Database column holiday SqlType(BIT)
   *  @param weekId Database column week_id SqlType(INT UNSIGNED)
   *  @param monthId Database column month_id SqlType(INT UNSIGNED) */
  case class DayRow(dayId: Int, dayName: String, dayNumInWeek: Boolean, weekday: Boolean, holiday: Boolean, weekId: Int, monthId: Int)
  /** GetResult implicit for fetching DayRow objects using plain SQL queries */
  implicit def GetResultDayRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[DayRow] = GR{
    prs => import prs._
    DayRow.tupled((<<[Int], <<[String], <<[Boolean], <<[Boolean], <<[Boolean], <<[Int], <<[Int]))
  }
  /** Table description of table day. Objects of this class serve as prototypes for rows in queries. */
  class Day(_tableTag: Tag) extends Table[DayRow](_tableTag, "day") {
    def * = (dayId, dayName, dayNumInWeek, weekday, holiday, weekId, monthId) <> (DayRow.tupled, DayRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(dayId), Rep.Some(dayName), Rep.Some(dayNumInWeek), Rep.Some(weekday), Rep.Some(holiday), Rep.Some(weekId), Rep.Some(monthId)).shaped.<>({r=>import r._; _1.map(_=> DayRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column day_id SqlType(INT UNSIGNED), PrimaryKey */
    val dayId: Rep[Int] = column[Int]("day_id", O.PrimaryKey)
    /** Database column day_name SqlType(VARCHAR), Length(10,true) */
    val dayName: Rep[String] = column[String]("day_name", O.Length(10,varying=true))
    /** Database column day_num_in_week SqlType(BIT) */
    val dayNumInWeek: Rep[Boolean] = column[Boolean]("day_num_in_week")
    /** Database column weekday SqlType(BIT) */
    val weekday: Rep[Boolean] = column[Boolean]("weekday")
    /** Database column holiday SqlType(BIT) */
    val holiday: Rep[Boolean] = column[Boolean]("holiday")
    /** Database column week_id SqlType(INT UNSIGNED) */
    val weekId: Rep[Int] = column[Int]("week_id")
    /** Database column month_id SqlType(INT UNSIGNED) */
    val monthId: Rep[Int] = column[Int]("month_id")

    /** Index over (monthId) (database name month_id_idx) */
    val index1 = index("month_id_idx", monthId)
    /** Index over (weekId) (database name week_id_idx) */
    val index2 = index("week_id_idx", weekId)
  }
  /** Collection-like TableQuery object for table Day */
  lazy val Day = new TableQuery(tag => new Day(tag))

  /** Entity class storing rows of table Department
   *  @param departmentId Database column department_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param departmentName Database column department_name SqlType(VARCHAR), Length(45,true)
   *  @param baseId Database column base_id SqlType(INT UNSIGNED) */
  case class DepartmentRow(departmentId: Int, departmentName: String, baseId: Int)
  /** GetResult implicit for fetching DepartmentRow objects using plain SQL queries */
  implicit def GetResultDepartmentRow(implicit e0: GR[Int], e1: GR[String]): GR[DepartmentRow] = GR{
    prs => import prs._
    DepartmentRow.tupled((<<[Int], <<[String], <<[Int]))
  }
  /** Table description of table department. Objects of this class serve as prototypes for rows in queries. */
  class Department(_tableTag: Tag) extends Table[DepartmentRow](_tableTag, "department") {
    def * = (departmentId, departmentName, baseId) <> (DepartmentRow.tupled, DepartmentRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(departmentId), Rep.Some(departmentName), Rep.Some(baseId)).shaped.<>({r=>import r._; _1.map(_=> DepartmentRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column department_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val departmentId: Rep[Int] = column[Int]("department_id", O.AutoInc, O.PrimaryKey)
    /** Database column department_name SqlType(VARCHAR), Length(45,true) */
    val departmentName: Rep[String] = column[String]("department_name", O.Length(45,varying=true))
    /** Database column base_id SqlType(INT UNSIGNED) */
    val baseId: Rep[Int] = column[Int]("base_id")

    /** Index over (baseId) (database name base_id_idx) */
    val index1 = index("base_id_idx", baseId)
  }
  /** Collection-like TableQuery object for table Department */
  lazy val Department = new TableQuery(tag => new Department(tag))

  /** Entity class storing rows of table Machine
   *  @param machineId Database column machine_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param machineCode Database column machine_code SqlType(VARCHAR), Length(45,true)
   *  @param machineName Database column machine_name SqlType(VARCHAR), Length(45,true), Default(None)
   *  @param browser Database column browser SqlType(VARCHAR), Length(45,true), Default(None)
   *  @param resolution Database column resolution SqlType(VARCHAR), Length(45,true), Default(None)
   *  @param firstDayId Database column first_day_id SqlType(INT UNSIGNED)
   *  @param lastDayId Database column last_day_id SqlType(INT UNSIGNED) */
  case class MachineRow(machineId: Int, machineCode: String, machineName: Option[String] = None, browser: Option[String] = None, resolution: Option[String] = None, firstDayId: Int, lastDayId: Int)
  /** GetResult implicit for fetching MachineRow objects using plain SQL queries */
  implicit def GetResultMachineRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[MachineRow] = GR{
    prs => import prs._
    MachineRow.tupled((<<[Int], <<[String], <<?[String], <<?[String], <<?[String], <<[Int], <<[Int]))
  }
  /** Table description of table machine. Objects of this class serve as prototypes for rows in queries. */
  class Machine(_tableTag: Tag) extends Table[MachineRow](_tableTag, "machine") {
    def * = (machineId, machineCode, machineName, browser, resolution, firstDayId, lastDayId) <> (MachineRow.tupled, MachineRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(machineId), Rep.Some(machineCode), machineName, browser, resolution, Rep.Some(firstDayId), Rep.Some(lastDayId)).shaped.<>({r=>import r._; _1.map(_=> MachineRow.tupled((_1.get, _2.get, _3, _4, _5, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column machine_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val machineId: Rep[Int] = column[Int]("machine_id", O.AutoInc, O.PrimaryKey)
    /** Database column machine_code SqlType(VARCHAR), Length(45,true) */
    val machineCode: Rep[String] = column[String]("machine_code", O.Length(45,varying=true))
    /** Database column machine_name SqlType(VARCHAR), Length(45,true), Default(None) */
    val machineName: Rep[Option[String]] = column[Option[String]]("machine_name", O.Length(45,varying=true), O.Default(None))
    /** Database column browser SqlType(VARCHAR), Length(45,true), Default(None) */
    val browser: Rep[Option[String]] = column[Option[String]]("browser", O.Length(45,varying=true), O.Default(None))
    /** Database column resolution SqlType(VARCHAR), Length(45,true), Default(None) */
    val resolution: Rep[Option[String]] = column[Option[String]]("resolution", O.Length(45,varying=true), O.Default(None))
    /** Database column first_day_id SqlType(INT UNSIGNED) */
    val firstDayId: Rep[Int] = column[Int]("first_day_id")
    /** Database column last_day_id SqlType(INT UNSIGNED) */
    val lastDayId: Rep[Int] = column[Int]("last_day_id")

    /** Index over (firstDayId) (database name first_day_id_idx) */
    val index1 = index("first_day_id_idx", firstDayId)
    /** Index over (lastDayId) (database name last_day_id_idx) */
    val index2 = index("last_day_id_idx", lastDayId)
  }
  /** Collection-like TableQuery object for table Machine */
  lazy val Machine = new TableQuery(tag => new Machine(tag))

  /** Entity class storing rows of table Module
   *  @param moduleId Database column module_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param moduleName Database column module_name SqlType(VARCHAR), Length(45,true)
   *  @param moduleUrl Database column module_url SqlType(VARCHAR), Length(2038,true)
   *  @param appId Database column app_id SqlType(INT UNSIGNED) */
  case class ModuleRow(moduleId: Int, moduleName: String, moduleUrl: String, appId: Int)
  /** GetResult implicit for fetching ModuleRow objects using plain SQL queries */
  implicit def GetResultModuleRow(implicit e0: GR[Int], e1: GR[String]): GR[ModuleRow] = GR{
    prs => import prs._
    ModuleRow.tupled((<<[Int], <<[String], <<[String], <<[Int]))
  }
  /** Table description of table module. Objects of this class serve as prototypes for rows in queries. */
  class Module(_tableTag: Tag) extends Table[ModuleRow](_tableTag, "module") {
    def * = (moduleId, moduleName, moduleUrl, appId) <> (ModuleRow.tupled, ModuleRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(moduleId), Rep.Some(moduleName), Rep.Some(moduleUrl), Rep.Some(appId)).shaped.<>({r=>import r._; _1.map(_=> ModuleRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column module_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val moduleId: Rep[Int] = column[Int]("module_id", O.AutoInc, O.PrimaryKey)
    /** Database column module_name SqlType(VARCHAR), Length(45,true) */
    val moduleName: Rep[String] = column[String]("module_name", O.Length(45,varying=true))
    /** Database column module_url SqlType(VARCHAR), Length(2038,true) */
    val moduleUrl: Rep[String] = column[String]("module_url", O.Length(2038,varying=true))
    /** Database column app_id SqlType(INT UNSIGNED) */
    val appId: Rep[Int] = column[Int]("app_id")

    /** Index over (appId) (database name app_id_idx) */
    val index1 = index("app_id_idx", appId)
  }
  /** Collection-like TableQuery object for table Module */
  lazy val Module = new TableQuery(tag => new Module(tag))

  /** Entity class storing rows of table Month
   *  @param monthId Database column month_id SqlType(INT UNSIGNED), PrimaryKey
   *  @param monthName Database column month_name SqlType(VARCHAR), Length(45,true)
   *  @param monthNumInYear Database column month_num_in_year SqlType(TINYINT)
   *  @param year Database column year SqlType(SMALLINT UNSIGNED) */
  case class MonthRow(monthId: Int, monthName: String, monthNumInYear: Byte, year: Short)
  /** GetResult implicit for fetching MonthRow objects using plain SQL queries */
  implicit def GetResultMonthRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Byte], e3: GR[Short]): GR[MonthRow] = GR{
    prs => import prs._
    MonthRow.tupled((<<[Int], <<[String], <<[Byte], <<[Short]))
  }
  /** Table description of table month. Objects of this class serve as prototypes for rows in queries. */
  class Month(_tableTag: Tag) extends Table[MonthRow](_tableTag, "month") {
    def * = (monthId, monthName, monthNumInYear, year) <> (MonthRow.tupled, MonthRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(monthId), Rep.Some(monthName), Rep.Some(monthNumInYear), Rep.Some(year)).shaped.<>({r=>import r._; _1.map(_=> MonthRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column month_id SqlType(INT UNSIGNED), PrimaryKey */
    val monthId: Rep[Int] = column[Int]("month_id", O.PrimaryKey)
    /** Database column month_name SqlType(VARCHAR), Length(45,true) */
    val monthName: Rep[String] = column[String]("month_name", O.Length(45,varying=true))
    /** Database column month_num_in_year SqlType(TINYINT) */
    val monthNumInYear: Rep[Byte] = column[Byte]("month_num_in_year")
    /** Database column year SqlType(SMALLINT UNSIGNED) */
    val year: Rep[Short] = column[Short]("year")
  }
  /** Collection-like TableQuery object for table Month */
  lazy val Month = new TableQuery(tag => new Month(tag))

  /** Entity class storing rows of table Page
   *  @param pageId Database column page_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param pageUrl Database column page_url SqlType(VARCHAR), Length(2038,true)
   *  @param appId Database column app_id SqlType(INT UNSIGNED)
   *  @param moduleId Database column module_id SqlType(INT UNSIGNED), Default(None) */
  case class PageRow(pageId: Int, pageUrl: String, appId: Int, moduleId: Option[Int] = None)
  /** GetResult implicit for fetching PageRow objects using plain SQL queries */
  implicit def GetResultPageRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]]): GR[PageRow] = GR{
    prs => import prs._
    PageRow.tupled((<<[Int], <<[String], <<[Int], <<?[Int]))
  }
  /** Table description of table page. Objects of this class serve as prototypes for rows in queries. */
  class Page(_tableTag: Tag) extends Table[PageRow](_tableTag, "page") {
    def * = (pageId, pageUrl, appId, moduleId) <> (PageRow.tupled, PageRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(pageId), Rep.Some(pageUrl), Rep.Some(appId), moduleId).shaped.<>({r=>import r._; _1.map(_=> PageRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column page_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val pageId: Rep[Int] = column[Int]("page_id", O.AutoInc, O.PrimaryKey)
    /** Database column page_url SqlType(VARCHAR), Length(2038,true) */
    val pageUrl: Rep[String] = column[String]("page_url", O.Length(2038,varying=true))
    /** Database column app_id SqlType(INT UNSIGNED) */
    val appId: Rep[Int] = column[Int]("app_id")
    /** Database column module_id SqlType(INT UNSIGNED), Default(None) */
    val moduleId: Rep[Option[Int]] = column[Option[Int]]("module_id", O.Default(None))

    /** Index over (appId) (database name page_app_idx) */
    val index1 = index("page_app_idx", appId)
    /** Index over (moduleId) (database name page_module_idx) */
    val index2 = index("page_module_idx", moduleId)
  }
  /** Collection-like TableQuery object for table Page */
  lazy val Page = new TableQuery(tag => new Page(tag))

  /** Entity class storing rows of table PageEvent
   *  @param pageEventId Database column page_event_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param machineId Database column machine_id SqlType(INT UNSIGNED)
   *  @param sessionCode Database column session_code SqlType(VARCHAR), Length(45,true)
   *  @param timestamp Database column timestamp SqlType(BIGINT UNSIGNED)
   *  @param pageId Database column page_id SqlType(INT UNSIGNED)
   *  @param eventType Database column event_type SqlType(VARCHAR), Length(45,true)
   *  @param eventData Database column event_data SqlType(VARCHAR), Length(5000,true)
   *  @param dayId Database column day_id SqlType(INT UNSIGNED) */
  case class PageEventRow(pageEventId: Int, machineId: Int, sessionCode: String, timestamp: Long, pageId: Int, eventType: String, eventData: String, dayId: Int)
  /** GetResult implicit for fetching PageEventRow objects using plain SQL queries */
  implicit def GetResultPageEventRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Long]): GR[PageEventRow] = GR{
    prs => import prs._
    PageEventRow.tupled((<<[Int], <<[Int], <<[String], <<[Long], <<[Int], <<[String], <<[String], <<[Int]))
  }
  /** Table description of table page_event. Objects of this class serve as prototypes for rows in queries. */
  class PageEvent(_tableTag: Tag) extends Table[PageEventRow](_tableTag, "page_event") {
    def * = (pageEventId, machineId, sessionCode, timestamp, pageId, eventType, eventData, dayId) <> (PageEventRow.tupled, PageEventRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(pageEventId), Rep.Some(machineId), Rep.Some(sessionCode), Rep.Some(timestamp), Rep.Some(pageId), Rep.Some(eventType), Rep.Some(eventData), Rep.Some(dayId)).shaped.<>({r=>import r._; _1.map(_=> PageEventRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column page_event_id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val pageEventId: Rep[Int] = column[Int]("page_event_id", O.AutoInc, O.PrimaryKey)
    /** Database column machine_id SqlType(INT UNSIGNED) */
    val machineId: Rep[Int] = column[Int]("machine_id")
    /** Database column session_code SqlType(VARCHAR), Length(45,true) */
    val sessionCode: Rep[String] = column[String]("session_code", O.Length(45,varying=true))
    /** Database column timestamp SqlType(BIGINT UNSIGNED) */
    val timestamp: Rep[Long] = column[Long]("timestamp")
    /** Database column page_id SqlType(INT UNSIGNED) */
    val pageId: Rep[Int] = column[Int]("page_id")
    /** Database column event_type SqlType(VARCHAR), Length(45,true) */
    val eventType: Rep[String] = column[String]("event_type", O.Length(45,varying=true))
    /** Database column event_data SqlType(VARCHAR), Length(5000,true) */
    val eventData: Rep[String] = column[String]("event_data", O.Length(5000,varying=true))
    /** Database column day_id SqlType(INT UNSIGNED) */
    val dayId: Rep[Int] = column[Int]("day_id")

    /** Index over (dayId) (database name day_id_idx) */
    val index1 = index("day_id_idx", dayId)
    /** Index over (pageId) (database name event_page_idx) */
    val index2 = index("event_page_idx", pageId)
    /** Index over (machineId) (database name machine_id_idx) */
    val index3 = index("machine_id_idx", machineId)
  }
  /** Collection-like TableQuery object for table PageEvent */
  lazy val PageEvent = new TableQuery(tag => new PageEvent(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param hash Database column hash SqlType(VARCHAR), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(TIMESTAMP)
   *  @param applyScript Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param revertScript Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param state Database column state SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(VARCHAR), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(TIMESTAMP) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column state SqlType(VARCHAR), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Length(16777215,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table Week
   *  @param weekId Database column week_id SqlType(INT UNSIGNED), PrimaryKey
   *  @param weekNumInYear Database column week_num_in_year SqlType(TINYINT UNSIGNED)
   *  @param year Database column year SqlType(SMALLINT UNSIGNED) */
  case class WeekRow(weekId: Int, weekNumInYear: Byte, year: Short)
  /** GetResult implicit for fetching WeekRow objects using plain SQL queries */
  implicit def GetResultWeekRow(implicit e0: GR[Int], e1: GR[Byte], e2: GR[Short]): GR[WeekRow] = GR{
    prs => import prs._
    WeekRow.tupled((<<[Int], <<[Byte], <<[Short]))
  }
  /** Table description of table week. Objects of this class serve as prototypes for rows in queries. */
  class Week(_tableTag: Tag) extends Table[WeekRow](_tableTag, "week") {
    def * = (weekId, weekNumInYear, year) <> (WeekRow.tupled, WeekRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(weekId), Rep.Some(weekNumInYear), Rep.Some(year)).shaped.<>({r=>import r._; _1.map(_=> WeekRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column week_id SqlType(INT UNSIGNED), PrimaryKey */
    val weekId: Rep[Int] = column[Int]("week_id", O.PrimaryKey)
    /** Database column week_num_in_year SqlType(TINYINT UNSIGNED) */
    val weekNumInYear: Rep[Byte] = column[Byte]("week_num_in_year")
    /** Database column year SqlType(SMALLINT UNSIGNED) */
    val year: Rep[Short] = column[Short]("year")
  }
  /** Collection-like TableQuery object for table Week */
  lazy val Week = new TableQuery(tag => new Week(tag))
}
