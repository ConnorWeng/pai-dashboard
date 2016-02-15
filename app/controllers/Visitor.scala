package controllers

import com.google.inject.Inject
import dao.ArchiveNumericDAO
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2016/1/23.
  */
class Visitor @Inject()(archiveNumericDAO: ArchiveNumericDAO) extends Controller {

  def index() = Action {
    Ok(views.html.visitor_overview())
  }

  def visitors(appId: Int, startDate: String, endDate: String) = Action.async {
    archiveNumericDAO.all(appId, startDate, endDate).map(rs =>
      Ok(Json.toJson(rs))
    )
  }

  def visitorOverviewDaily(appId: Int, startDate: String, endDate: String) = Action.async {
    archiveNumericDAO.visitorOverviewDaily(appId, startDate, endDate).map { rs =>
      val pps = for (i <- rs._3.indices) yield {
        if (rs._3(i) != 0) {
          rs._1(i).toFloat / rs._3(i).toFloat
        } else {
          0
        }
      }
      Ok(Json.obj(
        ("pageview", Json.toJson(rs._1)),
        ("unique_visitor", Json.toJson(rs._2)),
        ("pageview_per_session", Json.toJson(pps))))
    }
  }
}
