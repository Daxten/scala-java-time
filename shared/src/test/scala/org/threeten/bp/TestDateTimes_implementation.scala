/*
 * Copyright (c) 2007-present, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.threeten.bp

import org.scalatest.FunSuite

/** Test. */
class TestDateTimes_implementation extends FunSuite with AssertionsHelper {

  val safeAddIntProvider: List[List[Int]] =
    List(
      List(Integer.MIN_VALUE, 1, Integer.MIN_VALUE + 1),
      List(-1, 1, 0),
      List(0, 0, 0),
      List(1, -1, 0),
      List(Integer.MAX_VALUE, -1, Integer.MAX_VALUE - 1))

  test("test_safeAddInt") {
    safeAddIntProvider.foreach {
      case (a: Int) :: (b: Int) :: (expected: Int) :: Nil =>
        assertEquals(Math.addExact(a, b), expected)
      case _ =>
        fail()
    }
  }

  val safeAddIntProviderOverflow: List[List[Int]] =
    List(
      List(Integer.MIN_VALUE, -1),
      List(Integer.MIN_VALUE + 1, -2),
      List(Integer.MAX_VALUE - 1, 2),
      List(Integer.MAX_VALUE, 1))

  test("test_safeAddInt_overflow") {
    safeAddIntProviderOverflow.foreach {
      case (a: Int) :: (b: Int) :: Nil =>
        assertThrows[ArithmeticException] {
          Math.addExact(a, b)
        }
      case _ =>
        fail()
    }
  }

  val safeAddLongProvider: List[List[Long]] =
    List(
      List(Long.MinValue, 1, Long.MinValue + 1),
      List(-1, 1, 0),
      List(0, 0, 0),
      List(1, -1, 0),
      List(Long.MaxValue, -1, Long.MaxValue - 1))

  test("test_safeAddLong") {
    safeAddLongProvider.foreach {
      case (a: Long) :: (b: Long) :: (expected: Long) :: Nil =>
        assertEquals(Math.addExact(a, b), expected)
      case _ =>
        fail()
    }
  }

  val safeAddLongProviderOverflow: List[List[Long]] =
    List(
      List(Long.MinValue, -1),
      List(Long.MinValue + 1, -2),
      List(Long.MaxValue - 1, 2),
      List(Long.MaxValue, 1))

  test("test_safeAddLong_overflow") {
    safeAddLongProviderOverflow.foreach {
      case (a: Long) :: (b: Long) :: Nil =>
        assertThrows[ArithmeticException] {
          Math.addExact(a, b)
        }
      case _ =>
        fail()
    }
  }


  val safeSubtractIntProvider: List[List[Int]] =
    List(
      List(Integer.MIN_VALUE, -1, Integer.MIN_VALUE + 1),
      List(-1, -1, 0),
      List(0, 0, 0),
      List(1, 1, 0),
      List(Integer.MAX_VALUE, 1, Integer.MAX_VALUE - 1))

  test("test_safeSubtractInt") {
    safeSubtractIntProvider.foreach {
      case (a: Int) :: (b: Int) :: (expected: Int) :: Nil =>
        assertEquals(Math.subtractExact(a, b), expected)
      case _ =>
        fail()
    }
  }

  val safeSubtractIntProviderOverflow: List[List[Int]] =
    List(
      List(Integer.MIN_VALUE, 1),
      List(Integer.MIN_VALUE + 1, 2),
      List(Integer.MAX_VALUE - 1, -2),
      List(Integer.MAX_VALUE, -1))

  test("test_safeSubtractInt_overflow") {
    safeSubtractIntProviderOverflow.foreach {
      case (a: Int) :: (b: Int) :: Nil =>
        assertThrows[ArithmeticException] {
          Math.subtractExact(a, b)
        }
      case _ =>
        fail()
    }
  }

  val safeSubtractLongProvider: List[List[Long]] =
    List(
      List(Long.MinValue, -1L, Long.MinValue + 1),
      List(-1L, -1L, 0L),
      List(0L, 0L, 0L),
      List(1L, 1L, 0L),
      List(Long.MaxValue, 1L, Long.MaxValue - 1))

  test("test_safeSubtractLong") {
    safeSubtractLongProvider.foreach {
      case (a: Long) :: (b: Long) :: (expected: Long) :: Nil =>
        assertEquals(Math.subtractExact(a, b), expected)
      case _ =>
        fail()
    }
  }

  val safeSubtractLongProviderOverflow: List[List[Long]] =
    List(
      List(Long.MinValue, 1),
      List(Long.MinValue + 1, 2),
      List(Long.MaxValue - 1, -2),
      List(Long.MaxValue, -1))

