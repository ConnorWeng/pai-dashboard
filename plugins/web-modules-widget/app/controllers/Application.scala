package controllers.webmodules

import play.api.libs.json.Json
import play.api.mvc.{Controller, Action}
import models.webmodules.WebModule

/**
  * Created by ConnorWeng on 2015/12/24.
  */
object Application extends Controller {

  def index() = Action {
    Ok("web modules")
  }

  def findWebModulesByTime = Action {
    Ok(Json.toJson(WebModule.find(0, 99999999999999L)))
  }

}
