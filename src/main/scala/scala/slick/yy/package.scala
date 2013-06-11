package scala.slick

import ch.epfl.yinyang._
import ch.epfl.yinyang.typetransformers.PolyTransformer
import scala.language.experimental.macros
import scala.language.existentials
import scala.reflect.macros.Context
import scala.slick.jdbc.JdbcBackend
import scala.slick.driver.JdbcDriver

package object yy {
  //    def shallow[T](block: => T): T = macro implementations.slickYYVP[T]
  def shallow[T](block: => T): T = macro implementations.slickYYVPDebug[T]
  def shallowDebug[T](block: => T): T = macro implementations.slickYYVPDebug[T]
  //  def slickI[T](block: JdbcDriver => JdbcBackend#Session => T)(implicit driver: JdbcDriver, session: JdbcBackend#Session): T = macro implementations.slickYYVPImplicit[T]
  //  //  def slickYYImplicit[T](s: JdbcBackend#Session)(block: => T): T = macro implementations.slickYYImplicit[T]
  //  def slickYYImplicit[T](block: JdbcDriver => JdbcBackend#Session => T)(implicit driver: JdbcDriver, session: JdbcBackend#Session): T = macro implementations.slickYYImplicit[T]
  //  //    val res = slickYY(block)
  //  //    res(driver)(session)
  //  def slickYY[T](block: => T): T = macro implementations.slickYY[T]
  //  def slickYYDebug[T](block: => T): T = macro implementations.slickYYDebug[T]
  //  def slickYYV[T](block: => T): T = macro implementations.slickYYV[T]
  //  def slickYYVP[T](block: => T): T = macro implementations.slickYYVP[T]
  //  def slickYYVDebug[T](block: => T): T = macro implementations.slickYYVDebug[T]
  //  def slickYYVPDebug[T](block: => T): T = macro implementations.slickYYVPDebug[T]

  object implementations {
    //    def slickYY[T](c: Context)(block: c.Expr[T]): c.Expr[T] =
    //      new YYTransformer[c.type, T](c, "scala.slick.yy.SlickYinYang",
    //        shallow = false,
    //        debug = false,
    //        rep = false,
    //        slickHack = true)(block)
    //    def slickYYImplicit[T](c: Context)(block: c.Expr[(JdbcDriver => JdbcBackend#Session => T)])(driver: c.Expr[JdbcDriver], session: c.Expr[JdbcBackend#Session]): c.Expr[T] =
    //      {
    //        val res = new YYTransformer[c.type, (JdbcDriver => JdbcBackend#Session => T)](c, "scala.slick.yy.SlickYinYang",
    //          shallow = false,
    //          debug = false,
    //          rep = false,
    //          slickHack = true)(block)
    //        c.universe.reify {
    //          res.splice(driver.splice)(session.splice)
    //        }
    //
    //      }
    //
    //    def slickYYVPImplicit[T](c: Context)(block: c.Expr[(JdbcDriver => JdbcBackend#Session => T)])(driver: c.Expr[JdbcDriver], session: c.Expr[JdbcBackend#Session]): c.Expr[T] = {
    //
    //      val yyTranformers = new {
    //        val universe: c.universe.type = c.universe
    //        val mirror = c.mirror
    //      } with YYTransformers
    //      yyTranformers.VirtualClassCollector(block.tree)
    //      val ClassVirtualization = yyTranformers.ClassVirtualization.asInstanceOf[(Context#Tree => Context#Tree)]
    //
    //      val res = new YYTransformer[c.type, (JdbcDriver => JdbcBackend#Session => T)](c, "scala.slick.yy.SlickYinYang",
    //        shallow = false,
    //        debug = false,
    //        rep = false,
    //        slickHack = true,
    //        preprocess = ClassVirtualization)(block)
    //      c.universe.reify {
    //        res.splice(driver.splice)(session.splice)
    //      }
    //
    //    }
    //
    //    //    def slickYYImplicit[T](c: Context)(s: c.Expr[JdbcBackend#Session])(block: c.Expr[T]): c.Expr[T] = {
    //    //      import c.universe._
    //    //      object PostProcess extends (c.Tree => c.Tree) {
    //    //        def apply(tree: c.Tree): c.Tree = tree match {
    //    //          case Block(list, expr) => {
    //    //            val Block(newBlock, _) = reify {
    //    //              def session = s.splice
    //    //            }.tree
    //    //            println(newBlock)
    //    //            println(Block(newBlock ++ list, expr))
    //    //            Block(newBlock ++ list, expr)
    //    //          }
    //    //        }
    //    //      }
    //    //      new YYTransformer[c.type, T](c, "scala.slick.yy.ImplicitSlickYinYang",
    //    //        shallow = false,
    //    //        debug = true,
    //    //        rep = false,
    //    //        slickHack = true,
    //    //        preprocess = PostProcess.asInstanceOf[(Context#Tree => Context#Tree)])(block)
    //    //    }
    //
    //    def slickYYDebug[T](c: Context)(block: c.Expr[T]): c.Expr[T] =
    //      new YYTransformer[c.type, T](c, "scala.slick.yy.SlickYinYang",
    //        shallow = false,
    //        debug = true,
    //        rep = false,
    //        slickHack = true)(block)
    //
    //    def slickYYV[T](c: Context)(block: c.Expr[T]): c.Expr[T] = {
    //      val ClassVirtualization = {
    //        new {
    //          val universe: c.universe.type = c.universe
    //          val mirror = c.mirror
    //        } with YYTransformers
    //      }.ClassVirtualization.asInstanceOf[(Context#Tree => Context#Tree)]
    //
    //      new YYTransformer[c.type, T](c, "scala.slick.yy.SlickYinYang",
    //        shallow = false,
    //        debug = false,
    //        rep = false,
    //        slickHack = true,
    //        preprocess = ClassVirtualization)(block)
    //    }
    def slickYYVP[T](c: Context)(block: c.Expr[T]): c.Expr[T] = {
      //      println(c.universe.showRaw(block))
      val yyTranformers = new {
        val universe: c.universe.type = c.universe
        val mirror = c.mirror
      } with YYTransformers
      yyTranformers.VirtualClassCollector(block.tree)
      val ClassVirtualization = yyTranformers.ClassVirtualization.asInstanceOf[(Context#Tree => Context#Tree)]

      YYTransformer[c.type, T](c)("scala.slick.yy.SlickYinYang",
        new SlickTypeTransformer[c.type](c),
        Map("shallow" -> false, "debug" -> 0, "featureAnalysing" -> false, "ascriptionTransforming" -> false) /*,
        preprocess = ClassVirtualization*/ )(block)
    }
    def slickYYVPDebug[T](c: Context)(block: c.Expr[T]): c.Expr[T] = {
      //      println(c.universe.showRaw(block))
      val yyTranformers = new {
        val universe: c.universe.type = c.universe
        val mirror = c.mirror
      } with YYTransformers
      yyTranformers.VirtualClassCollector(block.tree)
      val ClassVirtualization = yyTranformers.ClassVirtualization.asInstanceOf[(Context#Tree => Context#Tree)]

      YYTransformer[c.type, T](c)("scala.slick.yy.SlickYinYang",
        new SlickTypeTransformer[c.type](c),
        Map("shallow" -> false, "debug" -> 1, "featureAnalysing" -> false, "ascriptionTransforming" -> false) /*,
        preprocess = ClassVirtualization*/ )(block)
    }

    //    def slickYYVDebug[T](c: Context)(block: c.Expr[T]): c.Expr[T] = {
    //      val ClassVirtualization = {
    //        new {
    //          val universe: c.universe.type = c.universe
    //          val mirror = c.mirror
    //        } with YYTransformers
    //      }.ClassVirtualization.asInstanceOf[(Context#Tree => Context#Tree)]
    //
    //      new YYTransformer[c.type, T](c, "scala.slick.yy.SlickYinYang",
    //        shallow = false,
    //        debug = true,
    //        rep = false,
    //        slickHack = true,
    //        preprocess = ClassVirtualization)(block)
    //    }
  }
}
