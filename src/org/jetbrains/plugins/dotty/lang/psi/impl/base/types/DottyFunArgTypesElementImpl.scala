package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.DottyFunArgTypesElement
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl

/**
  * @author adkozlov
  */
class DottyFunArgTypesElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottyFunArgTypesElement
