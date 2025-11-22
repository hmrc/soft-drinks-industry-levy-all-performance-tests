/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.sdil

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.regex.RegexCheckType
import io.gatling.http.Predef._
import io.gatling.http.check.header.HttpHeaderRegexCheckType
import io.gatling.core.session.Expression
import io.gatling.core.session.el.El
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait BaseRequest extends ServicesConfiguration {

  val SDILReturnsBaseUrl: String     = baseUrlFor("soft-drinks-industry-levy-returns-frontend")
  val submitVatReturnBaseUrl: String = baseUrlFor("soft-drinks-industry-levy-returns-frontend")

  val csrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  protected def elStringExpr(value: String): Expression[String]        = value.el[String]
  protected def elAnyExpr(value: String): Expression[Any]              = value.el[Any]
  protected def elCharSeqExpr(value: String): Expression[CharSequence] = value.el[CharSequence]

  protected val csrfTokenExpr: Expression[String]                 = session =>
    session("csrfToken").asOption[String].getOrElse("")
  protected val locationHeaderExpr: Expression[CharSequence]      = elCharSeqExpr("Location")
  protected val setCookieHeaderExpr: Expression[CharSequence]     = elCharSeqExpr("Set-Cookie")
  protected val redirectionUrlHeaderExpr: Expression[CharSequence] = elCharSeqExpr("RedirectionUrl")

  def saveCsrfToken(): CheckBuilder[RegexCheckType, String] =
    regex(_ => csrfPattern).optional.saveAs("csrfToken")

  def saveCookie(): CheckBuilder[HttpHeaderRegexCheckType, Response] =
    headerRegex(setCookieHeaderExpr, elStringExpr("mdtp=(.*)")).optional.saveAs("cookie")

  def absoluteRedirectTransform(baseUrl: String): String => String = { (redirectUrl: String) =>
    if (redirectUrl.startsWith("/")) {
      baseUrl + redirectUrl
    } else {
      redirectUrl
    }
  }

  def hasPackagingSites(redirectUrl: String): Boolean =
    redirectUrl.endsWith("/packaging-site-details")
}
