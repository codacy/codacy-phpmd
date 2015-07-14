<?php
//#Patterns: PHPMD_Controversial_Superglobals, PHPMD_Controversial_CamelCaseClassName
//#Patterns: PHPMD_Controversial_CamelCaseMethodName, PHPMD_Controversial_CamelCaseParameterName


//#Info: PHPMD_Controversial_CamelCaseClassName
class foo_foo
{

    protected $my_property;

    //#Info: PHPMD_Controversial_CamelCaseMethodName
    //#Info: PHPMD_Controversial_CamelCaseParameterName
    //#Info: PHPMD_Controversial_Superglobals
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