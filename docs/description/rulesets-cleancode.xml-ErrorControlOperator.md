Error suppression should be avoided if possible as it doesn't just suppress the error, that you are trying to stop, but will also suppress errors that you didn't predict would ever occur. Consider changing error_reporting() level and/or setting up your own error handler.

[Source](http://phpmd.org/rules/cleancode.html#errorcontroloperator)