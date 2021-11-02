<?php
//#Patterns: rulesets-controversial.xml-Superglobals, rulesets-controversial.xml-CamelCaseClassName
//#Patterns: rulesets-controversial.xml-CamelCaseMethodName, rulesets-controversial.xml-CamelCaseParameterName


//#Info: rulesets-controversial.xml-CamelCaseClassName
class foo_foo
{

    protected $my_property;

    //#Info: rulesets-controversial.xml-CamelCaseMethodName
    //#Info: rulesets-controversial.xml-CamelCaseParameterName
    //#Info: rulesets-controversial.xml-Superglobals
    public function bar_bar($arg1, $arg2, $arg3, $arg4, $arg_things, $arg6, $arg7, $arg8, $arg12, $arg10, $arg11)
    {
        $some_name = $_POST['foo'];
        $some_name2 = $arg1;
    }
}

class Foo
{
    protected $MyProperty;

    public function barBar($arg1, $arg2, $arg3, $arg4, $argThings, $arg6, $arg7, $arg8, $arg12, $arg10, $arg11)
    {
        $someName = $argThings;
    }
}

?>