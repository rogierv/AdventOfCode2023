package AdventOfCode2023

object Day02:
  enum Color {
    case Red, Green, Blue
  }
  
  case class Reveal(color: Color, count: Int)
  case class SubSet(reveals: Seq[Reveal])
  case class Game(number: Int, subSet: Seq[SubSet])

  private def matchColor(color: String) : Color =
    color match
      case "red" => Color.Red
      case "blue" => Color.Blue
      case "green" => Color.Green

  private def parser(input: String): Seq[Game] =
    val lines = input.split("\n").toSeq
    lines.map(line =>
      val game = line.split(':')
      val gameNumber = game.head.split(' ').last.toInt
      val subsets = game.last.split(';')
      val s: Seq[SubSet] = subsets.map(subset => {
        val reveals = subset.split(',')
        val r: Seq[Reveal] = reveals.map(reveal => {
          val cube = reveal.trim().split(' ')
          Reveal(color = matchColor(cube.last), count = cube.head.toInt)
        })
        SubSet(r)
      })
      Game(gameNumber, s)
    )



  def part1(input: String): Int =
    val games = parser(input)
    games.filter(IsValid).map(_.number).sum

  private def IsValid(game: Game): Boolean = game.subSet.forall(IsValid)

  private def IsValid(subSet: SubSet): Boolean = subSet.reveals.forall(IsValid)

  private def IsValid(reveal: Reveal): Boolean =
    reveal.color match
      case Color.Red if reveal.count > 12 => false
      case Color.Green if reveal.count > 13 => false
      case Color.Blue if reveal.count > 14 => false
      case _ => true

  def part2(input: String): Int =
    val games = parser(input)
    games.map(powerOfCubes).sum

  private def powerOfCubes(game: Game): Int = findMinimumSet(game).product

  private def findMinimumSet(game: Game): Seq[Int] =
    Color.values.map(color => {
      game.subSet
        .flatMap(_.reveals.filter(_.color == color))
        .maxBy(_.count)
        .count
    })

  def main(args: Array[String]): Unit =
    val data = io.Source.fromResource("AdventOfCode2023/Day02.txt").mkString
    val games = part1(data)
    println(part1(data))
    println(part2(data))
    