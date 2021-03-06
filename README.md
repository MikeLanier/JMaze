# JMaze  
In this repository, I'm studing algorithms for making mazes.  The algorithms used are 
defined at
https://en.wikipedia.org/wiki/Maze_generation_algorithm
and
http://www.astrolog.org/labyrnth/algrithm.htm . The app to demo these algorithms is written
in Java.  I eventually plan to port this code to JavaScript using AngularJS and/or Vue.
I'll create different repositories for these versions.  

## Algorithms: 
### Maze Creation
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

### Maze Solutions
#### Wall follower
The wall follower, the best-known rule for traversing mazes, is also known as either the 
left-hand rule or the right-hand rule. If the maze is simply connected, that is, all its 
walls are connected together or to the maze's outer boundary, then by keeping one hand in 
contact with one wall of the maze the solver is guaranteed not to get lost and will reach a 
different exit if there is one; otherwise, he or she will return to the entrance having 
traversed every corridor next to that connected section of walls at least once.

Another perspective into why wall following works is topological. If the walls are connected, 
then they may be deformed into a loop or circle.[2] Then wall following reduces to walking 
around a circle from start to finish. To further this idea, notice that by grouping together 
connected components of the maze walls, the boundaries between these are precisely the 
solutions, even if there is more than one solution (see figures on the right).

If the maze is not simply-connected (i.e. if the start or endpoints are in the center of the 
structure surrounded by passage loops, or the pathways cross over and under each other and 
such parts of the solution path are surrounded by passage loops), this method will not reach 
the goal.

Another concern is that care should be taken to begin wall-following at the entrance to the 
maze. If the maze is not simply-connected and one begins wall-following at an arbitrary point 
inside the maze, one could find themselves trapped along a separate wall that loops around on 
itself and containing no entrances or exits. Should it be the case that wall-following begins 
late, attempt to mark the position in which wall-following began. Because wall-following will 
always lead you back to where you started, if you come across your starting point a second 
time, you can conclude the maze is not simply-connected, and you should switch to an 
alternative wall not yet followed. See the Pledge Algorithm, below, for an alternative 
methodology.

Wall-following can be done in 3D or higher-dimensional mazes if its higher-dimensional passages 
can be projected onto the 2D plane in a deterministic manner. For example, if in a 3D maze 
"up" passages can be assumed to lead northwest, and "down" passages can be assumed to lead 
southeast, then standard wall following rules can apply. However, unlike in 2D, this requires 
that the current orientation be known, to determine which direction is the first on the left 
or right.

## Implementation Diary: 
#### 10/29/2017
* How to read/write XML files  
* Maintain multiple mazes
  * When starting app, 
    * create one raw maze 
    * add it to list
  * Names start with "Maze-1" and increment
  * keep mazes in MAP<>
  * add button with + to create a new maze
  * add button with - to delete current maze.
* Code to load/save a collection of mazes
* Look for a way to post events from the ControlPanel to be caught by the
MainFrm or Maze display panel. (Found a way to post events from the RadialMenu.  Can use that
to post events from the control panel)
* look at other algorithms
* need to learn how to "package" my app so that I can run it outside of the 
IDE.  Maven or Gradle may be the solution for this.  Intellij seems to 
already have a hook for Maven
* Auto solve the maze
* Print the maze
* _BUG:_ Change the location of the entrance/exit through control panel
does not update the display
* _BUG:_ Pick an entrance with Y set to south, draws one cell above maze to north
* _BUG:_ when I hit the right arrow, focus change to the a TextField control. 
  * Using 2, 4, 6, 8 on the keypad for now. 
  * (9/28/17) Update. Tried a kludge where I forward keypress events 
  on a control field to the maze.  But, it seems, sometimes the arrows 
  move focus around the controls.  Something I really don't want to 
  happen.  Need to rethink this. 
  * Also need to think about other keys for controls.  Some keyboards 
  don't have a keypad. Maybe make key map customizable.
#### 10/28/2017
* ~~Getting some drawing issue.  May be best to draw the floor and ceiling as well. Maybe
use a texture to give it some character.~~
  * ~~draw floors~~
  * ~~draw ceiling~~
  * ~~fit bitmap to shape~~
