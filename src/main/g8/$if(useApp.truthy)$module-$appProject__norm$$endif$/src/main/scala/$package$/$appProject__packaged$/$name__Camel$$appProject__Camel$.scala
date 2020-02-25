package $package$.$appProject;format="lower,package"$

import cats.effect._
import cats.implicits._

object $name;format="Camel"$App extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    IO {
      println("Hello, World! I am $name$ project, $appProject$ module")
    }.as(ExitCode.Success)

}
