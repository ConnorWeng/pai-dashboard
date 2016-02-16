package dao

import java.sql.Date

import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by ConnorWeng on 2016/2/16.
  */
class ArchiveBlobDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def all(appId: Int, startDate: String, endDate: String) = {
    val query = Tables.ArchiveBlob
      .filter(_.appId === appId)
      .filter(_.period === 1.toByte)
      .filter(_.name === "module")
      .filter(_.date1 >= Date.valueOf(startDate))
      .filter(_.date2 <= Date.valueOf(endDate))

    db.run(query.result)
  }

}
