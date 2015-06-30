
JavaScript allows you to omit curly braces when the block consists of only one statement, for example:

    while(day)
        shuffle();


However, in some circumstances, it can lead to bugs (you'd think that sleep() is a part of the loop while in reality it is not):

    while(day)
        shuffle();
        sleep();

You should always add curly braces after loops and conditional declarations.

[Source](http://www.jshint.com/docs/options/#curly)
      