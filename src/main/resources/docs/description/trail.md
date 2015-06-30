
Trailing whitespaces can be source of nasty bugs with multi-line strings in JavaScript:

    // This otherwise perfectly valid string will error if
    // there is a whitespace after \
    var str = "Hello \
    World";

[Source](http://www.jshint.com/docs/options/#trailing)
      