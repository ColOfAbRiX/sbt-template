# SBT Templates

A series of [Giter8][g8] templates for structured projects!

It includes:

* Best practice SBT plugins
* Most commonly used libraries
* Standard SBT project
* Module with standard Main method
* Module with Cat's IO
* Module for complex application
* Module for HTTP service

## Variables

### General

* `name`: Name of the repository. Defaults to: `Sample`
* `project`: Name of the project. Defaults to: `Basic`
* `scalaVersion`: Version of Scala. Defaults to: `2.13.0`
* `description`: Description of the project. Defaults to: `Sample Scala SBT project`
* `organization`: Root package. Defaults to: `com.colofabrix.scala`

### Enabled modules

* `useRoot`: Create the standard SBT module. Defaults to `yes`
* `useBasic`: Create the standard main application module. Defaults to `no`
* `useIoBasic`: Create the Cat's IO application module. Defaults to `no`
* `useApp`: Create the complex application module. Defaults to `no`
* `useWeb`: Create the http service module. Defaults to `no`
* `useUtils`: Must be set to `yes` when useApp=yes or useWeb=yes Defaults to `no`

### Modules configuration

* `basicProject`: Name of the standard main application module. Defaults to `basic`
* `ioBasicProject`: Name of the Cat's IO application module. Defaults to `ioBasic`
* `appProject`: Name of the complex application module. Defaults to `app`
* `webProject`: Name of the http service module. Defaults to `web`

## License

CC0 1.0

## Author Information

Fabrizio Colonna (@ColOfAbRiX)
