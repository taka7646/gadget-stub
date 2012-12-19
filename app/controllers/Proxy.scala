package controllers

import play.api._
import play.api.mvc._

object Proxy extends Controller {

  def index = Action{ implicit request =>
    Logger.info(request.path)
    Logger.info(request.queryString.toString())
    Ok("OK");
  }
}