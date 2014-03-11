package planstack.anml.model.concrete.statements

import planstack.anml.model._
import planstack.anml.model.abs.AbstractTemporalStatement

/**
 * Represents a temporally qualified, concrete ANML statement.
 * @param interval Temporal interval in which the statement applies
 * @param statement An ANML statement on global variables
 */
class TemporalStatement(val interval:TemporalAnnotation, val statement:Statement) {

  override def toString = "%s %s".format(interval, statement)
}

object TemporalStatement {

  def apply(context:Context, abs:AbstractTemporalStatement) =
    new TemporalStatement(abs.annotation, abs.statement.bind(context))
}