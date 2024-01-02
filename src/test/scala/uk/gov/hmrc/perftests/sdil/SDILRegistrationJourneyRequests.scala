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
import uk.gov.hmrc.perftests.sdil.SDILRegistrationRequests.{getPage, postPage, _}
import uk.gov.hmrc.perftests.sdil.SetupRequests._

trait SDILRegistrationJourneyRequests {

  val resetDataAndSignin = Seq(
    resetPending,
    resetReturns,
    resetRegistrations,
    sdilReturnsCollectionReset,
    resetReturnsUserAnswers(),
    navigateToAuth,
    createAuthSession("3000000000"),
    navigateToAuthSession,
  )

  def specificPagesForProducerType(producerType: String, allYes: Boolean = true): Seq[HttpRequestBuilder] = {
    producerType match {
      case "large" if allYes=> Seq(
        postPage("how-many-litres-globally", "large", "/operate-packaging-sites"),
        getPage("operate-packaging-sites"),
        postPage("operate-packaging-sites", "true", "/how-many-own-brands-next-12-months"),
        getPage("operate-packaging-sites"),
        postLitresPage("how-many-own-brands-next-12-months", "/contract-packing")
      )
      case "large" => Seq(
        postPage("how-many-litres-globally", "large", "/operate-packaging-sites"),
        getPage("operate-packaging-sites"),
        postPage("operate-packaging-sites", "false", "/contract-packing")
      )
      case "small" if allYes => Seq(
        postPage("how-many-litres-globally", "small", "/third-party-packagers"),
        getPage("third-party-packagers"),
        postPage("third-party-packagers", "true", "/operate-packaging-sites"),
        getPage("operate-packaging-sites"),
        postPage("operate-packaging-sites", "true", "/how-many-own-brands-next-12-months"),
        getPage("operate-packaging-sites"),
        postLitresPage("how-many-own-brands-next-12-months", "/contract-packing")
      )
      case "small" => Seq(
        postPage("how-many-litres-globally", "small", "/third-party-packagers"),
        getPage("third-party-packagers"),
        postPage("third-party-packagers", "false", "/operate-packaging-sites"),
        getPage("operate-packaging-sites"),
        postPage("operate-packaging-sites", "false", "/contract-packing")
      )
      case _ => Seq(
        postPage("how-many-litres-globally", "xnot", "/contract-packing"),
      )
    }
  }

  def sdilRegisterAnswerYesToAllForGivenProducerTypeJourneyRequests(producerType: String): Seq[HttpRequestBuilder] = {
    resetDataAndSignin ++
    Seq(
      getStartPage(),
      getPage("verify"),
      postPage("verify", "yesRegister", "/organisation-type"),
      getPage("organisation-type"),
      postPage("organisation-type", "limitedcompany", "/how-many-litres-globally"),
      getPage ("how-many-litres-globally")
    ) ++ specificPagesForProducerType(producerType) ++ Seq(
      postPage("contract-packing", "true", "/how-many-contract-packing-next-12-months"),
      getPage ("how-many-contract-packing-next-12-months"),
      postLitresPage("how-many-contract-packing-next-12-months", "/imports"),
      getPage("imports"),
      postPage("imports", "true", "/how-many-imports-next-12-months"),
      getPage("how-many-imports-next-12-months"),
      postLitresPage("how-many-imports-next-12-months", "/start-date"),
      getPage("start-date"),
      postStartDatePage("/pack-at-business-address"),
      getPage ("pack-at-business-address"),
      postPage("pack-at-business-address", "true", "/packaging-site-details"),
      getPage ("packaging-site-details"),
      postPage("packaging-site-details", "false", "/ask-secondary-warehouses"),
      getPage("ask-secondary-warehouses"),
      postPage("ask-secondary-warehouses", "false", "/contact-details"),
      getPage("contact-details"),
      postContactDetailsPage("/check-your-answers"),
      getPage("check-your-answers"),
      postCheckYourAnswersPage,
      getRegisterConfirmationPage
    )
  }

  val sdilRegisterAnswerYesToAllForLargeProducerJourneyRequests = sdilRegisterAnswerYesToAllForGivenProducerTypeJourneyRequests("large")
  val sdilRegisterAnswerYesToAllForSmallProducerJourneyRequests = sdilRegisterAnswerYesToAllForGivenProducerTypeJourneyRequests("small")
  val sdilRegisterAnswerYesToAllForNoneProducerJourneyRequests = sdilRegisterAnswerYesToAllForGivenProducerTypeJourneyRequests("none")

}
