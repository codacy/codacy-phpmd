package codacy

import codacy.phpmd.PhpMd
import com.codacy.tools.scala.seed.DockerEngine

object Engine extends DockerEngine(PhpMd)()
