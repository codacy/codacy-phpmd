Defining another value for the same key in an array literal overrides the previous key/value, which makes it effectively an unused code. If it's known from the beginning that the key will have different value, there is usually no point in defining first one.

[Source](http://phpmd.org/rules/cleancode.html#duplicatedarraykey)