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

import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.sdil.AuthRequests.{createAuthSession, navigateToAuth, navigateToAuthSession}
import uk.gov.hmrc.perftests.sdil.SDILAccountRequests.{getAccountHomePage, getAccountHomePageStartReturn1, getAccountHomePageStartReturn2, postAccountHomePageStartReturn}
import uk.gov.hmrc.perftests.sdil.SDILReturnsRequests._
import uk.gov.hmrc.perftests.sdil.SetupRequests._
trait SDILReturnJourneyRequests {

  val sdilReturnJourney1Requests: Seq[HttpRequestBuilder] = Seq(
    resetPending,
    resetReturns,
    resetRegistrations,
    sdilReturnsCollectionReset,
    resetReturnsUserAnswers(),

    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,

    getAccountHomePage,
    getAccountHomePageStartReturn1,
    getAccountHomePageStartReturn2,

    getOwnBrandsPackagedAtOwnSitesPage,
    postOwnBrandsPackagedAtOwnSitesPage,
    getHowManyOwnBrandsPackagedAtOwnSitesPage,
    postHowManyOwnBrandsPackagedAtOwnSitesPage,
    getPackagedAsContractPackerPage,
    postPackagedAsContractPackerPage,
    getHowManyPackagedAsContractPackerPage,
    postHowManyPackagedAsContractPackerPage,
    getExemptionsForSmallProducersPage,
    postExemptionsForSmallProducersPage,
    getAddSmallProducerPage,
    postAddSmallProducerPage,
    getSmallProducerDetailsPage,
    postSmallProducerDetailsPage,
    getBroughtIntoUKPage,
    postBroughtIntoUKPage,
    getHowManyBroughtIntoUKPage,
    postHowManyBroughtIntoUKPage,
    getBroughtIntoUKFromSmallProducersPage,
    postBroughtIntoUKFromSmallProducersPage,
    getHowManyBroughtIntoUKFromSmallProducersPage,
    postHowManyBroughtIntoUKFromSmallProducersPage,
    getClaimCreditsForExportsPage,
    postClaimCreditsForExportsPage,
    getHowManyCreditsForExportsPage,
    postHowManyCreditsForExportsPage,
    getClaimCreditsForLostDamagedPage,
    postClaimCreditsForLostDamagedPage,
    getHowManyCreditsForLostDamagedPage,
    postHowManyCreditsForLostDamagedPage,
    getCheckYourAnswersPage,
    postCheckYourAnswersPage,
    getReturnSentPage
  )

  val sdilReturnJourney2Requests: Seq[HttpRequestBuilder] = Seq(
    resetPending,
    resetReturns,
    resetRegistrations,
    sdilReturnsCollectionReset,
    resetReturnsUserAnswers(),

    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,

    getAccountHomePage,
    getAccountHomePageStartReturn1,
    getAccountHomePageStartReturn2,

    getOwnBrandsPackagedAtOwnSitesPage,
    postOwnBrandsPackagedAtOwnSitesPage,
    getHowManyOwnBrandsPackagedAtOwnSitesPage,
    postHowManyOwnBrandsPackagedAtOwnSitesPage,
    getPackagedAsContractPackerPage,
    postPackagedAsContractPackerPage,
    getHowManyPackagedAsContractPackerPage,
    postHowManyPackagedAsContractPackerPage,
    getExemptionsForSmallProducersPage,
    postExemptionsForSmallProducersPage,
    getAddSmallProducerPage,
    postAddSmallProducerPage,
    getSmallProducerDetailsPage,
    postSmallProducerDetailsPage,
    getBroughtIntoUKPage,
    postBroughtIntoUKPage,
    getHowManyBroughtIntoUKPage,
    postHowManyBroughtIntoUKPage,
    getBroughtIntoUKFromSmallProducersPage,
    postBroughtIntoUKFromSmallProducersPage,
    getHowManyBroughtIntoUKFromSmallProducersPage,
    postHowManyBroughtIntoUKFromSmallProducersPage,
    getClaimCreditsForExportsPage,
    postClaimCreditsForExportsPage,
    getHowManyCreditsForExportsPage,
    postHowManyCreditsForExportsPage,
    getClaimCreditsForLostDamagedPage,
    postClaimCreditsForLostDamagedPage,
    getHowManyCreditsForLostDamagedPage,
    postHowManyCreditsForLostDamagedPage,
    getReturnChangeRegistrationPage,
    postReturnChangeRegistrationPage,
    getPackAtBusinessAddressChangePage,
    postPackAtBusinessAddressChangePage,
    getProductionSiteDetailsPage,
    postProductionSiteDetailsPage,
    getSecondaryWarehouseDetailsPage,
    postSecondaryWarehouseDetailsPage,
    getCheckYourAnswersPage,
    postCheckYourAnswersPage,
    getReturnSentPage
  )

}