  test("test_safeSubtractLong_overflow") {
    safeSubtractLongProviderOverflow.foreach {
      case (a: Long) :: (b: Long) :: Nil =>
        assertThrows[ArithmeticException] {
          Math.subtractExact(a, b)
        }
      case _ =>
        fail()
    }
  }

  val safeMultiplyIntProvider: List[List[Int]] =
    List(
      List(Integer.MIN_VALUE, 1, Integer.MIN_VALUE),
      List(Integer.MIN_VALUE / 2, 2, Integer.MIN_VALUE),
      List(-1, -1, 1),
      List(-1, 1, -1),
      List(0, -1, 0),
      List(0, 0, 0),
      List(0, 1, 0),
      List(1, -1, -1),
      List(1, 1, 1),
      List(Integer.MAX_VALUE / 2, 2, Integer.MAX_VALUE - 1),
      List(Integer.MAX_VALUE, -1, Integer.MIN_VALUE + 1))

  test("test_safeMultiplyInt") {
    safeMultiplyIntProvider.foreach {
      case (a: Int) :: (b: Int) :: (expected: Int) :: Nil =>
        assertEquals(Math.multiplyExact(a, b), expected)
      case _ =>
        fail()
    }
  }

  val safeMultiplyIntProviderOverflow: List[List[Int]] =
    List(
      List(Integer.MIN_VALUE, 2),
      List(Integer.MIN_VALUE / 2 - 1, 2),
      List(Integer.MAX_VALUE, 2),
      List(Integer.MAX_VALUE / 2 + 1, 2),
      List(Integer.MIN_VALUE, -1),
      List(-1, Integer.MIN_VALUE))

  test("test_safeMultiplyInt_overflow") {
    safeMultiplyIntProviderOverflow.foreach {
      case (a: Int) :: (b: Int) :: Nil =>
        assertThrows[ArithmeticException] {
          Math.multiplyExact(a, b)
        }
      case _ =>
        fail()
    }
  }

  val safeMultiplyLongProvider: List[List[Long]] =
    List(
      List(Long.MinValue, 1, Long.MinValue),
      List(Long.MinValue / 2, 2, Long.MinValue),
      List(-1, -1, 1),
      List(-1, 1, -1),
      List(0, -1, 0),
      List(0, 0, 0),
      List(0, 1, 0),
      List(1, -1, -1),
      List(1, 1, 1),
      List(Long.MaxValue / 2, 2, Long.MaxValue - 1),
      List(Long.MaxValue, -1, Long.MinValue + 1),
      List(-1, Integer.MIN_VALUE, -Integer.MIN_VALUE.toLong))

  test("test_safeMultiplyLong") {
    safeMultiplyLongProvider.foreach {
      case (a: Long) :: (b: Long) :: (expected: Long) :: Nil =>
        assertEquals(Math.multiplyExact(a, b), expected)
      case _ =>
        fail()
    }
  }

  val safeMultiplyLongProviderOverflow: List[List[Any]] =
    List(
      List(Long.MinValue, 2),
      List(Long.MinValue / 2 - 1, 2),
      List(Long.MaxValue, 2),
      List(Long.MaxValue / 2 + 1, 2),
      List(Long.MinValue, -1))

  test("test_safeMultiplyLong_overflow") {
    safeMultiplyLongProviderOverflow.foreach {
      case (a: Long) :: (b: Long) :: Nil =>
        assertThrows[ArithmeticException] {
          Math.multiplyExact(a, b)
        }
      case _ =>
        fail()
    }
  }

  val safeMultiplyLongLongProvider: List[List[Long]] =
    List(
      List(Long.MinValue, 1, Long.MinValue),
      List(Long.MinValue / 2, 2, Long.MinValue),
      List(-1, -1, 1),
      List(-1, 1, -1),
      List(0, -1, 0),
      List(0, 0, 0),
      List(0, 1, 0),
      List(1, -1, -1),
      List(1, 1, 1),
      List(Long.MaxValue / 2, 2, Long.MaxValue - 1),
      List(Long.MaxValue, -1, Long.MinValue + 1))

  test("test_safeMultiplyLongLong") {
    safeMultiplyLongLongProvider.foreach {
      case (a: Long) :: (b: Long) :: (expected: Long) :: Nil =>
        assertEquals(Math.multiplyExact(a, b), expected)
      case _ =>
        fail()
    }
  }

  val safeMultiplyLongLongProviderOverflow: List[List[Long]] =
    List(
      List(Long.MinValue, 2),
      List(Long.MinValue / 2 - 1, 2),
      List(Long.MaxValue, 2),
      List(Long.MaxValue / 2 + 1, 2),
      List(Long.MinValue, -1),
      List(-1, Long.MinValue))

