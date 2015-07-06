
An exit-expression within regular code is untestable and therefore it should be avoided.
Consider to move the exit-expression into some kind of startup script where an
error/exception code is returned to the calling environment.

Example to avoid:

    class Foo {
        public function bar($param)  {
            if ($param === 42) {
                exit(23);
            }
        }
    }

[Source](http://phpmd.org/rules/design.html#exitexpression)
      