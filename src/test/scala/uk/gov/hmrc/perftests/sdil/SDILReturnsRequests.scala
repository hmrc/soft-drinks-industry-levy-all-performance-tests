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

object SDILReturnsRequests extends BaseRequest {

  val baseReturnsFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-returns-frontend")
  val returnsFrontEndRoute: String   = "soft-drinks-industry-levy-returns-frontend"
  val baseAccountFrontEndUrl: String = baseUrlFor("soft-drinks-industry-levy-account-frontend")
  val accountFrontEndRoute: String   = "soft-drinks-industry-levy-account-frontend"

  def redirectToBrandsPackagedAtOwnSitesPage: HttpRequestBuilder =
    http("REDIRECT to returns from accounts")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/submit-return/year/2023/quarter/0/nil-return/false")
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/own-brands-packaged-at-own-sites")))

  def getOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder =
    http("GET own-brands-packaged-at-own-sites")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/own-brands-packaged-at-own-sites")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder =
    http("POST own-brands-packaged-at-own-sites")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/own-brands-packaged-at-own-sites")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("true"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/how-many-own-brands-packaged-at-own-sites")))

  def getHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder =
    http("GET how-many-own-brands-packaged-at-own-sites")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-own-brands-packaged-at-own-sites")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder =
    http("POST how-many-own-brands-packaged-at-own-sites")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-own-brands-packaged-at-own-sites")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("lowBand", elAnyExpr("100"))
      .formParam("highBand", elAnyExpr("100"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/packaged-as-contract-packer")))

  def getPackagedAsContractPackerPage: HttpRequestBuilder =
    http("GET packaged-as-contract-packer")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/packaged-as-contract-packer")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postPackagedAsContractPackerPage: HttpRequestBuilder =
    http("POST packaged-as-contract-packer")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/packaged-as-contract-packer")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("true"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/how-many-packaged-as-contract-packer")))

  def getHowManyPackagedAsContractPackerPage: HttpRequestBuilder =
    http("GET how-many-packaged-as-contract-packer")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-packaged-as-contract-packer")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postHowManyPackagedAsContractPackerPage: HttpRequestBuilder =
    http("POST how-many-packaged-as-contract-packer")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-packaged-as-contract-packer")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("lowBand", elAnyExpr("100"))
      .formParam("highBand", elAnyExpr("100"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/exemptions-for-small-producers")))

  def getExemptionsForSmallProducersPage: HttpRequestBuilder =
    http("GET exemptions-for-small-producers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/exemptions-for-small-producers")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postExemptionsForSmallProducersPage: HttpRequestBuilder =
    http("POST exemptions-for-small-producers")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/exemptions-for-small-producers")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("true"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/add-small-producer")))
