# Homework

## Specification requirements

The first assignment to complete in the semester is a specification of your homework project. Note that not handing in a specification in by the deadline results in failing the course, as the homework assignment is a required part.

- It should be an easy to open and presentable file containing what you've chosen as your assignment. Good formats: pdf, txt. Bad formats: docx, png, exe.
- It can be high level. 3-10 sentences clearly describing the application that will be built is all that's expected here - keep it simple.

The specifications can be uploaded on [Moodle](https://edu.vik2.md.bme.hu/mod/assign/view.php?id=101399).

We don't expect large, complex applications to be implemented for this subject. We expect simple applications, but they have to be sensible, somewhat practical, and functional (as in operating well, not the [paradigm](https://en.wikipedia.org/wiki/Functional_programming)).

**Please note that Android applications may not be chosen as homework assignments, as they are covered in [their own course](https://www.aut.bme.hu/Course/android)**.

Tests are not generally required. For example, you are not expected to test GUI applications or web applications — but if you add tests, it's still appreciated. However, tests are required if you implement something very simple, such as the first idea below.

## Technical info and project hand-in

The starter projects in this repository might be useful for getting started, depending on the type of application you choose:

- Pure Kotlin Gradle project, for command line apps
  - [Configured with JDK 11](/projects/starters/kotlin-gradle-starter-jdk21) (recommended)
  - [Configured with JDK 17](/projects/starters/kotlin-gradle-starter-jdk17)
- JavaFX game project, for simple games, with basic rendering and input handling
  - [Configured with JDK 11](/projects/starters/javafx-game-starter-jdk21) (recommended)
  - [Configured with JDK 17](/projects/starters/javafx-game-starter-jdk17) 

You can also use the [Kotlin Multiplatform Wizard](https://kmp.jetbrains.com/) for Compose Multiplatform apps, including Desktop and Web apps.

We expect a zip file with the source code of the application created to be uploaded for the final hand-in, which is through [Moodle](https://edu.vik.bme.hu/mod/assign/view.php?id=101400).

> The easiest way to create a zip file without including unnecessary build artifacts and local files is to use the *File -> Export -> Project to Zip File...* option in IntelliJ IDEA.

Third-party dependencies may be used in the project, as long as the project itself still contains meaningful code. Please include these through Maven or Gradle, if possible, so that your project can be built and tested after hand-in.
 
Documentation for your project is not required, but if starting and testing the project is non-trivial, a short README with instructions would be appreciated.

## List of ideas

You'll find a list of possible homework ideas below. These are here to give you pointers on the kinds of applications you may choose to create. You can choose one of these ideas directly as-is, alter these ideas to suit what you feel like building, or come up with anything on your own that's of similar complexity.

As the course's materials don't cover any specific UI or web frameworks, command line apps can also be created, but you should feel free to use any frameworks you know or can learn independently.

### Matrix class

Implement a `Matrix` type which is idiomatic Kotlin. Have it implement any applicable standard interfaces, and all operators that make sense for such a type.

Cover the class with JUnit based tests. *Note that if your homework is of this complexity (a single class implementation), tests are required as part of the assignment.*

> Optionally, make sure that your implementation is multiplatform-ready, by placing it all in a common module. Have your tests execute on at least two platforms.

### Simple games

Implement a game such as snake, pacman, or tetris. Run an event loop, handle user input, and draw things on the screen, handle pausing and/or restarting the current game nicely. Optionally, save and display high scores.

See [this tutorial](https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835) and [this repository](https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development) for good samples of doing these things in JavaFX.

Your game can also use the command line for input and output instead of a graphics library.

### Game of Life

Implement the classic Game of Life cellular automaton. Use JavaFX for the graphical user interface, and make it interactive. Let users select and unselect cells, and start/stop/reset the simulation.

### Data analysis

Process a freely available dataset using Kotlin's advanced File handling and collection processing features. Pay attention to the readability and performance of your code. For example, using [this](https://www.kaggle.com/datasnaek/youtube-new) data set about Trending YouTube videos from [Kaggle](https://www.kaggle.com), answer questions such as:

- What are the top 5 categories of trending videos in Germany?
- Which videos trended in more than half of the countries?
- Are there any videos that trended in almost all countries, but are missing from just one or two of them?
- Which videos with more than 100k views have the best and worst like/dislike ratios?
- How many channels had just a single highly trending video?

Come up with interesting questions that *you* would want to know about a data set, and answer those!

Another interesting source of data might be [data.world](https://data.world/datasets/open-data).

### Custom sequences

Implement your own [`Sequence`](https://kotlinlang.org/docs/reference/sequences.html) type, with lazily evaluated operations, and terminal operators. The implementation can build on similar concepts as the original implementation, but of course, may not be a straight copy of it. Cover your implementation with JUnit based tests.

> Optionally, make sure that your implementation is multiplatform-ready, by placing it all in a common module. Have your tests execute on multiple platforms, at least the JVM and Native.

### API browser

Create a Compose Desktop or JavaFX application that accesses data from a public API, and enables browsing that data. Display lists of the data from the API (with sorting/filtering options), show a details page when the user selects a specific item (with related items), load images, etc.

You can find a wide variety of free, public APIs [here](https://github.com/public-apis/public-apis). Some might require you to register for an access token to authenticate with.

For your networking needs, the [Ktor client](https://ktor.io/clients/http-client.html) or [Retrofit](https://square.github.io/retrofit/) are both good choices.

### Basic multiplatform app

Create a simple application (for example, a basic tip calculator) that can compile to at least three different [target platforms](https://kotlinlang.org/docs/multiplatform-dsl-reference.html#targets) of Kotlin. Ideally, you should perform network calls or use a database in the application.

### Ktor app

Use [Ktor](https://ktor.io/), a *very Kotlin* web framework from JetBrains to create a simple web application that provides a REST API and stores data persistently in a database. The application should contain at least some basic business logic in addition to accessing and storing data.

This can be a pizza delivery service, a favourite movies app, or anything else that provides similar functionality.

### Spring Boot app

Same as the Ktor app idea above, using the [Spring Boot](https://spring.io/projects/spring-boot) framework (see also: [the Spring Initializr](https://start.spring.io/)).

### DSL

Create a domain specific language using Kotlin's advanced syntax capabilities. See the [official documentation](https://kotlinlang.org/docs/reference/type-safe-builders.html), [this showcase](https://www.grokkingandroid.com/creating-kotlin-dsls/), or [this article](https://zsmb.co/kotlin-dsl-design-with-village-dsl/) as learning resources. Choose your own problem domain for the task. Some vague ideas:
 
- State machines
- Organizational structures
- Graphs
- Validation rules
