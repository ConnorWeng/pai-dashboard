package controllers

import com.google.inject.Inject
import dao.ArchiveNumericDAO
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2016/2/17.
  */
class Browser @Inject()(archiveNumericDAO: ArchiveNumericDAO) extends Controller {

  def browsers(appId: Int, startDate: String, endDate: String) = Action.async {
    archiveNumericDAO.allBrowsers(appId, startDate, endDate).map { rs =>
      val browsers = collection.mutable.Buffer[String]()
      val dataset = collection.mutable.Buffer[Int]()
      rs.foreach { t =>
        browsers.append(t._1)
        dataset.append(t._2)
      }
      Ok(Json.obj(("browsers", Json.toJson(browsers)),("dataset", Json.toJson(dataset))))
    }
  }

}
