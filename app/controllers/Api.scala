package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.ws._
import play.api.libs.json._
import java.net.URLDecoder
import play.api.libs.json.Json._
import play.api.libs.json.Writes._

import models.Task

object Api extends Controller {

	def people(userId: String, selector: String) = Action { implicit request =>
		//Redirect(routes.Application.tasks)
		val requesterId = request.headers.get("xoauth_requestor_id").getOrElse("")
		var id = userId;
		if(userId == "@me"){
			id = requesterId;
		}
		if(selector.matches("@(all|friends)")){
			
		}else{
			
		}
		
		Ok("OKwww");
	}
	
	def isFriend(userId: String, selector: String, targetId: String) = Action { implicit request =>
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

	def getTextGroup(appId:String, gid:String) = Action{ implicit request =>
		val mb = Map.newBuilder[String,JsValue]
		var total = 0

		mb += "startIndex" -> toJson(1)
		mb += "itemPerPage" -> toJson(20)
		mb += "totalResults" -> toJson(total)
		Ok(toJson(mb.result))
	}

	def deleteText(appId:String, gid:String, tid:String) = Action(parse.json){ implicit request =>
		val mb = Map.newBuilder[String,JsValue]
		mb += "startIndex" -> toJson(1)
		mb += "itemPerPage" -> toJson(20)
		mb += "totalResults" -> toJson(1)
		Ok(toJson(mb.result))
	}
	
	def getTextAll(appId:String, gid:String, selector:String) = Action{ implicit request =>
		val mb = Map.newBuilder[String,JsValue]
		Ok(toJson(mb.result))
	}

	def getText(appId:String, gid:String, selector:String, ids:String) = Action{ implicit request =>
		val mb = Map.newBuilder[String,JsValue]
		Ok(toJson(mb.result))
	}
	
	def createText(appId:String, gid:String) = Action(parse.json){ implicit request =>
		val mb = Map.newBuilder[String,JsValue]
		(request.body \ "data").asOpt[String].map{ data =>
			
			// FIXME!! ここでテキストを保存して、IDを生成する。
			val id = 1;
			mb += "textData" -> toJson(Map(
					"published" -> "",
					"writerId" -> "",
					"updated" -> "",
					"id" -> id.toString,
					"groupName" -> gid,
					"data" -> data
					))
		}
		mb += "startIndex" -> toJson(1)
		mb += "itemPerPage" -> toJson(1)
		mb += "totalResults" -> toJson(1)
		Ok(toJson(mb.result))
	}

	def createTextGroup(appId:String, gid:String) = Action{ implicit request =>
		val mb = Map.newBuilder[String,JsValue]
		Ok(toJson(mb.result))
	}

	def deleteTextGroup(appId:String, gid:String, selector:String) = Action{ implicit request =>
		val mb = Map.newBuilder[String,JsValue]
		Ok(toJson(mb.result))
	}
}