# forza4

This application makes possible to play a connect 4 game with another player in local. It uses swing library, has automatic checks and is partially personalizable.


## REQUIREMENTS ##

Have Java installed on the machine.

## HOW TO INSTALL ##

Change directory and enters Forza4/src/forza4 folder and compile the program with the following command:

-$ javac -d ../../build Starter.java GUI/*.java interfaces/*.java IO/*.java logic/*.java testers/*.java

Now move in the new folder where are all the .class with the command

On windows:

-$ CHDIR ../../build

On linux:

-$ Mkdir ..\..\build


and create the jar

-$ jar cfe forza4.jar forza4.Starter forza4/Starter.class forza4/GUI/*.class  forza4/interfaces/*.class forza4/IO/*.class forza4/logic/*.class forza4/testers/*.class 

now open the program with the command

-$ java -jar forza4.jar

## LICENSE ##

© Paossi Davide, 2021

Licensed under the [GNU LICENSE](LICENSE)