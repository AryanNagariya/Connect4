=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1.2D Arrays :
  
  I chose to represent my connect4 board via a 2D integer array since this would be the best
  method that accurately models the multi dimensional structure of the board. So for example, [a][b]
  would represent the board, with a being the row and b being the column. Moreover, it is also easier
  to debug and understand internally, without the use of the GUI, since printing out the 2D board
  like the tictactoe sample code represents the checker board well. Moreover, I also
  took inspiration from the sample code and therefore chose it to be an integer array as
  opposed to some other object array, for example, a string array. This helped since 
  integers are easy to compare i.e can just use == rather than .equals and can also 
  represent other operations using numbers. For example, I used a 3 to represent a tie 
  and a 0 as the neutral. Moreover, it also also faster to use integers rather than string
  to write code and less prone to mistakes. My 2D array was initialized with a 0 all around, 
  and was each "box" of the grid was replaced by a 1 if player 1 placed their checker in this box
  and 2 if player 2 placed their checker. 

  2. Collections :
  
  I chose to use a collection to represent my undo button since collections are used to hold things of
  similar nature. Moreover, most collections have pre implemented methods that can be used that I would not
  have to code out. Furthermore, this is more efficient  as well as easier to implement and understand. 
  In sharp contrast, if I chose for example to use an array to store this, I might have had to 
  create another class for a object and then store the objects in the array. With that I would
  also have to implement a function to place the object in the array, as well as have setter and getter 
  methods for the object. The reason I chose to use Stacks for the undo button is because of stack's
  "last in, first out" method. This works for the undo button since for the undo button we want to remove
  the "last" turn played by the player. Furthermore, stacks conveniently also provide a method to 
  add an element as well as pop an element. Finally, another great benefit of stack is that I can 
  use the peek method to debug and see how my code does without actually removing the element. Other choices
  I would have would be Sets, however, these would be unordered or Maps, but it doesn't make sense
  to map the moves to the player, since knowing who played the last move doesn't matter when
  implemeting the undo button.

  3. FileI/O :
  
  I use to created a file and then store the top 3 high scores in this. The reason a file was appropriate
  for storing high scores was because a file can stay constant and can be access from all objects
  of my board. Therefore, I can show users the high scores from games that were played before the current one.
  I use the file writer methods to edit the top 3 highscores, if the conditions are met. Moreover, 
  I then use the read file method to gain the top 3 highscores, then store them in an array, compare
  it to the current score. If the current score deserves to be one of the top 3 highscore, then
  I edit the array and place the new top 3 scores into the file for the next play of the game
  to read and compare.To store the high score in the file, I use a "username|score" convention where
  the username and their score are separated by a "|". I can also read the file and display the
  highscores if the user wants to see it via the "show high scores" button. When displaying the score,
  I also arrange it a way that is standard for connect4 i.e a game finished with lower moves is considered
  better than a game finished with more moves.

  4. JUnit Test:
  
  I used Junit tests to test the game itself. Junit tests were useful since they allowed to
  implemenet the backend of the program and then work on the frontend which is the GUI. 
  Moreover, I also made sure that I split my functions acorss well so that
  I could test multiple different states of the same componenet game. All of this
  helped my debug my intital code that I had written for Connect4. Moreover, I also
  made sure to test edge cases. Finally, I did not only rely on the JUnit tests, 
  but also made sure that the game worked as expected in the GUI format. 
  
  

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  Talk about the implementation of file I/O.  Or going through the arrays and
  how to see if there are four in a row.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  yeah maybe refactor to separt



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
