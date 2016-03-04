package org.jetbrains.plugins.dotty.lang.psi.types

import org.jetbrains.plugins.scala.lang.psi.api.base.types.ScRefinement
import org.jetbrains.plugins.scala.lang.psi.api.statements._
import org.jetbrains.plugins.scala.lang.psi.types.api.TypeVisitor
import org.jetbrains.plugins.scala.lang.psi.types.{ScType, Signature, TypeAliasSignature, ValueType}

/**
  * @author adkozlov
  */
case class DottyRefinedType private(`type`: ScType,
                                    signatures: Set[Signature],
                                    typeAliasSignatures: Set[TypeAliasSignature]) extends DottyType with ValueType {
  override def visitType(visitor: TypeVisitor) = visitor match {
    case dottyVisitor: DottyTypeVisitor => dottyVisitor.visitRefinedType(this)
    case _ =>
  }
}

object DottyRefinedType {
  implicit val typeSystem = DottyTypeSystem

  def apply(`type`: ScType, refinement: ScRefinement): DottyRefinedType = {
    val signatures = refinement.holders.map {
      case function: ScFunction => Set(Signature(function))
      case variable: ScVariable =>
        val elements = variable.declaredElements
        elements.map(Signature.getter) ++ elements.map(Signature.setter)
      case value: ScValue => value.declaredElements.map(Signature.getter)
    }.foldLeft(Set[Signature]())(_ ++ _)

    val typeAliasSignatures = refinement.types.map {
      new TypeAliasSignature(_)
    }.toSet

    `type` match {
      case refinedType: DottyRefinedType => new DottyRefinedType(
        refinedType.`type`,
        refinedType.signatures ++ signatures,
        refinedType.typeAliasSignatures ++ typeAliasSignatures
      )
      case notRefinedType => new DottyRefinedType(
        notRefinedType,
        signatures,
        typeAliasSignatures)
    }
  }
}