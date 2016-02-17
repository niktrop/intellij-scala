package org.jetbrains.plugins.scala
package lang
package psi
package api
package base
package types

import org.jetbrains.plugins.scala.lang.psi.impl.ScalaPsiElementFactory._
import org.jetbrains.plugins.scala.macroAnnotations.{Cached, ModCount}

/** 
* @author Alexander Podkhalyuzin
* Date: 13.03.2008
*/

trait ScParameterizedTypeElement extends ScDesugarizableTypeElement {
  override protected val typeName = "ParametrizedType"

  def typeArgList: ScTypeArgs

  def typeElement: ScTypeElement

  def findConstructor: Option[ScConstructor]
}

object ScParameterizedTypeElement {
  def unapply(pte: ScParameterizedTypeElement): Option[(ScTypeElement, Seq[ScTypeElement])] = {
    pte match {
      case null => None
      case _ => Some(pte.typeElement, pte.typeArgList.typeArgs)
    }
  }
}

trait ScDesugarizableToParametrizedTypeElement extends ScDesugarizableTypeElement {
  @Cached(synchronized = true, ModCount.getBlockModificationCount, this)
  override def computeDesugarizedType: Option[ScParameterizedTypeElement] = createTypeElementFromText(desugarizedText, getContext, this) match {
    case typeElement: ScParameterizedTypeElement => Some(typeElement)
    case _ => None
  }
}