<?php
//#Patterns: rulesets-naming.xml-ShortVariable, rulesets-naming.xml-LongVariable
//#Patterns: rulesets-naming.xml-ShortMethodName, rulesets-naming.xml-ConstructorWithNameAsEnclosingClass
//#Patterns: rulesets-naming.xml-ConstantNamingConventions, rulesets-naming.xml-BooleanGetMethodName


class Something
{
    //#Info: rulesets-naming.xml-ShortVariable
    private $q = 15;
    //#Info: rulesets-naming.xml-LongVariable
    protected $reallyLongIntNameLongLong = -3;

    //#Info: rulesets-naming.xml-ConstantNamingConventions
    const myTest = "";

    //#Info: rulesets-naming.xml-ShortVariable
    public static function main(array $as)
    {
        //#Info: rulesets-naming.xml-ShortVariable
        $r = 20 + $this->q;
        for (int $i = 0; $i < 10; $i++) {
            $r += $this->q;
        }
    }

    //#Info: rulesets-naming.xml-ShortMethodName
    public function a( $index ) {
    }

    //#Info: rulesets-naming.xml-ConstructorWithNameAsEnclosingClass
    public function Something() {}

    /** * @return boolean */
    //#Info: rulesets-naming.xml-BooleanGetMethodName
    public function getFoo() {} // bad
}