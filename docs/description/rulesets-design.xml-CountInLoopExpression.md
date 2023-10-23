Using count/sizeof in loops expressions is considered bad practice and is a potential source of
many bugs, especially when the loop manipulates an array, as count happens on each iteration.

Example to avoid:

    class Foo {
        public function bar()
            {
                $array = array();
                for ($i = 0; count($array); $i++) {
                    // ...
                }
            }
    }

[Source](http://phpmd.org/rules/design.html#countinloopexpression)