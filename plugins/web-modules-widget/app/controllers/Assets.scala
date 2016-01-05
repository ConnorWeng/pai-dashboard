package controllers.webmodules

import play.api.http.LazyHttpErrorHandler

/**
  * Created by ConnorWeng on 2015/12/24.
  */

class Assets extends controllers.AssetsBuilder(LazyHttpErrorHandler)