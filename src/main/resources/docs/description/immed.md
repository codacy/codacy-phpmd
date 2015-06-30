
Wrapping parentheses assists readers of your code in understanding that the expression is the result of a function,
and not the function itself. If you want to execute the function immediately you should write:

    var example = (function() {
           /* your code here */
    })();

instead of:

    var example = function() {
           /* your code here */
    }();
      