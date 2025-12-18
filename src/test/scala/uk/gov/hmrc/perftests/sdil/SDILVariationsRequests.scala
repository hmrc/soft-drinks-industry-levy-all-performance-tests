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

object SDILVariationsRequests extends BaseRequest {

  val baseFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-variations-frontend")
  val frontEndRoute: String   = "soft-drinks-industry-levy-variations-frontend"

  def getPage(url: String): HttpRequestBuilder =
    http(s"GET $url")
      .get(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postFormlessPage(url: String, redirectUrl: String = ""): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/$redirectUrl")))

  def postPage(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr(body))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/$redirectUrl")))

  def postPage2(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("id", elAnyExpr(body))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/$redirectUrl")))

  def postPackagingSiteDetailsPage(url: String, body: String): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr(body))
      .check(status.is(303))

  def postPageRedirectToAddressLookup(url: String, body: String, redirectUrl: String = ""): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr(body))
      .check(status.is(303))
      .check(headerRegex(locationHeaderExpr, elStringExpr(s"(.*)$frontEndRoute/off-ramp/$redirectUrl(.*)")))

  def postChangeContactDetailsOnlyPage(url: String): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value[1]", elAnyExpr("contactDetails"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/change-registered-details/contact-details-add")))

  def getAddPackingSiteRequests: Seq[HttpRequestBuilder] =
    if (hasPackagingSites(s"#changeSitesUrl")) {
      Seq(
        getPage("change-registered-details/packaging-site-details"),
        postPackagingSiteDetailsPage("change-registered-details/packaging-site-details", "true"),
        getPage("change-registered-details/packaging-site-details"),
        postPage(
          "change-registered-details/packaging-site-details",
          "false",
          "change-registered-details/warehouse-details"
        )
      )
    } else Seq.empty

  def getRemovePackingSiteRequests: Seq[HttpRequestBuilder] =
    if (hasPackagingSites(s"#changeSitesUrl")) {
      Seq(
        getPage("change-registered-details/packaging-site-details"),
        postPackagingSiteDetailsPage("change-registered-details/packaging-site-details", "true"),
        getPage("change-registered-details/packaging-site-details"),
        getPage("change-registered-details/packaging-site-details/remove/0"),
        postPackagingSiteDetailsPage("change-registered-details/packaging-site-details/remove/0", "true"),
        getPage("change-registered-details/packaging-site-details"),
        postPage(
          "change-registered-details/packaging-site-details",
          "false",
          "change-registered-details/warehouse-details"
        )
      )
    } else {
      Seq.empty
    }

  def postChangeSitesOnlyPage(url: String): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value[0]", elAnyExpr("sites"))
      .check(status.is(303))
      .check(
        header(locationHeaderExpr).in(
          s"/$frontEndRoute/change-registered-details/packaging-site-details": String,
          s"/$frontEndRoute/change-registered-details/warehouse-details": String
        )
      )
      .check(header(locationHeaderExpr).saveAs("changeSitesUrl"))

  def postChangeBusinessDetailsOnlyPage(url: String): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value[2]", elAnyExpr("businessDetails"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/change-registered-details/")))

  def postChangeAllPage(url: String): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value[0]", elAnyExpr("sites"))
      .formParam("value[1]", elAnyExpr("contactDetails"))
      .formParam("value[2]", elAnyExpr("businessDetails"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/change-registered-details/")))

  def postLitresPage(
    url: String,
    redirectUrl: String = "",
    highBand: String = "100",
    lowBand: String = "100"
  ): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("litres.lowBand", elAnyExpr(lowBand))
      .formParam("litres.highBand", elAnyExpr(highBand))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/$redirectUrl")))

  def postLitresPageNxtPagePackagingSites(
    url: String,
    highBand: String = "100",
    lowBand: String = "100"
  ): HttpRequestBuilder =
    http(s"POST $url")
      .post(s"$baseFrontEndUrl/$frontEndRoute/$url")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("litres.lowBand", elAnyExpr(lowBand))
      .formParam("litres.highBand", elAnyExpr(highBand))
      .check(status.is(303))
      .check(
        header(locationHeaderExpr).in(
          s"/$frontEndRoute/change-activity/packaging-site-details": String,
          s"/$frontEndRoute/change-activity/pack-at-business-address": String
        )
      )
      .check(header(locationHeaderExpr).saveAs("updatePackagingSitesUrl"))

  def getAddPackingSiteIfRequiredOrNoUpdateRequests: Seq[HttpRequestBuilder] =
    if (hasPackagingSites(s"#updatePackagingSitesUrl")) {
      Seq(
        getPage("change-activity/packaging-site-details"),
        postPage("change-activity/packaging-site-details", "false", "change-activity/secondary-warehouse-details")
      )
    } else
      Seq(
        getPage("change-activity/pack-at-business-address"),
        postPage("change-activity/pack-at-business-address", "true", "change-activity/packaging-site-details"),
        getPage("change-activity/packaging-site-details"),
        postPage("change-activity/packaging-site-details", "false", "change-activity/secondary-warehouse-details")
      )

  def postContactDetailsAddPage(redirectUrl: String = ""): HttpRequestBuilder =
    http("POST change-registered-details/contact-details-add")
      .post(s"$baseFrontEndUrl/$frontEndRoute/change-registered-details/contact-details-add")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("fullName", elAnyExpr("Some Name"))
      .formParam("position", elAnyExpr("Manager"))
      .formParam("phoneNumber", elAnyExpr("01234567890"))
      .formParam("email", elAnyExpr("example@sample.com"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/$redirectUrl")))

  def postCancelDatePage: HttpRequestBuilder =
    http("POST cancel-registration/date")
      .post(s"$baseFrontEndUrl/$frontEndRoute/cancel-registration/date")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("cancelRegistrationDate.day", elAnyExpr(LocalDate.now().plusDays(2).getDayOfMonth.toString))
      .formParam("cancelRegistrationDate.month", elAnyExpr(LocalDate.now().plusDays(2).getMonthValue.toString))
      .formParam("cancelRegistrationDate.year", elAnyExpr(LocalDate.now().plusDays(2).getYear.toString))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/cancel-registration/check-your-answers")))

  def postAddSmallProducerPage: HttpRequestBuilder =
    http("POST add-small-producer")
      .post(s"$baseFrontEndUrl/$frontEndRoute/correct-return/add-small-producer")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("producerName", elAnyExpr("Fake Producer"))
      .formParam("referenceNumber", elAnyExpr("XWSDIL000000341"))
      .formParam("litres.lowBand", elAnyExpr("1000"))
      .formParam("litres.highBand", elAnyExpr("1000"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$frontEndRoute/correct-return/small-producer-details")))

}
