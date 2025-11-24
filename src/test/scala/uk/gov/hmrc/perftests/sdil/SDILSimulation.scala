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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner

class SDILSimulation
    extends PerformanceTestRunner
    with SDILRegistrationJourneyRequests
    with SDILReturnJourneyRequests
    with SDILVariationsJourneyRequests {

  setup("sdil-registration-full-allYes-largeProducer", "SDIL Registrations for large producer answers yes to all")
    .withRequests(sdilRegisterAnswerYesToAllForLargeProducerJourneyRequests*)
  setup("sdil-registration-full-allYes-smallProducer", "SDIL Registrations for small producer answers yes to all")
    .withRequests(sdilRegisterAnswerYesToAllForSmallProducerJourneyRequests*)
  setup("sdil-registration-full-allYes-noneProducer", "SDIL Registrations for none producer answers yes to all")
    .withRequests(sdilRegisterAnswerYesToAllForNoneProducerJourneyRequests*)

  setup("sdil-returns-journey-1", "SDIL Returns journey 1")
    .withRequests(sdilReturnJourney1Requests*)
  setup("sdil-returns-journey-2", "SDIL Returns journey 2")
    .withRequests(sdilReturnJourney2Requests*)

  setup("variations-update-registered-details-update-contact", "update-contact")
    .withRequests(sdilVariationUpdateRegisteredDetailsUpdateContactJourneyRequests*)
  setup("variations-update-registered-details-update-site", "update-packaging-site")
    .withRequests(sdilVariationUpdateRegisteredDetailsUpdateSiteJourneyRequests*)
  setup("variations-update-registered-details-remove-site", "remove-site")
    .withRequests(sdilVariationUpdateRegisteredDetailsRemoveSiteJourneyRequests*)
  setup("variations-change-activity-one-million-or-more", "1 million or more")
    .withRequests(sdilVariationChangeActivityOneMillionLitresOrMoreJourneyRequests*)
  setup("variations-change-activity-less-than-one-million", "less than 1 million")
    .withRequests(sdilVariationChangeActivityLessThanOneMillionLitresJourneyRequests*)
  setup("variations-change-activity-none-produced", "none produced")
    .withRequests(sdilVariationChangeActivityNoneProducedJourneyRequests*)
  setup("variations-cancel-registration", "cancel registration")
    .withRequests(sdilVariationCancelRegistrationJourneyRequests*)
  setup("variations-correct-return", "correct return")
    .withRequests(sdilVariationCorrectReturnJourneyRequests*)

  runSimulation()

}
