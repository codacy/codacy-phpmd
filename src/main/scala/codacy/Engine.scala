package codacy

import codacy.dockerApi.DockerEngine
import codacy.jshint.Jshint
import codacy.scalameta.ScalaMeta

//object Engine extends DockerEngine("scalameta",ScalaMeta)
object Engine extends DockerEngine("jshint",Jshint)