/*
 * Copyright 2016 Nicolas Rinaudo
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

package kantan.regex.joda

import kantan.codecs.export.Exported
import kantan.codecs.strings.StringDecoder
import kantan.codecs.strings.joda.time._
import kantan.regex._
import org.joda.time.{DateTime, LocalDate, LocalDateTime, LocalTime}

/** Brings all joda time instances in scope.
  *
  * Note that this is a convenience - the exact same effect can be achieved by importing
  * `kantan.codec.strings.joda.time._`. The sole purpose of this is to keep things simple for users that don't want or
  * need to learn about kantan.regex's internals.
  */
package object time extends JodaTimeDecoderCompanion[Option[String], DecodeError, codecs.type] {
  override def decoderFrom[D](d: StringDecoder[D]) = codecs.fromString(d)

  implicit val defaultDateTimeGroupDecoder: Exported[GroupDecoder[DateTime]] =
    Exported(defaultDateTimeDecoder)
  implicit val defaultLocalDateTimeGroupDecoder: Exported[GroupDecoder[LocalDateTime]] =
    Exported(defaultLocalDateTimeDecoder)
  implicit val defaultLocalDateGroupDecoder: Exported[GroupDecoder[LocalDate]] =
    Exported(defaultLocalDateDecoder)
  implicit val defaultLocalTimeGroupDecoder: Exported[GroupDecoder[LocalTime]] =
    Exported(defaultLocalTimeDecoder)
}
