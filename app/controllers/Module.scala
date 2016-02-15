package controllers

import com.google.inject.Inject
import dao.ModuleDAO
import models.WebModule
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2015/12/24.
  */
class Module @Inject()(moduleDAO: ModuleDAO) extends Controller {

  def index() = Action {
    Ok("web modules")
  }

  def modules(appId: Int, startDate: Int, endDate: Int) = Action.async {
    moduleDAO.all(appId, startDate, endDate).map { rs =>
      val result = rs.groupBy(_.moduleId).map { case (moduleId, webModules) =>
        val m = webModules.head
        val machineNames = (webModules./:[List[String]](List())(_ ::: _.machineNames)).distinct
        val moduleView = webModules./:[Long](0)(_ + _.moduleView)
        val duration = webModules./:[Long](0)(_ + _.duration)
        WebModule(0, m.appId, m.appName, moduleId, m.moduleName, machineNames, moduleView, duration, 0)
      }
      Ok(Json.toJson(result.toList.sortWith(_.moduleView > _.moduleView)))
    }
  }
}
