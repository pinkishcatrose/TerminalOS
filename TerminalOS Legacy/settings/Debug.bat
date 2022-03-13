@echo off
SETLOCAL EnableDelayedExpansion
for /F "tokens=1,2 delims=#" %%a in ('"prompt #$H#$E# & echo on & for %%b in (1) do     rem"') do (
  set "DEL=%%a"
)
:maindebug
ping localhost -n 1.9 >nul
cls
color 0e
type Resources\debug.txt
ping localhost -n 1.9 >nul
echo.
echo  Welcome to the TerminalOS Debug Menu
echo.
ping localhost -n 1.9 >nul
echo  ----------------------------------------
ping localhost -n 1.9 >nul
echo  Current Directory: %~dp0
ping localhost -n 1.9 >nul
echo  ----------------------------------------
ping localhost -n 1.9 >nul
echo.
ping localhost -n 1.9 >nul
echo  Availible actions:
echo. 
ping localhost -n 1.9 >nul
echo  1 - Factory Reset TerminalOS (Deletes all settings files, your apps are safe!)
ping localhost -n 1.9 >nul
echo.
echo  m = Return to Main Menu
ping localhost -n 1.9 >nul
echo.
ping localhost -n 1.9 >nul
set /p debug= Listening ^> 
if "%debug%"=="m" goto close
if "%debug%"=="1" goto freset
if not "%debug%"=="1"/"m"/stop goto maindebug
:freset
cls
call settings\freset.bat
goto maindebug
:close
cls
exit /b
:ColorText
echo off
echo %DEL% > "%~2"
findstr /v /a:%1 /R "^$" "%~2" nul
del "%~2" > nul 2>&1
goto :eof