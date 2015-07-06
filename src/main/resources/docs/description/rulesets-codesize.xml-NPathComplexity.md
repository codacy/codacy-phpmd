
NPath complexity measures all the possible code paths in your methods. For example, the following method has NPath complexity of 8:


    function foo($value) {
        $result = '';

        if($value % 3 == 0) {
            $result .= 'divisible by 3';
        }

        if($value % 5 == 0) {
            $result .= 'divisible by 5';
        }

        if(!$result) {
            $result = $value;
        }

        return $result;
    }

As a rule of thumb, methods with NPath complexity higher than 200 should be refactored.

[Source](http://phpmd.org/rules/codesize.html#npathcomplexity)
      