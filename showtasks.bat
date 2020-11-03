call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openChrome
echo.
echo Running crud War file errors
goto fail

:openChrome
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Opening website error
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.