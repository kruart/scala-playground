package demo_filesystem.filesystem

import java.util.Scanner

import demo_filesystem.commands.Command
import demo_filesystem.files.Directory

object FileSystem extends App {
  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (true) {
    state.show
    state = Command.from(scanner.nextLine()).apply(state)
  }
}
