package models.webmodules

import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by Connor on 12/27/15.
  */
case class WebModule(appName: String, moduleName: String, machineNames: List[String], moduleView: Long, duration: Long) {}

object WebModule {
  implicit val webModuleWrites: Writes[WebModule] = (
    (JsPath \ "appName").write[String] and
    (JsPath \ "moduleName").write[String] and
    (JsPath \ "machineNames").write[List[String]] and
    (JsPath \ "moduleView").write[Long] and
    (JsPath \ "duration").write[Long]
  )(unlift(WebModule.unapply))
}
