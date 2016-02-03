package org.jetbrains.plugins.dotty.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.TokenSet
import org.jetbrains.plugins.dotty.lang.parser.DottyElementTypes._
import org.jetbrains.plugins.dotty.lang.psi.impl.base.types._
import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.plugins.scala.lang.parser.ScalaElementTypes._
import org.jetbrains.plugins.scala.lang.parser.ScalaPsiCreator

/**
  * @author adkozlov
  */
object DottyPsiCreator extends ScalaPsiCreator {
  private val idTokenSet = TokenSet.create(REFERENCE, ScalaTokenTypes.tAND, ScalaTokenTypes.tOR)

  override protected def inner(node: ASTNode): PsiElement = node.getElementType match {
    case TYPE => new DottyFunctionalTypeElementImpl(node)
    case ANNOT_TYPE => new DottyAnnotatedTypeElementImpl(node)
    case ARG_TYPE => new DottyArgTypeElementImpl(node)
    case FUN_ARG_TYPE => new DottyFunArgTypeElementImpl(node)
    case FUN_ARG_TYPES => new DottyFunArgTypesElementImpl(node)
    case INFIX_TYPE => node.findChildByType(idTokenSet).getElementType match {
      case REFERENCE => new DottyInfixTypeElementImpl(node)
      case ScalaTokenTypes.tAND => new DottyAndTypeElementImpl(node)
      case ScalaTokenTypes.tOR => new DottyOrTypeElementImpl(node)
    }
    case REFINED_TYPE => new DottyRefinedTypeElementImpl(node)
    case SIMPLE_TYPE => new DottySimpleTypeElementImpl(node)
    case TYPE_GENERIC_CALL => new DottyAppliedTypeElementImpl(node)
    case TYPES => new DottyArgTypesElementImpl(node)
    case TYPE_ARGS => new DottyTypeArgsElementImpl(node)
    case TYPE_BOUNDS => new DottyTypeBoundsElementImpl(node)
    case WITH_TYPE => new DottyWithTypeElementImpl(node)
    case _ => super.inner(node)
  }
}
