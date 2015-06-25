
This pattern warns when you compare the typeof result with an invalid value which often can be a typo.

    // 'fuction' instead of 'function'
    if (typeof a == "fuction") { // Invalid typeof value 'fuction'
      /* ... */
    }

[Source](http://www.jshint.com/docs/options/#notypeof)
      