package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.DottySimpleTypeElement
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl
import org.jetbrains.plugins.scala.lang.psi.types.Any
import org.jetbrains.plugins.scala.lang.psi.types.result.TypingContext

/**
  * @author adkozlov
  */
class DottySimpleTypeElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottySimpleTypeElement {
  // TODO: rewrite
  override protected def innerType(context: TypingContext) = collectFailures(Seq(), Any)(_ => Any)
}
