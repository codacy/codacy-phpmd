
This pattern prohibits the use of a variable before it was defined.

JavaScript has function scope only and, in addition to that, all variables are always moved or hoisted to the top of the function.
This behavior can lead to some very nasty bugs and that's why it is safer to always use variable only after they have been explicitly defined.

[Source](http://www.jshint.com/docs/options/#latedef)
      