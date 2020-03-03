package $package$.$ioBasicModuleName;format="lower,package"$

import cats.effect._
import cats.implicits._

object $name;format="Camel"$IoBasic extends IOApp {

  def run(args: List[String]): IO[ExitCode] = IO {
    println("Hello, World! I am $name$ project, $ioBasicModuleName$ module")
  }.as(ExitCode.Success)

}
