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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.sdil.AuthRequests._
import uk.gov.hmrc.perftests.sdil.SDILReturnsRequests._
import uk.gov.hmrc.perftests.sdil.SDILVariationsRequests.{getPage, postContactDetailsAddPage, postFormlessPage, postPage}
import uk.gov.hmrc.perftests.sdil.SetupRequests._

class SDILSimulation extends PerformanceTestRunner {

  setup("sdil-returns-journey", "SDIL Returns journey").withRequests(
    resetPending,
    resetReturns,
    resetRegistrations,
    sdilReturnsCollectionReset,
    resetUserAnswers(),

    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,

    getToReturns,
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

  setup("sdil-variations-journey-update-contact", "SDIL Variations journey - update contact").withRequests(
    resetUserAnswers(),
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getPage("select-change"),
    postPage("select-change", "updateRegisteredDetails"),
    //  getPage("what do you need to update"),
    //  postPage("what do you need to update", "add or remove packaging sites or warehouse addresses"),
    getPage("change-registered-details/packaging-site-details"),
    postPage("change-registered-details/packaging-site-details", "false"),
    getPage("change-registered-details/warehouse-details"),
    postPage("change-registered-details/warehouse-details", "false"),
    //  getPage("change-registered-details/contact-details"),
    //  postContactDetailsPage,
    getPage("change-registered-details/contact-details-add"),
    postContactDetailsAddPage,
    //  getPage("change-registered-details/business-address"),
    //  postBusinessAddressPage,
    //  getPage("change-registered-details/business-address-add"),
    //  postBusinessAddressAddPage,
    getPage("change-registered-details/check-your-answers"),
    postFormlessPage("change-registered-details/check-your-answers"),
    //  getPage("change-registered-details/variation-done")
  )

  setup("sdil-variations-journey-update-site", "Variations - update packaging site").withRequests(
    resetUserAnswers(),
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getPage("select-change"),
    postPage("select-change", "updateRegisteredDetails"),
    //  getPage("what do you need to update"),
    //  postPage("what do you need to update", "add or remove packaging sites or warehouse addresses"),
    getPage("change-registered-details/packaging-site-details"),
    postPage("change-registered-details/packaging-site-details", "true"),
    // getPage("change-registered-details/packaging-site-details-add"),
    // postPackagingSiteDetailsAdd,
    getPage("change-registered-details/packaging-site-details"),
    postPage("change-registered-details/packaging-site-details", "false"),
    getPage("change-registered-details/warehouse-details"),
    postPage("change-registered-details/warehouse-details", "true"),
    // getPage("change-registered-details/warehouse-details-add"),
    // postWarehouseDetailsAddPage,
    getPage("change-registered-details/warehouse-details"),
    postPage("change-registered-details/warehouse-details", "false"),
    //  getPage("change-registered-details/contact-details"),
    //  postContactDetailsPage,
    getPage("change-registered-details/contact-details-add"),
    postContactDetailsAddPage,
    //  getPage("change-registered-details/business-address"),
    //  postBusinessAddressPage,
    //  getPage("change-registered-details/business-address-add"),
    //  postBusinessAddressAddPage,
    getPage("change-registered-details/check-your-answers"),
    postFormlessPage("change-registered-details/check-your-answers"),
    //  getPage("change-registered-details/variation-done")
  )

  setup("sdil-variations-journey-remove-site", "Variations - remove site").withRequests(
    resetUserAnswers(),
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getPage("select-change"),
    postPage("select-change", "updateRegisteredDetails"),
    //  getPage("what do you need to update"),
    //  postPage("what do you need to update", "add or remove packaging sites or warehouse addresses"),
    getPage("change-registered-details/packaging-site-details"),
    postPage("change-registered-details/packaging-site-details", "true"),
    // getPage("change-registered-details/packaging-site-details-add"),
    // postPackagingSiteDetailsAdd,
    getPage("change-registered-details/packaging-site-details"),
    getPage("change-registered-details/packaging-site-details/remove/1"),
    postPage("change-registered-details/packaging-site-details/remove/1", "true"),
    getPage("change-registered-details/warehouse-details"),
    getPage("change-registered-details/warehouse-details/remove/1"),
    postPage("change-registered-details/warehouse-details/remove/1", "false"),
    getPage("change-registered-details/warehouse-details"),
    postPage("change-registered-details/warehouse-details", "false"),
    getPage("change-registered-details/contact-details-add"),
    postContactDetailsAddPage,
    //  getPage("change-registered-details/business-address"),
    //  postBusinessAddressPage,
    //  getPage("change-registered-details/business-address-add"),
    //  postBusinessAddressAddPage,
    getPage("change-registered-details/check-your-answers"),
    postFormlessPage("change-registered-details/check-your-answers"),
    //  getPage("change-registered-details/variation-done")
  )

  runSimulation()

}
