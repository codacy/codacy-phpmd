
Defining functions inside of loops can lead to bugs such as this one:

    var nums = [];
    for (var i = 0; i < 10; i++) {
      nums[i] = function (j) {
        return i + j;
      };
    }

    nums[0](2); // Prints 12 instead of 2

To fix the code above you need to copy the value of i:

    var nums = [];
    for (var i = 0; i < 10; i++) {
      (function (i) {
        nums[i] = function (j) {
            return i + j;
        };
      }(i));
    }

[Source](http://www.jshint.com/docs/options/#loopfunc)
      