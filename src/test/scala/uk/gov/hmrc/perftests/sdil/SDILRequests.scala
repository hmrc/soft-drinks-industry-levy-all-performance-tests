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
    http("get sdil own-brands-packaged-at-own-sites page")
      .get(s"$baseUrl/$route/own-brands-packaged-at-own-sites": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("post sdil own-brands-packaged-at-own-sites page")
      .post(s"$baseUrl/$route/own-brands-packaged-at-own-sites": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))
//      .check(header("Location").is(s"/$baseUrl/how-many-own-brands-packaged-at-own-sites": String))
  }

  def getHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("get sdil how-many-own-brands-packaged-at-own-sites page")
      .get(s"$baseUrl/$route/how-many-own-brands-packaged-at-own-sites": String)
      .check(saveCsrfToken())
      .check(status.is(200))
  }

  def postHowManyOwnBrandsPackagedAtOwnSitesPage: HttpRequestBuilder = {
    http("post sdil how-many-own-brands-packaged-at-own-sites page")
      .post(s"$baseUrl/$route/how-many-own-brands-packaged-at-own-sites": String)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("lowBand", "100")
      .formParam("highBand", "100")
      .check(status.is(303))
//      .check(header("Location").is(s"/$baseUrl/packaged-as-contract-packer": String))
  }

}
