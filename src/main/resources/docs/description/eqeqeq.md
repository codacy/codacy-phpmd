
The == comparison in Javascript is dangerous because its behaviour depends on the type of data being compared. For example:

    1 == "1"  //true because "1" is converted to 1
    1 === "1" //false

    1 == true  //true because true is converted to 1
    1 === true //false

Most of the time, you want to make an explicit comparison without any surprises so you should use === and !===.
For a detailed description of the type conversion rules you can check [the documentation](http://es5.github.io/#x11.9.3).
      