package models.webmodules

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
    List(WebModule("testapp", "testname", List("testusers"), "testpage", 1L, 1L))
  }
}
