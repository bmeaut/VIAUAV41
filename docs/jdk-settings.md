# JDK settings

Setting the correct JDK in IntelliJ IDEA can be tricky sometimes, as it has to be set correctly in two different places.

First, set it in the project settings (*File -> Project Structure -> Project tab*):

![Project level SDK settings](./images/jdk-settings-project.png)

Then go to the global IDE settings (*File -> Settings*), and check that Gradle (*Build, Execution, Deployment -> Build Tools -> Gradle*) is set to use the same JDK. This can either be set explicitly, or by choosing to use the Project SDK:

![Gradle JDK settings](./images/jdk-settings-gradle.png)

---

The list of configured JDKs (that will be available to choose in the two locations mentioned above) can be checked by going to the *SDKs* tab of the *Project structure* dialog:

![List of available SDKs](./images/jdk-settings-jdks.png)

For more about managing SDKs, see the [SDKs page](https://www.jetbrains.com/help/idea/sdk.html) of the IDEA documentation.
