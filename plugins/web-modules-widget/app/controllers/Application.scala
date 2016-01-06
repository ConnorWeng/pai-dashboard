package controllers.webmodules

import com.google.inject.Inject
import dao.ModuleDAO
import models.webmodules.WebModule
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{Json, Writes}
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
    implicit val moduleWrites = new Writes[(String, Long, String)] {
      def writes(t: (String, Long, String)) = Json.obj(
        "moduleName" -> t._1,
        "duration" -> t._2,
        "machineName" -> t._3
      )
    }
    moduleDAO.all().map { result =>
      val rs = for {
        (module, machine) <- result
      } yield (module.moduleName, module.duration, machine.machineName)
      Ok(Json.toJson(rs))
    }
  }
}
