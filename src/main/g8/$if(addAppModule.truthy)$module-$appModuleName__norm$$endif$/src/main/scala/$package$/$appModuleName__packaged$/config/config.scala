package $package$.$appModuleName;format="lower,package"$

import cats.Show
import org.log4s._
import pureconfig._
import pureconfig.generic.auto._
import $package$.utils.ADT

object config {

  private[this] val logger = getLogger

  //  CONFIG  //

  final case class AppConfig(
      sampleValue: String,
  ) extends ADT

  /**
   * Main application configuration
   */
  val serviceConfig: AppConfig =
    ConfigSource
      .default
      .at(BuildInfo.projectPackage)
      .loadOrThrow[AppConfig]

  logger.info(s"Loaded configuration: \${Show[AppConfig].show(serviceConfig)}")

}
