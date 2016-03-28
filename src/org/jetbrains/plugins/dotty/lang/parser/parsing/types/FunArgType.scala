package org.jetbrains.plugins.dotty.lang.parser.parsing.types

import org.jetbrains.plugins.dotty.lang.parser.DottyElementTypes._
import org.jetbrains.plugins.dotty.lang.parser.util.ParserUtils._
import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.plugins.scala.lang.parser.parsing.builder.ScalaPsiBuilder

/**
  * @author adkozlov
  */

/*
 * FunArgType ::= ArgType | `=>' ArgType
 */
object FunArgType extends org.jetbrains.plugins.scala.lang.parser.parsing.types.Type {
  override protected val infixType = InfixType

  override def parse(builder: ScalaPsiBuilder, star: Boolean, isPattern: Boolean) = {
    def parseArgType() = ArgType.parse(builder, star, isPattern)

    builder.getTokenType match {
      case ScalaTokenTypes.tFUNTYPE => parseWithPrefixToken(builder, FUN_ARG_TYPE, Some("argument.type.expected")) {
        parseArgType()
      }
      case _ => parseArgType()
    }
  }
}
