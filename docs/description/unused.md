
Detects when variables are declared and never used. For example, variables `c` and `d` can be removed in this example:

    function test(a, b) {
      var c, d = 2;

      return a + d;
    }

    test(1, 2);

In addition to that, this pattern will also warn you about unused global variables declared via /*global ... */ directive.

[Source](http://www.jshint.com/docs/options/#unused)
      