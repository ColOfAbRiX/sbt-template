package $package$.$webProject;format="lower,package"$

import cats.effect.IO
import org.http4s.server.{ Server => Http4sServer }
import org.log4s.getLogger
import scala.io.StdIn
import $package$.$webProject;format="lower,package"$.config._

object Server {

  private[this] val logger = getLogger

  def main(server: Http4sServer[IO]): IO[_] = IO {
    logger.info(s"Started \${BuildInfo.description} version \${BuildInfo.version}")
    logger.trace(server.toString)

    if (serviceConfig.server.debugMode) {
      StdIn.readLine()
    } else {
      ()
    }
  }

}
