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

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.sdil.AuthRequests.saveCsrfToken

object SDILRequests extends ServicesConfiguration {

  val baseUrl: String = baseUrlFor("soft-drinks-industry-levy-returns-frontend")
  val route: String   = "soft-drinks-industry-levy-returns-frontend"

  def getOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("GET own-brands-packaged-at-own-sites")
      .get(s"$baseUrl/$route/own-brands-packaged-at-own-sites": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("POST own-brands-packaged-at-own-sites")
      .post(s"$baseUrl/$route/own-brands-packaged-at-own-sites": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$route/how-many-own-brands-packaged-at-own-sites": String))
  }

  def getHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("GET how-many-own-brands-packaged-at-own-sites")
      .get(s"$baseUrl/$route/how-many-own-brands-packaged-at-own-sites": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("POST how-many-own-brands-packaged-at-own-sites")
      .post(s"$baseUrl/$route/how-many-own-brands-packaged-at-own-sites": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$route/packaged-as-contract-packer": String))
  }

  def getPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("GET packaged-as-contract-packer")
      .get(s"$baseUrl/$route/packaged-as-contract-packer": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("POST packaged-as-contract-packer")
      .post(s"$baseUrl/$route/packaged-as-contract-packer": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$route/how-many-packaged-as-contract-packer": String))
  }

  def getHowManyPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("GET how-many-packaged-as-contract-packer")
      .get(s"$baseUrl/$route/how-many-packaged-as-contract-packer": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyPackagedAsContractPackerPage: HttpRequestBuilder = {
    http("POST how-many-packaged-as-contract-packer")
      .post(s"$baseUrl/$route/how-many-packaged-as-contract-packer": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
      .check(header("Location").is(s"/$route/exemptions-for-small-producers": String))
  }

  def getExemptionsForSmallProducersPage: HttpRequestBuilder = {
    http("GET exemptions-for-small-producers")
      .get(s"$baseUrl/$route/exemptions-for-small-producers": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postExemptionsForSmallProducersPage: HttpRequestBuilder = {
    http("POST exemptions-for-small-producers")
      .post(s"$baseUrl/$route/exemptions-for-small-producers": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
      .check(header("Location").is(s"/$route/add-small-producer": String))
  }

  def getAddSmallProducerPage: HttpRequestBuilder = {
    http("GET add-small-producer")
      .get(s"$baseUrl/$route/add-small-producer": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postAddSmallProducerPage: HttpRequestBuilder = {
    http("POST add-small-producer")
      .post(s"$baseUrl/$route/add-small-producer": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("producerName", "Fake Producer")
      .formParam("referenceNumber", "XGSDIL000001611")
      .formParam("lowBand", "1000")
      .formParam("highBand", "1000")
      .check(status.is(303))
      .check(header("Location").is(s"/$route/small-producer-details": String))
  }

  def getSmallProducerDetailsPage: HttpRequestBuilder = {
    http("GET small-producer-details")
      .get(s"$baseUrl/$route/small-producer-details": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postSmallProducerDetailsPage: HttpRequestBuilder = {
    http("POST small-producer-details")
      .post(s"$baseUrl/$route/small-producer-details": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "false")
      .check(status.is(303))
      .check(header("Location").is(s"/$route/brought-into-uk": String))
  }

}
