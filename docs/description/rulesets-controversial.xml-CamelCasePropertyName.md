
Naming class properties in camel case is considered a best practice. Instead of writing:

    class MyClass {
        protected $my_property;
    }

You should write:

    class MyClass {
        protected $MyProperty;
    }

[Source](http://phpmd.org/rules/controversial.html#camelcasepropertyname)
      