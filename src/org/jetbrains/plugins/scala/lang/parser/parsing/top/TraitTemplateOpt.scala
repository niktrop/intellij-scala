package org.jetbrains.plugins.scala
package lang
package parser
package parsing
package top

import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.plugins.scala.lang.parser.parsing.builder.ScalaPsiBuilder
import org.jetbrains.plugins.scala.lang.parser.parsing.top.template.TemplateBody

/**
* @author Alexander Podkhalyuzin
* Date: 06.02.2008
*/

/*
 * TraitTemplateOpt ::= 'extends' TraitTemplate | [['extends'] TemplateBody]
 */
object TraitTemplateOpt extends TraitTemplateOpt {
  override protected val templateBody = TemplateBody
  override protected val earlyDef = EarlyDef
  override protected val mixinParents = MixinParents
}

//It's very similar code to ClassTemplateOpt
trait TraitTemplateOpt {
  protected val templateBody: TemplateBody
  protected val earlyDef: EarlyDef
  protected val mixinParents: MixinParents

  def parse(builder: ScalaPsiBuilder): Unit = {
    val extendsMarker = builder.mark
    //try to find extends keyword
    builder.getTokenType match {
      case ScalaTokenTypes.kEXTENDS | ScalaTokenTypes.tUPPER_BOUND => builder.advanceLexer() //Ate extends
      case ScalaTokenTypes.tLBRACE =>
        if (builder.twoNewlinesBeforeCurrentToken) {
          extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
          return
        }
        templateBody parse builder
        extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
        return
      case _ =>
        extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
        return
    }
    // try to split TraitParents and TemplateBody
    builder.getTokenType match {
      //hardly case, becase it's same token for ClassParents and TemplateBody
      case ScalaTokenTypes.tLBRACE =>
        //try to parse early definition if we can't => it's template body
        if (earlyDef parse builder) {
          mixinParents parse builder
          //parse template body
          builder.getTokenType match {
            case ScalaTokenTypes.tLBRACE => {
              if (builder.twoNewlinesBeforeCurrentToken) {
                extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
                return
              }
              templateBody parse builder
              extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
              return
            }
            case _ => {
              extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
              return
            }
          }
        }
        else {
          //parse template body
          builder.getTokenType match {
            case ScalaTokenTypes.tLBRACE => {
              templateBody parse builder
              extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
              return
            }
            case _ => {
              extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
              return
            }
          }
        }
      //if we find nl => it could be TemplateBody only, but we can't find nl after extends keyword
      //In this case of course it's ClassParents
      case _ =>
        mixinParents parse builder
        //parse template body
        builder.getTokenType match {
          case ScalaTokenTypes.tLBRACE => {
            if (builder.twoNewlinesBeforeCurrentToken) {
              extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
              return
            }
            templateBody parse builder
            extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
            return
          }
          case _ => {
            extendsMarker.done(ScalaElementTypes.EXTENDS_BLOCK)
            return
          }
        }
    }
  }
}