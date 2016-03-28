package org.jetbrains.plugins.dotty.lang.parser.parsing.types

import org.jetbrains.plugins.dotty.lang.parser.DottyElementTypes._
import org.jetbrains.plugins.scala.ScalaBundle
import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.plugins.scala.lang.parser.ScalaElementTypes._
import org.jetbrains.plugins.scala.lang.parser.parsing.builder.ScalaPsiBuilder

/**
  * @author adkozlov
  */

/*
 * Type ::= FunArgTypes `=>' Type | InfixType
 */
object Type extends org.jetbrains.plugins.scala.lang.parser.parsing.types.Type {
  override protected val infixType = InfixType

  override def parse(builder: ScalaPsiBuilder, star: Boolean, isPattern: Boolean): Boolean = {
    val marker = builder.mark()
    if (!FunArgTypes.parse(builder, star, isPattern)) {
      marker.drop()
      return false
    }

    builder.getTokenType match {
      case ScalaTokenTypes.tFUNTYPE =>
        val funMarker = marker.precede()
        marker.done(FUN_ARG_TYPES)

        builder.advanceLexer() // ate '=>'
        if (!parse(builder, star, isPattern)) {
          builder.error(ScalaBundle.message("wrong.type"))
        }

        funMarker.done(TYPE)
      case _ => marker.drop()
    }
    true
  }
}
