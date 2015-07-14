<?php
//#Patterns: PHPMD_CodeSize_CyclomaticComplexity: { "reportLevel": "2" }
//#Patterns: PHPMD_CodeSize_NPathComplexity: { "minimum": "4" }
//#Patterns: PHPMD_CodeSize_ExcessiveMethodLength: { "minimum": "11" }
//#Patterns: PHPMD_CodeSize_ExcessiveClassLength: { "minimum": "2" }
//#Patterns: PHPMD_CodeSize_ExcessiveParameterList: { "minimum": "2" }
//#Patterns: PHPMD_CodeSize_ExcessivePublicCount: { "minimum": "2" }
//#Patterns: PHPMD_CodeSize_TooManyFields: { "maxfields": "2" }
//#Patterns: PHPMD_CodeSize_TooManyMethods: { "maxmethods": "2" }
//#Patterns: PHPMD_CodeSize_ExcessiveClassComplexity: { "maximum": "2" }
//#Patterns: PHPMD_CleanCode_ElseExpression



//#Warn: PHPMD_CodeSize_ExcessiveClassLength
//#Warn: PHPMD_CodeSize_ExcessiveClassComplexity
//#Warn: PHPMD_CodeSize_ExcessivePublicCount
//#Warn: PHPMD_CodeSize_TooManyFields
//#Warn: PHPMD_CodeSize_TooManyMethods
class Token
{
    //#Warn: PHPMD_CodeSize_ExcessiveParameterList
    public function __construct(TokenType $type, $value)
    {
        $this->type = $type;
        $this->value = $value;
    }

    //#Warn: PHPMD_CodeSize_CyclomaticComplexity
    //#Warn: PHPMD_CodeSize_ExcessiveMethodLength
    //#Warn: PHPMD_CodeSize_NPathComplexity
    public static function createLiteral($value)
    {
        if (is_integer($value) || is_float($value)) {
            return new Token(TokenType::NUMBER_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_bool($value)) {
            return new Token(TokenType::BOOLEAN_LITERAL(), $value);
        } elseif (is_null($value)) {
            return new Token(TokenType::NULL_LITERAL(), null);
        //#Info: PHPMD_CleanCode_ElseExpression
        } else {
            return new Token(TokenType::STRING_LITERAL(), strval($value));
        }
    }

    //#Warn: PHPMD_CodeSize_CyclomaticComplexity
    public function type()
    {
        if (true) {
            return;
        }
            return $this->type;
    }

    public function value()
    {
        return $this->value;
    }

    public $type1;
    public $type2;
    public $type3;
    public $type4;
    public $type5;
    public $type6;
    public $type7;
    public $type8;
    public $type9;
    public $type10;

    private $type;
    private $value;






































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































    public $type11;
    public $type12;
    public $type13;
    public $type14;
    public $type15;
    public $type16;
    public $type17;
    public $type18;
    public $type19;
    public $type20;
    public $type21;
    public $type22;
    public $type23;
    public $type24;
    public $type25;
    public $type26;
    public $type27;
    public $type28;
    public $type29;
    public $type30;
    public $type31;
    public $type32;
    public $type33;
    public $type34;
    public $type35;
    public $type36;
    public $type37;
    public $type38;
    public $type39;
    public $type40;
    public $type41;
    public $type42;
    public $type43;
    public $type44;
    public $type45;
    public $type46;
    public $type47;
    public $type48;
    public $type49;
    public $type50;

    private $tp11;
    private $tp12;
    private $tp13;
    private $tp14;
    private $tp15;
    private $tp16;
    private $tp17;
    private $tp18;
    private $tp19;
    private $tp20;
    private $tp21;
    private $tp22;
    private $tp23;
    private $tp24;
    private $tp25;
    private $tp26;
    private $tp27;
    private $tp28;
    private $tp29;
    private $tp30;

    public function value1() {}
    public function value2() {}
    public function value3() {}
    public function value4() {}
    public function value5() {}
    public function value6() {}
    public function value7() {}
    public function value8() {}
    public function value9() {}
    public function value10() {}

}

?>
