package services

object MqttConfig {

  import com.typesafe.config.ConfigFactory

  val RABBITMQ_HOST: String = ConfigFactory.load.getString("rabbitmq.host")
  val RABBITMQ_QUEUE: String = ConfigFactory.load.getString("rabbitmq.queue")
  val RABBITMQ_EXCHANGEE: String = ConfigFactory.load.getString("rabbitmq.exchange")
}
