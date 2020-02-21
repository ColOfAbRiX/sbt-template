package com.colofabrix.scala.sample

import cats.effect._
import cats.implicits._
import com.colofabrix.scala.sample.web.Server
import com.colofabrix.scala.sample.web.api.Routes
import com.colofabrix.scala.sample.web.config._
import org.http4s.HttpApp
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.syntax.kleisli._

object SampleWeb extends IOApp {

  private[this] def httpApp: HttpApp[IO] =
    Router(
      "/"     -> Routes.allRoutes,
      "/docs" -> Routes.redocDocsRoute,
    ).orNotFound

  override def run(args: List[String]): IO[ExitCode] = {
    BlazeServerBuilder[IO]
      .bindHttp(serviceConfig.server.port, serviceConfig.server.host)
      .withHttpApp(httpApp)
      .resource
      .use(Server.main)
      .as(ExitCode.Success)
  }

}
