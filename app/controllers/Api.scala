package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.ws._
import java.net.URLDecoder;

import models.Task

object Api extends Controller {

  def people(userId: String, selector: String, targetId: String) = Action { implicit request =>
    //Redirect(routes.Application.tasks)
    val requesterId = request.headers.get("xoauth_requestor_id").getOrElse("")
    var id = userId;
    if(userId == "@me"){
      id = requesterId;
    }
    if(selector.matches("(selector|friends)")){
      
    }else{
      
    }
    Ok("OKwww");
  }
  

}