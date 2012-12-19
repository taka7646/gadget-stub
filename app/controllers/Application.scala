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
    Ok("OKwww");
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