
Even though JavaScript has only two real scopes, declaring variables inside control structures and using them outside of those structures leads to confusion among people new to the language and causes hard-to-debug bugs.

    function test() {
       if (true) {
        var x = 0;
      }

      x += 1; // 'x' used out of scope.
    }

You should declare your variables at the function scope.

[Source](http://www.jshint.com/docs/options/#funcscope)
      