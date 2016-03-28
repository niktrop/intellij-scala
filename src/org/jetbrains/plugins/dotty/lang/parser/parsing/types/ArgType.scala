package org.jetbrains.plugins.dotty.lang.parser.parsing.types

import org.jetbrains.plugins.dotty.lang.parser.DottyElementTypes._
import org.jetbrains.plugins.dotty.lang.parser.util.ParserUtils._
import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.plugins.scala.lang.parser.parsing.builder.ScalaPsiBuilder

/**
  * @author adkozlov
  */

/*
 * ArgType ::=  Type | `_' TypeBounds
 */
object ArgType extends org.jetbrains.plugins.scala.lang.parser.parsing.types.Type {
  override protected val infixType = InfixType

  override def parse(builder: ScalaPsiBuilder, star: Boolean, isPattern: Boolean): Boolean = {
    builder.getTokenType match {
      case ScalaTokenTypes.tUNDER => parseWithPrefixToken(builder, ARG_TYPE) {
        TypeBounds.parse(builder, star, isPattern)
      }
      case _ => Type.parse(builder, star, isPattern)
    }
  }
}
