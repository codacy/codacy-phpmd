package codacy.phpmd

import org.scalatest.wordspec._

class ConfigFileSanitizerTest extends AnyWordSpec {
  "ConfigFileValidator" should {
    "Not allow custom rules to be run in the file" in {
      val input = """<?xml version="1.0" encoding="UTF-8"?>
      |<ruleset>
      |    <rule name="Foo" message="message" class="Foo" file="Foo.php" />
      |    <rule ref="rulesets/design.xml/GotoStatement" />
      |</ruleset>
      |""".stripMargin

      val result = ConfigFileSanitizer.sanitize(input)

      val expected = """<?xml version='1.0' encoding='UTF-8'?>
        |<ruleset>
        |    
        |    <rule ref="rulesets/design.xml/GotoStatement"/>
        |</ruleset>""".stripMargin

      assert(result == expected)
    }
  }
}
