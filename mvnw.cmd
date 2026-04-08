@REM ----------------------------------------------------------------------------
@REM Maven Wrapper for Windows
@REM ----------------------------------------------------------------------------
@echo off
setlocal

set MAVEN_WRAPPER_DIR=%~dp0.mvn\wrapper
set WRAPPER_JAR=%MAVEN_WRAPPER_DIR%\maven-wrapper.jar
set WRAPPER_PROPERTIES=%MAVEN_WRAPPER_DIR%\maven-wrapper.properties

if not exist "%WRAPPER_JAR%" (
  echo Maven wrapper jar not found. Downloading...
  for /f "usebackq tokens=1,* delims==" %%A in ("%WRAPPER_PROPERTIES%") do (
    if "%%A"=="wrapperUrl" set WRAPPER_URL=%%B
  )
  if "%WRAPPER_URL%"=="" (
    echo wrapperUrl not set in %WRAPPER_PROPERTIES%
    exit /b 1
  )
  if not exist "%MAVEN_WRAPPER_DIR%" mkdir "%MAVEN_WRAPPER_DIR%"
  powershell -NoProfile -ExecutionPolicy Bypass -Command ^
    "$ProgressPreference='SilentlyContinue'; Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile '%WRAPPER_JAR%'" || exit /b 1
)

set JAVA_EXE=java
if defined JAVA_HOME set JAVA_EXE=%JAVA_HOME%\bin\java.exe

"%JAVA_EXE%" -classpath "%WRAPPER_JAR%" -Dmaven.multiModuleProjectDirectory=%~dp0 org.apache.maven.wrapper.MavenWrapperMain %*
exit /b %ERRORLEVEL%

