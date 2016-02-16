package controllers

import com.google.inject.Inject
import dao.{ArchiveBlobDAO, ModuleDAO}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.{Action, Controller}

/**
  * Created by ConnorWeng on 2015/12/24.
  */
class Module @Inject()(moduleDAO: ModuleDAO, archiveBlobDAO: ArchiveBlobDAO) extends Controller {

  def index() = Action {
    Ok("web modules")
  }

  def modules(appId: Int, startDate: String, endDate: String) = Action.async {
    archiveBlobDAO.all(appId, startDate, endDate).map { rs =>
      var map = collection.mutable.Map[String, (Int, String)]()
      rs.foreach { r =>
        val valueObj = Json.parse(r.value.getOrElse("")).as[JsObject] // {"abc": [1, "123,322"], "bcd": [2, "232,121"]}
        val moduleNames = valueObj.keys
        moduleNames.foreach { name =>
          val moduleView = (valueObj \ name).get.head.get.toString.toInt
          val machineNames = (valueObj \ name).get.last.get.toString.replaceAll("\"", "")
          if (map.exists(_._1 == name)) {
            map = map.updated(name, (map(name)._1 + moduleView, map(name)._2 + "," + machineNames))
          } else {
            map += ((name, (moduleView, machineNames)))
          }
        }
      }
      var jsonArray = Json.arr()
      map.toList
        .sortWith((t1, t2) => t1._2._1 > t2._2._1)
        .foreach(t => {
          val machineNamesJsonArray = Json.toJson(t._2._2.split(",").distinct)
          jsonArray = jsonArray.append(
            Json.parse(s"""{"moduleName":"${t._1}","moduleView":${t._2._1}}""").asInstanceOf[JsObject] + ("machineNames", machineNamesJsonArray))
        })
      Ok(jsonArray) // [{moduleName: "abc", moduleView: 15, machineNames: ["111", "222"]}]
    }
  }
}
