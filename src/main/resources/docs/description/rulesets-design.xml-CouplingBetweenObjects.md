
A class with to many dependencies has negative impacts on several quality aspects of a class.
This includes quality criterias like stability, maintainability and understandability

Example:

    class Foo {
        /**
         * @var \foo\bar\X
         */
        private $x = null;

        /**
         * @var \foo\bar\Y
         */
        private $y = null;

        /**
         * @var \foo\bar\Z
         */
        private $z = null;

        public function setFoo(\Foo $foo) {}
        public function setBar(\Bar $bar) {}
        public function setBaz(\Baz $baz) {}

        /**
         * @return \SplObjectStorage
         * @throws \OutOfRangeException
         * @throws \InvalidArgumentException
         * @throws \ErrorException
         */
        public function process(Iterator $it) {}

        // ...
    }

[Source](http://phpmd.org/rules/design.html#couplingbetweenobjects)
      