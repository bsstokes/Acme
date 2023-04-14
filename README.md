# Acme

## Project

* Repository: https://github.com/bsstokes/acme
* Requirements
    * Android Studio Flamingo (2022.2.1)
    * JDK 17 (This is the version that ships with Android Studio.)

I organized my work using [issues][issues] (for high-level features) and
[pull requests][pull_requests], which I hope helps demonstrate my thought process as I built it. Is
this overkill? You bet!

## Running

Run the app using the "app" run configuration.

## Testing

You can run all unit tests from the IDE or command line:

* IDE: Use the "All unit tests" run configuration.
* CLI: `./gradlew :app:testDebugUnitTest :domain:test :data:test :algorithm:test`

I opted to use only unit tests for this project since they cover all the cases I was interested in.
The Compose UI tests are implemented using Robolectric, where we can verify our views are correct
without needing an emulator.

## Structure

The app is split into several modules:

* `:algorithm`: Implementation of algorithm used to make assignments
* `:app`: Android application
* `:domain`: Domain-specific "core" classes
* `:data`: Implementation of repository
* `:test-utils`: Test helpers

## Algorithm

I chose to use the [Hungarian algorithm][wiki] to make the assignments. That article has a nice
collection of [implementations][implementations], from which I chose
[this Java library][algorithm].

[issues]: https://github.com/bsstokes/Acme/issues?q=is%3Aissue
[pull_requests]: https://github.com/bsstokes/Acme/pulls?q=is%3Apr
[wiki]: https://en.wikipedia.org/wiki/Hungarian_algorithm
[implementations]: https://en.wikipedia.org/wiki/Hungarian_algorithm#Implementations
[algorithm]: https://github.com/KevinStern/software-and-algorithms/blob/master/src/main/java/blogspot/software_and_algorithms/stern_library/optimization/HungarianAlgorithm.java
