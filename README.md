# JMaze
In this repository, I'm studing algorithms for making mazes.  The 
algorithms used are defined at 
https://en.wikipedia.org/wiki/Maze_generation_algorithm .
The app to demo these algorithms are written in Java.  I eventually
plan to port this code to JavaScript using AngularJS and/or Vue.  I'll
create different repositories for these versions.  
  
The following is somewhat of a diary of my effort in creating this
app.  For each day, I'll have a list of what I need to do.  If I
complete an item, it will get a check and stay under that day.  Anything
worked on will be copied to the next day.  Anything not worked on
will be moved to the next day.

## 09/10/2017  
[ ] Special cells for entrance/exit  
[ ] Add code to cell to show cell as starting point for maze creation  
[ ] code for random number  
[ ] map selected algorithm to "maze create" function  
[ ] create the maze  
## 09/09/2017  
[x] Move code for creating cell/walls into function  
[-] Special cells for entrance/exit  

## Algorithms
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