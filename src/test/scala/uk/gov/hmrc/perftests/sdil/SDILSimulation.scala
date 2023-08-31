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
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.sdil.AuthRequests._
import uk.gov.hmrc.perftests.sdil.SDILReturnsRequests._
import uk.gov.hmrc.perftests.sdil.SDILVariationsRequests.{getPage, postCancelDatePage, postContactDetailsAddPage, postFormlessPage, postLitresPage, postPage}
import uk.gov.hmrc.perftests.sdil.SetupRequests._

class SDILSimulation extends PerformanceTestRunner
  with SDILReturnJourneyRequests
  with SDILVariationsJourneyRequests {

  setup("sdil-returns-journey-1", "SDIL Returns journey 1").withRequests(
    sdilReturnJourney1Requests:_*
  )

  setup("sdil-returns-journey-2", "SDIL Returns journey 2").withRequests(
    sdilReturnJourney2Requests:_*
  )

  setup("variations-update-registered-details-update-contact", "update-contact").withRequests(
    sdilVariationUpdateRegisteredDetailsUpdateContactJourneyRequests:_*
  )

  setup("variations-update-registered-details-update-site", "update-packaging-site").withRequests(
    sdilVariationUpdateRegisteredDetailsUpdateSiteJourneyRequests:_*
  )

  setup("variations-update-registered-details-remove-site", "remove-site").withRequests(
    sdilVariationUpdateRegisteredDetailsRemoveSiteJourneyRequests:_*
  )

  setup("variations-change-activity-one-million-or-more", "1 million or more").withRequests(
    sdilVariationChangeActivityOneMillionLitresOrMoreJourneyRequests:_*
  )

  setup("variations-change-activity-less-than-one-million", "less than 1 million").withRequests(
    sdilVariationChangeActivityLessThanOneMillionLitresJourneyRequests:_*
  )

  setup("variations-change-activity-none-produced", "none produced").withRequests(
    sdilVariationChangeActivityNoneProducedJourneyRequests:_*
  )

  setup("variations-cancel-registration", "cancel registration").withRequests(
    sdilVariationCancelRegistrationJourneyRequests:_*
  )

  setup("variations-correct-return", "correct return").withRequests(
    sdilVariationCorrectReturnJourneyRequests:_*
  )

  runSimulation()

}
