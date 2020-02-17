package com.colofabrix.scala.sample

import cats.effect._
import cats.implicits._

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    IO {

      System.out.println("Hello, World!")

    }.as(ExitCode.Success)

}
