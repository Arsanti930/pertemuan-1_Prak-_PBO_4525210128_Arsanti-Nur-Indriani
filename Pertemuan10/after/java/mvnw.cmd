@echo off
setlocal
for %%i in ("%~dp0.") do set "MAVEN_PROJECTBASEDIR=%%~fi"

if not defined JAVA_HOME (
    echo JAVA_HOME is not set. Please configure a JDK before running Maven.
    exit /b 1
)

"%JAVA_HOME%\bin\java.exe" -classpath "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %*
exit /b %ERRORLEVEL%
