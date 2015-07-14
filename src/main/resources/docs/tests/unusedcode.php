<?php
//#Patterns: PHPMD_UnusedCodeRules_UnusedPrivateField, PHPMD_UnusedCodeRules_UnusedLocalVariable
//#Patterns: PHPMD_UnusedCodeRules_UnusedPrivateMethod, PHPMD_UnusedCodeRules_UnusedFormalParameter

class Foo {
    //#Warn: PHPMD_UnusedCodeRules_UnusedPrivateField
    private $unusedPvt = null;

    //#Warn: PHPMD_UnusedCodeRules_UnusedPrivateMethod
    private function unusedMethod()  {
        //#Warn: PHPMD_UnusedCodeRules_UnusedLocalVariable
        $unusedLocal = null;
    }

    //#Warn: PHPMD_UnusedCodeRules_UnusedFormalParameter
    public function baz($unusedArg)  {}
}

?>
