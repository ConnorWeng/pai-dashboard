package models

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

  def findAll = menus
}
