package models

import play.api.db.DB
import play.api.Play.current
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by ConnorWeng on 2015/12/4.
  */
case class PAIEvent(appId: String, mid: String, event: String, srcElement: String, clicks: Long) {

}

object PAIEvent {
  implicit val paiEventWrites: Writes[PAIEvent] = (
    (JsPath \ "appId").write[String] and
    (JsPath \ "mid").write[String] and
    (JsPath \ "event").write[String] and
    (JsPath \ "srcElement").write[String] and
    (JsPath \ "clicks").write[Long]
  )(unlift(PAIEvent.unapply))

  val events = List()

  def find(appId: String, page: String) = {
    var events = List[PAIEvent]()
    DB.withConnection { conn =>
      val stmt = conn.createStatement()
      val rs = stmt.executeQuery(
        s"""
          |select *
          |from page_view
          |where appId='$appId' and page='$page'
        """.stripMargin)
      while (rs.next()) {
        events = events ::: List(PAIEvent(
          rs.getString("appId"),
          rs.getString("mid"),
          "click",
          rs.getString("srcElement"),
          rs.getLong("clicks")
        ))
      }
    }
    events
  }
}


