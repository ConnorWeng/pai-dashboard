package models

import play.api.libs.functional.syntax._
import play.api.libs.json._

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
    List(PAIEvent("testapp", "testmid", "testevent", "testelement", 1L))
  }
}


