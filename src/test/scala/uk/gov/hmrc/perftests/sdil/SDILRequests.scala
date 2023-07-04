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

object SDILRequests extends ServicesConfiguration {

  val baseFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-returns-frontend")
  val frontEndRoute: String   = "soft-drinks-industry-levy-returns-frontend"

  def getOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("GET own-brands-packaged-at-own-sites")
      .get(s"$baseFrontEndUrl/$frontEndRoute/own-brands-packaged-at-own-sites": String)
      .check(saveCsrfToken())
      .check(status.is(303))
  }

  def postOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("POST own-brands-packaged-at-own-sites")
      .post(s"$baseFrontEndUrl/$frontEndRoute/own-brands-packaged-at-own-sites": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/how-many-own-brands-packaged-at-own-sites": String))
  }

  def getHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("GET how-many-own-brands-packaged-at-own-sites")
      .get(s"$baseFrontEndUrl/$frontEndRoute/how-many-own-brands-packaged-at-own-sites": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("POST how-many-own-brands-packaged-at-own-sites")
      .post(s"$baseFrontEndUrl/$frontEndRoute/how-many-own-brands-packaged-at-own-sites": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/packaged-as-contract-packer": String))
  }

  def getPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("GET packaged-as-contract-packer")
      .get(s"$baseFrontEndUrl/$frontEndRoute/packaged-as-contract-packer": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("POST packaged-as-contract-packer")
      .post(s"$baseFrontEndUrl/$frontEndRoute/packaged-as-contract-packer": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/how-many-packaged-as-contract-packer": String))
  }

  def getHowManyPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("GET how-many-packaged-as-contract-packer")
      .get(s"$baseFrontEndUrl/$frontEndRoute/how-many-packaged-as-contract-packer": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("POST how-many-packaged-as-contract-packer")
      .post(s"$baseFrontEndUrl/$frontEndRoute/how-many-packaged-as-contract-packer": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/exemptions-for-small-producers": String))
  }

  def getExemptionsForSmallProducersPage: HttpRequestBuilder = {
    http("GET exemptions-for-small-producers")
      .get(s"$baseFrontEndUrl/$frontEndRoute/exemptions-for-small-producers": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postExemptionsForSmallProducersPage: HttpRequestBuilder = {
    http("POST exemptions-for-small-producers")
      .post(s"$baseFrontEndUrl/$frontEndRoute/exemptions-for-small-producers": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/add-small-producer": String))
  }

  def getAddSmallProducerPage: HttpRequestBuilder = {
    http("GET add-small-producer")
      .get(s"$baseFrontEndUrl/$frontEndRoute/add-small-producer": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postAddSmallProducerPage: HttpRequestBuilder = {
    http("POST add-small-producer")
      .post(s"$baseFrontEndUrl/$frontEndRoute/add-small-producer": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("producerName", "Fake Producer")
      .formParam("referenceNumber", "XGSDIL000001611")
      .formParam("lowBand", "1000")
      .formParam("highBand", "1000")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/small-producer-details": String))
  }

  def getSmallProducerDetailsPage: HttpRequestBuilder = {
    http("GET small-producer-details")
      .get(s"$baseFrontEndUrl/$frontEndRoute/small-producer-details": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postSmallProducerDetailsPage: HttpRequestBuilder = {
    http("POST small-producer-details")
      .post(s"$baseFrontEndUrl/$frontEndRoute/small-producer-details": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/brought-into-uk": String))
  }

  def getBroughtIntoUKPage: HttpRequestBuilder = {
    http("GET brought-into-uk")
      .get(s"$baseFrontEndUrl/$frontEndRoute/brought-into-uk": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postBroughtIntoUKPage: HttpRequestBuilder = {
    http("POST brought-into-uk")
      .post(s"$baseFrontEndUrl/$frontEndRoute/brought-into-uk": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/how-many-brought-into-uk": String))
  }

  def getHowManyBroughtIntoUKPage: HttpRequestBuilder = {
    http("GET how-many-brought-into-uk")
      .get(s"$baseFrontEndUrl/$frontEndRoute/how-many-brought-into-uk": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyBroughtIntoUKPage: HttpRequestBuilder = {
    http("POST how-many-brought-into-uk")
      .post(s"$baseFrontEndUrl/$frontEndRoute/how-many-brought-into-uk": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/brought-into-uk-from-small-producers": String))
  }

  def getBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder = {
    http("GET brought-into-uk-from-small-producers")
      .get(s"$baseFrontEndUrl/$frontEndRoute/brought-into-uk-from-small-producers": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder = {
    http("POST brought-into-uk-from-small-producers")
      .post(s"$baseFrontEndUrl/$frontEndRoute/brought-into-uk-from-small-producers": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/how-many-into-uk-small-producers": String))
  }

  def getHowManyBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder = {
    http("GET how-many-into-uk-small-producers")
      .get(s"$baseFrontEndUrl/$frontEndRoute/how-many-into-uk-small-producers": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder = {
    http("POST how-many-into-uk-small-producers")
      .post(s"$baseFrontEndUrl/$frontEndRoute/how-many-into-uk-small-producers": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/claim-credits-for-exports": String))
  }

  def getClaimCreditsForExportsPage: HttpRequestBuilder = {
    http("GET claim-credits-for-exports")
      .get(s"$baseFrontEndUrl/$frontEndRoute/claim-credits-for-exports": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postClaimCreditsForExportsPage: HttpRequestBuilder = {
    http("POST claim-credits-for-exports")
      .post(s"$baseFrontEndUrl/$frontEndRoute/claim-credits-for-exports": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/how-many-credits-for-exports": String))
  }

  def getHowManyCreditsForExportsPage: HttpRequestBuilder = {
    http("GET how-many-into-uk-small-producers")
      .get(s"$baseFrontEndUrl/$frontEndRoute/how-many-credits-for-exports": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyCreditsForExportsPage: HttpRequestBuilder = {
    http("POST how-many-credits-for-exports")
      .post(s"$baseFrontEndUrl/$frontEndRoute/how-many-credits-for-exports": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/claim-credits-for-lost-damaged": String))
  }

  def getClaimCreditsForLostDamagedPage: HttpRequestBuilder = {
    http("GET claim-credits-for-lost-damaged")
      .get(s"$baseFrontEndUrl/$frontEndRoute/claim-credits-for-lost-damaged": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postClaimCreditsForLostDamagedPage: HttpRequestBuilder = {
    http("POST claim-credits-for-lost-damaged")
      .post(s"$baseFrontEndUrl/$frontEndRoute/claim-credits-for-lost-damaged": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/how-many-credits-for-lost-damaged": String))
  }

  def getHowManyCreditsForLostDamagedPage: HttpRequestBuilder = {
    http("GET how-many-credits-for-lost-damaged")
      .get(s"$baseFrontEndUrl/$frontEndRoute/how-many-credits-for-lost-damaged": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyCreditsForLostDamagedPage: HttpRequestBuilder = {
    http("POST how-many-credits-for-lost-damaged")
      .post(s"$baseFrontEndUrl/$frontEndRoute/how-many-credits-for-lost-damaged": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$frontEndRoute/check-your-answers": String))
  }

  def getCheckYourAnswersPage: HttpRequestBuilder = {
    http("GET check-your-answers")
      .get(s"$baseFrontEndUrl/$frontEndRoute/check-your-answers": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def getReturnSentPage: HttpRequestBuilder = {
    http("GET return-sent")
      .get(s"$baseFrontEndUrl/$frontEndRoute/return-sent": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }
}