  test("test_safeMultiplyLongLong_overflow") {
    safeMultiplyLongLongProviderOverflow.foreach {
      case (a: Long) :: (b: Long) :: Nil =>
        assertThrows[ArithmeticException] {
          Math.multiplyExact(a, b)
        }
      case _ =>
        fail()
    }
  }

  val safeToIntProvider: List[Int] =
      List(
        Integer.MIN_VALUE,
        Integer.MIN_VALUE + 1,
        -1,
        0,
        1,
        Integer.MAX_VALUE - 1,
        Integer.MAX_VALUE)

  test("test_safeToInt") {
    safeToIntProvider.foreach { l =>
      assertEquals(Math.toIntExact(l), l)
    }
  }

  val safeToIntProviderOverflow: List[Long] =
    List(
      Long.MinValue,
      Integer.MIN_VALUE - 1L,
      Integer.MAX_VALUE + 1L,
      Long.MaxValue)

  test("test_safeToInt_overflow") {
    safeToIntProviderOverflow.foreach { l =>
      assertThrows[ArithmeticException] {
        Math.toIntExact(l)
      }
    }
  }

  test("test_safeCompare_int") {
    doTest_safeCompare_int(Integer.MIN_VALUE, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2, -2, -1, 0, 1, 2, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE)
  }

  private def doTest_safeCompare_int(values: Int*): Unit = {
    var i: Int = 0
    while (i < values.length) {
      val a: Int = values(i)
      var j: Int = 0
      while (j < values.length) {
        val b: Int = values(j)
        assertEquals(Integer.compare(a, b), if (a < b) -1 else if (a > b) 1 else 0, a + " <=> " + b)
        j += 1
      }
      i += 1
    }
  }

  test("test_safeCompare_long") {
    doTest_safeCompare_long(Long.MinValue, Long.MinValue + 1, Long.MinValue + 2, Integer.MIN_VALUE, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2, -2, -1, 0, 1, 2, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE, Long.MaxValue - 2, Long.MaxValue - 1, Long.MaxValue)
  }

  private def doTest_safeCompare_long(values: Long*): Unit = {
    var i: Int = 0
    while (i < values.length) {
      val a: Long = values(i)
      var j: Int = 0
      while (j < values.length) {
        val b: Long = values(j)
        assertEquals(java.lang.Long.compare(a, b), if (a < b) -1 else if (a > b) 1 else 0, a + " <=> " + b)
        j += 1
      }
      i += 1
    }
  }

  val data_floorDiv: List[List[_ <: Any]] =
    List(
      List(5L, 4, 1L),
      List(4L, 4, 1L),
      List(3L, 4, 0L),
      List(2L, 4, 0L),
      List(1L, 4, 0L),
      List(0L, 4, 0L),
      List(-1L, 4, -1L),
      List(-2L, 4, -1L),
      List(-3L, 4, -1L),
      List(-4L, 4, -1L),
      List(-5L, 4, -2L))

  test("test_floorDiv_long") {
    data_floorDiv.foreach {
      case (a: Long) :: (b: Long) :: (expected: Long) :: Nil =>
        assertEquals(Math.floorDiv(a, b), expected)
      case _ =>
        fail()
    }
  }

  test("test_floorDiv_int") {
    data_floorDiv.foreach {
      case (a: Long) :: (b: Long) :: (expected: Long) :: Nil =>
        if (a <= Integer.MAX_VALUE && a >= Integer.MIN_VALUE) {
          assertEquals(Math.floorDiv(a.toInt, b), expected.toInt)
        }
      case _ =>
        fail()
    }
  }

  val data_floorMod: List[List[_ <: Any]] =
    List(
      List(5L, 4, 1),
      List(4L, 4, 0),
      List(3L, 4, 3),
      List(2L, 4, 2),
      List(1L, 4, 1),
      List(0L, 4, 0),
      List(-1L, 4, 3),
      List(-2L, 4, 2),
      List(-3L, 4, 1),
      List(-4L, 4, 0),
      List(-5L, 4, 3))

  test("test_floorMod_long") {
    data_floorMod.foreach {
      case (a: Long) :: (b: Long) :: (expected: Long) :: Nil =>
        assertEquals(Math.floorMod(a, b), expected)
      case _ =>
        fail()
    }
  }

  test("test_floorMod_int") {
    data_floorMod.foreach {
      case (a: Long) :: (b: Long) :: (expected: Long) :: Nil =>
        if (a <= Integer.MAX_VALUE && a >= Integer.MIN_VALUE) {
          assertEquals(Math.floorMod(a.toInt, b), expected)
        }
      case _ =>
        fail()
    }
  }
}
