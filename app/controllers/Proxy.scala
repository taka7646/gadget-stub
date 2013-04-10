package controllers

import play.api._
import play.api.mvc._
import play.api.libs.ws._
import java.net.URLDecoder;

object Proxy extends Controller {

	def index = Action{ implicit request =>
		val escapedUrl = request.queryString.getOrElse("url", List("")).head;
		val url = URLDecoder.decode(escapedUrl, "utf-8");
		val ua = request.headers.get(USER_AGENT).getOrElse("");
		Logger.info(ua);
		Logger.info(url);
		Async{
			WS.url(url).withHeaders((USER_AGENT,ua)).get().map{ response =>
				var body = response.body
				val contentType = response.header("Content-type").head
		Logger.info(contentType);
				if(contentType.matches("html")){
					body = body.replace("<body>","<body><div class='local-header'>local header</div>")
					body = body.replace("</body>","<div class='local-footer'>local footer</div></body>")
				}
				Ok(body).withHeaders(
						CONTENT_TYPE -> contentType
						);
			}
		}
	}
}