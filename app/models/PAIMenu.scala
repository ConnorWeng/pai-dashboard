package models

import play.api.db.DB
import play.api.Play.current

/**
  * Created by ConnorWeng on 2015/12/4.
  */
case class PAIMenu(appId: String, users: List[String], menu: String, page: String,
                  clicks: Long, duration: Long) {

}

object PAIMenu {
  val menus = List(
    PAIMenu("cams", List("user1", "user2"), "功能模块1", "http://somewhere.com/page1", 154L, 11300000L),
    PAIMenu("cams", List("user2","user4"), "功能模块2", "http://somewhere.com/page2", 14L, 100000L),
    PAIMenu("cams", List("user1"), "功能模块3", "http://somewhere.com/page3", 15L, 9900L),
    PAIMenu("cams", List("user1", "user2", "user3", "user4"), "功能模块4", "http://somewhere.com/page4", 99L, 5800L),
    PAIMenu("cams", List("user3"), "功能模块5", "http://somewhere.com/page5", 7L, 4300L),
    PAIMenu("cams", List("user4"), "功能模块6", "http://somewhere.com/page6", 10L, 3300L),
    PAIMenu("cams", List("user3", "user4"), "功能模块7", "http://somewhere.com/page7", 10L, 1900L)
  )

  def findAllTest = menus

  def findAll = {
    var menus = List[PAIMenu]()
    DB.withConnection { conn =>
      val stmt = conn.createStatement()
      val rs = stmt.executeQuery("""
        select
          max(appId) appId, group_concat(mid separator ',') users,
          menu, max(page) page, sum(clicks) clicks, sum(duration) duration
        from menu_view
        where appId in ("http://83.24.113.34", "http://107.252.77.210")
        group by menu
      """.stripMargin);
      while (rs.next()) {
        menus = menus ::: List(PAIMenu(
          rs.getString("appId"),
          rs.getString("users").split(",").toList,
          rs.getString("menu"),
          rs.getString("page"),
          rs.getLong("clicks"),
          rs.getLong("duration")))
      }
    }
    menus.sortWith(_.duration > _.duration)
  }
}
