package org.jetbrains.plugins.dotty.lang.psi.api.base.types

import org.jetbrains.plugins.scala.lang.psi.api.base.types.ScTypeElement
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScAnnotation

/**
  * @author adkozlov
  */
trait DottyAnnotatedTypeElement extends ScTypeElement {
  override val typeName = "AnnotatedType"

  def typeElement = findChildByClassScala(classOf[ScTypeElement])

  def annotations = findChildrenByClassScala(classOf[ScAnnotation])
}
