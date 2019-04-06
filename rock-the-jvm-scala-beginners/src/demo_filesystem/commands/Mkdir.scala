package demo_filesystem.commands

import demo_filesystem.files.{DirEntry, Directory}
import demo_filesystem.filesystem.State

class Mkdir(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry = {
    Directory.empty(state.wd.path, name)
  }
}