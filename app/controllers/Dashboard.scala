package controllers

import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2015/12/21.
  */
class Dashboard extends Controller {

  def index = Action {
    Ok(views.html.dashboard())
  }

}