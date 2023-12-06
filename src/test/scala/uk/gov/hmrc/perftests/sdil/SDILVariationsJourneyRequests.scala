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

import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.sdil.AuthRequests.{createAuthSession, navigateToAuth, navigateToAuthSession}
import uk.gov.hmrc.perftests.sdil.SDILAccountRequests.{getAccountHomePage, postAccountHomePageStartNoActivityReturn, postAccountHomePageTellHMRCAboutAChange}
import uk.gov.hmrc.perftests.sdil.SDILReturnsRequests.{getCheckYourAnswersPage, getReturnSentPage, postCheckYourAnswersPage}
import uk.gov.hmrc.perftests.sdil.SDILVariationsRequests._

trait SDILVariationsJourneyRequests {

  val sdilVariationUpdateRegisteredDetailsUpdateContactJourneyRequests: Seq[HttpRequestBuilder] = Seq(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getAccountHomePage,
    //postAccountHomePageTellHMRCAboutAChange,
    getPage("select-change"),
    postPage("select-change", "updateRegisteredDetails", "change-registered-details"),
    getPage("change-registered-details"),
    postChangeContactDetailsOnlyPage("change-registered-details"),
    getPage("change-registered-details/contact-details-add"),
    postContactDetailsAddPage("change-registered-details/check-your-answers"),
    getPage("change-registered-details/check-your-answers"),
    postFormlessPage("change-registered-details/check-your-answers", "change-registered-details/update-done"),
    getPage("change-registered-details/update-done")
  )

  val sdilVariationUpdateRegisteredDetailsUpdateSiteJourneyRequests: Seq[HttpRequestBuilder] = Seq(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getAccountHomePage,
    //postAccountHomePageTellHMRCAboutAChange,
    getPage("select-change"),
    postPage("select-change", "updateRegisteredDetails", "change-registered-details"),
    getPage("change-registered-details"),
    postChangeSitesOnlyPage("change-registered-details"),
    getPage("change-registered-details/packaging-site-details"),
    postPackagingSiteDetailsPage("change-registered-details/packaging-site-details", "true", "change-registered-details/packaging-site-details"),
    getPage("change-registered-details/packaging-site-details"),
    postPage("change-registered-details/packaging-site-details", "false", "change-registered-details/warehouse-details"),
    getPage("change-registered-details/warehouse-details"),
    postPageRedirectToAddressLookup("change-registered-details/warehouse-details", "true", "secondary-warehouses"),
    getPage("change-registered-details/warehouse-details"),
    postPage("change-registered-details/warehouse-details", "false", "change-registered-details/check-your-answers"),
    getPage("change-registered-details/check-your-answers"),
    postFormlessPage("change-registered-details/check-your-answers", "change-registered-details/update-done"),
    getPage("change-registered-details/update-done")
  )

  val sdilVariationUpdateRegisteredDetailsRemoveSiteJourneyRequests: Seq[HttpRequestBuilder] = Seq(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getAccountHomePage,
    //postAccountHomePageTellHMRCAboutAChange,
    getPage("select-change"),
    postPage("select-change", "updateRegisteredDetails", "change-registered-details"),
    getPage("change-registered-details"),
    postChangeSitesOnlyPage("change-registered-details"),
    getPage("change-registered-details/packaging-site-details"),
    postPackagingSiteDetailsPage("change-registered-details/packaging-site-details", "true", "change-registered-details/packaging-site-details"),
    getPage("change-registered-details/packaging-site-details"),
    getPage("change-registered-details/packaging-site-details/remove/0"),
    postPackagingSiteDetailsPage("change-registered-details/packaging-site-details/remove/0", "true", "change-registered-details/packaging-site-details"),
    getPage("change-registered-details/packaging-site-details"),
    postPage("change-registered-details/packaging-site-details", "false", "change-registered-details/warehouse-details"),
    getPage("change-registered-details/warehouse-details"),
    getPage("change-registered-details/warehouse-details/remove/1"),
    postPage("change-registered-details/warehouse-details/remove/1", "false", "change-registered-details/warehouse-details"),
    getPage("change-registered-details/warehouse-details"),
    postPage("change-registered-details/warehouse-details", "false", "change-registered-details/check-your-answers"),
    getPage("change-registered-details/check-your-answers"),
    postFormlessPage("change-registered-details/check-your-answers", "change-registered-details/update-done"),
    getPage("change-registered-details/update-done")
  )

