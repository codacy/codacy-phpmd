package codacy

package phpmd {

  class RuleName(val value:String) extends AnyVal{ override def toString = value }
  class RuleId(  val value:String) extends AnyVal{ override def toString = value }

  object RuleName{ def apply(v:String): RuleName = new RuleName(v) }
  object RuleId{   def apply(v:String): RuleId   = new RuleId(v)   }
}

package object phpmd {

}
