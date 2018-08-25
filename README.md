# ScratchBasic

**INTRODUCTION**

This project aims to deliver a simplistic app in which users can program batchUI programs
in the ScratchBasic language. This language includes features such as:
  Initialising variables
  Printing
  Goto
  IF conditions
  Sub-Processes
  Commenting

The source code contained in this application is written by Jared Blackman and Siyang Liang.
Specifically, Jared Blackman wrote the UI whilst Siyang Liang wrote the compiler.

The latest release allows users to create sub-programs and select variables from drop-down menus.

**GETTING STARTED**

*Writing code*

Begin by downloading and opening the application on your android device.

Click the 'LET' button at the bottom of the screen.
Click the first text area inside the newly created 'LET' line of code.
Select the variable 'x'.
Click the second text area inside the newly created 'LET' line of code.
Write "5".

Click the 'PRINT' button.
Click the first text area inside the newly created 'PRINT' line of code.
Select the variable 'x'.

Click the 'LET' button again.
Click the first text area inside the newly created 'LET' line of code.
Select the variable 'x'.
Click the second text area inside the newly created 'LET' line of code.
Write "x + 1".

Click the 'PRINT' button.
Click the first text area inside the newly created 'PRINT' line of code.
Select the variable 'x'.

Click the 'REM' button.
Click the first text area inside the newly created 'REM' line of code.
Write 'This statment is going to be deleted'.
CLICK and HOLD the newly created 'REM' line of code.

*Testing*

When you are happy with your code you can execute it by clicking the 'run' button located at the top of the application.
Behold as the application compiles and runs your code. Watch as it outputs the program to the console.

If you ever encounter an error when you run you program, you can debug it by clicking the 'debug' button.
Doing so will bring up the Debug activity which displays the output (bottom left) and the variables and their values (bottom right).
The height of the debug console can be adjusted at any time by dragging the black slider bar in the middle.

Whenever you are done testing you may exit by clicking the 'halt' button

**BUGS**

The focus on spinners is non-optimal.
You cannot halt the program if caught in a loop.
Namespaces cannot be mixed except though a return statement.
