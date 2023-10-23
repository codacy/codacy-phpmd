Usually empty try-catch is a bad idea because you are silently swallowing an error condition
and then continuing execution. Occasionally this may be the right thing to do, but often
it's a sign that a developer saw an exception, didn't know what to do about it,
and so used an empty catch to silence the problem.

Example to avoid:

    class Foo {
        public function bar()
            {
                try {
                    // ...
                } catch (Exception $e) {} // empty catch block
            }
    }

[Source](http://phpmd.org/rules/design.html#emptycatchblock)
      