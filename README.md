# JMaze
In this repository, I'm studing algorithms for making mazes.  The 
algorithms used are defined at 
https://en.wikipedia.org/wiki/Maze_generation_algorithm and 
http://www.astrolog.org/labyrnth/algrithm.htm .
The app to demo these algorithms is written in Java.  I eventually
plan to port this code to JavaScript using AngularJS and/or Vue.  I'll
create different repositories for these versions.  

## Algorithms: 
https://en.wikipedia.org/wiki/Maze_generation_algorithm
### Depth-first search
This algorithm is a randomized version of the depth-first search algorithm. 
Frequently implemented with a stack, this approach is one of the simplest ways 
to generate a maze using a computer. Consider the space for a maze being a large 
grid of cells (like a large chess board), each cell starting with four walls. 
Starting from a random cell, the computer then selects a random neighbouring 
cell that has not yet been visited. The computer removes the wall between the 
two cells and marks the new cell as visited, and adds it to the stack to 
facilitate backtracking. The computer continues this process, with a cell that 
has no unvisited neighbours being considered a dead-end. When at a dead-end it
backtracks through the path until it reaches a cell with an unvisited neighbour, 
continuing the path generation by visiting this new, unvisited cell (creating 
a new junction). This process continues until every cell has been visited, 
causing the computer to backtrack all the way back to the beginning cell. 
We can be sure every cell is visited.  

As given above this algorithm involves deep recursion which may cause stack 
overflow issues on some computer architectures. The algorithm can be rearranged 
into a loop by storing backtracking information in the maze itself. This also 
provides a quick way to display a solution, by starting at any given point and 
backtracking to the beginning.

Mazes generated with a depth-first search have a low branching factor and contain many long corridors, because the algorithm explores as far as possible along each branch before backtracking.  

## Recursive backtracker
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

## Perfect Maze Creation Algorithms: 
http://www.astrolog.org/labyrnth/algrithm.htm
  
There are a number of ways of creating perfect Mazes, each with its own characteristics. Here's a list of specific algorithms. All of these describe creating the Maze by carving passages, however unless otherwise specified each can also be done by adding walls:
  
