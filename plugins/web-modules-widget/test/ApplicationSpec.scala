import dao.ModuleDAO
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
class ApplicationSpec @RunWith(classOf[JUnitRunner]) extends Specification with Mockito {
  val mockDAO = mock[ModuleDAO]
  when(mockDAO.all()).thenReturn(Future(List()))

  val app = new GuiceApplicationBuilder()
    .configure("play.http.router" -> "webmodules.Routes")
    .overrides(bind[ModuleDAO] toInstance mockDAO)
    .build

  "Application" should {
    "return empty json array when no modules found" in new WithApplication(app) {
      val modules = route(FakeRequest(GET, "/modules")).get
      contentAsJson(modules) must equalTo(Json.parse("[]"))
    }
  }

}
