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
    Ok("visitor")
  }

  def visitors(appId: Int, startDate: String, endDate: String) = Action.async {
    archiveNumericDAO.all(appId, startDate, endDate).map( rs =>
      Ok(Json.toJson(rs))
    )
  }

}
