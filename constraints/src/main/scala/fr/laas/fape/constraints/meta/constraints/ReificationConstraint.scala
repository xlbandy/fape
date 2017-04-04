package fr.laas.fape.constraints.meta.constraints

import fr.laas.fape.constraints.meta.CSP
import fr.laas.fape.constraints.meta.domains.BooleanDomain
import fr.laas.fape.constraints.meta.events.{DomainChange, Event, NewConstraintEvent}
import fr.laas.fape.constraints.meta.variables.{IVar, Variable}

import ConstraintSatisfaction._

class ReificationConstraint(val booleanVar: Variable, val constraint: Constraint with ReversibleConstraint) extends Constraint {

  override def variables(implicit csp: CSP): Set[IVar] = constraint.variables + booleanVar

  override def _propagate(event: Event)(implicit csp: CSP): Unit = {
    event match {
      case NewConstraintEvent(c) if c == this =>
        checkVariable
        checkConstraint
      case event: DomainChange =>
        if(event.variable == booleanVar)
          checkVariable
        else
          checkConstraint
    }
  }

  private def checkVariable(implicit csp: CSP): Unit = {
    val dom = csp.dom(booleanVar)
    if(dom.isSingleton && dom.contains(1)) {
      csp.post(constraint)
    } else if(dom.isSingleton && dom.contains(0)) {
      csp.post(constraint.reverse)
    } else {
      assert(dom.size == 2, "Reification variable should never have an empty domain")
    }
  }

  private def checkConstraint(implicit csp: CSP): Unit = {
    if(constraint.isSatisfied)
      csp.updateDomain(booleanVar, new BooleanDomain(Set(true)))
    else if(constraint.isViolated)
      csp.updateDomain(booleanVar, new BooleanDomain(Set(false)))
  }

  override def satisfaction(implicit csp: CSP): Satisfaction = {
    val dom = csp.dom(booleanVar)
    val cSat = constraint.satisfaction
    if (dom.isSingleton && dom.contains(1)) {
      if (cSat == SATISFIED) SATISFIED
      else if (cSat == UNDEFINED) UNDEFINED
      else VIOLATED
    } else if (dom.isSingleton && dom.contains(0)) {
      if (cSat == SATISFIED) VIOLATED
      else if (cSat == UNDEFINED) UNDEFINED
      else SATISFIED
    } else {
      assert(dom.size == 2, "Reification variable should never have an empty domain")
      UNDEFINED
    }
  }

  override def toString = s"[${booleanVar.id}] <=> $constraint"
}
