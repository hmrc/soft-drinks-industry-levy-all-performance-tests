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
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.sdil.AuthRequests.saveCsrfToken

import java.time.LocalDate

object SDILRegistrationRequests extends ServicesConfiguration {

  val baseFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-registration-frontend")
  val frontEndRoute: String = "soft-drinks-industry-levy-registration"

  def getPage(url: String): HttpRequestBuilder = {
    http(s"GET $url")
      .get(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .check(status.is(200))
      .check(saveCsrfToken())
  }

  def getStartPage(redirectUrl: String = "/verify"): HttpRequestBuilder = {
    http(s"GET start")
      .get(s"$baseFrontEndUrl/$frontEndRoute/start": String)
      .check(status.is(303))
      .check(saveCsrfToken())
      .check(header("Location").is(s"/$frontEndRoute$redirectUrl": String))
  }

  def postFormlessPage(url: String, redirectUrl: String = ""): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute$redirectUrl": String))
  }

  def postPage(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", body)
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute$redirectUrl": String))
  }

  def postPageRedirectToAddressLookup(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder = {
    println("here1")
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", body)
      .check(status.is(303))
      .check(headerRegex("Location", s"(.*)$frontEndRoute/off-ramp$redirectUrl(.*)": String))
      .check(header("Location").saveAs("addressOffRampUrl"))
  }

  def getPackagingSiteNamePage(): HttpRequestBuilder = {
    println("here2")
    http(s"POST packaging-site-name")
      .get(s"$baseFrontEndUrl/$frontEndRoute/packaging-site-name": String)
      .check(status.is(303))
      .check(saveCsrfToken())
  }

  def postPackagingSiteNamePage(redirectUrl: String = ""): HttpRequestBuilder = {
    println("here3")
    http(s"POST packaging-site-name")
      .post(s"$baseFrontEndUrl/$frontEndRoute/packaging-site-name": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("packagingSiteName", "pSiteName")
      .check(status.is(303))
      .check(headerRegex("Location", s"(.*)$frontEndRoute/off-ramp$redirectUrl(.*)": String))
  }

  def getAddressRampOffPage(redirectUrl: String): HttpRequestBuilder = {
    http(s"GET $${addressOffRampUrl}")
      .get(s"$${addressOffRampUrl}": String)
      .check(status.is(303))
      .check(saveCsrfToken())
      .check(header("Location").is(s"/$frontEndRoute$redirectUrl": String))
  }

  def postLitresPage(url: String, redirectUrl: String = ""): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute$redirectUrl": String))
  }

  def postContactDetailsPage(redirectUrl: String = ""): HttpRequestBuilder = {
    http("POST contact-details")
      .post(s"$baseFrontEndUrl/$frontEndRoute/contact-details": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("fullName", "Some Name")
      .formParam("position", "Manager")
      .formParam("phoneNumber", "01234567890")
      .formParam("email", "example@sample.com")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute$redirectUrl": String))
  }

  def postStartDatePage(redirectUrl: String): HttpRequestBuilder = {
    http("POST start-date")
      .post(s"$baseFrontEndUrl/$frontEndRoute/start-date": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("startDate.day", (LocalDate.now().minusDays(2).getDayOfMonth).toString)
      .formParam("startDate.month", (LocalDate.now().minusDays(2).getMonthValue).toString)
      .formParam("startDate.year", (LocalDate.now().minusDays(2).getYear).toString)
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute$redirectUrl": String))
  }

  def postCheckYourAnswersPage: HttpRequestBuilder = {
    http("POST check-your-anwers")
      .post(s"$baseFrontEndUrl/$frontEndRoute/check-your-answers": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/registration-confirmation": String))
  }

  def getRegisterConfirmationPage: HttpRequestBuilder = {
    http("GET return-sent")
      .get(s"$baseFrontEndUrl/$frontEndRoute/registration-confirmation": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }
}
