
Methods named 'getX()' should not return boolean types. They should be renamed to 'isX()' or 'hasX()':

    class Foo {
        /** * @return boolean */
        public function getFoo() {} // bad
        /** * @return bool */
        public function isFoo(); // ok
    }

[Source](http://phpmd.org/rules/naming.html#booleangetmethodname)
      