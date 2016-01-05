package dao

import javax.inject.Inject

import models.Base
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.Future

/**
  * Created by Connor on 1/4/16.
  */
class BaseDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val Bases = TableQuery[BaseTable]

  def all(): Future[Seq[Base]] = db.run(Bases.result)

  private class BaseTable(tag: Tag) extends Table[Base](tag, "base") {
    def id = column[Int]("base_id", O.PrimaryKey)
    def name = column[String]("base_name")

    override def * : ProvenShape[Base] = (id, name) <> (Base.tupled, Base.unapply)
  }
}
