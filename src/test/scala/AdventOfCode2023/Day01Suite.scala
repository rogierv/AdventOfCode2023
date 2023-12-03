package AdventOfCode2023

import org.scalatest.funsuite.AnyFunSuite

class Day01Suite extends AnyFunSuite:
  val sample: String =
    """
      |1abc2
      |pqr3stu8vwx
      |a1b2c3d4e5f
      |treb7uchet
      |""".stripMargin.trim

  test("Part 1 should handle sample input correctly") {
    assert(Day01.part1(sample) == 142)
  }

  val sample2: String =
    """
      |two1nine
      |eightwothree
      |abcone2threexyz
      |xtwone3four
      |4nineeightseven2
      |zoneight234
      |7pqrstsixteen
      |""".stripMargin.trim

  test("Part 2 should handle sample input correctly") {
    assert(Day01.part2(sample2) == 281)
  }

  test("More") {
    assert(Day01.part2("234zoneight") == 28)
  }