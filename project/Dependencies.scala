import sbt._

object Dependencies {
  object Versions {
    val organizeImports = "0.6.0"
  }
  object Libraries {
    // scalafix rules
    val organizeImports = "com.github.liancheng" %% "organize-imports" % Versions.organizeImports
  }

}

