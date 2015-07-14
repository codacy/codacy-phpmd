<?php
//#Patterns: PHPMD_CleanCode_ElseExpression, PHPMD_CleanCode_StaticAccess

class Foo
{
    public function bar($flag)
    {
        //#Info: PHPMD_CleanCode_StaticAccess
        Bar::baz();

        if ($flag) {
            // one branch
        //#Info: PHPMD_CleanCode_ElseExpression
        } else {
            // another branch
        }
    }
}

?>
