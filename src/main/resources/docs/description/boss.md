
This patterns warns you about the use of assignments in cases where comparisons are expected.
Usually code like this is a typo:

    if (a = 10) {
       ...
    }

You meant to write:

    if (a === 10) {
       ...
    }

[Source](http://www.jshint.com/docs/options/#boss)
      