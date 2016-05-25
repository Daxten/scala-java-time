package scalajs.testsuite.javalibex

import java.util.Locale

import org.junit.{Before, Test}
import org.junit.Assert._

import scala.scalajs.locale.LocaleRegistry
import scalajs.testsuite.utils.AssertThrows.expectThrows
import scalajs.testsuite.utils.Platform

class LocaleTest {
  @Before def reset(): Unit = {
    // Ensure no locale has been installed
    LocaleRegistry.resetRegistry()
  }

  @Test def test_null_constructor(): Unit = {
    expectThrows(classOf[NullPointerException], new Locale(null))
    expectThrows(classOf[NullPointerException], new Locale("", null))
    expectThrows(classOf[NullPointerException], new Locale("", "", null))
  }

  @Test def test_constructor(): Unit = {
    assertEquals("en", new Locale("en", "US").getLanguage)
    assertEquals("US", new Locale("en", "US").getCountry)
    assertEquals("POSIX", new Locale("en", "US", "POSIX").getVariant)

    // No syntactic checks
    assertEquals("abcdef", new Locale("ABCDEF", "longcountryname").getLanguage)
    assertEquals("LONGCOUNTRYNAME",
      new Locale("abcdef", "longcountryname").getCountry)
  }

  @Test def test_special_cases(): Unit = {
    assertEquals("ja", new Locale("ja", "JP", "JP").getLanguage)
    assertEquals("JP", new Locale("ja", "JP", "JP").getCountry)
    assertEquals("JP", new Locale("ja", "JP", "JP").getVariant)
    assertEquals("ca-japanese", new Locale("ja", "JP", "JP").getExtension('u'))

    assertEquals("th", new Locale("th", "TH", "TH").getLanguage)
    assertEquals("TH", new Locale("th", "TH", "TH").getCountry)
    assertEquals("TH", new Locale("th", "TH", "TH").getVariant)
    assertEquals("nu-thai", new Locale("th", "TH", "TH").getExtension('u'))
  }

  @Test def test_default_ENGLISH(): Unit = {
    assertEquals("en", Locale.forLanguageTag("en").getLanguage)
    assertEquals("", Locale.forLanguageTag("en").getCountry)
    assertEquals("", Locale.forLanguageTag("en").getVariant)
    assertEquals("", Locale.forLanguageTag("en").getScript)

    assertEquals(Locale.ENGLISH, Locale.forLanguageTag("en"))
  }

  @Test def test_default_FRENCH(): Unit = {
    assertEquals("fr", Locale.forLanguageTag("fr").getLanguage)
    assertEquals("", Locale.forLanguageTag("fr").getCountry)
    assertEquals("", Locale.forLanguageTag("fr").getVariant)
    assertEquals("", Locale.forLanguageTag("fr").getScript)

    assertEquals(Locale.FRENCH, Locale.forLanguageTag("fr"))
  }

  @Test def test_default_GERMAN(): Unit = {
    assertEquals("de", Locale.forLanguageTag("de").getLanguage)
    assertEquals("", Locale.forLanguageTag("de").getCountry)
    assertEquals("", Locale.forLanguageTag("de").getVariant)
    assertEquals("", Locale.forLanguageTag("de").getScript)

    assertEquals(Locale.GERMAN, Locale.forLanguageTag("de"))
  }

  @Test def test_default_ITALIAN(): Unit = {
    assertEquals("it", Locale.forLanguageTag("it").getLanguage)
    assertEquals("", Locale.forLanguageTag("it").getCountry)
    assertEquals("", Locale.forLanguageTag("it").getVariant)
    assertEquals("", Locale.forLanguageTag("it").getScript)

    assertEquals(Locale.ITALIAN, Locale.forLanguageTag("it"))
  }

  @Test def test_default_JAPANESE(): Unit = {
    assertEquals("ja", Locale.forLanguageTag("ja").getLanguage)
    assertEquals("", Locale.forLanguageTag("ja").getCountry)
    assertEquals("", Locale.forLanguageTag("ja").getVariant)
    assertEquals("", Locale.forLanguageTag("ja").getScript)

    assertEquals(Locale.JAPANESE, Locale.forLanguageTag("ja"))
  }

  @Test def test_default_KOREAN(): Unit = {
    assertEquals("ko", Locale.forLanguageTag("ko").getLanguage)
    assertEquals("", Locale.forLanguageTag("ko").getCountry)
    assertEquals("", Locale.forLanguageTag("ko").getVariant)
    assertEquals("", Locale.forLanguageTag("ko").getScript)

    assertEquals(Locale.KOREAN, Locale.forLanguageTag("ko"))
  }

