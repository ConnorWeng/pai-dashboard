package models

import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by Connor on 12/27/15.
  */
case class WebModule(moduleDailyId: Int, appId: Int, appName: String, moduleId: Int, moduleName: String, machineNames: List[String], moduleView: Long, duration: Long, dayId: Int) {}

object WebModule {
  implicit val webModuleWrites: Writes[WebModule] = (
    (JsPath \ "moduleDailyId").write[Int] and
    (JsPath \ "appId").write[Int] and
    (JsPath \ "appName").write[String] and
    (JsPath \ "moduleId").write[Int] and
    (JsPath \ "moduleName").write[String] and
    (JsPath \ "machineNames").write[List[String]] and
    (JsPath \ "moduleView").write[Long] and
    (JsPath \ "duration").write[Long] and
    (JsPath \ "dayId").write[Int]
  )(unlift(WebModule.unapply))
}
