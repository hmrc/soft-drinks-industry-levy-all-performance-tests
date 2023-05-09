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
import uk.gov.hmrc.perftests.sdil.SDILRequests._

class SDILSimulation extends PerformanceTestRunner {

  setup("sdil-returns-journey", "SDIL Returns journey").withRequests(
    navigateToAuth,
    createAuthSession(),
    navigateToAuthSession,

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

  runSimulation()

}
