package services

import com.google.inject.Inject
import com.rabbitmq.client
import com.rabbitmq.client.{AMQP, Channel, DefaultConsumer, Envelope}

import scala.concurrent.{Future, Promise}

class ChatService @Inject() (mqttService: MqttService){

  def send(): Future[String] ={

    val promise = Promise[String]

    try{

      val conn = (this.mqttService.getMqttConnection());
      val channel: client.Channel = conn.createChannel();
      channel.queueDeclare(MqttConfig.RABBITMQ_QUEUE, false, false, false, null);
      channel.basicPublish(null, MqttConfig.RABBITMQ_QUEUE, null, "Producer: Hello World! CARALEO".getBytes());

      promise.success("hello world, veio!!!");

    }catch {
      case e: Throwable => promise.failure(e)
    }

    promise.future
  }

  def receive(channel: Channel) ={

    val consumer = new DefaultConsumer(channel) {

      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit ={
        println("Consumer: " + helpers.Utils.fromBytes(body))
      }
    }
    channel.basicConsume(MqttConfig.RABBITMQ_QUEUE, true, consumer)
  }

}
