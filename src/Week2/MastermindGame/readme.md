# Mastermind game

Mastermind is a board game for two players. The first player invents a code and the second player tries to guess this code. A code is made up 4 coloured pins and their position. There are 6 colours to choose from and the same colour can be repeated multiple times.

##### Examples of different codes:

Red Green Blue Yellow
Red Green Yellow Blue
Black White Red Orange
Red Red Blue White
(note that while the first two codes use the same colours, they are different as Blue and Yellow occupy different positions)

##### The game play is as follows:

The second player (the one that is guessing) sets out a series of pins in order to guess the code. The first player (that defined the code) then provides some feedback to the player in light of how close they are to the correct combination.

##### The feedback is as follows:

Number of pins that are both the right colour and position
Number of pins that are correct in colour but in the wrong position
Note that the rest pins in the code will pins that are neither correct in colour or position.

###### Your task is to evaluate a guess made by player two of the code set by player one. For the sake of simplicity, we use uppercase letters from A to F instead of colours.

