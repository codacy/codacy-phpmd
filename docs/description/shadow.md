
This option warns when it finds variable shadowing i.e. declaring a variable that had been already declared somewhere in the outer scope.

    var a = 1;
    function b() {
     var a = 2; //Shadow of variable a
    }

[Source](http://www.jshint.com/docs/options/#shadow)
      