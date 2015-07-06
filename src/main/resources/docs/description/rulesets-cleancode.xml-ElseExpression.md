
Instead of using an else block, you can use an early return statement:

    class Foo
    {
        public function bar($flag)
        {
            if ($flag) {
                // one branch
            } else {
                // another branch
            }
        }
    }

Rewrite without the else:

    class Foo
    {
        public function bar($flag)
        {
            if ($flag) {
                // one branch
                return;
            }

            // another branch
        }
    }
      