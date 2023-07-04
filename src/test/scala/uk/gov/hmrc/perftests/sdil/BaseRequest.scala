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
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.regex.RegexCheckType
import io.gatling.http.Predef._
import io.gatling.http.check.header.HttpHeaderRegexCheckType
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait BaseRequest extends ServicesConfiguration {

  val SDILReturnsBaseUrl: String = baseUrlFor("soft-drinks-industry-levy-returns-frontend")
  val submitVatReturnBaseUrl: String = baseUrlFor("soft-drinks-industry-levy-returns-frontend")

  val csrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  def saveCsrfToken(): CheckBuilder[RegexCheckType, String, String] =
    regex(_ => csrfPattern).optional.saveAs("csrfToken")

  def saveCookie(): CheckBuilder[HttpHeaderRegexCheckType, Response, String] =
    headerRegex("Set-Cookie", "mdtp=(.*)").optional.saveAs("cookie")
}