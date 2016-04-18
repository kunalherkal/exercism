package minesweeper

/**
  * Created by khn3193 on 4/13/16.
  */
object Minesweeper {
  val emptySpace = ' '
  val bomb = '*'


  def annotate(list: List[String]): List[String] = {
    if(!validate(list))
      return Nil

    val array2D: Array[Array[Element]] = convertTo2DArray(list)

    array2D.map(row => {
      row.map({element =>
        element.value match {
          case '*' => bomb
          case _ => {
            val l = adjacentElements(array2D, element.rowIndex, element.columnIndex)
            val count = matchCount(l)
            count match {
              case 0 => emptySpace
              case _ => count.toString.charAt(0)
            }
          }
        }
      })
    }.mkString).toList
  }

  def adjacentElements(arr: Array[Array[Element]], currentRowIndex: Int, currentColumnIndex: Int) : List[Char] = {

    val previousColumnIndex = currentColumnIndex - 1
    val nextColumnIndex = currentColumnIndex + 1

    val previousRowIndex = currentRowIndex - 1
    val nextRowIndex = currentRowIndex + 1

    val currentRowPreviousElement = element(arr, currentRowIndex, previousColumnIndex)
    val currentRowNextElement = element(arr, currentRowIndex, nextColumnIndex)

    val previousRowPreviousElement = element(arr, previousRowIndex, previousColumnIndex)
    val previousRowCurrentElement = element(arr, previousRowIndex, currentColumnIndex)
    val previousRowNextElement = element(arr, previousRowIndex, nextColumnIndex)

    val nextRowPreviousElement = element(arr, nextRowIndex, previousColumnIndex)
    val nextRowCurrentElement = element(arr, nextRowIndex, currentColumnIndex)
    val nextRowNextElement = element(arr, nextRowIndex, nextColumnIndex)

    List(currentRowNextElement, currentRowPreviousElement, previousRowCurrentElement, previousRowPreviousElement,
      previousRowNextElement, nextRowCurrentElement, nextRowNextElement, nextRowPreviousElement)
  }

  def element(arr: Array[Array[Element]], rowIndex: Int, columnIndex: Int): Char = {
    val rows = arr.length
    val columns = arr(0).length

    val validElement = (rowIndex >= 0 && rowIndex < rows) && (columnIndex >= 0 && columnIndex < columns)

    if(validElement) arr(rowIndex)(columnIndex).value else emptySpace
  }

  def validate(list: List[String]) = {
    list.forall(s => list.head.length == s.length)
  }

  def convertTo2DArray(list: List[String]): Array[Array[Element]] = {
    val a: Array[(Array[(Char, Int)], Int)] = list.map(s => s.toArray.zipWithIndex).zipWithIndex.toArray

    a.map(row => {
      row._1.map(element => {
        Element(element._1, row._2, element._2)
      })
    })
  }

  def matchCount(a: List[Char]): Int = {
    a.map(s => if (s == bomb) 1 else 0).sum
  }


}


object Demo extends App {
  Minesweeper.annotate(List(
    "  *  ",
    "  *  ",
    "*****",
    "  *  ",
    "  *  ")).foreach(println)

}