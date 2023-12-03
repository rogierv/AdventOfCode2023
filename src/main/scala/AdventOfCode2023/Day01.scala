package AdventOfCode2023

import scala.language.postfixOps
import scala.util.matching.Regex

object Day01:
  private def parser(input: String): Seq[String] = input.split("\n").toSeq

  def part1(input: String): Int =
    val lines = parser(input)
    val pattern: Regex = "[0-9]"r
    def calibrate(value: String) =
      val values = pattern.findAllIn(value).toSeq
      (values.head + values.last).toInt
    lines.map(calibrate).sum

  def part2(input: String): Int =
    val lines = parser(input)
    val pattern: Regex = "(?=([0-9])|(one)|(two)|(three|(four)|(five)|(six)|(seven)|(eight)|(nine)))"r

    def calibrate(value: String) =
      val v = pattern.findAllMatchIn(value).toSeq
      val matches = v.map(_.subgroups.filterNot(_ == null).head)
      val newValues = matches.map {
        case "one" => "1"
        case "two" => "2"
        case "three" => "3"
        case "four" => "4"
        case "five" => "5"
        case "six" => "6"
        case "seven" => "7"
        case "eight" => "8"
        case "nine" => "9"
        case number => number
      }
      (newValues.head + newValues.last).toInt

    lines.map(calibrate).sum

  def main(args: Array[String]): Unit =
    val data = io.Source.fromResource("AdventOfCode2023/Day01.txt").mkString
    println(part1(data))
    println(part2(data))
    