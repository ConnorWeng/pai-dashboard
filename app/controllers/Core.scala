package controllers

import models.WebModule
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2015/12/21.
  */
class Core extends Controller {

  def index = Action {
    Ok(views.html.dashboard())
  }

  def findWebModulesByTime = Action {
    Ok(Json.toJson(WebModule.find(0, 99999999999999L)))
  }

}