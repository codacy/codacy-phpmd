
Deeply nested blocks indicate excessive complexity. You should probably refactor the code to ensure maintainability.
Example of deeply nested code:

    function main(meaning) {
      var day = true;
      if (meaning === 42) {
        while (day) {
          shuffle();
          if (tired) { // Blocks are nested too deeply (3).
              sleep();
          }
        }
      }
    }

[Source](http://www.jshint.com/docs/options/#maxdepth)
      