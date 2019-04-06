package demo_filesystem.commands

import demo_filesystem.filesystem.State

trait Command {
  def apply(state: State): State
}

object Command {
  def from(input: String): Command =
    new UnknownCommand
}
