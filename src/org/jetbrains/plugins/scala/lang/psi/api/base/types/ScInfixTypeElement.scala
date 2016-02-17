package org.jetbrains.plugins.scala
package lang
package psi
package api
package base
package types

/** 
* @author Alexander Podkhalyuzin
* Date: 22.02.2008
*/

trait ScInfixTypeElement extends ScDesugarizableToParametrizedTypeElement {
  override protected val typeName = "InfixType"

  def lOp = findChildByClassScala(classOf[ScTypeElement])

  def rOp = findChildrenByClassScala(classOf[ScTypeElement]) match {
    case Array(_, r) => Some(r)
    case _ => None
  }

  def ref = findChildByClassScala(classOf[ScStableCodeReferenceElement])
}