package planstack.anml.model.abs.statements

import planstack.anml.model.{Context, LStatementRef, AbstractParameterizedStateVariable}
import planstack.anml.model.concrete.statements._

/** An abstract ANML resource Statement.
  *
  * It applies on a parameterized state variable and takes integer parameters.
  * Further work should be done to support more complex right hand side expressions.
  *
  * @param sv State Variable on which this statement applies
  * @param param Right side of the statement: a numeric value. For instance, in the statement `energy :use 50`, 50 would be the param.
  * @param id A local reference to the Statement (for temporal constraints).
  */
abstract class AbstractResourceStatement(sv:AbstractParameterizedStateVariable, val param:Float, id:LStatementRef) extends AbstractStatement(sv, id) {
  require(sv.func.valueType == "integer")

  def operator : String

  override def toString = "%s : %s %s %s".format(id, sv, operator, param)

  /**
   * Produces the corresponding concrete statement, by replacing all local variables
   * by the global ones defined in Context
   * @param context Context in which this statement appears.
   * @return
   */
  def bind(context: Context): ResourceStatement = {
    val variable = sv.bind(context)

    this match {
      case _:AbstractProduceResource => new ProduceResource(variable, param)
      case _:AbstractSetResource => new SetResource(variable, param)
      case _:AbstractConsumeResource => new ConsumeResource(variable, param)
      case _:AbstractLendResource => new LendResource(variable, param)
      case _:AbstractUseResource => new UseResource(variable, param)
      case _:AbstractRequireResource => new RequireResource(variable, operator, param)
    }
  }

}

class AbstractProduceResource(sv:AbstractParameterizedStateVariable, param:Float, id:LStatementRef) extends AbstractResourceStatement(sv, param, id) {
  val operator = ":produce"
}

class AbstractSetResource(sv:AbstractParameterizedStateVariable, param:Float, id:LStatementRef) extends AbstractResourceStatement(sv, param, id) {
  val operator = ":="
}

class AbstractLendResource(sv:AbstractParameterizedStateVariable, param:Float, id:LStatementRef) extends AbstractResourceStatement(sv, param, id) {
  val operator = ":lend"
}

class AbstractUseResource(sv:AbstractParameterizedStateVariable, param:Float, id:LStatementRef) extends AbstractResourceStatement(sv, param, id) {
  val operator = ":use"
}

class AbstractConsumeResource(sv:AbstractParameterizedStateVariable, param:Float, id:LStatementRef) extends AbstractResourceStatement(sv, param, id) {
  val operator = ":consume"
}

class AbstractRequireResource(sv:AbstractParameterizedStateVariable, val operator:String, param:Float, id:LStatementRef) extends AbstractResourceStatement(sv, param, id) {
  require(Set("<=","<",">=",">").contains(operator))
}