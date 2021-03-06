package controllers

import javax.inject._

import akka.actor.ActorSystem
import play.api.mvc._
import services.MqttService

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future, Promise}


@Singleton
class AsyncController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem, mqttService: MqttService)(implicit exec: ExecutionContext) extends AbstractController(cc) {

  /**
   * Creates an Action that returns a plain text message after a delay
   * of 1 second.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/message`.
   */
  def message = Action.async {

    getFutureMessage(1.second).map { msg => Ok(msg) }
  }

  private def getFutureMessage(delayTime: FiniteDuration): Future[String] = {
    val promise: Promise[String] = Promise[String]()
    actorSystem.scheduler.scheduleOnce(delayTime) {
      promise.success("Hi!")
    }(actorSystem.dispatcher) // run scheduled tasks using the actor system's dispatcher
    promise.future
  }

}
