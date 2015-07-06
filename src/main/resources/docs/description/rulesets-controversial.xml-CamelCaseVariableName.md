
Naming variables in camel case is considered a best practice. Instead of writing:

    class MyClass {
         public function getSomething() {
             $some_variable = new Something();
         }
    }

You should write:

    class MyClass {
         public function getSomething() {
             $someVariable = new Something();
         }
    }

[Source](http://phpmd.org/rules/controversial.html#camelcasevariablename)
      