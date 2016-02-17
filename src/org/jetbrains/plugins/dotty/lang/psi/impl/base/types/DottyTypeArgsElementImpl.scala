package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.DottyTypeArgsElement
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl

/**
  * @author adkozlov
  */
class DottyTypeArgsElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottyTypeArgsElement
