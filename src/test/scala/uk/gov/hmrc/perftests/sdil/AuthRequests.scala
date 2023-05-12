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
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.regex.RegexCheckType
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object AuthRequests extends BaseRequest {

  val authWizUrl: String = baseUrlFor("auth-login-stub") + "/auth-login-stub/gg-sign-in"
  val authSession: String = baseUrlFor("auth-login-stub") + "/auth-login-stub/session"
  val bearerTokenPattern =
    """<td data-session-id="authToken" style="word-break: break-all">
      |            <code style="border: none">([^"]+)</code>""".stripMargin

  def saveBearerTokenFromBody(): CheckBuilder[RegexCheckType, String, String] = regex(_ => bearerTokenPattern).saveAs("bearerToken")

  lazy val navigateToAuth: HttpRequestBuilder =
    http("Navigate to Auth wizard")
      .get(authWizUrl)
      .check(status.is(200))

  def createAuthSession(utr: String = "0000000437"): HttpRequestBuilder =
    http("Create Auth Session")
      .post(authWizUrl)
      .formParam("redirectionUrl", authSession)
      .formParam("credentialStrength", "strong")
      .formParam("authorityId", "")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Individual")
      .formParam("enrolment[0].name", "IR-CT")
      .formParam("enrolment[0].taxIdentifier[0].name", "UTR")
      .formParam("enrolment[0].taxIdentifier[0].value", utr)
      .formParam("enrolment[0].state", "Activated")
      .check(status.is(303))

  lazy val navigateToAuthSession: HttpRequestBuilder =
    http("Navigate to Auth Session page")
      .get(authSession)
      .check(status.is(200))
      .check(saveBearerTokenFromBody())
}