  @Test def test_default_CHINESE(): Unit = {
    assertEquals("zh", Locale.forLanguageTag("zh").getLanguage)
    assertEquals("", Locale.forLanguageTag("zh").getCountry)
    assertEquals("", Locale.forLanguageTag("zh").getVariant)
    assertEquals("", Locale.forLanguageTag("zh").getScript)

    assertEquals(Locale.CHINESE, Locale.forLanguageTag("zh"))
  }

  @Test def test_default_SIMPLIFIED_CHINESE(): Unit = {
    assertEquals("zh", Locale.forLanguageTag("zh-CN").getLanguage)
    assertEquals("CN", Locale.forLanguageTag("zh-CN").getCountry)
    assertEquals("", Locale.forLanguageTag("zh-CN").getVariant)
    assertEquals("", Locale.forLanguageTag("zh-CN").getScript)

    assertEquals(Locale.SIMPLIFIED_CHINESE, Locale.forLanguageTag("zh-CN"))
  }

  @Test def test_default_FRANCE(): Unit = {
    assertEquals("fr", Locale.forLanguageTag("fr-FR").getLanguage)
    assertEquals("FR", Locale.forLanguageTag("fr-FR").getCountry)
    assertEquals("", Locale.forLanguageTag("fr-FR").getVariant)
    assertEquals("", Locale.forLanguageTag("fr-FR").getScript)

    assertEquals(Locale.FRANCE, Locale.forLanguageTag("fr-FR"))
  }

  @Test def test_default_GERMANY(): Unit = {
    assertEquals("de", Locale.forLanguageTag("de-DE").getLanguage)
    assertEquals("DE", Locale.forLanguageTag("de-DE").getCountry)
    assertEquals("", Locale.forLanguageTag("de-DE").getVariant)
    assertEquals("", Locale.forLanguageTag("de-DE").getScript)

    assertEquals(Locale.GERMANY, Locale.forLanguageTag("de-DE"))
  }

  @Test def test_default_ITALY(): Unit = {
    assertEquals("it", Locale.forLanguageTag("it-IT").getLanguage)
    assertEquals("IT", Locale.forLanguageTag("it-IT").getCountry)
    assertEquals("", Locale.forLanguageTag("it-IT").getVariant)
    assertEquals("", Locale.forLanguageTag("it-IT").getScript)

    assertEquals(Locale.ITALY, Locale.forLanguageTag("it-IT"))
  }
  @Test def test_default_JAPAN(): Unit = {
    assertEquals("ja", Locale.forLanguageTag("ja-JP").getLanguage)
    assertEquals("JP", Locale.forLanguageTag("ja-JP").getCountry)
    assertEquals("", Locale.forLanguageTag("ja-JP").getVariant)
    assertEquals("", Locale.forLanguageTag("ja-JP").getScript)

    assertEquals(Locale.JAPAN, Locale.forLanguageTag("ja-JP"))
  }

  @Test def test_default_KOREA(): Unit = {
    assertEquals("ko", Locale.forLanguageTag("ko-KR").getLanguage)
    assertEquals("KR", Locale.forLanguageTag("ko-KR").getCountry)
    assertEquals("", Locale.forLanguageTag("ko-KR").getVariant)
    assertEquals("", Locale.forLanguageTag("ko-KR").getScript)

    assertEquals(Locale.KOREA, Locale.forLanguageTag("ko-KR"))
  }

  @Test def test_default_CHINA(): Unit = {
    assertEquals("zh", Locale.forLanguageTag("zh-CN").getLanguage)
    assertEquals("CN", Locale.forLanguageTag("zh-CN").getCountry)
    assertEquals("", Locale.forLanguageTag("zh-CN").getVariant)
    assertEquals("", Locale.forLanguageTag("zh-CN").getScript)

    assertEquals(Locale.CHINA, Locale.forLanguageTag("zh-CN"))
  }

  @Test def test_default_PRC(): Unit = {
    assertEquals("zh", Locale.forLanguageTag("zh-CN").getLanguage)
    assertEquals("CN", Locale.forLanguageTag("zh-CN").getCountry)
    assertEquals("", Locale.forLanguageTag("zh-CN").getVariant)
    assertEquals("", Locale.forLanguageTag("zh-CN").getScript)

    assertEquals(Locale.PRC, Locale.forLanguageTag("zh-CN"))
  }

