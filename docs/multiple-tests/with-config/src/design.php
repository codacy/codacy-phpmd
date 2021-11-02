<?php
//#Patterns: rulesets-design.xml-ExitExpression, rulesets-design.xml-EvalExpression, rulesets-design.xml-GotoStatement
//#Patterns: rulesets-design.xml-NumberOfChildren, rulesets-design.xml-DepthOfInheritance, rulesets-design.xml-CouplingBetweenObjects


class Foo {
    public function bar($param)  {
        if ($param === 42) {
            //#Warn: rulesets-design.xml-EvalExpression
            eval('$param = 23;');
            //#Warn: rulesets-design.xml-ExitExpression
            exit(23);
        }
    }
}

class Bar extends Foo {
    public function bar($param)  {
        A:
        if ($param === 42) {
            //#Warn: rulesets-design.xml-GotoStatement
            goto X;
        }
        Y:
        if (time() % 42 === 23) {
            //#Warn: rulesets-design.xml-GotoStatement
            goto Z;
        }
        X:
        if (time() % 23 === 42) {
            //#Warn: rulesets-design.xml-GotoStatement
            goto Y;
        }
        Z:
        return 42;
    }
}

class Baz extends Bar {
}

//#Warn: rulesets-design.xml-NumberOfChildren
class Qux extends Baz {
}

class Quux extends Qux {
}

class Quuux extends Quux {
}

//#Warn: rulesets-design.xml-DepthOfInheritance
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