//      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/small-producer-details")))

  def getAddSmallProducerPage: HttpRequestBuilder =
    http("GET add-small-producer")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/add-small-producer")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postAddSmallProducerPage: HttpRequestBuilder =
    http("POST add-small-producer")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/add-small-producer")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("producerName", elAnyExpr("Fake Producer"))
      .formParam("referenceNumber", elAnyExpr("XWSDIL000000341"))
      .formParam("lowBand", elAnyExpr("1000"))
      .formParam("highBand", elAnyExpr("1000"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/small-producer-details")))

  def getSmallProducerDetailsPage: HttpRequestBuilder =
    http("GET small-producer-details")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/small-producer-details")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postSmallProducerDetailsPage: HttpRequestBuilder =
    http("POST small-producer-details")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/small-producer-details")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("false"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/brought-into-uk")))

  def getBroughtIntoUKPage: HttpRequestBuilder =
    http("GET brought-into-uk")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/brought-into-uk")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postBroughtIntoUKPage: HttpRequestBuilder =
    http("POST brought-into-uk")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/brought-into-uk")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("true"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/how-many-brought-into-uk")))

  def getHowManyBroughtIntoUKPage: HttpRequestBuilder =
    http("GET how-many-brought-into-uk")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-brought-into-uk")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postHowManyBroughtIntoUKPage: HttpRequestBuilder =
    http("POST how-many-brought-into-uk")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-brought-into-uk")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("lowBand", elAnyExpr("100"))
      .formParam("highBand", elAnyExpr("100"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/brought-into-uk-from-small-producers")))

  def getBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder =
    http("GET brought-into-uk-from-small-producers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/brought-into-uk-from-small-producers")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder =
    http("POST brought-into-uk-from-small-producers")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/brought-into-uk-from-small-producers")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("true"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/how-many-into-uk-small-producers")))

  def getHowManyBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder =
    http("GET how-many-into-uk-small-producers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-into-uk-small-producers")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postHowManyBroughtIntoUKFromSmallProducersPage: HttpRequestBuilder =
    http("POST how-many-into-uk-small-producers")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-into-uk-small-producers")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("lowBand", elAnyExpr("100"))
      .formParam("highBand", elAnyExpr("100"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/claim-credits-for-exports")))

  def getClaimCreditsForExportsPage: HttpRequestBuilder =
    http("GET claim-credits-for-exports")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/claim-credits-for-exports")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postClaimCreditsForExportsPage: HttpRequestBuilder =
    http("POST claim-credits-for-exports")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/claim-credits-for-exports")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("true"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/how-many-credits-for-exports")))

  def getHowManyCreditsForExportsPage: HttpRequestBuilder =
    http("GET how-many-into-uk-small-producers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-credits-for-exports")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postHowManyCreditsForExportsPage: HttpRequestBuilder =
    http("POST how-many-credits-for-exports")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-credits-for-exports")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("lowBand", elAnyExpr("100"))
      .formParam("highBand", elAnyExpr("100"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/claim-credits-for-lost-damaged")))

  def getClaimCreditsForLostDamagedPage: HttpRequestBuilder =
    http("GET claim-credits-for-lost-damaged")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/claim-credits-for-lost-damaged")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postClaimCreditsForLostDamagedPage: HttpRequestBuilder =
    http("POST claim-credits-for-lost-damaged")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/claim-credits-for-lost-damaged")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("true"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/how-many-credits-for-lost-damaged")))

  def getHowManyCreditsForLostDamagedPage: HttpRequestBuilder =
    http("GET how-many-credits-for-lost-damaged")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-credits-for-lost-damaged")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postHowManyCreditsForLostDamagedPage: HttpRequestBuilder =
    http("POST how-many-credits-for-lost-damaged")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/how-many-credits-for-lost-damaged")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("lowBand", elAnyExpr("100"))
      .formParam("highBand", elAnyExpr("100"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/check-your-answers")))

  def getReturnChangeRegistrationPage: HttpRequestBuilder =
    http("GET return-change-registration")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/return-change-registration")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postReturnChangeRegistrationPage: HttpRequestBuilder =
    http("POST claim-credits-for-lost-damaged")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/return-change-registration")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("false"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/ask-secondary-warehouses-in-return")))

  def getPackAtBusinessAddressChangePage: HttpRequestBuilder =
    http("GET pack-at-business-address-in-return")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/pack-at-business-address-in-return")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postPackAtBusinessAddressChangePage: HttpRequestBuilder =
    http("POST pack-at-business-address")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/pack-at-business-address-in-return")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("true"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/packaging-site-details")))

  def getProductionSiteDetailsPage: HttpRequestBuilder =
    http("GET packaging-site-details")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/packaging-site-details")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postProductionSiteDetailsPage: HttpRequestBuilder     =
    http("POST packaging-site-details")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/packaging-site-details")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("false"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/check-your-answers")))
  def getRemovePackagingSiteDetailsPage: HttpRequestBuilder =
    http("GET remove-packaging-site-details")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/remove-packaging-site-details/")
      .check(saveCsrfToken())
      .check(status.is(200))

  def getSecondaryWarehouseDetailsPage: HttpRequestBuilder = //secondary-warehouse-details
    http("GET secondary-warehouse-details")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/secondary-warehouse-details")
      .check(saveCsrfToken())
      .check(status.is(200))

  postSecondaryWarehouseDetailsPage

  def postSecondaryWarehouseDetailsPage: HttpRequestBuilder =
    http("POST secondary-warehouse-details")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/secondary-warehouse-details")
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("value", elAnyExpr("false"))
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/check-your-answers")))

  def getRemoveWarehouseConfirmPage: HttpRequestBuilder = //remove-warehouse-confirm
    http("GET remove-warehouse-details/:index")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/remove-warehouse-details/0")
      .check(saveCsrfToken())
      .check(status.is(200))

  def getCheckYourAnswersPage: HttpRequestBuilder =
    http("GET check-your-answers")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/check-your-answers")
      .check(saveCsrfToken())
      .check(status.is(200))

  def postCheckYourAnswersPage: HttpRequestBuilder =
    http("POST check-your-anwers")
      .post(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/check-your-answers")
      .formParam("csrfToken", csrfTokenExpr)
      .check(status.is(303))
      .check(header(locationHeaderExpr).is(elStringExpr(s"/$returnsFrontEndRoute/return-sent")))

  def getReturnSentPage: HttpRequestBuilder =
    http("GET return-sent")
      .get(s"$baseReturnsFrontEndUrl/$returnsFrontEndRoute/return-sent")
      .check(saveCsrfToken())
      .check(status.is(200))
}
