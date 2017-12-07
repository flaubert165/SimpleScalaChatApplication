package services

import java.nio.charset.StandardCharsets

import akka.actor.{Actor, Props}
import com.newmotion.akka.rabbitmq
import com.rabbitmq.client
import com.rabbitmq.client.AMQP.Basic.Consume
import com.rabbitmq.client._
import com.typesafe.config.ConfigFactory
import java.io.IOException
import scala.concurrent.{Future, Promise}

class MqttService {
  private var channell: Channel = null;
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
      channel.basicPublish("", ConfigFactory.load().getString("rabbitmq.queue"), null, "Producer: Hello World! CARALEO".getBytes());
      receive(channel)
      promise.success("hello world, porra!!!");

    }catch {
      case e: Throwable => { promise.failure(e)}
    }

    promise.future
  }

  def fromBytes(x: Array[Byte]) = new String(x, StandardCharsets.UTF_8)
  def toBytes(x: Long) =  x.toString.getBytes(StandardCharsets.UTF_8)

  @throws(classOf[IOException])
  def receive(channel: Channel) ={

    val consumer = new DefaultConsumer(channel) {

      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit ={
        println("Consumer: " + fromBytes(body))
      }
    }
    channel.basicConsume("porra/test", true, consumer)
  }
}
