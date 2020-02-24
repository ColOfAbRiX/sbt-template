package $package$.$appProject;format="lower,package"$

import cats.effect._
import cats.implicits._

object $name;format="Camel"$App extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    IO {
      System.out.println("Hello, World!")
    }.as(ExitCode.Success)

}
