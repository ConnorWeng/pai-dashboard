package controllers

import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2015/12/21.
  */
class Core extends Controller {

  def index = Action {
    Ok(views.html.dashboard())
  }

}