#### 10/19/2017
* ~~On screen controls~~
  * ~~Radial Menu with four buttons; move forward, turn left, turn right and turn around~~
  * ~~wire up radial menu to walk through maze~~
#### 10/10/2017
* ~~Maintain multiple mazes~~
  * ~~Maze size is per maze, but cell size is a display parameter, move control~~
  * ~~change name to Mazes on control panel~~
  * ~~change maze name TextField to combo box~~

#### 10/5/2017
* ~~Create code to name, save and load mazes. As for the game, will not
need this to start and adventure, but will need it to save and
adventure~~
   * ~~Create a class which contains all info for a given maze~~

#### 10/1/2017
#### 09/30/2017
* ~~create entrance/exit cell for down/up stairs~~

#### 09/29/2017
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
#### 09/28/2017
* ~~pick start cell by clicking the random button~~
  * ~~change x or y of start cell and update the display~~
  * ~~click the random button, update the controls and display~~
#### 09/27/2017
* ~~prelim for using this...~~
  * ~~randomly add rooms~~
    * ~~controls to define x/y for the top/left corner of the room~~
    * ~~controls to define width/height of the room.~~
    * ~~method to create the room~~
      * ~~mark all cells as room cells. change background to blue.  Update display~~
  * ~~all paths in/out of a room has a door~~
* ~~_BUG:_ when using animate create, the entrance cell is not marked and 3D
in Maze view is blank~~
#### 09/26/2017
#### 09/25/2017
* ~~3D "in maze" view using 2D graphics~~
  * ~~_BUG:_ Display when at edge of maze grid shows too much~~
  * ~~show left/right wall of current cell~~
* ~~tooltips for controls. Have on a couple, but not all~~
#### 09/23/2017
#### 09/22/2017
* ~~3D "in maze" view using 2D graphics~~
  * ~~step forward using up arrow~~
  * ~~turn left/right using left/right arrow (4/6 keypad button)~~
  * ~~adjust the code for looking in a direction other than east~~
* ~~in the 2D maze, in the current cell, (which will be the entrance right
after the maze is created) draw an arrow (triangle) pointer in the direction
facing.~~
#### 09/20/2017
~~Not comfortable with having the 3D/2D stuff in this app, so, moving it over to
the J3DSandbox app. (Maybe over-complicating what I want to do with this app).~~  

* ~~move 2D/3D stuff out over to J3DSandbox~~
* ~~when change cell size, automatically redraw the maze~~
* ~~create the maze independed of the size then apply the size when drawing~~
* maze walkthrough. I have an old C++ app which did this.  Try to recall it.
* ~~create a separator object for the control panel.  space and/or line~~
* ~~create a global class for data shared between the ControlPanel and Maze
display panel~~

#### 09/19/2017
* ~~For 3D/2D maze, draw arrow at entrance/exit (Moved to J3DSandbox)~~
* ~~For 3D/2D maze, tie to controls(Moved to J3DSandbox)~~
* ~~Merge code for 2D and 3D/2D mazes(Moved to J3DSandbox)~~
* ~~Hide control not pertinant to 2D, 3D/2D and/or 3D mazes(Moved to J3DSandbox)~~
* ~~Add controls for 3D manipulation(Moved to J3DSandbox)~~

#### 09/18/2017
* ~~move maze display to separate module~~
* ~~separate ControlPanel into sub-module.  One for each control group~~
* ~~3D display of a 2D maze. create module for 3D display of a 2D maze.~~

#### 09/12/2017
* ~~images for the random buttons~~
* ~~how to split code in mainframe.java into multiple source files~~
#### 09/11/2017
* ~~entrance/exit cell controls: change to combo box. droplist contains "north", 
"south", "east", "west", and numbers from "0" to sizeX-1 or sizeY-1.~~  
* ~~map selected algorithm to "maze create" function~~  
* ~~reset maze when create clicked second time~~
* ~~implement a stack for backtracking steps in maze creation~~
* ~~create the maze~~  
#### 09/10/2017  
* ~~Special cells for entrance/exit~~  
* ~~show cell as starting point for maze creation~~  
* ~~show cell as entrance and exit~~
* ~~code for random number~~  
#### 09/09/2017  
* ~~Move code for creating cell/walls into function~~  
