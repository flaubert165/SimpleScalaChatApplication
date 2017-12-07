package controllers

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc._
import services.ChatService

import scala.concurrent.ExecutionContext

@Singleton
class ChatController @Inject()(chatService: ChatService)(implicit exec: ExecutionContext) extends Controller {

  def send = Action.async {
    this.chatService.send().map { msg => Ok(Json.toJson(msg)) }
  }
}