  val sdilVariationChangeActivityOneMillionLitresOrMoreJourneyRequests: Seq[HttpRequestBuilder] = Seq(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getAccountHomePage,
    //postAccountHomePageTellHMRCAboutAChange,
    getPage("select-change"),
    postPage("select-change", "changeActivity", "change-activity/amount-produced"),
    getPage("change-activity/amount-produced"),
    postPage("change-activity/amount-produced", "large", "change-activity/operate-packaging-site"),
    getPage("change-activity/operate-packaging-site"),
    postPage("change-activity/operate-packaging-site", "true", "change-activity/how-many-own-brands-next-12-months"),
    getPage("change-activity/how-many-own-brands-next-12-months"),
    postLitresPage("change-activity/how-many-own-brands-next-12-months", "change-activity/contract-packing"),
    getPage("change-activity/contract-packing"),
    postPage("change-activity/contract-packing", "true", "change-activity/how-many-contract-packing-next-12-months"),
    getPage("change-activity/how-many-contract-packing-next-12-months"),
    postLitresPage("change-activity/how-many-contract-packing-next-12-months", "change-activity/imports"),
    getPage("change-activity/imports"),
    postPage("change-activity/imports", "true", "change-activity/how-many-imports-next-12-months"),
    getPage("change-activity/how-many-imports-next-12-months"),
    postLitresPage("change-activity/how-many-imports-next-12-months", "change-activity/packaging-site-details"),
    getPage("change-activity/packaging-site-details"),
    postPage("change-activity/packaging-site-details", "false", "change-activity/secondary-warehouse-details"),
    getPage("change-activity/secondary-warehouse-details"),
    postPage("change-activity/secondary-warehouse-details", "false", "change-activity/check-your-answers"),
    getPage("change-activity/check-your-answers"),
    postFormlessPage("change-activity/check-your-answers", "change-activity/variation-done"),
    getPage("change-activity/variation-done")
  )

  val sdilVariationChangeActivityLessThanOneMillionLitresJourneyRequests: Seq[HttpRequestBuilder] = Seq(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getAccountHomePage,
    //postAccountHomePageTellHMRCAboutAChange,
    getPage("select-change"),
    postPage("select-change", "changeActivity", "change-activity/amount-produced"),
    getPage("change-activity/amount-produced"),
    postPage("change-activity/amount-produced", "small", "change-activity/third-party-packagers"),
    getPage("change-activity/third-party-packagers"),
    postPage("change-activity/third-party-packagers", "true", "change-activity/operate-packaging-site"),
    getPage("change-activity/operate-packaging-site"),
    postPage("change-activity/operate-packaging-site", "true", "change-activity/how-many-own-brands-next-12-months"),
    getPage("change-activity/how-many-own-brands-next-12-months"),
    postLitresPage("change-activity/how-many-own-brands-next-12-months", "change-activity/contract-packing"),
    getPage("change-activity/contract-packing"),
    postPage("change-activity/contract-packing", "true", "change-activity/how-many-contract-packing-next-12-months"),
    getPage("change-activity/how-many-contract-packing-next-12-months"),
    postLitresPage("change-activity/how-many-contract-packing-next-12-months", "change-activity/imports"),
    getPage("change-activity/imports"),
    postPage("change-activity/imports", "true", "change-activity/how-many-imports-next-12-months"),
    getPage("change-activity/how-many-imports-next-12-months"),
    postLitresPage("change-activity/how-many-imports-next-12-months", "change-activity/packaging-site-details"),
    getPage("change-activity/packaging-site-details"),
    postPage("change-activity/packaging-site-details", "false", "change-activity/secondary-warehouse-details"),
    getPage("change-activity/secondary-warehouse-details"),
    postPage("change-activity/secondary-warehouse-details", "false", "change-activity/check-your-answers"),
    getPage("change-activity/check-your-answers"),
    postFormlessPage("change-activity/check-your-answers", "change-activity/variation-done"),
    getPage("change-activity/variation-done")
  )

  val sdilVariationChangeActivityNoneProducedJourneyRequests: Seq[HttpRequestBuilder] = Seq(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getAccountHomePage,
    //postAccountHomePageTellHMRCAboutAChange,
    getPage("select-change"),
    postPage("select-change", "changeActivity", "change-activity/amount-produced"),
    getPage("change-activity/amount-produced"),
    postPage("change-activity/amount-produced", "none", "change-activity/contract-packing"),
    getPage("change-activity/contract-packing"),
    postPage("change-activity/contract-packing", "true", "change-activity/how-many-contract-packing-next-12-months"),
    getPage("change-activity/how-many-contract-packing-next-12-months"),
    postLitresPage("change-activity/how-many-contract-packing-next-12-months", "change-activity/imports"),
    getPage("change-activity/imports"),
    postPage("change-activity/imports", "true", "change-activity/how-many-imports-next-12-months"),
    getPage("change-activity/how-many-imports-next-12-months"),
    postLitresPage("change-activity/how-many-imports-next-12-months", "change-activity/packaging-site-details"),
    getPage("change-activity/suggest-deregistration")
  )

