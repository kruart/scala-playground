package demo_filesystem.files

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def hasEntry(name: String): Boolean = ???

  def getAllFoldersInPath: List[String] = {
    path.substring(1).split(Directory.SEPARATOR).toList
    // /a/b/c/d -> List["a", "b", "c", "d"}
  }

  def findDescendant(allDirsInPath: List[String]): Directory = {
    ???
  }

  def addEntry(newEntry: DirEntry): Directory = ???

  def findEntry(head: String): DirEntry = ???

  def replaceEntry(name: String, newEntry: DirEntry): Directory = ???

  override def asDirectory: Directory = this
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory =
    new Directory(parentPath, name, List())
}

