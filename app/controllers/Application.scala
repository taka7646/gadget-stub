package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.ws._
import java.net.URLDecoder;

import models.Task

object Application extends Controller {
  val taskForm = Form{
    "label" -> nonEmptyText
  }
  def index = Action { implicit request =>
    //Redirect(routes.Application.tasks)
    request.headers;
    val escapedUrl = request.queryString.getOrElse("url", List("")).head;
    val url = URLDecoder.decode(escapedUrl, "utf-8");
    Logger.info(url);
    Async{
      WS.url(url).get().map{ response =>
        var body = response.body
        body = body.replace("<body>","<body><div class='local-header'>local header</div>")
        body = body.replace("</body>","<div class='local-footer'>local footer</div></body>")
        Ok(body).withHeaders(
            CONTENT_TYPE -> response.header("Content-type").head
            );
      }
    }
  }
  
  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }
  
  def newTask = Action{ implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all, errors)),
      label => {
        Task.create(label)
        Redirect(routes.Application.tasks)
      }
    )
  }
  
  def deleteTask(id: Long) = Action { 
    Task.delete(id);
    Redirect(routes.Application.tasks)
  }
}