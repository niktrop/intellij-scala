package org.jetbrains.plugins.dotty.lang.psi.api.base.types

import org.jetbrains.plugins.scala.lang.psi.api.base.types.ScTypeElement

/**
  * @author adkozlov
  */
trait DottyAndOrTypeElement extends ScTypeElement {
  def leftTypeElement = typeElements(0)

  def rightTypeElement = typeElements(1)

  private def typeElements = findChildrenByClassScala(classOf[ScTypeElement])
}

trait DottyAndTypeElement extends DottyAndOrTypeElement {
  override val typeName = "AndType"
}

trait DottyOrTypeElement extends DottyAndOrTypeElement {
  override val typeName = "OrType"
}