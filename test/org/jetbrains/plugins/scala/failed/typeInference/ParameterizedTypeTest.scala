package org.jetbrains.plugins.scala.failed.typeInference

import org.jetbrains.plugins.scala.PerfCycleTests
import org.jetbrains.plugins.scala.base.ScalaLightCodeInsightFixtureTestAdapter
import org.junit.experimental.categories.Category

/**
  * @author Roman.Shein
  * @since 28.03.2016.
  */
@Category(Array(classOf[PerfCycleTests]))
class ParameterizedTypeTest extends ScalaLightCodeInsightFixtureTestAdapter {

  def testSCL4990() = {
    val text =
      """
        |object Example {
        |  def mangle[A, M[_]](m: M[A]) = {}
        |
        |  case class OneParameterType[A](value: A)
        |  case class TwoParameterType[S, A](value: (S, A))
        |  type Alias[A] = TwoParameterType[String, A]
        |
        |  val a: OneParameterType[Int] = OneParameterType(1)
        |  val b: Alias[Int] = TwoParameterType(("s", 1))
        |  mangle(a)
        |  mangle(b)
        |}
      """.stripMargin
    checkTextHasNoErrors(text)
  }

  def testSCL8161() = {
    val text =
      """
        |trait TypeMismatch {
        |  type M[X]
        |
        |  def ok[F[_],A](v: F[A]) = ???
        |
        |  ok(??? : M[Option[Int]])
        |}
      """.stripMargin
    checkTextHasNoErrors(text)
  }
}
