@echo off
SETLOCAL EnableDelayedExpansion
for /F "tokens=1,2 delims=#" %%a in ('"prompt #$H#$E# & echo on & for %%b in (1) do     rem"') do (
  set "DEL=%%a"
)
wscript "D:\Command Panel\fullscreen.vbs"
title TerminalOS
color 0f
type logo.txt
echo.
echo.
echo    Loading... --
ping localhost -n 2 >nul
cls
type logo.txt
echo.
echo.
echo    Loading... \
ping localhost -n 2 >nul
cls
type logo.txt
echo.
echo.
echo    Loading... ^|
ping localhost -n 2 >nul
cls
type logo.txt
echo.
echo.
echo    Loading... /
cls
:pass
cls
color 3f
type logo.txt
echo.
ping localhost -n 1.9 >nul
set /p pass= Please enter your password ^> 
if %pass%==0076 goto goodpass
if not %pass%==0076 goto pass
:goodpass
ping localhost -n 1.9 >nul
echo.
call :ColorText 32 " -- Access Granted --"
ping localhost -n 2 >nul
goto MainMenu
:MainMenu
ping localhost -n 1.9 >nul
cls
color 3e
echo.
echo ------------------------------------------------
echo.
ping localhost -n 1.9 >nul
echo What would you like to do?
echo.
ping localhost -n 1.9 >nul
echo  1 - Settings Menu
ping localhost -n 1.9 >nul
echo  2 - Change Color
ping localhost -n 1.9 >nul
echo  3 - Change Power Mode
echo.
ping localhost -n 1.9 >nul
echo  f - Toggle Fullscreen
ping localhost -n 1.9 >nul
call :ColorText 34 " d - Debug Menu"
ping localhost -n 1.9 >nul
echo  a - App Menu (Not Functional)
echo.
ping localhost -n 1.9 >nul
call :ColorText 34 " s - Shut down TerminalOS"
ping localhost -n 1.9 >nul
call :ColorText 34 " r - Restart TerminalOS"
echo.
ping localhost -n 1.9 >nul
set /p cmd= Listening ^> 
if %cmd%==1 goto settings
if %cmd%==2 goto colors
if %cmd%==3 goto power
if %cmd%==s goto shutdown
if %cmd%==r goto restart
if %cmd%==f goto fullscreen
if %cmd%==t goto test
if %cmd%==d goto debug
if not %cmd%==1/2/3/s/r/f/t/d/stop goto MainMenu
:debug
ping localhost -n 1.9 >nul
cls
color 0f
echo.
echo ------------------------------------------------
echo.
ping localhost -n 1.9 >nul
echo  Welcome to the TerminalOS Debug Menu
echo.
echo  Availible actions:
echo. 
echo  1 - List Directory
echo.
echo  m = Return to Main Menu
echo.
ping localhost -n 1.9 >nul
set /p debug= Listening ^> 
if %debug%==1 goto cd
if %debug%==m goto MainMenu
if not %debug%==1/m/stop goto debug
:cd
ping localhost -n 1.9 >nul
cls
color 0f
echo.
echo ------------------------------------------------
echo.
cd
ping localhost -n 5 >nul
goto debug
set "debug=0"
:fullscreen
cls
wscript "D:\Command Panel\fullscreen.vbs"
goto mainmenu
:settings
cls
call settings\settings.bat
goto MainMenu
:colors
cls
call settings\rgb.bat
goto MainMenu
:test
cls
echo.
echo loading --  
ping localhost -n 2 >nul
cls
echo.
echo loading \  
ping localhost -n 2 >nul
cls
echo.
echo loading ^| 
ping localhost -n 2 >nul
cls
echo.
echo loading / 
ping localhost -n 2 >nul
cls
call Apps.bat
goto MainMenu
:power
ping localhost -n 1.9 >nul
cls
color 9f
echo.
echo ------------------------------------------------
echo.
echo  Which power mode would you like?
echo. 
echo  Installed power modes:
echo.
call :ColorText 09 " 1 - Low Power"
call :ColorText 0c " 2 - High Power"
echo.
echo  m - Return to Main Menu
echo.
ping localhost -n 1.9 >nul
set /p pwr= Listening ^> 
if %pwr%==1 goto lowpwr
if %pwr%==2 goto hipwr
if %pwr%==m goto MainMenu
if not %pwr%==1/2/m/stop goto power
:lowpwr
ping localhost -n 1.9 >nul
cls
color 9f
echo.
echo ------------------------------------------------
echo.
ping localhost -n 1.9 >nul
echo  Setting power state to Low...
echo.
powercfg /setactive a1841308-3541-4fab-bc81-f71556f20b4a
ping localhost -n 2 >nul
echo  Limiting CPU Frequency...
echo.
ping localhost -n 2 >nul
echo  Success
ping localhost -n 2 >nul
goto power
:hipwr
cls
color 9c
echo.
echo ------------------------------------------------
echo.
ping localhost -n 1.9 >nul
echo  Setting power state to High...
echo.
powercfg /setactive 8851a545-78ca-41b6-9828-b69a4bd9a3d8
ping localhost -n 2 >nul
echo  Turning up CPU Frequency...
echo.
ping localhost -n 2 >nul
echo  Success
ping localhost -n 2 >nul
goto power
:shutdown
cls
color 0f
type logo.txt
echo.
echo.
echo    Shutting Down... --
ping localhost -n 2 >nul
cls
type logo.txt
echo.
echo.
echo    Shutting Down... \
ping localhost -n 2 >nul
cls
type logo.txt
echo.
echo.
echo    Shutting Down... ^|
ping localhost -n 2 >nul
cls
type logo.txt
echo.
echo.
echo    Shutting Down... /
ping localhost -n 2 >nul
exit
:restart
cls
color 0f
type logo.txt
echo.
echo.
echo    Restarting... --
ping localhost -n 2 >nul
cls
type logo.txt
echo.
echo.
echo    Restarting... \
ping localhost -n 2 >nul
cls
type logo.txt
echo.
echo.
echo    Restarting... ^|
ping localhost -n 2 >nul
cls
type logo.txt
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