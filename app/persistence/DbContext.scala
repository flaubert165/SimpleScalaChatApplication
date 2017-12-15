package persistence

import com.typesafe.config.ConfigFactory
import org.mongodb.scala.MongoClient

private[persistence] object DbContext {
  private val mongoClient = MongoClient(ConfigFactory.load().getString("db"))
  val context = mongoClient.getDatabase("ScalaChatApp")
}
