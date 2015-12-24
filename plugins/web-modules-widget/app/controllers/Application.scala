package controllers.webmodules

import play.api.mvc.{Controller, Action}

/**
  * Created by ConnorWeng on 2015/12/24.
  */
object Application extends Controller {

  def index() = Action {
    Ok("web modules")
  }

}
