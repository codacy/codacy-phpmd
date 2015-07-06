
An eval-expression is untestable, a security risk and bad practice.
Therefore it should be avoided.
Consider to replace the eval-expression with regular code.

Example to avoid:

    class Foo {
        public function bar($param)  {
            if ($param === 42) {
                eval('$param = 23;');
            }
        }
    }

[Source](http://phpmd.org/rules/design.html#evalexpression)
      