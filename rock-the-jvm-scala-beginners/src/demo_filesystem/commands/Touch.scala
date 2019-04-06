package demo_filesystem.commands

import demo_filesystem.files.{DirEntry, File}
import demo_filesystem.filesystem.State

class Touch(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)
}
