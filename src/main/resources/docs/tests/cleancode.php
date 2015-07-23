<?php
//#Patterns: rulesets-cleancode.xml-ElseExpression, rulesets-cleancode.xml-StaticAccess

class Foo
{
    public function bar($flag)
    {
        //#Info: rulesets-cleancode.xml-StaticAccess
        Bar::baz();

        if ($flag) {
            // one branch
        //#Info: rulesets-cleancode.xml-ElseExpression
        } else {
            // another branch
        }
    }
}

?>
