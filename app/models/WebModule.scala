package models

import play.api.Play.current
import play.api.db.DB
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by Connor on 12/27/15.
  */
case class WebModule(appId: String, name: String, users: List[String], page: String, clicks: Long, duration: Long) {}

object WebModule {
  implicit val webModuleWrites: Writes[WebModule] = (
    (JsPath \ "appId").write[String] and
    (JsPath \ "name").write[String] and
    (JsPath \ "users").write[List[String]] and
    (JsPath \ "page").write[String] and
    (JsPath \ "clicks").write[Long] and
    (JsPath \ "duration").write[Long]
  )(unlift(WebModule.unapply))

  def find(startTime: Long, endTime: Long) = {
    var modules = List[WebModule]()
    DB.withConnection { conn =>
      val stmt = conn.createStatement()
      val rs = stmt.executeQuery(s"""
        select
          max(appId) appId, group_concat(distinct mid separator ',') users,
          menu, max(page) page, sum(clicks) clicks, sum(duration) duration
        from menu_view
        where appId in ("http://83.24.113.34", "http://107.252.77.210")
        and start_time > $startTime
        and end_time < $endTime
        group by menu
      """.stripMargin);
      while (rs.next()) {
        modules = modules ::: List(WebModule(
          rs.getString("appId"),
          rs.getString("menu"),
          rs.getString("users").split(",").toList,
          rs.getString("page"),
          rs.getLong("clicks"),
          rs.getLong("duration")))
      }
    }
    modules.sortWith(_.duration > _.duration)
  }
}
