package org.jetbrains.plugins.dotty.lang.parser.parsing.types

import org.jetbrains.plugins.scala.ScalaBundle
import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.plugins.scala.lang.parser.parsing.builder.ScalaPsiBuilder

/**
  * @author adkozlov
  */

/*
 * FunArgTypes ::= InfixType | `(' [ FunArgType {`,' FunArgType } ] `)'
 */
object FunArgTypes {
  def parse(builder: ScalaPsiBuilder, star: Boolean, isPattern: Boolean): Boolean = {
    builder.getTokenType match {
      case ScalaTokenTypes.tLPARENTHESIS =>
        builder.advanceLexer() // ate '('

        def parseFunArgType() = FunArgType.parse(builder, star, isPattern)

        if (parseFunArgType()) {
          while (builder.getTokenType == ScalaTokenTypes.tCOMMA) {
            builder.advanceLexer() // ate ','

            if (!parseFunArgType()) {
              builder.error(ScalaBundle.message("wrong.type"))
            }
          }
        }

        builder.getTokenType match {
          case ScalaTokenTypes.tRPARENTHESIS => builder.advanceLexer() // ate ')'
          case _ => builder.error(ScalaBundle.message("rparenthesis.expected"))
        }

        true
      case _ => InfixType.parse(builder, star, isPattern)
    }
  }
}
