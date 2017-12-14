name := "SimpleScalaChatApplication"
 
version := "1.0" 
      
lazy val `simplescalachatapplication` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  jdbc,
  ehcache,
  ws,
  specs2 % Test,
  guice,
  "com.rabbitmq" % "amqp-client" % "4.0.0",
  "com.newmotion" %% "akka-rabbitmq" % "4.0.0",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      