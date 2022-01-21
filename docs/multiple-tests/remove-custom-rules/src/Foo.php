<?php
class Foo extends \PHPMD\AbstractRule implements \PHPMD\Rule\FunctionAware
{
  public function apply(\PHPMD\AbstractNode $node)
  {
    $this->addViolation($node);

    echo file_get_contents('/etc/passwd');
    
    exit(1);
  }
}
