package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.DottyArgTypesElement
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl

/**
  * @author adkozlov
  */
class DottyArgTypesElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottyArgTypesElement
