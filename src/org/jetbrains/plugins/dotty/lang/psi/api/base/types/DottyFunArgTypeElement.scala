package org.jetbrains.plugins.dotty.lang.psi.api.base.types

import org.jetbrains.plugins.scala.lang.psi.api.base.types.ScTypeElement

/**
  * @author adkozlov
  */
trait DottyFunArgTypeElement extends ScTypeElement {
  override val typeName = "FunctionArgumentType"
}
