package part2actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorCapabilities extends App {
  class SimpleActor extends Actor {
    override def receive: Receive = {
      case "Hi!" => context.sender() ! "Hello, there!"  // replying to a message
      case message: String => println(s"[${context.self.path}] I have received $message")
      case number: Int => println(s"$self] I have received a Number: $number")
      case SpecialMessage(contents) => println(s"[simple actor] I have received something SPECIAL: $contents")
      case SendMessageToYourself(content) => self ! content
      case SayHiTo(ref) => ref ! "Hi!" // alice is being passed as the sender
      case WirelessPhoneMessage(content, ref) => ref forward (content  + "s")// I keep the original sender of the WPM
    }
  }

  val system = ActorSystem("actorCapabilitiesDemo")
  val simpleActor = system.actorOf(Props[SimpleActor], "simpleActor")

  simpleActor ! "hello, actor"

  // 1 - messages can be of may type
  // a) messages must be IMMUTABLE
  // b) messages must be SERIALIZABLE

  // in practice use case classes and case objects
  simpleActor ! 42  // who is the sender?!

  case class SpecialMessage(contents: String)
  simpleActor ! SpecialMessage("some special content")

  // 2 - actors have information about their context and about themselves
  // context.self === `this` in OOP

  case class SendMessageToYourself(content: String)
  simpleActor ! SendMessageToYourself("I am an actor and I am proud of it")

  // 3 - actors can REPLY to messages
  val alice = system.actorOf(Props[SimpleActor], "alice")
  val bob = system.actorOf(Props[SimpleActor], "bob")

  case class SayHiTo(ref: ActorRef)
  alice ! SayHiTo(bob)

  // 4 - dead letters
  alice ! "Hi!"

  // 5 - forwarding messages
  // D -> A -> B
  // forwarding = sending a message with the ORIGINAL sender
  case class WirelessPhoneMessage(content: String, ref: ActorRef)
  alice ! WirelessPhoneMessage("Hi", bob)
}