  @Test def test_default_TAIWAN(): Unit = {
    assertEquals("zh", Locale.forLanguageTag("zh-TW").getLanguage)
    assertEquals("TW", Locale.forLanguageTag("zh-TW").getCountry)
    assertEquals("", Locale.forLanguageTag("zh-TW").getVariant)
    assertEquals("", Locale.forLanguageTag("zh-TW").getScript)

    assertEquals(Locale.TAIWAN, Locale.forLanguageTag("zh-TW"))
    assertEquals(Locale.TRADITIONAL_CHINESE, Locale.forLanguageTag("zh-TW"))
  }

  @Test def test_default_UK(): Unit = {
    assertEquals("en", Locale.forLanguageTag("en-GB").getLanguage)
    assertEquals("GB", Locale.forLanguageTag("en-GB").getCountry)
    assertEquals("", Locale.forLanguageTag("en-GB").getVariant)
    assertEquals("", Locale.forLanguageTag("en-GB").getScript)

    assertEquals(Locale.UK, Locale.forLanguageTag("en-GB"))
  }

  @Test def test_default_US(): Unit = {
    assertEquals("en", Locale.forLanguageTag("en-US").getLanguage)
    assertEquals("US", Locale.forLanguageTag("en-US").getCountry)
    assertEquals("", Locale.forLanguageTag("en-US").getVariant)
    assertEquals("", Locale.forLanguageTag("en-US").getScript)

    assertEquals(Locale.US, Locale.forLanguageTag("en-US"))
  }

  @Test def test_default_CANADA(): Unit = {
    assertEquals("en", Locale.forLanguageTag("en-CA").getLanguage)
    assertEquals("CA", Locale.forLanguageTag("en-CA").getCountry)
    assertEquals("", Locale.forLanguageTag("en-CA").getVariant)
    assertEquals("", Locale.forLanguageTag("en-CA").getScript)

    assertEquals(Locale.CANADA, Locale.forLanguageTag("en-CA"))
  }

  @Test def test_default_CANADA_FRENCH(): Unit = {
    assertEquals("fr", Locale.forLanguageTag("fr-CA").getLanguage)
    assertEquals("CA", Locale.forLanguageTag("fr-CA").getCountry)
    assertEquals("", Locale.forLanguageTag("fr-CA").getVariant)
    assertEquals("", Locale.forLanguageTag("fr-CA").getScript)

    assertEquals(Locale.CANADA_FRENCH, Locale.forLanguageTag("fr-CA"))
  }

  @Test def test_default_ROOT(): Unit = {
    assertEquals("", Locale.forLanguageTag("").getLanguage)
    assertEquals("", Locale.forLanguageTag("").getCountry)
    assertEquals("", Locale.forLanguageTag("").getVariant)
    assertEquals("", Locale.forLanguageTag("").getScript)

    assertEquals(Locale.ROOT, Locale.forLanguageTag(""))
  }

  @Test def test_extension_flags(): Unit = {
    assertTrue('u' == Locale.UNICODE_LOCALE_EXTENSION)
    assertTrue('x' == Locale.PRIVATE_USE_EXTENSION)
  }

  @Test def test_chinese_equivalences(): Unit = {
    assertEquals(Locale.SIMPLIFIED_CHINESE, Locale.CHINA)
    assertEquals(Locale.TRADITIONAL_CHINESE, Locale.TAIWAN)
  }

  // Unlike the JVM, the Js backend cannot give a default locale
  @Test def test_no_default_locale(): Unit = {
    if (!Platform.executingInJVM) {
      expectThrows(classOf[IllegalStateException], Locale.getDefault)
    }
  }

  // Unlike the JVM, the Js backend cannot give a default locale
  @Test def test_no_default_locale_per_category(): Unit = {
    if (!Platform.executingInJVM) {
      expectThrows(classOf[IllegalStateException],
        Locale.getDefault(Locale.Category.DISPLAY))
      expectThrows(classOf[IllegalStateException],
        Locale.getDefault(Locale.Category.FORMAT))
    }
    expectThrows(classOf[NullPointerException], Locale.getDefault(null))
  }

  @Test def test_set_default_locale(): Unit = {
    Locale.setDefault(Locale.CANADA_FRENCH)
    assertEquals(Locale.CANADA_FRENCH, Locale.getDefault)
    // As a side effect this sets the defaults for each category
    assertEquals(Locale.CANADA_FRENCH,
      Locale.getDefault(Locale.Category.DISPLAY))
    assertEquals(Locale.CANADA_FRENCH,
      Locale.getDefault(Locale.Category.FORMAT))

    Locale.setDefault(Locale.Category.DISPLAY, Locale.CHINESE)
    assertEquals(Locale.CANADA_FRENCH, Locale.getDefault)
    assertEquals(Locale.CHINESE, Locale.getDefault(Locale.Category.DISPLAY))
  }

