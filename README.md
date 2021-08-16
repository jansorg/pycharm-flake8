# Flake8 support
This plugin adds support for flake8 `# noqa` and `# flake8: noqa`.
With this plugin installed PyCharm will now show error markers any more as long as one of the two markers are defined.

Previously you had to do this to remove warnings from both flake8 and PyCharm:
```python
# noinspection PyUnusedLocal
def foo():
    x = 1 # noqa
```

Now you can just use flake8's markers:
```python
def foo():
    x = 1 # noqa
```

or 

```python
# flake8: noqa
def foo():
    x = 1
```

# Contribution guidelines
- I'm not planning to do much further work on this but I'm always glad to get pull requests
- This code and your contribution is licensed under the BSD-2-clause license. 

# Webinar info
For details about the webinar see [the PyCharm blog](https://blog.jetbrains.com/pycharm/2018/12/webinar-live-development-of-a-pycharm-plugin-with-joachim-ansorg/).
The repository used as base for the webinar is available at https://github.com/jansorg/pycharm-webinar-base.

The info below is still useful it you'd like to work on plugin.

## Please report an issue if the setup isn't working for you!
I'm sorry that the initial setup is this complicated. You'll only need to this once, promise!

These steps won't be shown during the webinar. They're necessary only once to setup your environment to work on a Python plugin.

## Setup your IDE
1. **Clone** this repository:
   ```bash
   git clone https://github.com/jansorg/pycharm-webinar-base.git
   ```
1. **Install IntelliJ 2018.3.3** (Community or Ultimate). 

   Download it from [JetBrains](https://www.jetbrains.com/idea/). Feel free to use the Toolbox app, if you prefer that.
    
   In the following steps we assume that you downloaded 2018.3.3. 
   If you didn't, then make sure that you choose the right versions of the Python plugins.
1. **Start the IntelliJ IDE**. Make sure that the DevKit plugin is enabled, we'll need it!
   - If it's the first statup, then it'll look like this. Choosing 'Skip Remaining and Set Defaults' is fine.
    
    ![](docs/intellij-initial-setup.png)
   - **Choose 'Open'** if it's the first startup or **"File > Open"** if you're already using IntelliJ.
1. Make sure to **choose the repository directory `pycharm-webinar-base` in the file selector**. If you don't then the project won't work for plugin development.
 
    ![](docs/intellij-welcome-open.png)
1. **Setup your Java 8 SDK**. IntelliJ still needs a Java 8 JDK. 
    
    Both OpenJDK or Oracle versions are compatible. Make sure to download a Java 8 SE JDK, not a JRE! Of course, if you 
    already have Java 8 installed, then you dont't need to download it again.
    
    Oracle's downloads are available at [www.oracle.com/technetwork/java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
     
    - Choose `File > Project settings...`. Click on `New...`, then click on `JDK`.
 
       ![](docs/intellij-setup-java-sdk-menu.png)
       
    - In the file selector choose the installation path of your Java 8 SDK:   
       
       ![](docs/intellij-setup-java-sdk.png)
       
    - Now setup your IntelliJ SDK. Click `New...` on the project page and select `IntelliJ Platform Plugin SDK`. The default location should be the installation path of your IntelliJ 2018.3.3. Use this path for your new SDK setting.  
       
       ![](docs/intellij-setup-sdk-screen.png)
    - Make sure to select your Java 1.8 SDK in the next screen:
      
       ![](docs/intellij-setup-sdk-java.png)
1. **Configure your IntelliJ SDK**
    - **Choose** `File > Project settings...`
 
       ![](docs/intellij-setup-project.png)
    - Now **setup your IntelliJ SDK**. Click `New...` on the project page and select `IntelliJ Platform Plugin SDK`. The default location should be the installation path of your IntelliJ 2018.3.3. Use this path for your new SDK setting.  
       
       ![](docs/intellij-setup-sdk-screen.png)
    - Make sure to **choose your Java 1.8 SDK** in the next screen:
      
       ![](docs/intellij-setup-sdk-java.png)
1. Now **download the Python plugin** to finish the setup of your project's SDK. 

   Note: The PyCharm package can't be used as an SDK because it's not coming with the plugin development tooling we need.
   
   Download the PyCharm plugin which matches your edition of IntelliJ. Choose the download which is compatible with 2018.3.3. The red markers are highlighting the compatible versions.
   - Plugin for IntelliJ Community: https://plugins.jetbrains.com/plugin/7322-python-community-edition\
   
     ![](docs/plugin-community.png)
   - Plugin for IntelliJ Ultimate: https://plugins.jetbrains.com/plugin/631-python
   
      ![](docs/plugin-ultimate.png)
1. The downloaded file is a ZIP file. **Unzip** it on disk. We'll need it in the next step.
1. **Add the PyCharm plugin to the SDK** to let IntelliJ know about the Python plugin: 
   1. Choose `File > Project Settings ...` and select the page `SDK`:
  
      ![](docs/intellij-sdk-pycharm-plugin.png)
   1. Navigate to the directory of the unzipped Python plugin. Select everything belwo `python-ce/libs/` and click `Ok`:
   
      ![](docs/intellij-sdk-pycharm-libs.png)
   1. Close the dialog with `Ok`.
     
1. Almost ready! 
1. **Run the plugin to test your environment**: Click on the little arrow next to the run configuration `Webinar Plugin`:

   ![](docs/intellij-run-config.png)
1. Now the plugin will be compiled, packaged and started within a new instance of IntelliJ. If you see error messages, the
   there's something wrong with your setup.

## General notes

### Run the plugin using Gradle
If you just want to execute your plugin in a IntelliJ test environment then execute this command:
```bash
gradle runIDE
```
Of course, you'll need to [install Gradle](https://gradle.org/install/) if you've not yet done that.

### Directory structure
- `src/main/java`: Java sources
- `src/main/resources`: Java resource files
- `src/test/java`: Java test case sources
- `src/test/resources`: Java test case resource files

### Continuous integration builds
`.travis.yml` configures a [travis-ci.com](https://travis-ci.com/) build.

### Relevant Documentation 
- Introduction to plugin development: https://www.plugin-dev.com/intellij/introduction/
- IntelliJ DevGuide: http://www.jetbrains.org/intellij/sdk/docs/welcome.html
- Gradle build plugin: http://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
