package planstack.anml.parser

import AnmlParser._
import java.io.FileReader
import planstack.anml.ANMLException
import scala.util.matching.Regex.Match
import planstack.anml.model.AnmlProblem

object ANMLFactory {

//  val MultiLineCommentRegExp = """/\*(?:.|[\n\r])*?\*/""".r
//  val SingleLineCommentRegExp = """(//[^\n\r]*)[\n\r]""".r
  val commentRegEx = """(/\*(?:.|[\n\r])*?\*/)|(//[^\n\r]*)[\n\r]""".r

  def withoutComments(anmlString :String) = {
    // given a match, this function produces is a string with the same layout (spaces for chars
    // and newlines for new lines)
    val replace = (m :Match) => {
      var i=m.start
      var replacement = ""
      while(i < m.end) {
        if(anmlString.charAt(i) == '\n')
          replacement += "\n"
        else
          replacement += " "
        i += 1
      }
      replacement
    }

    commentRegEx.replaceAllIn(anmlString, replace)
  }

  def lines(in:String) = in.replaceAll("""//[^\n]*""", "")

  def parseAnmlFromFile(file:String) : ParseResult = {
    val reader = scala.io.Source.fromFile(file)
    val anmlString = reader.mkString
    reader.close()

    parseAnmlString(anmlString)
  }

  def parseAnmlString(anmlString:String) : ParseResult = {
    val commentFree = withoutComments(anmlString)

    parseAll(anml, commentFree) match {
      case Success(res, _) => new ParseResult(res)
      case err => throw new ANMLException("Unable to parse ANML:\n" + err.toString)
    }
  }

  def main(args :Array[String]) {
    if(args.size < 1) 
      println("Please, provide at least one filename as argument");
    else {
      val res = parseAnmlFromFile(args(0))
      val pb = new AnmlProblem
      pb.addAnml(res)
    }
  }
}
