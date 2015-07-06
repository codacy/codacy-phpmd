
Naming methods in camel case is considered a best practice. Instead of writing:

    class MyClass {
         public function getSomething($some_argument) { }
    }

You should write:

    class MyClass {
         public function getSomething($someArgument) { }
    }

[Source](http://phpmd.org/rules/controversial.html#camelcaseparametername)
      