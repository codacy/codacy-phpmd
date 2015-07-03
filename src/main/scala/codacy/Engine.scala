package codacy

import codacy.dockerApi.DockerEngine
import codacy.phpmd.PhpMd

object Engine extends DockerEngine(PhpMd)