package $package$

import cats.effect._
import cats.implicits._

object $name;format="Camel"$ extends IOApp {

  def run(args: List[String]): IO[ExitCode] = IO {
    println("Hello, World! I am $name$ project, IOApp style")
  }.as(ExitCode.Success)

}
