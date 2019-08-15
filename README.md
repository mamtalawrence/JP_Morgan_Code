# Albums
Application have screen which displays albums list sorted by title and persisted for offline viewing.
1. Mockito tool is used for junit code coverage.
2. For offline viewing data is store in shared preference using gson parsing and store objects as string.

# Instructions
Need network for first time to get and save data.

# Environment Configuration #JDK
1. Install JDK 1.8 i586 update 101, for example in C:\Java\jdk1.8.0_101;
2. Set JAVA_HOME environment variable to C:\Java\jdk1.8.0_101;
3. Append JAVA_HOME to PATH variable as follows: PATH...;%JAVA_HOME%\bin

# ANDROID SDK
1. Install Android SDK for example in C:\Android\android-sdk;
2. Launch "SDK Manager.exe" from C:\Android\android-sdk;
3. Leave default checks for "Extras" category; For "Tools" category check only "Android SDK Platform-tools", "Android SDK Build-tools" rev 21.x.x; Uncheck all API levels except API 21 (SDK Platform) through 23 and then install selected packages.
4. Set ANDROID_HOME environment variable to C:\Android\android-sdk.
5. Append ANDROID_HOME to PATH variable as follows: PATH...;%ANDROID_HOME%\tools\;%ANDROID_HOME%\platform-tools

# IDE Application
Install Android Studio

# PROJECT SETUP
1. Get clean copy of the project repository to some directory (suppose “gitProjects”)
git clone https://github.com/mamta-lawrence/JP_Morgan_Code.git
Note: cloning is recommended but is not required

2. In Android Studio 2.1.2 open project located in gitProjects\Albums
3. In project structure at platform settings add JDK 1.8 SDK (leaving it’s default name “1.8”) and Project SDK Android API 21 platform(java sdk version 1.8.0_45)
4. Set Project SDK to “Android API 21 platform(java sdk version 1.8.0_45)” if needed.
