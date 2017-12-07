package services

import com.newmotion.akka.rabbitmq
import com.rabbitmq.client._

class MqttService() {

  private var connection: rabbitmq.Connection = null;

  def getMqttConnection(): rabbitmq.Connection = {
    connection match {
      case null => {
        val factory = new ConnectionFactory();
        factory.setHost(MqttConfig.RABBITMQ_HOST);
        factory.newConnection();
      }
      case _ => connection
    }
  }


}
