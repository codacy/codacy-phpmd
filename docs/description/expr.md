
This pattern warns you about the use of expressions where normally you would expect to see assignments or
function calls. Most of the time, such code is a typo:

    var a = 2;
    a + 1; //unusually placed expression, you probably meant: a = a + 1
    if(a > 2) {
      ...
    }

[Source](http://www.jshint.com/docs/options/#expr)
      