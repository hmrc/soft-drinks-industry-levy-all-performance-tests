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
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import java.time.LocalDate

object SDILRegistrationRequests extends BaseRequest {

  val baseFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-registration-frontend")
  val frontEndRoute: String   = "soft-drinks-industry-levy-registration"

  def getPage(url: String): HttpRequestBuilder =
    http(s"GET $url")
      .get(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .check(status.is(200))
      .check(saveCsrfToken())

  def getStartPage(redirectUrl: String = "/verify"): HttpRequestBuilder =
    http(s"GET start")
      .get(s"$baseFrontEndUrl/$frontEndRoute/start")
      .check(status.is(303))
      .check(saveCsrfToken())
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute$redirectUrl")))

  def postFormlessPage(url: String, redirectUrl: String = ""): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute$redirectUrl")))

  def postPage(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr(body))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute$redirectUrl")))

  def postPageRedirectToAddressLookup(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr(body))
      .check(status.is(303))
      .check(headerRegex(locationHeaderExpr, elStringExpr(s"(.*)$frontEndRoute/off-ramp$redirectUrl(.*)")))
      .check(header(locationHeaderExpr).saveAs("addressOffRampUrl"))

  def getPackagingSiteNamePage: HttpRequestBuilder =
    http(s"POST packaging-site-name")
      .get(s"$baseFrontEndUrl/$frontEndRoute/packaging-site-name")
      .check(status.is(303))
      .check(saveCsrfToken())

  def postPackagingSiteNamePage(redirectUrl: String = ""): HttpRequestBuilder = {
    println("here3")
    http(s"POST packaging-site-name")
      .post(s"$baseFrontEndUrl/$frontEndRoute/packaging-site-name")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("packagingSiteName", elAnyExpr("pSiteName"))
      .check(status.is(303))
      .check(headerRegex(locationHeaderExpr, elStringExpr(s"(.*)$frontEndRoute/off-ramp$redirectUrl(.*)")))
  }

  def getAddressRampOffPage(redirectUrl: String): HttpRequestBuilder =
    http(s"GET #{addressOffRampUrl}")
      .get(s"#{addressOffRampUrl}")
      .check(status.is(303))
      .check(saveCsrfToken())
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute$redirectUrl")))

  def postLitresPage(url: String, redirectUrl: String = ""): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("lowBand", elAnyExpr("100"))
      .formParam("highBand", elAnyExpr("100"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute$redirectUrl")))

  def postContactDetailsPage(redirectUrl: String = ""): HttpRequestBuilder =
    http("POST contact-details")
      .post(s"$baseFrontEndUrl/$frontEndRoute/contact-details")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("fullName", elAnyExpr("Some Name"))
      .formParam("position", elAnyExpr("Manager"))
      .formParam("phoneNumber", elAnyExpr("01234567890"))
      .formParam("email", elAnyExpr("example@sample.com"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute$redirectUrl")))

  def postStartDatePage(redirectUrl: String): HttpRequestBuilder = {
    val fourDaysAgo = LocalDate.now().minusDays(10)
    val startDay    = fourDaysAgo.getDayOfMonth.toString
    val startMonth  = fourDaysAgo.getMonthValue.toString
    val startYear   = fourDaysAgo.getYear.toString

    http("POST start-date")
      .post(s"$baseFrontEndUrl/$frontEndRoute/start-date")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("startDate.day", elAnyExpr(startDay))
      .formParam("startDate.month", elAnyExpr(startMonth))
      .formParam("startDate.year", elAnyExpr(startYear))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute$redirectUrl")))
  }

  def postCheckYourAnswersPage: HttpRequestBuilder =
    http("POST check-your-anwers")
      .post(s"$baseFrontEndUrl/$frontEndRoute/check-your-answers")
      .formParam("csrfToken", csrfTokenExpr)
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/registration-confirmation")))

  def getRegisterConfirmationPage: HttpRequestBuilder =
    http("GET return-sent")
      .get(s"$baseFrontEndUrl/$frontEndRoute/registration-confirmation")
      .check(saveCsrfToken())
      .check(status.is(200))
}