  @Test def test_get_available_locales(): Unit = {
    assertTrue(Locale.getAvailableLocales.contains(Locale.CHINESE))
    assertTrue(Locale.getAvailableLocales.contains(Locale.ENGLISH))
    assertTrue(Locale.getAvailableLocales.contains(Locale.ITALY))
  }

  @Test def test_get_iso_codes(): Unit = {
    // The data from CLDR gives a different amount of countries and
    // languages than the JVM
    val countriesCount = if (Platform.executingInJVM) 250 else 245
    val languagesCount = if (Platform.executingInJVM) 188 else 122
    assertTrue(countriesCount == Locale.getISOCountries.length)
    assertTrue(languagesCount == Locale.getISOLanguages.length)
  }

  @Test def test_has_extensions(): Unit = {
    // You can only add extensions with Locale.Builder
    val b1 = new Locale.Builder()
    val locale = b1.setExtension('a', "ca-japanese").build
    assertTrue(locale.hasExtensions)

    assertFalse(new Locale("en", "US").hasExtensions)
    // Special cases
    assertTrue(new Locale("ja", "JP", "JP").hasExtensions)

    // Unicode extensions
    val b2 = new Locale.Builder()
    val locale2 = b2.setUnicodeLocaleKeyword("nu", "thai").build
    assertTrue(locale2.hasExtensions)
  }

  @Test def test_strip_extensions(): Unit = {
    // You can only add extensions with Locale.Builder
    val b1 = new Locale.Builder()
    val locale = b1.setExtension('a', "ca-japanese").build
    assertFalse(locale.stripExtensions.hasExtensions)

    // Special cases
    assertFalse(new Locale("ja", "JP", "JP").stripExtensions().hasExtensions)
    assertFalse(new Locale("th", "TH", "TH").stripExtensions().hasExtensions)
  }

  @Test def test_to_string(): Unit = {
    // Examples from javadocs
    val l1 = new Locale.Builder().setLanguage("en").build
    assertEquals("en", l1.toString)
    val l2 = new Locale.Builder().setLanguage("de").setRegion("DE").build
    assertEquals("de_DE", l2.toString)
    val l3 = new Locale.Builder().setRegion("GB").build
    assertEquals("_GB", l3.toString)
    val l4 = new Locale("en", "US", "WIN")
    assertEquals("en_US_WIN", l4.toString)
    val l5 = new Locale.Builder().setLanguage("de").setVariant("POSIX").build
    assertEquals("de__POSIX", l5.toString)
    val l6 = new Locale.Builder().setLanguage("zh").setRegion("CN").setScript("Hans").build
    assertEquals("zh_CN_#Hans", l6.toString)
    val l7 = new Locale.Builder().setLanguage("zh").setRegion("TW").setScript("Hant").setExtension('x', "java").build
    assertEquals("zh_TW_#Hant_x-java", l7.toString)
    val l8 = new Locale("th", "TH", "TH")
    assertEquals("th_TH_TH_#u-nu-thai", l8.toString)
    val l9 = new Locale("", "", "POSIX")
    assertEquals("", l9.toString)
  }

  @Test def test_to_language_tag(): Unit = {
    // Examples from javadocs
    val l1 = new Locale.Builder().setLanguage("en").build
    assertEquals("en", l1.toLanguageTag)
    val l2 = new Locale.Builder().setLanguage("de").setRegion("DE").build
    assertEquals("de-DE", l2.toLanguageTag)
    val l3 = new Locale.Builder().setRegion("GB").build
    assertEquals("und-GB", l3.toLanguageTag)
    val l4 = new Locale("en", "US", "WIN")
    assertEquals("en-US-x-lvariant-WIN", l4.toLanguageTag)
    val l5 = new Locale.Builder().setLanguage("de").setVariant("POSIX").build
    assertEquals("de-POSIX", l5.toLanguageTag)
    val l6 = new Locale.Builder().setLanguage("zh").setRegion("CN").setScript("Hans").build
    assertEquals("zh-Hans-CN", l6.toLanguageTag)
    val l7 = new Locale.Builder().setLanguage("zh").setRegion("TW").setScript("Hant").setExtension('x', "java").build
    assertEquals("zh-Hant-TW-x-java", l7.toLanguageTag)
    val l8 = new Locale("th", "TH", "TH")
    assertEquals("th-TH-u-nu-thai-x-lvariant-TH", l8.toLanguageTag)
    val l9 = new Locale("en", "US", "Oracle_JDK_Standard_Edition")
    assertEquals("en-US-Oracle-x-lvariant-JDK-Standard-Edition", l9.toLanguageTag)

  }

}
