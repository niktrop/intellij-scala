package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.{DottyAndOrTypeElement, DottyAndTypeElement, DottyOrTypeElement}
import org.jetbrains.plugins.dotty.lang.psi.types._
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl
import org.jetbrains.plugins.scala.lang.psi.api.base.types.ScTypeElement
import org.jetbrains.plugins.scala.lang.psi.types.result.{Success, TypeResult, TypingContext}
import org.jetbrains.plugins.scala.lang.psi.types.{Any, Nothing, ScType}

/**
  * @author adkozlov
  */
abstract class DottyAndOrTypeElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottyAndOrTypeElement {
  protected val defaultType: ScType

  protected def apply: Seq[ScType] => ScType

  protected def innerType(context: TypingContext) = {
    collectFailures(Seq(leftTypeElement, rightTypeElement).map(_.getType(context)), defaultType)(apply)
  }
}

class DottyAndTypeElementImpl(node: ASTNode) extends DottyAndOrTypeElementImpl(node) with DottyAndTypeElement {
  override protected val defaultType = Nothing

  override protected def apply = DottyAndType(_)
}

class DottyOrTypeElementImpl(node: ASTNode) extends DottyAndOrTypeElementImpl(node) with DottyOrTypeElement {
  override protected val defaultType = Any

  override protected def apply = DottyOrType(_)
}
