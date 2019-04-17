package part2actors

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.event.Logging

object ActorLoggingDemo extends App {

  class SimpleActorWithExplicitLogger extends Actor {
    // #1 - explicit logging
    val logger = Logging(context.system, this)

    override def receive: Receive = {
      case msg => logger.info(msg.toString)   // LOG it
    }
  }

  val system = ActorSystem("LoggingDemo")
  val actor: ActorRef = system.actorOf(Props[SimpleActorWithExplicitLogger])

  actor ! "Logging a simple message"

  // #2 ActorLogging
  class ActorWithLogging extends Actor with ActorLogging {
    override def receive: Receive = {
      case (a, b) => log.info("Two things: {} and {}", a, b)
      case msg => log.info(msg.toString)
    }
  }

  val simpleActor: ActorRef = system.actorOf(Props[ActorWithLogging])
  simpleActor ! "Logging a simple message by extending a trait"

  simpleActor ! (42, 65)
}
