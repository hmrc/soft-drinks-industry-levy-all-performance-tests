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

object SDILVariationsRequests extends ServicesConfiguration {

  val baseFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-variations-frontend")
  val frontEndRoute: String = "soft-drinks-industry-levy-variations-frontend"

  def getPage(url: String): HttpRequestBuilder = {
    http(s"GET $url")
      .get(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .check(status.is(200))
      .check(saveCsrfToken())
  }

  def postFormlessPage(url: String, redirectUrl: String = ""): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/$redirectUrl": String))
  }

  def postPage(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", body)
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/$redirectUrl": String))
  }

  def postPage2(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("id", body)
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/$redirectUrl": String))
  }

  def postPackagingSiteDetailsPage(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", body)
      .check(status.is(303))
  }

  def postPageRedirectToAddressLookup(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", body)
      .check(status.is(303))
      .check(headerRegex("Location", s"(.*)$frontEndRoute/off-ramp/$redirectUrl(.*)": String))
  }

  def postChangeContactDetailsOnlyPage(url: String): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value[1]", "contactDetails")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/change-registered-details/contact-details-add": String))
  }

  def postChangeSitesOnlyPage(url: String): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value[0]", "sites")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/change-registered-details/packaging-site-details": String))
  }

  def postChangeBusinessDetailsOnlyPage(url: String): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value[2]", "businessDetails")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/change-registered-details/": String))
  }

  def postChangeAllPage(url: String): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value[0]", "sites")
      .formParam("value[1]", "contactDetails")
      .formParam("value[2]", "businessDetails")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/change-registered-details/": String))
  }

  def postLitresPage(url: String, redirectUrl: String = "", highBand: String = "100", lowBand: String = "100"): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("litres.lowBand", lowBand)
      .formParam("litres.highBand", highBand)
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/$redirectUrl": String))
  }

  def postContactDetailsAddPage(redirectUrl: String = ""): HttpRequestBuilder = {
    http("POST change-registered-details/contact-details-add")
      .post(s"$baseFrontEndUrl/$frontEndRoute/change-registered-details/contact-details-add": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("fullName", "Some Name")
      .formParam("position", "Manager")
      .formParam("phoneNumber", "01234567890")
      .formParam("email", "example@sample.com")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/$redirectUrl": String))
  }

  def postCancelDatePage: HttpRequestBuilder = {
    http("POST cancel-registration/date")
      .post(s"$baseFrontEndUrl/$frontEndRoute/cancel-registration/date": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("cancelRegistrationDate.day", (LocalDate.now().plusDays(2).getDayOfMonth).toString)
      .formParam("cancelRegistrationDate.month", (LocalDate.now().plusDays(2).getMonthValue).toString)
      .formParam("cancelRegistrationDate.year", (LocalDate.now().plusDays(2).getYear).toString)
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/cancel-registration/check-your-answers": String))
  }

  def postAddSmallProducerPage: HttpRequestBuilder = {
    http("POST add-small-producer")
      .post(s"$baseFrontEndUrl/$frontEndRoute/correct-return/add-small-producer": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("producerName", "Fake Producer")
      .formParam("referenceNumber", "XZSDIL000000234")
      .formParam("litres.lowBand", "1000")
      .formParam("litres.highBand", "1000")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/correct-return/small-producer-details": String))
  }

}
