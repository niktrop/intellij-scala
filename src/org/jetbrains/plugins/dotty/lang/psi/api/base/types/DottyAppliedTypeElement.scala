package org.jetbrains.plugins.dotty.lang.psi.api.base.types

import org.jetbrains.plugins.scala.lang.psi.api.base.types.ScTypeElement

/**
  * @author adkozlov
  */
trait DottyAppliedTypeElement extends ScTypeElement {
  override val typeName = "AppliedType"
}
