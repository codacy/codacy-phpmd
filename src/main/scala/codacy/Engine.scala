package codacy

import codacy.dockerApi.DockerEngine
import codacy.scalameta.ScalaMeta

object Engine extends DockerEngine("scalameta",ScalaMeta)