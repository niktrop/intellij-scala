package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.DottyAnnotatedTypeElement
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl
import org.jetbrains.plugins.scala.lang.psi.types.result.TypingContext

/**
  * @author adkozlov
  */
class DottyAnnotatedTypeElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottyAnnotatedTypeElement {
  override protected def innerType(context: TypingContext) = typeElement.getType(context)
}
