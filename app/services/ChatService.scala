package services

import com.google.inject.Inject

import scala.concurrent.{Future, Promise}

class ChatService @Inject() (mqttService: MqttService){

  def send(): Future[Boolean] ={
    val promise = Promise[Boolean]

    try{


    }catch {
      case e: Throwable => promise.failure(e)
    }

    promise.future
  }

}