### Recursive backtracker: 
This is somewhat related to the recursive backtracker solving method, and requires stack up to the size of the Maze. When carving, be as greedy as possible, and always carve into an unmade section if one is next to the current cell. Each time you move to a new cell, push the former cell on the stack. If there are no unmade cells next to the current position, pop the stack to the previous position. The Maze is done when you pop everything off the stack. This algorithm results in Mazes with about as high a "river" factor as possible, with fewer but longer dead ends, and usually a very long and twisty solution. When implemented efficiently it runs fast, with only highly specialized algorithms being faster. Recursive backtracking doesn't work as a wall adder, because doing so tends to result in a solution path that follows the outside edge, where the entire interior of the Maze is attached to the boundary by a single stem.
### Kruskal's algorithm: 
This is Kruskal's algorithm to produce a minimum spanning tree. It's interesting because it doesn't "grow" the Maze like a tree, but rather carves passage segments all over the Maze at random, but yet still results in a perfect Maze in the end. It requires storage proportional to the size of the Maze, along with the ability to enumerate each edge or wall between cells in the Maze in random order (which usually means creating a list of all edges and shuffling it randomly). Label each cell with a unique id, then loop over all the edges in random order. For each edge, if the cells on either side of it have different id's, then erase the wall, and set all the cells on one side to have the same id as those on the other. If the cells on either side of the wall already have the same id, then there already exists some path between those two cells, so the wall is left alone so as to not create a loop. This algorithm yields Mazes with a low "river" factor, but not as low as Prim's algorithm. Merging the two sets on either side of the wall will be a slow operation if each cell just has a number and are merged by a loop. Merging as well as lookup can be done in near constant time by using the union-find algorithm: Place each cell in a tree structure, with the id at the root, in which merging is done quickly by splicing two trees together. Done right, this algorithm runs reasonably fast, but is slower than most because of the edge list and set management.
### Prim's algorithm (true): 
This is Prim's algorithm to produce a minimum spanning tree, operating over uniquely random edge weights. It requires storage proportional to the size of the Maze. Start with any vertex (the final Maze will be the same regardless of which vertex one starts with). Proceed by selecting the passage edge with least weight connecting the Maze to a point not already in it, and attach it to the Maze. The Maze is done when there are no more edges left to consider. To efficiently select the next edge, a priority queue (usually implemented with a heap) is needed to store the frontier edges. Still, this algorithm is somewhat slow, because selecting elements from and maintaining the heap requires log(n) time. Therefore Kruskal's algorithm which also produces a minimum spanning tree can be considered better, since it's faster and produces Mazes with identical texture. In fact, given the same random number seed, identical Mazes can be created with both Prim's and Kruskal's algorithms.
### Prim's algorithm (simplified): 
This is Prim's algorithm to produce a minimum spanning tree, simplified such that all edge weights are the same. It requires storage proportional to the size of the Maze. Start with a random vertex. Proceed by randomly selecting a passage edge connecting the Maze to a point not already in it, and attach it to the Maze. The Maze is done when there are no more edges left to consider. Since edges are unweighted and unordered, they can be stored in a simple list, which means selecting elements from the list is very fast and only takes constant time. Therefore this is much faster than true Prim's algorithm with random edge weights. The texture of the Maze produced will have a lower "river" factor and a simpler solution than true Prim's algorithm, because it will out spread equally from the start point like poured syrup, instead of bypassing and flowing around clumps of higher weighted edges that don't get covered until later.
### Prim's algorithm (modified): 
This is Prim's algorithm to produce a minimum spanning tree, modified so all edge weights are the same, but also implemented by looking at cells instead of edges. It requires storage proportional to the size of the Maze. During creation, each cell is one of three types: (1) "In": The cell is part of the Maze and has been carved into already, (2) "Frontier": The cell is not part of the Maze and has not been carved into yet, but is next to a cell that's already "in", and (3) "Out": The cell is not part of the Maze yet, and none of its neighbors are "in" either. Start by picking a cell, making it "in", and setting all its neighbors to "frontier". Proceed by picking a "frontier" cell at random, and carving into it from one of its neighbor cells that are "in". Change that "frontier" cell to "in", and update any of its neighbors that are "out" to "frontier". The Maze is done when there are no more "frontier" cells left (which means there are no more "out" cells left either, so they're all "in"). This algorithm results in Mazes with a very low "river" factor with many short dead ends, and a rather direct solution. The resulting Maze is very similar to simplified Prim's algorithm, with only the subtle distinction that indentations in the expanding tree get filled only if that frontier cell is randomly selected, as opposed to having triple the chance of filling that cell via one of the frontier edges leading to it. It also runs very fast, faster than simplified Prim's algorithm because it doesn't need to compose and maintain a list of edges.
### Aldous-Broder algorithm: 
The interesting thing about this algorithm is that it's uniform, which means it generates all possible Mazes of a given size with equal probability. It also requires no extra storage or stack. Pick a point, and move to a neighboring cell at random. If an uncarved cell is entered, carve into it from the previous cell. Keep moving to neighboring cells until all cells have been carved into. This algorithm yields Mazes with a low "river" factor, only slightly higher than Kruskal's algorithm. (This means for a given size there are more Mazes with a low "river" factor than high "river", since an average equal probability Maze has low "river".) The bad thing about this algorithm is that it's very slow, since it doesn't do any intelligent hunting for the last cells, where in fact it's not even guaranteed to terminate. However since the algorithm is simple it can move over many cells quickly, so finishes faster than one might think. On average it takes about seven times longer to run than standard algorithms, although in bad cases it can take much longer if the random number generator keeps making it avoid the last few cells. This can be done as a wall adder if the boundary wall is treated as a single vertex, i.e. if a move goes to the boundary wall, teleport to a random point along the boundary before moving again. As a wall adder this runs nearly twice as fast, because the boundary wall teleportation allows quicker access to distant parts of the Maze.
### Wilson's algorithm: 
This is an improved version of the Aldous-Broder algorithm, in that it produces Mazes with exactly the same texture as that algorithm (the algorithms are uniform with all possible Mazes generated with equal probability), however Wilson's algorithm runs much faster. It requires storage up to the size of the Maze. Begin by making a random starting cell part of the Maze. Proceed by picking a random cell not already part of the Maze, and doing a random walk until a cell is found which is already part of the Maze. Once the already created part of the Maze is hit, go back to the random cell that was picked, and carve along the path that was taken, adding those cells to the Maze. More specifically, when retracing the path, at each cell carve along the direction that the random walk most recently took when it left that cell. That avoids adding loops along the retraced path, resulting in a single long passage being appended to the Maze. The Maze is done when all cells have been appended to the Maze. This has similar performance issues as Aldous-Broder, where it may take a long time for the first random path to find the starting cell, however once a few paths are in place, the rest of the Maze gets carved quickly. On average this runs five times faster than Aldous-Broder, and takes less than twice as long as the top algorithms. Note this runs twice as fast when implemented as a wall adder, because the whole boundary wall starts as part of the Maze, so the first walls are connected much quicker.
### Hunt and kill algorithm: 
This algorithm is nice because it requires no extra storage or stack, and is therefore suited to creating the largest Mazes or Mazes on the most limited systems, since there are no issues of running out of memory. Since there are no rules that must be followed all the time, it's also the easiest to modify and to get to create Mazes of different textures. It's most similar to the recursive backtracker, except when there's no unmade cell next to the current position, you enter "hunting" mode, and systematically scan over the Maze until an unmade cell is found next to an already carved into cell, at which point you start carving again at that new location. The Maze is done when all cells have been scanned over once in "hunt" mode. This algorithm tends to make Mazes with a high "river" factor, but not as high as the recursive backtracker. You can make this generate Mazes with a lower river factor by choosing to enter "hunt" mode more often. It runs slower due to the time spent hunting for the last cells, but isn't much slower than Kruskal's algorithm. This can be done as a wall adder if you randomly teleport on occasion, to avoid the issues the recursive backtracker has.
### Growing tree algorithm: 
This is a general algorithm, capable of creating Mazes of different textures. It requires storage up to the size of the Maze. Each time you carve a cell, add that cell to a list. Proceed by picking a cell from the list, and carving into an unmade cell next to it. If there are no unmade cells next to the current cell, remove the current cell from the list. The Maze is done when the list becomes empty. The interesting part that allows many possible textures is how you pick a cell from the list. For example, if you always pick the most recent cell added to it, this algorithm turns into the recursive backtracker. If you always pick cells at random, this will behave similarly but not exactly to Prim's algorithm. If you always pick the oldest cells added to the list, this will create Mazes with about as low a "river" factor as possible, even lower than Prim's algorithm. If you usually pick the most recent cell, but occasionally pick a random cell, the Maze will have a high "river" factor but a short direct solution. If you randomly pick among the most recent cells, the Maze will have a low "river" factor but a long windy solution.
### Growing forest algorithm: 
This is a more general algorithm, that combines both tree and set based types. It's an extension of the Growing Tree algorithm, that basically involves multiple instances of it expanding at the same time. Start with all cells randomly sorted into a "new" list, and also each cell in its own set similar to the start of Kruskal's algorithm. Begin by selecting one or more cells, moving them from the "new" to an "active" list. Proceed by picking a cell from the "active" list, and carving into an unmade cell in the "new" list next to it, adding the new cell to the "active" list, and merging the two cells' sets. If an attempt is made to carve into an existing part of the Maze, allow it if the cells are in different sets, and merge the sets as done with Kruskal's algorithm. If there are no unmade "new" cells next to the current cell, move the current cell to a "done" list. The Maze is complete when the "active" list becomes empty. At the end, merge any remaining sets together as done with Kruskal's algorithm. You may periodically spawn new trees by moving one or more cells from the "new" list to the "active" list like at the beginning. The number of initial trees and the rate of newly spawned trees can generate many unique textures, combined with the already versatile settings of the Growing Tree algorithm.
### Eller's algorithm: 
This algorithm is special because it's not only faster than all the others that don't have obvious biases or blemishes, but its creation is also the most memory efficient. It doesn't even require the whole Maze to be in memory, only using storage proportional to the size of a row. It creates the Maze one row at a time, where once a row has been generated, the algorithm no longer looks at it. Each cell in a row is contained in a set, where two cells are in the same set if there's a path between them through the part of the Maze that's been made so far. This information allows passages to be carved in the current row without creating loops or isolations. This is actually quite similar to Kruskal's algorithm, just this completes one row at a time, while Kruskal's looks over the whole Maze. Creating a row consists of two parts: Randomly connecting adjacent cells within a row, i.e. carving horizontal passages, then randomly connecting cells between the current row and the next row, i.e. carving vertical passages. When carving horizontal passages, don't connect cells already in the same set (as that would create a loop), and when carving vertical passages, you must connect a cell if it's a set of size one (as abandoning it would create an isolation). When carving horizontal passages, when connecting cells union the sets they're in (since there's now a path between them), and when carving vertical passages, when not connecting a cell put it in a set by itself (since it's now disconnected from the rest of the Maze). Creation starts with each cell in its own set before connecting cells within the first row, and creation ends after connecting cells within the last row, with a special final rule that every cell must be in the same set by the time we're done to prevent isolations. (The last row is done by connecting each pair of adjacent cells if not already in the same set.) The best way to implement the set is with a circular doubly linked list of cells (which can just be an array mapping cells to the pair of cells on either side in the same set) which allows insertion, deletion, and checking whether adjacent cells are in the same set in constant time. One issue with this algorithm is that it's not balanced with respect to how it treats the different edges of the Maze, where connecting vs. not connecting cells need to be done in the right proportions to prevent texture blemishes.
### Recursive division: 
This algorithm is somewhat similar to recursive backtracking, since they're both stack based, except this focuses on walls instead of passages. Start by making a random horizontal or vertical wall crossing the available area in a random row or column, with an opening randomly placed along it. Then recursively repeat the process on the two subareas generated by the dividing wall. For best results, give bias to choosing horizontal or vertical based on the proportions of the area, e.g. an area twice as wide as it is high should be divided by a vertical wall more often. This is the fastest algorithm without directional biases, and can even rival binary tree mazes because it can finish multiple cells at once, although it has the obvious blemish of long walls crossing the interior. This algorithm is a form of nested fractal Mazes, except instead of always making fixed cell size Mazes with Mazes of the same size within each cell, it divides the given area randomly into a random sized 1x2 or 2x1 Maze. Recursive division doesn't work as a passage carver, because doing so results in an obvious solution path that either follows the outside edge or else directly crosses the interior.
### Binary tree Mazes: 
This is basically the simplest and fastest algorithm possible, however Mazes produced by it have a very biased texture. For each cell carve a passage either leading up or leading left, but not both. In the wall added version, for each vertex add a wall segment leading down or right, but not both. Each cell is independent of every other cell, where you don't have to refer to the state of any other cells when creating it. Hence this is a true memoryless Maze generation algorithm, with no limit to the size of Maze you can create. This is basically a computer science binary tree, if you consider the upper left corner the root, where each node or cell has one unique parent which is the cell above or to the left of it. Binary tree Mazes are different than standard perfect Mazes, since about half the cell types can never exist in them. For example there will never be a crossroads, and all dead ends have passages pointing up or left, and never down or right. The Maze tends to have passages leading diagonally from upper left to lower right, where the Maze is much easier to navigate from lower right to upper left. You will always be able to travel up or left, but never both, so you can always deterministically travel diagonally up and to the left without hitting any barriers. Traveling down and to the right is when you'll encounter choices and dead ends. Note if you flip a binary tree Maze upside down and treat passages as walls and vice versa, the result is basically another binary tree.
### Sidewinder Mazes: 
This simple algorithm is very similar to the binary tree algorithm, and only slightly more complicated. The Maze is generated one row at a time: For each cell randomly decide whether to carve a passage leading right. If a passage is not carved, then consider the horizontal passage just completed, formed by the current cell and any cells to the left that carved passages leading to it. Randomly pick one cell along this passage, and carve a passage leading up from it (which must be the current cell if the adjacent cell didn't carve). While a binary tree Maze always goes up from the leftmost cell of a horizontal passage, a sidewinder Maze goes up from a random cell. While binary tree has the top and left edges of the Maze one long passage, a sidewinder Maze has just the top edge one long passage. Like binary tree, a sidewinder Maze can be solved deterministically without error from bottom to top, because at each row, there will always be exactly one passage leading up. A solution to a sidewinder Maze will never double back on itself or visit a row more than once, although it will "wind from side to side". The only cell type that can't exist in a sidewinder Maze is a dead end with the passage facing down, because that would contradict the fact that every passage going up leads back to the start. A sidewinder Maze tends to have an elitist solution, where the right path is very direct, but there are many long false paths leading down from the top next to it. 
  

## 09/25/2017
* 3D "in maze" view using 2D graphics
  * _BUG:_ when I hit the right arrow, focus change to the a TextField control. 
Using 2, 4, 6, 8 on the keypad for now.
  * _BUG:_ Display when at edge of maze grid shows too much
  * show left/right wall of current cell
* Look for a way to post events from the ControlPanel to be caught by the
MainFrm or Maze display panel  
* ~~tooltips for controls. Have on a couple, but not all~~
* on change to entrance/exit combo box, validate selection
* pick start cell by clicking the random button
* look at other algorithms
* prelim for using this for a game, randomly add doors and rooms
## 09/23/2017
* 3D "in maze" view using 2D graphics
  * _BUG:_ when I hit the right arrow, focus change to the a TextField control. 
Using 2, 4, 6, 8 on the keypad for now.
  * _BUG:_ Display when at edge of maze grid shows too much
  * show left/right wall of current cell
* Look for a way to post events from the ControlPanel to be caught by the
MainFrm or Maze display panel  
* tooltips for controls. Have on a couple, but not all
* on change to entrance/exit combo box, validate selection
* pick start cell by clicking the random button
* look at other algorithms
* prelim for using this for a game, randomly add doors and rooms
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
