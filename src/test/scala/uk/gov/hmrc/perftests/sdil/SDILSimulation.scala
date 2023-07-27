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
import uk.gov.hmrc.perftests.sdil.SDILVariationsRequests.{getPage, postContactDetailsAddPage, postFormlessPage, postLitresPage, postPage}
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

  setup("variations-update-registered-details-update-contact", "update-contact").withRequests(
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
    getPage("change-registered-details/check-your-answers"),
    postFormlessPage("change-registered-details/check-your-answers"),
    //  getPage("change-registered-details/variation-done")
  )

  setup("variations-update-registered-details-update-site", "update-packaging-site").withRequests(
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
    getPage("change-registered-details/packaging-site-details"),
    postPage("change-registered-details/packaging-site-details", "false"),
    getPage("change-registered-details/warehouse-details"),
    postPage("change-registered-details/warehouse-details", "true"),
    getPage("change-registered-details/warehouse-details"),
    postPage("change-registered-details/warehouse-details", "false"),
    //  getPage("change-registered-details/contact-details"),
    //  postContactDetailsPage,
    getPage("change-registered-details/contact-details-add"),
    postContactDetailsAddPage,
    //  getPage("change-registered-details/business-address"),
    //  postBusinessAddressPage,
    getPage("change-registered-details/check-your-answers"),
    postFormlessPage("change-registered-details/check-your-answers"),
    //  getPage("change-registered-details/variation-done")
  )

  setup("variations-update-registered-details-remove-site", "remove-site").withRequests(
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
    getPage("change-registered-details/check-your-answers"),
    postFormlessPage("change-registered-details/check-your-answers"),
    //  getPage("change-registered-details/variation-done")
  )

  setup("variations-change-activity-one-million-or-more", "1 million or more").withRequests(
    resetUserAnswers(),
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getPage("select-change"),
    postPage("select-change", "changeActivity", "/change-activity/amount-produced"),
    getPage ("change-activity/amount-produced"),
    postPage("change-activity/amount-produced", "large", "/change-activity/operate-packaging-site"),
    getPage("change-activity/operate-packaging-site"),
    postPage("change-activity/operate-packaging-site", "true", "/change-activity/how-many-own-brands-next-12-months"),
    getPage("change-activity/how-many-own-brands-next-12-months"),
    postLitresPage("change-activity/how-many-own-brands-next-12-months", "/change-activity/contract-packing"),
    getPage("change-activity/contract-packing"),
    postPage("change-activity/contract-packing", "true", "/change-activity/how-many-contract-packing-next-12-months"),
    getPage("change-activity/how-many-contract-packing-next-12-months"),
    postLitresPage("change-activity/how-many-contract-packing-next-12-months", "/change-activity/imports"),
    getPage("change-activity/imports"),
    postPage("change-activity/imports", "true", "/change-activity/how-many-imports-next-12-months"),
    getPage("change-activity/how-many-imports-next-12-months"),
    postLitresPage("change-activity/how-many-imports-next-12-months", "/change-activity/packaging-site-details"),
    getPage("change-activity/packaging-site-details"),
    postPage("change-activity/packaging-site-details", "false", "/change-activity/secondary-warehouse-details"),
    getPage("change-activity/secondary-warehouse-details"),
    postPage("change-activity/secondary-warehouse-details", "false")
    // TODO - add return sent after page has been developed
  )

  setup("variations-change-activity-less-than-one-million", "less than 1 million").withRequests(
    resetUserAnswers(),
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getPage("select-change"),
    postPage("select-change", "changeActivity", "/change-activity/amount-produced"),
    getPage("change-activity/amount-produced"),
    postPage("change-activity/amount-produced", "small", "/change-activity/third-party-packagers"),
    getPage("change-activity/third-party-packagers"),
    postPage("change-activity/third-party-packagers", "true", "/change-activity/operate-packaging-site"),
    getPage("change-activity/operate-packaging-site"),
    postPage("change-activity/operate-packaging-site", "true", "/change-activity/how-many-own-brands-next-12-months"),
    getPage("change-activity/how-many-own-brands-next-12-months"),
    postLitresPage("change-activity/how-many-own-brands-next-12-months", "/change-activity/contract-packing"),
    getPage("change-activity/contract-packing"),
    postPage("change-activity/contract-packing", "true", "/change-activity/how-many-contract-packing-next-12-months"),
    getPage("change-activity/how-many-contract-packing-next-12-months"),
    postLitresPage("change-activity/how-many-contract-packing-next-12-months", "/change-activity/imports"),
    getPage("change-activity/imports"),
    postPage("change-activity/imports", "true", "/change-activity/how-many-imports-next-12-months"),
    getPage("change-activity/how-many-imports-next-12-months"),
    postLitresPage("change-activity/how-many-imports-next-12-months", ""), //TODO - complete when navigation for this page is completed
    getPage("change-activity/packaging-site-details"),
    postPage("change-activity/packaging-site-details", "false", "/change-activity/secondary-warehouse-details"),
    getPage("change-activity/secondary-warehouse-details"),
    postPage("change-activity/secondary-warehouse-details", "false")
    // TODO - add return sent after page has been developed
  )

  setup("variations-change-activity-none-produced", "none produced").withRequests(
    resetUserAnswers(),
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getPage("select-change"),
    postPage("select-change", "changeActivity", "/change-activity/amount-produced"),
    getPage("change-activity/amount-produced"),
    postPage("change-activity/amount-produced", "none", "/change-activity/contract-packing")
  )
  runSimulation()

}
