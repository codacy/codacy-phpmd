<?php
//#Patterns: PHPMD_NamingRules_ShortVariable, PHPMD_NamingRules_LongVariable
//#Patterns: PHPMD_NamingRules_ShortMethodName, PHPMD_NamingRules_ConstructorWithNameAsEnclosingClass
//#Patterns: PHPMD_NamingRules_ConstantNamingConventions, PHPMD_NamingRules_BooleanGetMethodName


class Something
{
    //#Info: PHPMD_NamingRules_ShortVariable
    private $q = 15;
    //#Info: PHPMD_NamingRules_LongVariable
    protected $reallyLongIntNameLongLong = -3;

    //#Info: PHPMD_NamingRules_ConstantNamingConventions
    const myTest = "";

    //#Info: PHPMD_NamingRules_ShortVariable
    public static function main(array $as)
    {
        //#Info: PHPMD_NamingRules_ShortVariable
        $r = 20 + $this->q;
        for (int $i = 0; $i < 10; $i++) {
            $r += $this->q;
        }
    }

    //#Info: PHPMD_NamingRules_ShortMethodName
    public function a( $index ) {
    }

    //#Info: PHPMD_NamingRules_ConstructorWithNameAsEnclosingClass
    public function Something() {}

    /** * @return boolean */
    //#Info: PHPMD_NamingRules_BooleanGetMethodName
    public function getFoo() {} // bad
}