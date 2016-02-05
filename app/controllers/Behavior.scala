package controllers

import akka.util.Crypt.md5
import org.apache.hadoop.fs.Path
import org.apache.hadoop.hbase.client.{ConnectionFactory, Scan}
import org.apache.hadoop.hbase.filter.Filter
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import play.Play.application
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.collection.JavaConversions._

/**
  * Created by Connor on 2/4/16.
  */
class Behavior extends Controller {
  val config = HBaseConfiguration.create()
  config.addResource(new Path(application().configuration().getString("hbase.conf.dir"), "hbase-site.xml"))

  def events(appId: String, mid: String, page: String) = Action {
    val con = ConnectionFactory.createConnection(config)
    val table = con.getTable(TableName.valueOf("page_event"))
    val scan = new Scan
    scan.setRowPrefixFilter((md5(appId) + md5(mid) + md5(page)).getBytes)
    val scanner = table.getScanner(scan)
    val results = try {
      scanner.toList.map { result =>
        Json.parse(result.getValue("pe".getBytes, "other".getBytes))
      }
    } finally {
      table.close()
      con.close()
    }
    Ok(Json.toJson(results))
  }
}
