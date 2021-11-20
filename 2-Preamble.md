# Preamble

**OK - so what to do now?**
  Well - you should practice manipulating an array, for hours of fun.  Use ints, strings, doubles, etc.

**What about the assignment?**
  Right. You will create an 8x8 2D array that simulates a Battleship playing board and add elements to it.
  
**Don't you play with two boards?**
  Hold on... let take this in small chunks

**Right, whats a 2D array?**
  An array with two dimensions, think rows and columns.
  
**huh?**
  Think of a table in HTML - building a row of cells, then adding another row of cells, etc.  If you apply the same concept, 
  you can create the same idea with two sets of square brackets.  
  
  **int[][] numbers = new int[8][8];**

| A  | B | C  | D | E  | E | F  | G |
| -- | -- | -- | -- | -- | -- | -- | -- |
| 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |

  
  Where we have created a table with 8 rows of 8 columns.  **(Notice my description there, 8 rows OF 8 columns)**
  
  NOTE:  Typically, 2D arrays are row focused (count/reference the row first, then the 'columns' within that row.
  You can have ragged dimensions on a 2D array, but may be harder to keep track etc - so plan carefully when building'designing your arrays.
  
  Onto the instructions - you will build a 'game' environment of placing 'boats' at a position and then build from that direction.  (Start with a single point - x,y coordinate - then prompt direction and length of boat
  
  your program SHOULD be intuitive enough to check to see if you have enough room to place a boat on that point and in the correct direction.
  
  Doesn't hurt to print the boards as you proceed (part 1/player 1 places boats) player 2 guesses position of boats.
  
  100 points.
  
