package com.colofabrix.scala.sample

import cats.effect._
import cats.implicits._

object SampleIoBasic extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    IO {
      System.out.println("Hello, World!")
    }.as(ExitCode.Success)

}
