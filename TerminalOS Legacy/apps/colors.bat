@echo off
::------------------------------------------- 
SETLOCAL EnableDelayedExpansion
for /F "tokens=1,2 delims=#" %%a in ('"prompt #$H#$E# & echo on & for %%b in (1) do rem"') do (
	set "DEL=%%a"
	)
::-------------------------------------------
title Colors
color 3e
:menue 
cls 
color 3e
echo ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
echo  0 = Black
echo  8 = Gray
echo.
echo  1 = Blue
echo  9 = Light Blue
echo.
echo  2 = Green
echo  a = Light Green
echo.
echo  3 = Aqua
echo  b = Light Aqua
echo.
echo  4 = Red
echo  c = Light Red
echo.
echo  5 = Purple
echo  d = Light Purple
echo.
echo  6 = Yellow
echo  e = Light Yellow
echo.
echo  7 = White
echo  f = Bright White
echo.
echo  To see what each color looks like, 
echo  please enter the corresponding number/letter.
echo  To close, enter "q"
echo.
set /p input=^| ^>
if %input%==0 (
	cls & color 0f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==1 (
	cls & color 1f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==2 (
	cls & color 2f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==3 (
	cls & color 3f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
)
if %input%==4 (
	cls & color 4f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==5 (
	cls & color 5f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...                      
	pause>nul 
	goto menue
) 
if %input%==6 (
	cls & color 6f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==7 (
	cls & color 7f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==8 (
	cls & color 8f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==9 (
	cls & color 9f 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==a (
	cls & color a0 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==b (
	cls & color b0 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==c (
	cls & color c0 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==d (
	cls & color d0 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==e (
	cls & color e0 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==f (
	cls & color f0 
	echo.
	echo.
	echo.
	echo  Press any key to go to main menue...
	pause>nul 
	goto menue
) 
if %input%==q (
	exit /b
) 
if not %input%==0/1/2/3/4/5/6/7/8/9/a/b/c/d/e/f/q/stop goto menue 

::--------------------------------------------
goto :eof

:ct
echo off
echo %DEL% > "%~2"
findstr /v /a:%1 /R "^$" "%~2" nul
del "%~2" > nul 2>&1
goto :eof
::--------------------------------------------