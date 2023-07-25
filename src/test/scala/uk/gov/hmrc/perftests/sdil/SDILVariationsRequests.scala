/*
 * Copyright 2023 HM Revenue & Customs
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

object SDILVariationsRequests extends ServicesConfiguration {

  val baseFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-variations-frontend")
  val frontEndRoute: String = "soft-drinks-industry-levy-variations-frontend"

  def getPage(url: String): HttpRequestBuilder = {
    http(s"GET $url")
      .get(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .check(status.is(200))
      .check(saveCsrfToken())
  }

  def postPage(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder = {
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", body)
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute$redirectUrl": String))
  }

  def postContactDetailsAddPage: HttpRequestBuilder = {
    http("POST change-registered-details/contact-details-add")
      .post(s"$baseFrontEndUrl/$frontEndRoute/change-registered-details/contact-details-add": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("fullName", "Some Name")
      .formParam("position", "Manager")
      .formParam("phoneNumber", "01234567890")
      .formParam("email", "example@sample.com")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute": String))
  }

}
