package controllers.webmodules

import com.google.inject.Inject
import dao.webmodules.ModuleDAO
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2015/12/24.
  */
class Application @Inject()(moduleDAO: ModuleDAO) extends Controller {
  
  def index() = Action {
    Ok("web modules")
  }

  def modules = Action.async {
    moduleDAO.all().map { result =>
      Ok(Json.toJson(result.toList.sortWith(_.duration > _.duration)))
    }
  }
}
