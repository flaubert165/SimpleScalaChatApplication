class ConnectionFactory {

  import com.typesafe.config.ConfigFactory

  val RABBITMQ_HOST = ConfigFactory.load.getString("rabbitmq.host")
  val RABBITMQ_QUEUE = ConfigFactory.load.getString("rabbitmq.queue")
  val RABBITMQ_EXCHANGE = ConfigFactory.load.getString("rabbitmq.exchange")
}
