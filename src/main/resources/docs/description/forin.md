
When you use a `for in` to loop to iterate over property names in an object you might get unexpected results because
`for in` loops include members inherited from the prototype chain and the name of methods. Consider the following example:

    var team = ["Mr. Pink", "Mr. White", "Nice Guy Eddie"];
    Array.prototype.each = function () { /* something */ }

    for (var person in team) {
        window.alert(team[person] + ": I don't tip.");
    }

Since we added a new method to the Array prototype, the loop will pop up 4 alerts: one for each person and one for the `each` method.
In order to fix this, we need to ensure that we are only iterating through the object's own properties using, for example, the hasOwnProperty filter:

    for (var person in team) {
        if (team.hasOwnProperty(person)) {
            window.alert(team[person] + ": I don't tip.");
        }
    }

For a more detailed explanation of for in loops in Javascript read
[Exploring JavaScript for-in loops](http://javascriptweblog.wordpress.com/2011/01/04/exploring-javascript-for-in-loops/) by Angus Croll
      