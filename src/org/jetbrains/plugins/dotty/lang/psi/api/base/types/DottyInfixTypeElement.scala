package org.jetbrains.plugins.dotty.lang.psi.api.base.types

import org.jetbrains.plugins.scala.lang.psi.api.base.ScStableCodeReferenceElement
import org.jetbrains.plugins.scala.lang.psi.api.base.types.{ScDesugarizableTypeElement, ScTypeElement}

/**
  * @author adkozlov
  */
trait DottyInfixTypeElement extends ScDesugarizableTypeElement {
  override val typeName = "InfixType"

  def leftTypeElement = findChildByClassScala(classOf[ScTypeElement])

  def rightTypeElement = findChildrenByClassScala(classOf[ScTypeElement]) match {
    case Array(_, right) => Some(right)
    case _ => None
  }

  def operation = findChildByClassScala(classOf[ScStableCodeReferenceElement])
}
