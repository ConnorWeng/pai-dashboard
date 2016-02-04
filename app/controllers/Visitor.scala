package controllers

import com.google.inject.Inject
import dao.VisitorDailyDAO
import models.Visitors
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2016/1/23.
  */
class Visitor @Inject()(visitorDailyDAO: VisitorDailyDAO) extends Controller {

  def index() = Action {
    Ok("visitor")
  }

  def visitors(appId: Int, startDate: Int, endDate: Int) = Action.async {
    visitorDailyDAO.all(appId, startDate, endDate).map( rs =>
      Ok(Json.toJson(rs.getOrElse(Visitors(appId, 0, 0, 0, 0, 0))))
    )
  }

}
