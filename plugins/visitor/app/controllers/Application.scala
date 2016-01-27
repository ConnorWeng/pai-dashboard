package controllers.visitor

import com.google.inject.Inject
import dao.visitor.VisitorDailyDAO
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2016/1/23.
  */
class Application @Inject()(visitorDailyDAO: VisitorDailyDAO) extends Controller {

  def index() = Action {
    Ok("visitor")
  }

  def visitors(startDate: Int, endDate: Int) = Action.async {
    visitorDailyDAO.all(startDate, endDate).map( rs =>
      Ok(Json.toJson(rs))
    )
  }

}
