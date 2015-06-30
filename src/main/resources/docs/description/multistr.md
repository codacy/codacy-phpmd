
Multi-line strings can be dangerous in JavaScript because errors will occur if you accidentally put a whitespace in between the escape character (\) and a new line.

Note that even though this option allows correct multi-line strings, it still warns about multi-line strings without escape characters or with anything in between the escape character and a whitespace.

    var text = "Hello\
    World"; // All good.

    text = "Hello
    World"; // Warning, no escape character.

    text = "Hello\
    World"; // Warning, there is a space after \

[Source](http://www.jshint.com/docs/options/#multistr)
      