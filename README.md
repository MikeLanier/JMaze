# JMaze
In this repository, I'm studing algorithms for making mazes.  The 
algorithms used are defined at 
https://en.wikipedia.org/wiki/Maze_generation_algorithm and 
http://www.astrolog.org/labyrnth/algrithm.htm .
The app to demo these algorithms is written in Java.  I eventually
plan to port this code to JavaScript using AngularJS and/or Vue.  I'll
create different repositories for these versions.  

## Algorithms: 
#### Recursive backtracker
The depth-first search algorithm of maze generation is frequently implemented
using backtracking:

* Make the initial cell the current cell and mark it as visited  
* While there are unvisited cells  
  * If the current cell has any neighbours which have not been visited  
    * Choose randomly one of the unvisited neighbours  
    * Push the current cell to the stack  
    * Remove the wall between the current cell and the chosen cell  
    * Make the chosen cell the current cell and mark it as visited  
  * Else if stack is not empty  
    * Pop a cell from the stack  
    * Make it the current cell  

_In my implementation of_ Depth-first search _I used a stack for backtracking
during maze creation. The description for this algorithm sounds similar to my
interpretation of_ Depth-first search. _For that reason I've not created a separate
implementation for_ Recursize backtracker.

## 09/27/2017
* Create code to name, save and load mazes.
* prelim for using this for a game (The game I have in mind will be based
on the old Apple ][ game called Wizardry.  I will probably take much of
the rules and character generation from that game. Although I no longer
have the hardware to play that game, I'll do my best given the rule book
I still have)
  * randomly add rooms
    * controls to define x/y for the top/left corner of the room
    * controls to define width/height of the room.
    * make sure width/height is adjusted to not extend beyond the outer edge
    of the maze
    * room cannot include the cell adjacent to the entrance (the cell
    you enter when stepping from the entrance).
    * room cannot include the cell adjacent to the exit (the cell
    you leave when stepping into the exit).
  * number of rooms a factor of the size of the maze
  * size of a room is random
  * all paths in/out of a room has a door
  * some doors will be locked, must search maze for key.
  * rooms cannot overlap (I think.  Might not be a bad idea for
  creating odd shaped rooms)
  * randomly add items to be found
  * number of items is based on room size
  * value of item is random
  * items can only be found in a room
  * number of monsters to battle is based on the size of the maze
  * monsters move around the maze at random
  * monsters can appear anywhere
  * entrance cell will be the bottom of a staircase, exit the top.  For the 
  3D in maze view, draw and up/down staircase.
* _BUG:_ when I hit the right arrow, focus change to the a TextField control. 
Using 2, 4, 6, 8 on the keypad for now.
* _BUG:_ when using animate create, the entrance cell is not marked and 3D
in Maze view is blank
* Look for a way to post events from the ControlPanel to be caught by the
MainFrm or Maze display panel  
* on change to entrance/exit combo box, validate selection
* pick start cell by clicking the random button
* look at other algorithms
* need to learn how to "package" my app so that I can run it outside of the 
IDE.  Maven or Gradle may be the solution for this.  Intellij seems to 
already have a hook for Maven
## 09/26/2017
## 09/25/2017
* ~~3D "in maze" view using 2D graphics~~
  * ~~_BUG:_ Display when at edge of maze grid shows too much~~
  * ~~show left/right wall of current cell~~
* ~~tooltips for controls. Have on a couple, but not all~~
## 09/23/2017
## 09/22/2017
* ~~3D "in maze" view using 2D graphics~~
  * ~~step forward using up arrow~~
  * ~~turn left/right using left/right arrow (4/6 keypad button)~~
  * ~~adjust the code for looking in a direction other than east~~
* ~~in the 2D maze, in the current cell, (which will be the entrance right
after the maze is created) draw an arrow (triangle) pointer in the direction
facing.~~
## 09/20/2017
~~Not comfortable with having the 3D/2D stuff in this app, so, moving it over to
the J3DSandbox app. (Maybe over-complicating what I want to do with this app).~~  

* ~~move 2D/3D stuff out over to J3DSandbox~~
* ~~when change cell size, automatically redraw the maze~~
* ~~create the maze independed of the size then apply the size when drawing~~
* maze walkthrough. I have an old C++ app which did this.  Try to recall it.
* ~~create a separator object for the control panel.  space and/or line~~
* ~~create a global class for data shared between the ControlPanel and Maze
display panel~~

## 09/19/2017
* ~~For 3D/2D maze, draw arrow at entrance/exit (Moved to J3DSandbox)~~
* ~~For 3D/2D maze, tie to controls(Moved to J3DSandbox)~~
* ~~Merge code for 2D and 3D/2D mazes(Moved to J3DSandbox)~~
* ~~Hide control not pertinant to 2D, 3D/2D and/or 3D mazes(Moved to J3DSandbox)~~
* ~~Add controls for 3D manipulation(Moved to J3DSandbox)~~

## 09/18/2017
* ~~move maze display to separate module~~
* ~~separate ControlPanel into sub-module.  One for each control group~~
* ~~3D display of a 2D maze. create module for 3D display of a 2D maze.~~

## 09/12/2017
* ~~images for the random buttons~~
* ~~how to split code in mainframe.java into multiple source files~~
## 09/11/2017
* ~~entrance/exit cell controls: change to combo box. droplist contains "north", 
"south", "east", "west", and numbers from "0" to sizeX-1 or sizeY-1.~~  
* ~~map selected algorithm to "maze create" function~~  
* ~~reset maze when create clicked second time~~
* ~~implement a stack for backtracking steps in maze creation~~
* ~~create the maze~~  
## 09/10/2017  
* ~~Special cells for entrance/exit~~  
* ~~show cell as starting point for maze creation~~  
* ~~show cell as entrance and exit~~
* ~~code for random number~~  
## 09/09/2017  
* ~~Move code for creating cell/walls into function~~  
