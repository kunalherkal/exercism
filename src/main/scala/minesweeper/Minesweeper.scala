package minesweeper

/**
  * Created by khn3193 on 4/13/16.
  */
object Minesweeper {
  val emptySpace = ' '

  def annotate(list: List[String]) = {
    if(!validate(list))
      throw new IllegalArgumentException

    val arrFormatWithIndices = convert(list)

    arrFormatWithIndices.map(row => {
      row._1.map(element => {
        element._1 match {
          case '*' => '*'
          case _ => {
            val l = adjacentElements(arrFormatWithIndices, row._2, element._2)
            val count = matchCount(l)
            count match {
              case 0 => emptySpace
              case _ => count.toString.charAt(0)
            }
          }
        }
      })
    }.mkString)
  }

  def adjacentElements(arr: Array[(Array[(Char, Int)], Int)], currentRowIndex: Int, currentElementIndex: Int) : List[Char] = {

    val rows = arr.length
    val columns = arr(0)._1.length

    val previousElementIndex = currentElementIndex - 1
    val nextElementIndex = currentElementIndex + 1

    val previousRowIndex = currentRowIndex - 1
    val nextRowIndex = currentRowIndex + 1

    val currentRowPreviousElement = if(previousElementIndex >= 0) arr(currentRowIndex)._1(previousElementIndex)._1 else emptySpace
    val currentRowNextElement = if(nextElementIndex < columns) arr(currentRowIndex)._1(nextElementIndex)._1 else emptySpace

    val previousRowPreviousElement = if(previousRowIndex >= 0 && previousElementIndex >= 0 ) arr(previousRowIndex)._1(previousElementIndex)._1 else emptySpace
    val previousRowCurrentElement = if(previousRowIndex >= 0) arr(previousRowIndex)._1(currentElementIndex)._1 else emptySpace
    val previousRowNextElement = if(previousRowIndex >= 0 && nextElementIndex < columns ) arr(previousRowIndex)._1(nextElementIndex)._1 else emptySpace

    val nextRowPreviousElement = if(nextRowIndex < rows && previousElementIndex >= 0 ) arr(nextRowIndex)._1(previousElementIndex)._1 else emptySpace
    val nextRowCurrentElement = if(nextRowIndex < rows) arr(nextRowIndex)._1(currentElementIndex)._1 else emptySpace
    val nextRowNextElement = if(nextRowIndex < rows && nextElementIndex < columns ) arr(nextRowIndex)._1(nextElementIndex)._1 else emptySpace

    List(currentRowNextElement, currentRowPreviousElement, previousRowCurrentElement, previousRowPreviousElement,
      previousRowNextElement, nextRowCurrentElement, nextRowNextElement, nextRowPreviousElement)
  }

  def element(arr: Array[(Array[(Char, Int)], Int)], rowIndex: Int, elementIndex: Int, f: => Boolean): Char = {
    if(f) arr(rowIndex)._1(elementIndex)._1 else emptySpace
  }

  def validate(list: List[String]) = {
    list.forall(s => list.head.length == s.length)
  }

  def convert(list: List[String]) = {
    list.map(s => s.toArray.zipWithIndex).zipWithIndex.toArray
  }

  def matchCount(a: List[Char]): Int = {
    a.map(s => if (s == '*') 1 else 0).sum
  }


}


object Demo extends App {
  Minesweeper.annotate(List("  *  ", "* * *", "     "))

}