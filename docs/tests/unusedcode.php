<?php
//#Patterns: rulesets-unusedcode.xml-UnusedPrivateField, rulesets-unusedcode.xml-UnusedLocalVariable
//#Patterns: rulesets-unusedcode.xml-UnusedPrivateMethod, rulesets-unusedcode.xml-UnusedFormalParameter

class Foo {
    //#Warn: rulesets-unusedcode.xml-UnusedPrivateField
    private $unusedPvt = null;

    //#Warn: rulesets-unusedcode.xml-UnusedPrivateMethod
    private function unusedMethod()  {
        //#Warn: rulesets-unusedcode.xml-UnusedLocalVariable
        $unusedLocal = null;
    }

    //#Warn: rulesets-unusedcode.xml-UnusedFormalParameter
    public function baz($unusedArg)  {}
}

?>
