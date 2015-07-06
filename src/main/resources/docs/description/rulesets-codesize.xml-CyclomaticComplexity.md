
Cyclomatic complexity is determined by the number of decision points in a method plus one for the method entry.
The following keywords create new decision points: 'if', 'while', 'for', and 'case labels'. For example, this
code has a cyclomatic complexity level of 2:

    function foo()
    {
        $t=date("H");
        if ($t<"20")
        {
            echo "Have a good day!";
        }
    }

1-4 indicates low complexity, 5-7 medium complexity, 8-10 high complexity and 11+ indicates very high complexity.

[Source](http://phpmd.org/rules/codesize.html#cyclomaticcomplexity)
      