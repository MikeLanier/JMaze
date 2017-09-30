# JMaze
In this repository, I'm studing algorithms for making mazes.  The 
algorithms used are defined at 
https://en.wikipedia.org/wiki/Maze_generation_algorithm and 
http://www.astrolog.org/labyrnth/algrithm.htm .
The app to demo these algorithms is written in Java.  I eventually
plan to port this code to JavaScript using AngularJS and/or Vue.  I'll
create different repositories for these versions.  
---
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
---
## The Game
My ultimate goal is to use these mazes for a game based old Apple ][ game 
called Wizardry. I enjoyed the game way back when and would like to play
it again. But, I no longer have an Apple ][. The PC emulators I found year
ago are not compatable with today's computers and the version ported to
PC's for sale is way expensive.  So, I thought about writing my own.

As I said, I plan to base this game on Wizardry, enough so, someone might
be justified in calling me a thief.  But, since I have no plans to sell
my game, and since I'll probably be the only one to play this game, who
cares.  I am using GitHub for saving my code, so, anyone curious enough 
to load and build the code, I say "yes, it is a rip off. It is not for sale,
and it is not for public distribution."

In developing the game, I'll take the rules from Wizardry (for which I still
have the book), algorithms from the internet for building characters, 
monsters, enemies, treasures, etc, and my own imagination for designing
the play of the game.  I'm hoping to build in enough randomness so that
no two adventures will be alike.  Honestly, I have no idea what the end
result will be like.  I have ideas as to what I want. But, as I'm
implementing the game, I expect those ideas to change.  Very agile like
huh.

#### Requirements for game
  * randomly add rooms
    * method to create the room
      * ~~on create remove interior walls and place doors (09/28/2017)~~
      * make sure width/height is adjusted to not extend beyond the outer edge
      of the maze
      * room cannot include the cell adjacent to the entrance (the cell
      you enter when stepping from the entrance).
      * room cannot include the cell adjacent to the exit (the cell
      you leave when stepping into the exit).
  * number of rooms a factor of the size of the maze
  * size of a room is random
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
  * entrance cell will be the bottom of a staircase, exit the top.
    * For the 3D in maze view, draw and up/down staircase.
    * For the 2D display, draw cell with hash marks
---

## 09/29/2017
* Create code to name, save and load mazes.
* create entrance/exit cell for down/up stairs
* ~~If the maze size changes, validate the entrance/exit cell parameters.~~
* ~~entrance/edit cells~~
  * ~~validate when values in x/y controls change. If not valid disable
  create buttons. Show an error icon with tooltip that explains the nature
  of the problem.~~
  * ~~add a create button.  Only want to change when satisfied with values
   in controls.  Turns out, don't need~~
  * ~~clean up the old cell before creating a new one~~
  * ~~make sure new cell is facing the right direction~~
  * ~~rules for cell relationship~~
    * ~~cell must enter/exit the maze in a quadrant opposite the other cell. 
    IE. If the entrance cell is on the top-left quadrant, the exit cell must
    be on either the top-right or bottom-right quadrant.~~  
    * ~~cells cannot be on same side of the maze. IE. If the entrance is to 
    the top of the top-left quadrant, the exit cannot be on the top of 
    the top-right quadrant.~~
  * ~~pick entrance/exit cell by clicking the random button. the randomly
  picked cell will obey the relationship rules outlined above. Cell will
  be automatically created.  No need to click create button~~
* _BUG:_ when I hit the right arrow, focus change to the a TextField control. 
  * Using 2, 4, 6, 8 on the keypad for now. 
  * (9/28/17) Update. Tried a kludge where I forward keypress events 
  on a control field to the maze.  But, it seems, sometimes the arrows 
  move focus around the controls.  Something I really don't want to 
  happen.  Need to rethink this. 
  * Also need to think about other keys for controls.  Some keyboards 
  don't have a keypad
* Look for a way to post events from the ControlPanel to be caught by the
MainFrm or Maze display panel  
* look at other algorithms
* need to learn how to "package" my app so that I can run it outside of the 
IDE.  Maven or Gradle may be the solution for this.  Intellij seems to 
already have a hook for Maven
## 09/28/2017
* ~~pick start cell by clicking the random button~~
  * ~~change x or y of start cell and update the display~~
  * ~~click the random button, update the controls and display~~
## 09/27/2017
* ~~prelim for using this...~~
  * ~~randomly add rooms~~
    * ~~controls to define x/y for the top/left corner of the room~~
    * ~~controls to define width/height of the room.~~
    * ~~method to create the room~~
      * ~~mark all cells as room cells. change background to blue.  Update display~~
  * ~~all paths in/out of a room has a door~~
* ~~_BUG:_ when using animate create, the entrance cell is not marked and 3D
in Maze view is blank~~
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
