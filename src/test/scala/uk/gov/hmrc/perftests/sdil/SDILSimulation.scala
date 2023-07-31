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
import uk.gov.hmrc.perftests.sdil.SDILVariationsRequests.{getPage, postCancelDatePage, postContactDetailsAddPage, postFormlessPage, postLitresPage, postPage}
import uk.gov.hmrc.perftests.sdil.SetupRequests._

class SDILSimulation extends PerformanceTestRunner {

  setup("sdil-returns-journey", "SDIL Returns journey").withRequests(
    resetPending,
    resetReturns,
    resetRegistrations,
    sdilReturnsCollectionReset,
    resetReturnsUserAnswers(),

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
    postPage("change-activity/secondary-warehouse-details", "false"),
    getPage ("change-activity/check-your-answers"),
    postFormlessPage("change-activity/check-your-answers")
    // TODO - add return sent after page has been developed
  )

  setup("variations-change-activity-less-than-one-million", "less than 1 million").withRequests(
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
    postPage("change-activity/secondary-warehouse-details", "false"),
    getPage("change-activity/check-your-answers"),
    postFormlessPage("change-activity/check-your-answers")
    // TODO - add return sent after page has been developed
  )

  setup("variations-change-activity-none-produced", "none produced").withRequests(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getPage("select-change"),
    postPage("select-change", "changeActivity", "/change-activity/amount-produced"),
    getPage("change-activity/amount-produced"),
    postPage("change-activity/amount-produced", "none", "/change-activity/contract-packing"),
    getPage("change-activity/contract-packing"),
    postPage("change-activity/contract-packing", "true", "/change-activity/how-many-contract-packing-next-12-months"),
    getPage("change-activity/how-many-contract-packing-next-12-months"),
    postLitresPage("change-activity/how-many-contract-packing-next-12-months", "/change-activity/imports"),
    getPage("change-activity/imports"),
    postPage("change-activity/imports", "true", "/change-activity/how-many-imports-next-12-months"),
    getPage("change-activity/how-many-imports-next-12-months"),
    postLitresPage("change-activity/how-many-imports-next-12-months", "/change-activity/packaging-site-details"),
    getPage("change-activity/suggest-deregistration")
  )

  setup("variations-cancel-registration", "cancel registration").withRequests(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getPage("select-change"),
    postPage("select-change", "cancelRegistration"),
    getPage("/cancel-registration/reason"),
    postPage("/cancel-registration/reason", "some reason"),
    getPage("/cancel-registration/date"),
    postCancelDatePage,
    getPage("cancel-registration/check-your-answers"),
    // postFormlessPage("cancel-registration/check-your-answers") TODO - when CYA page developed
  )

  setup("variations-correct-return", "correct return").withRequests(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getPage("select-change"),
    postPage("select-change", "correctReturn"),
//    getPage("/correct-return/select"), //TODO - find a utr that has a return to correct or set one up
//    postSelectPeriodPage,

    getPage("correct-return/own-brands-packaged-at-own-sites"),
    postPage("correct-return/own-brands-packaged-at-own-sites", "true", "/correct-return/how-many-own-brands-packaged-at-own-sites"),
    getPage("correct-return/how-many-own-brands-packaged-at-own-sites"),
    postLitresPage("correct-return/how-many-own-brands-packaged-at-own-sites"),

    getPage("correct-return/packaged-as-contract-packer"),
    postPage("correct-return/packaged-as-contract-packer", "true", "/correct-return/how-many-packaged-as-contract-packer"),
    getPage("correct-return/how-many-packaged-as-contract-packer"),
    postLitresPage("correct-return/how-many-packaged-as-contract-packer"),

    getPage("correct-return/exemptions-for-small-producers"),
    postPage("correct-return/exemptions-for-small-producers", "no"),

    getPage("correct-return/small-producer-details"),
    postPage("correct-return/small-producer-details", "false"),

    getPage("correct-return/brought-into-uk"),
    postPage("correct-return/brought-into-uk", "true", "/correct-return/how-many-brought-into-uk"),
    getPage("correct-return/how-many-brought-into-uk"),
    postLitresPage("correct-return/how-many-brought-into-uk"),

    getPage("correct-return/brought-into-uk-from-small-producers"),
    postPage("correct-return/brought-into-uk-from-small-producers", "true", "/correct-return/how-many-into-uk-small-producers"),
    getPage("correct-return/how-many-into-uk-small-producers"),
    postLitresPage("correct-return/how-many-into-uk-small-producers"),

//    getPage("correct-return/claim-credits-for-exports"), TODO - complete after pages have been implemented
//    postPage("correct-return/claim-credits-for-exports", "true", "/correct-return/how-many-credits-for-exports"),
//    getPage("correct-return/how-many-credits-for-exports"),
//    postLitresPage("correct-return/how-many-credits-for-exports"),

    getPage("correct-return/claim-credits-for-lost-damaged"),
    postPage("correct-return/claim-credits-for-lost-damaged", "true", "/correct-return/how-many-credits-for-lost-damaged"),
    getPage("correct-return/how-many-credits-for-lost-damaged"),
    postLitresPage("correct-return/how-many-credits-for-lost-damaged"),

    getPage("correct-return/check-your-answers"),
//    postFormlessPage("correct-return/check-your-answers"), TODO - complete after pages have been implemented

    getPage("correct-return/correction-reason"),
    postPage("correct-return/correction-reason", "some reason"),
    getPage("correct-return/repayment-method"),
    postPage("correct-return/repayment-method", "bankAccount"),
    getPage("correct-return//check-your-answers"),

    getPage("correct-return/check-your-answers"),
    //    postFormlessPage("correct-return/check-your-answers"), TODO - complete after pages have been implemented
  )

  runSimulation()

}
