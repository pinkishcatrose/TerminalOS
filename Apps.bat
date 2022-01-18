@echo off
SETLOCAL enabledelayedexpansion
::Iterates throw all batch files on this current folder and its 
::Subfolders and ignoring our main batch file in execution;
::And Populate the array with existent files in this folder and its subfolders.
Set "MainFolder=%~dp0"
Set "Ext=bat"
Set /a "Count=0"
@For /f "delims=" %%f in ('dir /b /s "apps" ^| find /I /V "%~nx0"') DO (
    Set /a "Count+=1"
    Set "list[!Count!]=%%~nxf"
    Set "listpath[!Count!]=%%~dpFf"
)
::--------------------------------------------------------------------------------
:Display_Results
cls & color 3e
echo wscript.echo Len("%MainFolder%"^) + 20 >"%tmp%\length.vbs"
@for /f %%a in ('Cscript /nologo "%tmp%\length.vbs"') do ( set "cols=%%a")
If %cols% LSS 50 set /a cols=%cols% + 24
echo(
type Resources\\apps.txt
echo.
echo.
echo  ------------------------------------------
ECHO   Apps Folder: "%MainFolder%apps"
echo  ------------------------------------------
echo.
echo  Installed Apps:
echo.
rem Display array elements 
@For /L %%i in (1,1,%Count%) do (
        set "Select=%%i - !list[%%i]!"
        echo  !Select!
)
echo(
echo  m - Return to Main Menu
echo.
set /p Input= Listening ^> 
if "%input%"=="m" goto close
@For /L %%i in (1,1,%Count%) Do (
   If "%INPUT%" EQU "%%i" (call "!listpath[%%i]!")
)
Goto close
::--------------------------------------------------------------------------------
:close
cls
exit /b