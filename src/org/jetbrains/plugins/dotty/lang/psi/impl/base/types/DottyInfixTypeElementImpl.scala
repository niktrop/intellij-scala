package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.DottyInfixTypeElement
import org.jetbrains.plugins.dotty.lang.psi.impl.DottyPsiElementFactory._
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl

/**
  * @author adkozlov
  */
class DottyInfixTypeElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottyInfixTypeElement {
  override def desugarizedText: String = s"${operation.getText}[${leftTypeElement.getText}, ${rightTypeElement.map(_.getText).getOrElse("Nothing")}]"

  override def computeDesugarizedType: Option[DottyAppliedTypeElementImpl] = createTypeElementFromText(desugarizedText, getContext, this) match {
    case typeElement: DottyAppliedTypeElementImpl => Some(typeElement)
    case _ => None
  }
}
