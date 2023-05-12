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

object SetupRequests extends BaseRequest {

  val baseBackendUrl: String = baseUrlFor("soft-drinks-industry-levy")
  val backendRoute: String = "soft-drinks-industry-levy/test-only"
  val baseFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-returns-frontend")
  val frontEndRoute: String = "soft-drinks-industry-levy-returns-frontend"

  def resetAll: HttpRequestBuilder = {
    resetPending
    resetReturns
    resetRegistrations
    sdilReturnsCollectionReset
    resetUserAnswers()
  }

  def resetPending: HttpRequestBuilder = {
    http("GET reset-pending")
      .get(s"$baseBackendUrl/$backendRoute/reset-pending": String)
  }

  def resetReturns: HttpRequestBuilder = {
    http("GET reset-returns")
      .get(s"$baseBackendUrl/$backendRoute/reset-returns": String)
  }

  def resetRegistrations: HttpRequestBuilder = {
    http("GET reset-registrations")
      .get(s"$baseBackendUrl/$backendRoute/reset-registrations": String)
  }

  def sdilReturnsCollectionReset: HttpRequestBuilder = {
    http("GET sdilReturnsCollectionReset")
      .get(s"$baseBackendUrl/$backendRoute/sdilReturnsCollectionReset": String)
  }

  def resetUserAnswers(sdilRef: String = "XGSDIL000000437"): HttpRequestBuilder = {
    http(s"DELETE user-answers $sdilRef")
      .get(s"$baseFrontEndUrl/$frontEndRoute/test-only/user-answers/$sdilRef": String)
  }

}
