package controllers

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc._
import services.ChatService

@Singleton
class ChatController @Inject()(chatService: ChatService) extends Controller {

  def send = Action.async {
    this.chatService.send().map { msg => Ok(Json.toJson(msg)) }
  }
}
