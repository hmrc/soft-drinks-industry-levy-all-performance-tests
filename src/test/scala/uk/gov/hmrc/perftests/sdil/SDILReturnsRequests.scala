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

object SDILReturnsRequests extends ServicesConfiguration {

  val baseReturnsFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-returns-frontend")
  val returnsFrontEndRoute: String   = "soft-drinks-industry-levy-returns-frontend"
  val baseAccountFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-account-frontend")
  val accountFrontEndRoute: String   = "soft-drinks-industry-levy-account-frontend"


  def redirectToBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("REDIRECT to returns from accounts")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/submit-return/year/2023/quarter/0/nil-return/false": String)
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/own-brands-packaged-at-own-sites": String))
  }

  def getOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("GET own-brands-packaged-at-own-sites")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/own-brands-packaged-at-own-sites": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("POST own-brands-packaged-at-own-sites")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/own-brands-packaged-at-own-sites": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/how-many-own-brands-packaged-at-own-sites": String))
  }

  def getHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("GET how-many-own-brands-packaged-at-own-sites")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-own-brands-packaged-at-own-sites": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("POST how-many-own-brands-packaged-at-own-sites")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-own-brands-packaged-at-own-sites": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/packaged-as-contract-packer": String))
  }

  def getPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("GET packaged-as-contract-packer")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/packaged-as-contract-packer": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("POST packaged-as-contract-packer")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/packaged-as-contract-packer": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/how-many-packaged-as-contract-packer": String))
  }

  def getHowManyPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("GET how-many-packaged-as-contract-packer")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-packaged-as-contract-packer": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("POST how-many-packaged-as-contract-packer")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-packaged-as-contract-packer": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/exemptions-for-small-producers": String))
  }

  def getExemptionsForSmallProducersPage: HttpRequestBuilder = {
    http("GET exemptions-for-small-producers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/exemptions-for-small-producers": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postExemptionsForSmallProducersPage: HttpRequestBuilder = {
    http("POST exemptions-for-small-producers")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/exemptions-for-small-producers": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/add-small-producer": String))
//      .check(header("Location").is(s"/$returnsFrontEndRoute/small-producer-details": String))
  }

  def getAddSmallProducerPage: HttpRequestBuilder = {
    http("GET add-small-producer")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/add-small-producer": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postAddSmallProducerPage: HttpRequestBuilder = {
    http("POST add-small-producer")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/add-small-producer": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("producerName", "Fake Producer")
      .formParam("referenceNumber", "XPSDIL000000161")
      .formParam("lowBand", "1000")
      .formParam("highBand", "1000")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/small-producer-details": String))
  }

  def getSmallProducerDetailsPage: HttpRequestBuilder = {
    http("GET small-producer-details")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/small-producer-details": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postSmallProducerDetailsPage: HttpRequestBuilder = {
    http("POST small-producer-details")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/small-producer-details": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/brought-into-uk": String))
  }

  def getBroughtIntoUKPage: HttpRequestBuilder = {
    http("GET brought-into-uk")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/brought-into-uk": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postBroughtIntoUKPage: HttpRequestBuilder = {
    http("POST brought-into-uk")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/brought-into-uk": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/how-many-brought-into-uk": String))
  }

  def getHowManyBroughtIntoUKPage: HttpRequestBuilder = {
    http("GET how-many-brought-into-uk")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-brought-into-uk": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyBroughtIntoUKPage: HttpRequestBuilder = {
    http("POST how-many-brought-into-uk")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-brought-into-uk": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/brought-into-uk-from-small-producers": String))
  }

  def getBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder = {
    http("GET brought-into-uk-from-small-producers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/brought-into-uk-from-small-producers": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder = {
    http("POST brought-into-uk-from-small-producers")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/brought-into-uk-from-small-producers": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/how-many-into-uk-small-producers": String))
  }

  def getHowManyBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder = {
    http("GET how-many-into-uk-small-producers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-into-uk-small-producers": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder = {
    http("POST how-many-into-uk-small-producers")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-into-uk-small-producers": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/claim-credits-for-exports": String))
  }

  def getClaimCreditsForExportsPage: HttpRequestBuilder = {
    http("GET claim-credits-for-exports")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/claim-credits-for-exports": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postClaimCreditsForExportsPage: HttpRequestBuilder = {
    http("POST claim-credits-for-exports")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/claim-credits-for-exports": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/how-many-credits-for-exports": String))
  }

  def getHowManyCreditsForExportsPage: HttpRequestBuilder = {
    http("GET how-many-into-uk-small-producers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-credits-for-exports": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyCreditsForExportsPage: HttpRequestBuilder = {
    http("POST how-many-credits-for-exports")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-credits-for-exports": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/claim-credits-for-lost-damaged": String))
  }

  def getClaimCreditsForLostDamagedPage: HttpRequestBuilder = {
    http("GET claim-credits-for-lost-damaged")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/claim-credits-for-lost-damaged": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postClaimCreditsForLostDamagedPage: HttpRequestBuilder = {
    http("POST claim-credits-for-lost-damaged")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/claim-credits-for-lost-damaged": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/how-many-credits-for-lost-damaged": String))
  }

  def getHowManyCreditsForLostDamagedPage: HttpRequestBuilder = {
    http("GET how-many-credits-for-lost-damaged")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-credits-for-lost-damaged": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyCreditsForLostDamagedPage: HttpRequestBuilder = {
    http("POST how-many-credits-for-lost-damaged")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-credits-for-lost-damaged": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/check-your-answers": String))
  }

  def getReturnChangeRegistrationPage: HttpRequestBuilder = {
    http("GET return-change-registration")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/return-change-registration": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postReturnChangeRegistrationPage: HttpRequestBuilder = {
    http("POST claim-credits-for-lost-damaged")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/return-change-registration": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/ask-secondary-warehouses-in-return": String))
  }

  def getPackAtBusinessAddressChangePage: HttpRequestBuilder = {
    http("GET pack-at-business-address-in-return")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/pack-at-business-address-in-return": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postPackAtBusinessAddressChangePage: HttpRequestBuilder = {
    http("POST pack-at-business-address")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/pack-at-business-address-in-return": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/packaging-site-details": String))
  }

  def getProductionSiteDetailsPage: HttpRequestBuilder = {
    http("GET packaging-site-details")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/packaging-site-details": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postProductionSiteDetailsPage: HttpRequestBuilder = {
    http("POST packaging-site-details")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/packaging-site-details": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/check-your-answers": String))
  }
  def getRemovePackagingSiteDetailsPage: HttpRequestBuilder = {
    http("GET remove-packaging-site-details")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/remove-packaging-site-details/": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def getSecondaryWarehouseDetailsPage: HttpRequestBuilder = { //secondary-warehouse-details
    http("GET secondary-warehouse-details")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/secondary-warehouse-details": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  postSecondaryWarehouseDetailsPage

  def postSecondaryWarehouseDetailsPage: HttpRequestBuilder = {
    http("POST secondary-warehouse-details")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/secondary-warehouse-details": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/check-your-answers": String))
  }

  def getRemoveWarehouseConfirmPage: HttpRequestBuilder = { //remove-warehouse-confirm
    http("GET remove-warehouse-details/:index")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/remove-warehouse-details/0": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def getCheckYourAnswersPage: HttpRequestBuilder = {
    http("GET check-your-answers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/check-your-answers": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postCheckYourAnswersPage: HttpRequestBuilder = {
    http("POST check-your-anwers")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/check-your-answers": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"/$returnsFrontEndRoute/return-sent": String))
  }

  def getReturnSentPage: HttpRequestBuilder = {
    http("GET return-sent")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/return-sent": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }
}
