package services

import com.newmotion.akka.rabbitmq
import com.rabbitmq.client
import com.rabbitmq.client.ConnectionFactory
import com.typesafe.config.ConfigFactory

import scala.concurrent.{Future, Promise}

class MqttService {

  private var connection: rabbitmq.Connection = null;

  def getMqttConnection(): rabbitmq.Connection = {
    connection match {
      case null => {
        val factory = new ConnectionFactory();
        factory.setHost(ConfigFactory.load().getString("rabbitmq.host"));
        factory.newConnection();
      }
      case _ => connection
    }
  }

  def send(): Future[String] ={
    val promise = Promise[String]

    try{
      val conn = this.getMqttConnection();

      val channel: client.Channel = conn.createChannel();
      channel.queueDeclare(ConfigFactory.load().getString("rabbitmq.queue"), false, false, false, null);
      channel.basicPublish("", ConfigFactory.load().getString("rabbitmq.queue"), null, "Hello World!".getBytes());

      promise.success("hello world, porra!!!");

    }catch {
      case e: Throwable => promise.failure(e)
    }

    promise.future
  }
}
