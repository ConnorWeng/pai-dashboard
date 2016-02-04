package models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Writes}

/**
  * Created by ConnorWeng on 2016/1/27.
  */
case class Visitors(appId: Int, pageViews: Int, sessions: Int, bounceRate: Int, uniqueVisitors: Int, dayId: Int) {}

object Visitors {
  implicit val VisitorsWrites: Writes[Visitors] = (
    (JsPath \ "appId").write[Int] and
    (JsPath \ "pageViews").write[Int] and
    (JsPath \ "sessions").write[Int] and
    (JsPath \ "bounceRate").write[Int] and
    (JsPath \ "uniqueVisitors").write[Int] and
    (JsPath \ "dayId").write[Int]
  )(unlift(Visitors.unapply))
}
