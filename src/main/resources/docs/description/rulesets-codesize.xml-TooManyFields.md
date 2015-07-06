
Classes with lots of fields are difficult to maintain.
You should redesign them and group the information in other classes - ex: instead of `zip`, `city`
and `country` fields you could have an `Address` class

[Source](http://phpmd.org/rules/codesize.html#toomanyfields)
      