If you want to explore the sourcecode and maybe even improve it, first of all a :thumbsup: for you, and here are the instructions on getting the source code and building it on your machine:

## Setting up your local git-repo

```shell
git clone https://github.com/opencodeiiita/PanoViewer.git
cd PanoViewer
```

## Building the plugin with Gradle

This project uses the so-called Gradle wrapper. That means you have to install nothing on your machine in order
to build the project. The wrapper consists of the two scripts `gradlew` (for UNIX-based systems like Mac and Linux)
and `gradlew.bat` (for systems running Windows). The following examples shows the commands for Linux/Mac users,
Windows users can simply replace `./gradlew` with `./gradlew.bat`.

If you want to create jar file or run tests, then the following command is for you:
```shell
./gradlew build
```
For info about other available tasks you can run
```shell
./gradlew tasks
```
