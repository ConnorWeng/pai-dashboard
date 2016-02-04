import org.junit.runner._
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import play.api.inject._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

/**
  * Created by Connor on 1/5/16.
  */
class WebModuleSpec @RunWith(classOf[JUnitRunner]) extends Specification with Mockito {
  val mockDAO = mock[ModuleDAO]
  val app = new GuiceApplicationBuilder()
    .configure("play.http.router" -> "webmodules.Routes")
    .overrides(bind[ModuleDAO] toInstance mockDAO)
    .build

  "Application" should {
    "return empty json array when no modules found" in new WithApplication(app) {
      when(mockDAO.all()).thenReturn(Future(List()))
      val modules = route(FakeRequest(GET, "/modules")).get
      contentAsJson(modules) must equalTo(Json.parse("[]"))
    }

    "return merged modules when there are modules with same module id" in new WithApplication(app) {
      when(mockDAO.all()).thenReturn(Future(List(
        WebModule(1, 1, "BMDP", 1, "菜单管理", List("user1", "user2"), 10, 15000, 20160101),
        WebModule(2, 1, "BMDP", 1, "菜单管理", List("user3"), 10, 15000, 20160102)
      )))
      val modules = route(FakeRequest(GET, "/modules")).get
      contentAsJson(modules) must equalTo(Json.parse(
        """
          |[{
          |  "moduleDailyId": 0,
          |  "appId": 1,
          |  "appName": "BMDP",
          |  "moduleId": 1,
          |  "moduleName": "菜单管理",
          |  "machineNames": ["user1", "user2", "user3"],
          |  "moduleView": 20,
          |  "duration": 30000,
          |  "dayId": 0
          |}]
        """.stripMargin))
    }
  }

}
