package controllers

import play.api.mvc._

class Admin extends Controller {

  def newapp = Action {
    Ok(views.html.newapp())
  }

}
