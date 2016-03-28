package org.jetbrains.plugins.dotty.lang.parser

import org.jetbrains.plugins.scala.lang.lexer.ScalaElementType

/**
  * @author adkozlov
  */
object DottyElementTypes {

  val REFINED_TYPE = createElementType("refined type")
  val WITH_TYPE = createElementType("with type")
  val ARG_TYPE = createElementType("argument type")
  val FUN_ARG_TYPE = createElementType("function argument type")
  
  val FUN_ARG_TYPES = createElementType("function argument types")
  val TYPE_BOUNDS = createElementType("type bounds")

  private def createElementType(description: String) = new ScalaElementType(s"dotty $description")
}
