---
layout: tutorial
title: "Java 8 dates and times"
section: tutorial
sort_order: 10
---
Java 8 comes with a better thought out dates and times API. Unfortunately, it cannot be supported as part of the core
kantan.regex API - we still support Java 7. There is, however, a dedicated optional module that you can include by
adding the following line to your `build.sbt` file:

```scala
libraryDependencies += "com.nrinaudo" %% "kantan.regex-java8" % "@VERSION@"
```

You then need to import the corresponding package:

```tut:silent
import kantan.regex.java8._
```

And this will bring [`GroupDecoder`] instances in scope for the following types:

* [`Instant`]
* [`LocalDateTime`]
* [`ZonedDateTime`]
* [`OffsetDateTime`]
* [`LocalDate`]
* [`LocalTime`]

These will use the default Java 8 formats. For example:

```tut:silent
import kantan.regex.implicits._
import java.time._

val input = "[1978-10-12] and [2015-01-09]"
```

We can decode the bracketed dates without providing an explicit decoder:

```tut
input.evalRegex[LocalDate](rx"\[(\d\d\d\d-\d\d-\d\d)\]", 1).foreach(println _)
```

It's also possible to provide your own format. For example, for [`LocalDateTime`]:

```tut:silent
import java.time.format.DateTimeFormatter
import java.time.LocalDate
import kantan.regex._

implicit val decoder: GroupDecoder[LocalDate] = localDateDecoder(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

val input = "[10/12/1978] and [09/01/2015]"
```

And we can now simply write:

```tut
input.evalRegex[LocalDate](rx"\[(\d\d/\d\d/\d\d\d\d)\]", 1).foreach(println _)
```

[`GroupDecoder`]:{{ site.baseurl }}/api/kantan/regex/package$$GroupDecoder.html
[`Instant`]:https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html
[`LocalDateTime`]:https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html
[`OffsetDateTime`]:https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html
[`ZonedDateTime`]:https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html
[`LocalDate`]:https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
[`LocalTime`]:https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html
