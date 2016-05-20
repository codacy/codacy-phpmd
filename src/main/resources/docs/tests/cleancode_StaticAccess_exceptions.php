<?php
//#Patterns: rulesets-cleancode.xml-StaticAccess: {"exceptions":"MyBar"}

class Foo
{
    public function bar($flag)
    {
        //#Info: rulesets-cleancode.xml-StaticAccess
        Bar::baz();

        //Ok: This one is part of the exceptions list
        MyBar::baz();

    }
}

?>
