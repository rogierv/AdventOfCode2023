package AdventOfCode2023

object Day02:
  enum Color { case Red, Green, Blue }
  case class Reveal(color: Color, count: Int)
  case class SubSet(reveals: Seq[Reveal])
  case class Game(number: Int, subSet: Seq[SubSet])

  private def parser(input: String): Seq[Game] =
    val lines = input.split("\n").toSeq
    lines.map(line =>
      val game = line.split(':')
      val gameNumber = game.head.split(' ').last.toInt
      val subsets = game.last.split(';').toSeq
      val subSet = subsets.map(subset =>
        val revealList = subset.split(',').toSeq
        val reveals = revealList.map(reveal =>
          val cube = reveal.trim().split(' ')
          Reveal(cube.last match
            case "red" => Color.Red
            case "blue" => Color.Blue
            case "green" => Color.Green, cube.head.toInt))
        SubSet(reveals))
      Game(gameNumber, subSet)
    )

  def part1(input: String): Int =
    val games = parser(input)
    games
      .filter(_.subSet.forall(_.reveals.forall(reveal => reveal.color match
        case Color.Red   if reveal.count > 12 => false
        case Color.Green if reveal.count > 13 => false
        case Color.Blue  if reveal.count > 14 => false
        case _                                => true)))
      .map(_.number)
      .sum

  def part2(input: String): Int =
    val games = parser(input)
    games
      .map(game => Color.values.map(color =>
        game.subSet
          .flatMap(_.reveals.filter(_.color == color))
          .maxBy(_.count)
          .count)
        .product)
      .sum

  def main(args: Array[String]): Unit =
    val data = io.Source.fromResource("AdventOfCode2023/Day02.txt").mkString
    val games = part1(data)
    println(part1(data))
    println(part2(data))
    