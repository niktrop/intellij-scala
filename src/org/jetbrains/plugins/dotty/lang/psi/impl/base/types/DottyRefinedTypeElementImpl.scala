package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.DottyRefinedTypeElement
import org.jetbrains.plugins.dotty.lang.psi.types.DottyRefinedType
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl
import org.jetbrains.plugins.scala.lang.psi.types.Any
import org.jetbrains.plugins.scala.lang.psi.types.result.{Success, TypingContext}

/**
  * @author adkozlov
  */
class DottyRefinedTypeElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottyRefinedTypeElement {
  override protected def innerType(context: TypingContext) = Success(
    DottyRefinedType(typeElement.getType(context).getOrElse(Any), refinement),
    Some(this))
}
