
[Superglobal variables](http://www.php.net/manual/en/language.variables.superglobals.php) in PHP should not be accessed directly.
Instead of writing:

    $name = $_POST['foo'];

You should encapsulate the call in an single class.
Most PHP frameworks already wrap Superglobals into a request object so you can use that.

[Source](http://phpmd.org/rules/controversial.html#superglobals)
      