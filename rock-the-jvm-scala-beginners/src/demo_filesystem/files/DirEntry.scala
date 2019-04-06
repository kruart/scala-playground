package demo_filesystem.files

import demo_filesystem.filesystem.FilesystemException

abstract class DirEntry(val parentPath: String, val name: String) {
  def path: String = parentPath + Directory.SEPARATOR + name

  def asDirectory: Directory
  def asFile: File = throw new FilesystemException("A directory cannot be converted to a file!")

  def getType: String
}
