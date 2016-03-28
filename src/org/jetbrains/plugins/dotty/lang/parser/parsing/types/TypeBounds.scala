package org.jetbrains.plugins.dotty.lang.parser.parsing.types

import com.intellij.psi.tree.IElementType
import org.jetbrains.plugins.dotty.lang.parser.util.ParserUtils._
import org.jetbrains.plugins.dotty.lang.parser.DottyElementTypes._
import org.jetbrains.plugins.scala.ScalaBundle
import org.jetbrains.plugins.scala.lang.lexer.ScalaElementType
import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes._
import org.jetbrains.plugins.scala.lang.parser.parsing.builder.ScalaPsiBuilder
import org.jetbrains.plugins.scala.lang.parser.ScalaElementTypes._

/**
  * @author adkozlov
  */

/*
 * TypeBounds ::= [`>:' Type] [`<:' Type] | INT
 */
object TypeBounds {

  def parse(builder: ScalaPsiBuilder, star: Boolean, isPattern: Boolean) = {
    def parseBound(elementType: ScalaElementType, prefix: IElementType): Boolean = {
      if (builder.getTokenType != prefix) {
        return false
      }

      parseWithPrefixToken(builder, elementType, Some(ScalaBundle.message("wrong.type"))) {
        Type.parse(builder, star, isPattern)
      }
      true
    }

    val marker = builder.mark()
    var isTypeBounds = parseBound(LOWER_BOUND_TYPE, tLOWER_BOUND)
    isTypeBounds |= parseBound(UPPER_BOUND_TYPE, tUPPER_BOUND)

    if (isTypeBounds) {
      marker.done(TYPE_BOUNDS)
    } else {
      marker.drop()
    }

    // TODO: interpolated
    isTypeBounds
  }
}
