Assignments in if clauses and the like are considered a code smell. Assignments in PHP return the right operand as their result. In many cases, this is an expected behavior, but can lead to many difficult to spot bugs, especially when the right operand could result in zero, null or an empty string and the like.

[Source](http://phpmd.org/rules/cleancode.html#ifstatementassignment)
