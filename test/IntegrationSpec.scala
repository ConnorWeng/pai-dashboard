import java.util.concurrent.TimeUnit

import org.fluentlenium.core.filter.FilterConstructor._
import org.junit.runner._
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test._

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
trait WithChrome extends WithBrowser[ChromeDriver] {
  override val webDriver: WebDriver = WebDriverFactory(classOf[ChromeDriver])
}

@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {

  "Application" should {

    "work from within a browser" in new WithChrome {
      browser.goTo("http://localhost:" + port)
      browser.pageSource must contain("PAIDashboard")
    }

  }

  "Dashboard" should {

    "display web modules according to date range" in new WithChrome {
      browser.goTo("http://localhost:" + port + "/dashboard")
      browser
        .click("#main-date-range-picker")
        .fill(".input-mini", withName("daterangepicker_start")).`with`("12/01/2015")
        .fill(".input-mini", withName("daterangepicker_end")).`with`("12/02/2015")
        .click(".applyBtn")
      browser.$("#main-date-range-picker").getValue must contain("12/01/2015 - 12/02/2015")
      browser.await().atMost(5, TimeUnit.SECONDS).until(".web-modules-table > tbody").containsText("00:00:10")
      browser.$(".web-modules-table > tbody").getText must contain("00:00:10")
    }

  }
}
