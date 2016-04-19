package minesweeper

import scala.util.Random

/**
  * Created by khn3193 on 4/13/16.
  */
object Minesweeper {
  val emptySpace = ' '
  val mine = '*'
  val random = new Random

  def generate() : Array[String] = {
    val arr = new Array[String](81)
    val mineLocations = (0 to 9).map(a => random.nextInt(81)).toList
    mineLocations.foreach(l => arr(l) = "*")
    (0 to 81).foreach(a => {
      if(a % 8 == 0) arr(a) = "\n"
    })
    arr
  }

  def annotate(list: List[String]): List[String] = {
    if(!validate(list))
      throw new IllegalArgumentException("Invalid list")

    val array2D: Array[Array[Element]] = convertTo2DArray(list)

    array2D.map(row => {
      row.map({element =>
        element.value match {
          case `mine` => mine
          case _ =>
            val count = adjacentMines(array2D, element)
            count match {
              case 0 => emptySpace
              case _ => count.toString.charAt(0)
            }
        }
      })
    }.mkString).toList
  }

  def adjacentGrid(arr: Array[Array[Element]], currentRowIndex: Int, currentColumnIndex: Int, level: Int) : List[Char] = {
    val rows = arr.length
    val columns = arr(0).length

    val startRowIndex = currentRowIndex - level
    val endRowIndex = currentRowIndex + level
    val startColumnIndex = currentColumnIndex - level
    val endColumnIndex = currentColumnIndex + level

    (startRowIndex to endRowIndex).flatMap(row => (startColumnIndex to endColumnIndex).map(column => {
      val validElement = (row >= 0 && row < rows) && (column >= 0 && column < columns)
      if(validElement) arr(row)(column).value else emptySpace
    })).toList
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

  def adjacentMines(arr: Array[Array[Element]], element: Element): Int = {
    val l = adjacentGrid(arr, element.rowIndex, element.columnIndex, 1)
    l map isMine sum
  }

  def isMine(s: Char): Int = {
    if (s == mine) 1 else 0
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