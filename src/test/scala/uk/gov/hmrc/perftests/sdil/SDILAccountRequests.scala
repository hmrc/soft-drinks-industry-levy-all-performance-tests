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
import uk.gov.hmrc.perftests.sdil.AuthRequests.{absoluteRedirectTransform, saveCsrfToken}

object SDILAccountRequests extends ServicesConfiguration {

  val baseAccountFrontEndUrl: String  = baseUrlFor("soft-drinks-industry-levy-account-frontend")
  val accountFrontEndRoute: String    = "soft-drinks-industry-levy-account-frontend"
  val baseReturnsFrontEndUrl: String  = baseUrlFor("soft-drinks-industry-levy-returns-frontend")
  val returnsFrontEndRoute: String    = "soft-drinks-industry-levy-returns-frontend"
  val variationsFrontEndRoute: String = "soft-drinks-industry-levy-variations-frontend"

  def getAccountHomePage: HttpRequestBuilder =
    http("GET account-home")
      .get(s"$baseAccountFrontEndUrl/$accountFrontEndRoute": String)
      .check(status.is(200))
      .check(saveCsrfToken())

  def getNextRequest: HttpRequestBuilder =
    http("GET account-home")
      .get(s"$baseAccountFrontEndUrl/$accountFrontEndRoute/start-a-return/nilReturn/:isNilReturn": String)
      .check(status.is(200))
      .check(saveCsrfToken())

  def postAccountHomePageStartReturn: HttpRequestBuilder =
    http(s"POST account-home")
      .post(s"$baseAccountFrontEndUrl/$accountFrontEndRoute/start-a-return": String)
      .formParam("csrfToken", s"#{csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/start-a-return": String))

  def getAccountHomePageStartReturn1: HttpRequestBuilder =
    http(s"GET account-home-start-return")
      .get(s"$baseAccountFrontEndUrl/$accountFrontEndRoute/start-a-return/nilReturn/false": String)
      .check(status.is(303))
      .check(saveCsrfToken())
      .check(header("Location").transform(absoluteRedirectTransform(baseReturnsFrontEndUrl)).saveAs("startReturnUrl"))

  def getAccountHomePageStartReturn2: HttpRequestBuilder =
    http(s"GET account-home-start-return-2")
      .get("#{startReturnUrl}")
      .check(status.is(303))
      .check(saveCsrfToken())

  def postAccountHomePageTellHMRCAboutAChange: HttpRequestBuilder =
    http(s"POST account-home")
      .post(s"$baseAccountFrontEndUrl/$accountFrontEndRoute/make-a-change": String)
      //.formParam("csrfToken", s"#{csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/start-a-return": String))

}