  val sdilVariationCancelRegistrationJourneyRequests: Seq[HttpRequestBuilder] = Seq(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,
    getAccountHomePage,
    //postAccountHomePageTellHMRCAboutAChange,
    getPage("select-change"),
    postPage("select-change", "cancelRegistration", "cancel-registration/file-return-before-deregistration"),
    getPage("cancel-registration/reason"),
    postPage("cancel-registration/reason", "some reason", "cancel-registration/date"),
    getPage("cancel-registration/date"),
    postCancelDatePage,
    getPage("cancel-registration/check-your-answers"),
    postFormlessPage("cancel-registration/check-your-answers", "cancel-registration/cancellation-request-done"),
    getPage("cancel-registration/cancellation-request-done")
  )

  val sdilVariationCorrectReturnJourneyRequests: Seq[HttpRequestBuilder] = Seq(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,

    getAccountHomePage,
    //postAccountHomePageStartNoActivityReturn,
    getCheckYourAnswersPage,
    postCheckYourAnswersPage,
    getReturnSentPage,

    getAccountHomePage,
    postAccountHomePageTellHMRCAboutAChange,

    getPage("select-change"),
    postPage("select-change", "correctReturn", "correct-return/select"),

    getPage("/correct-return/select"),
    postPage("select", "YEAR-2023-QUARTER-1", "correct-return/own-brands-packaged-at-own-sites"),
    //postSelectPeriodPage,

    getPage("correct-return/own-brands-packaged-at-own-sites"),
    postPage("correct-return/own-brands-packaged-at-own-sites", "true", "correct-return/how-many-own-brands-packaged-at-own-sites"),
    getPage("correct-return/how-many-own-brands-packaged-at-own-sites"),
    postLitresPage("correct-return/how-many-own-brands-packaged-at-own-sites"),

    getPage("correct-return/packaged-as-contract-packer"),
    postPage("correct-return/packaged-as-contract-packer", "true", "correct-return/how-many-packaged-as-contract-packer"),
    getPage("correct-return/how-many-packaged-as-contract-packer"),
    postLitresPage("correct-return/how-many-packaged-as-contract-packer"),

    getPage("correct-return/exemptions-for-small-producers"),
    postPage("correct-return/exemptions-for-small-producers", "no"),

    getPage("correct-return/small-producer-details"),
    postPage("correct-return/small-producer-details", "false"),

    getPage("correct-return/brought-into-uk"),
    postPage("correct-return/brought-into-uk", "true", "correct-return/how-many-brought-into-uk"),
    getPage("correct-return/how-many-brought-into-uk"),
    postLitresPage("correct-return/how-many-brought-into-uk"),

    getPage("correct-return/brought-into-uk-from-small-producers"),
    postPage("correct-return/brought-into-uk-from-small-producers", "true", "correct-return/how-many-into-uk-small-producers"),
    getPage("correct-return/how-many-into-uk-small-producers"),
    postLitresPage("correct-return/how-many-into-uk-small-producers"),

    getPage("correct-return/claim-credits-for-exports"), //TODO - complete after pages have been implemented
    postPage("correct-return/claim-credits-for-exports", "true", "correct-return/how-many-credits-for-exports"),
    getPage("correct-return/how-many-credits-for-exports"),
    postLitresPage("correct-return/how-many-credits-for-exports"),

    getPage("correct-return/claim-credits-for-lost-damaged"),
    postPage("correct-return/claim-credits-for-lost-damaged", "true", "correct-return/how-many-credits-for-lost-damaged"),
    getPage("correct-return/how-many-credits-for-lost-damaged"),
    postLitresPage("correct-return/how-many-credits-for-lost-damaged"),

    getPage("correct-return/check-your-answers"),
    postFormlessPage("correct-return/check-your-answers"), //TODO - complete after pages have been implemented

    getPage("correct-return/correction-reason"),
    postPage("correct-return/correction-reason", "some reason"),
    getPage("correct-return/repayment-method"),
    postPage("correct-return/repayment-method", "bankAccount"),
    getPage("correct-return//check-your-answers"),

    getPage("correct-return/check-your-answers"),
    postFormlessPage("correct-return/check-your-answers"), //TODO - complete after pages have been implemented
  )
}
