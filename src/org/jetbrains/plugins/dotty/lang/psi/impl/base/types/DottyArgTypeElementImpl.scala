package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.DottyArgTypeElement
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl
import org.jetbrains.plugins.scala.lang.psi.types.result.TypingContext

/**
  * @author adkozlov
  */
class DottyArgTypeElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottyArgTypeElement {
  override protected def innerType(context: TypingContext) = ???
}
