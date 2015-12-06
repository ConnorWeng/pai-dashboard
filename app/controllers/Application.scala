package controllers

import java.lang.Long

import models.PAIMenu
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index(PAIMenu.findAll))
  }

  def doc = Action {
    Ok(views.html.doc("doc"))
  }
}

object Application {
  object Formats {
    def formatDuration(duration: Long): String = {
      duration / 1000 match {
        case x if x < 60 => f"00:00:$x%02d"
        case x if x >= 60 && x < 3600 => f"00:${x/60}%02d:${x%60}%02d"
        case x if x >= 3600 => f"${x/3600}%02d:${x%3600/60}%02d:${x%3600%60}%02d"
      }
    }
  }
}
