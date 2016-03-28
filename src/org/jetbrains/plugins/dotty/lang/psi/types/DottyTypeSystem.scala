package org.jetbrains.plugins.dotty.lang.psi.types

import org.jetbrains.plugins.scala.lang.psi.types.api.TypeSystem

/**
  * @author adkozlov
  */
object DottyTypeSystem extends TypeSystem {
  override val name = "Dotty"
  override lazy val equivalence = Equivalence
  override lazy val conformance = Conformance
  override lazy val bounds = Bounds
  override lazy val bridge = ScTypePsiTypeBridge
  protected override lazy val presentation = ScTypePresentation
}
