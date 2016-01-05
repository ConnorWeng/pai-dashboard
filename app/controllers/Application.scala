package controllers

import java.lang.Long
import java.text.DateFormat
import java.util.Date
import javax.inject.Inject

import dao.BaseDAO
import models.{PAIEvent, PAIMenu}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._

class Application @Inject()(baseDAO: BaseDAO) extends Controller {

  def index(startTime: String, endTime: String) = Action {
    val df = DateFormat.getDateInstance()
    val start = if (startTime.isEmpty) 0 else df.parse(startTime).getTime
    val end = if (endTime.isEmpty) (new Date().getTime) else df.parse(endTime).getTime + 24*60*60*1000
    Ok(views.html.index(PAIMenu.findAll(start, end), startTime, endTime))
  }

  def eventsClick(appId: String, page: String) = Action {
    Ok(views.html.event(PAIEvent.find(appId, page)))
  }

  def bases() = Action.async {
    baseDAO.all().map(b => Ok("ok"))
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
