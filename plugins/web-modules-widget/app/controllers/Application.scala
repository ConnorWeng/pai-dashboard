package controllers.webmodules

import com.google.inject.Inject
import dao.ModuleDAO
import models.webmodules.WebModule
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

  def findWebModulesByTime = Action {
    Ok(Json.toJson(WebModule.find(0, 99999999999999L)))
  }

  def modules = Action.async {
    moduleDAO.all().map { result =>
      val rs = for {
        (module, machine) <- result
      } yield (module.moduleName, module.duration, machine.machineName)
      Ok(rs.toString)
    }
  }
}
