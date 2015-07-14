<?php
//#Patterns: PHPMD_DesignRules_ExitExpression, PHPMD_DesignRules_EvalExpression, PHPMD_DesignRules_GotoStatement
//#Patterns: PHPMD_DesignRules_NumberOfChildren, PHPMD_DesignRules_DepthOfInheritance, PHPMD_DesignRules_CouplingBetweenObjects


class Foo {
    public function bar($param)  {
        if ($param === 42) {
            //#Warn: PHPMD_DesignRules_EvalExpression
            eval('$param = 23;');
            //#Warn: PHPMD_DesignRules_ExitExpression
            exit(23);
        }
    }
}

class Bar extends Foo {
    public function bar($param)  {
        A:
        if ($param === 42) {
            //#Warn: PHPMD_DesignRules_GotoStatement
            goto X;
        }
        Y:
        if (time() % 42 === 23) {
            //#Warn: PHPMD_DesignRules_GotoStatement
            goto Z;
        }
        X:
        if (time() % 23 === 42) {
            //#Warn: PHPMD_DesignRules_GotoStatement
            goto Y;
        }
        Z:
        return 42;
    }
}

class Baz extends Bar {
}

//#Warn: PHPMD_DesignRules_NumberOfChildren
class Qux extends Baz {
}

class Quux extends Qux {
}

class Quuux extends Quux {
}

//#Warn: PHPMD_DesignRules_DepthOfInheritance
class Quuuux extends Quuux {
}

class FooBar1 extends Qux {
}

class FooBar2 extends Qux {
}

class FooBar3 extends Qux {
}

class FooBar4 extends Qux {
}

class FooBar5 extends Qux {
}

class FooBar6 extends Qux {
}

class FooBar7 extends Qux {
}

class FooBar8 extends Qux {
}

class FooBar9 extends Qux {
}

class FooBar10 extends Qux {
}

class FooBar11 extends Qux {
}

class FooBar12 extends Qux {
}

class FooBar12 extends Qux {
}

class FooBar14 extends Qux {
}

class FooBar15 extends Qux {
}

//--#Warn: PHPMD_DesignRules_CouplingBetweenObjects
class FooBar {
    private $x = new Foo();
    private $y = new Bar();
    private $z = new Baz();
    private $a = new Qux();
    private $b = new FooBar1();
    private $c = new FooBar2();
    private $d = new FooBar3();
    private $e = new FooBar4();
    private $f = new FooBar5();
    private $g = new FooBar6();
    private $h = new FooBar7();
    private $i = new FooBar8();
    private $j = new FooBar9();
    private $k = new FooBar10();
}

?>
