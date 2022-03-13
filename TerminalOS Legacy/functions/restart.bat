@echo off
SETLOCAL EnableDelayedExpansion
for /F "tokens=1,2 delims=#" %%a in ('"prompt #$H#$E# & echo on & for %%b in (1) do     rem"') do (
  set "DEL=%%a"
)
:main
cls
color 0e
type Resources\logo.txt
echo.
echo.
echo    Restarting... --
ping localhost -n 2 >nul
cls
type Resources\logo.txt
echo.
echo.
echo    Restarting... \
ping localhost -n 2 >nul
cls
type Resources\logo.txt
echo.
echo.
echo    Restarting... ^|
ping localhost -n 2 >nul
cls
type Resources\logo.txt
echo.
echo.
echo    Restarting... /
ping localhost -n 2 >nul
start cmd /c "TerminalOS.bat"
exit
:ColorText
echo off
echo %DEL% > "%~2"
findstr /v /a:%1 /R "^$" "%~2" nul
del "%~2" > nul 2>&1
goto :eof