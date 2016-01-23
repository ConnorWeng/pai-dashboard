package controllers.mousemove

import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2016/1/23.
  */
class Application extends Controller {

  def index() = Action {
    Ok("mouse move")
  }

}
