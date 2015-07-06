
 You should use PHP 5 notation for constructors:

    class MyClass {
        // this is bad because it is PHP 4 style
        public function MyClass() {}
        // this is good because it is a PHP 5
        constructor public function __construct() {}
    }
[Source](http://phpmd.org/rules/naming.html#constructorwithnameasenclosingclass)
      