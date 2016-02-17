package org.jetbrains.plugins.dotty.lang.psi.impl.base.types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.dotty.lang.psi.api.base.types.DottyTypeParamBoundsElement
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl

/**
  * @author adkozlov
  */
class DottyTypeParamBoundsElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with DottyTypeParamBoundsElement
