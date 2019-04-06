package demo_filesystem.commands

import demo_filesystem.filesystem.State

class UnknownCommand extends Command {
  override def apply(state: State): State =
    state.setMessage("Command not found!")
}
