ticTacLife
==========

A simple framework for tinkering with AI written by John Serrano

Was inspired to make this by the question, "How would you decide who won a tic tac toe game with a board of N size?"
Wound up with a fun little framework for playing with AI.

The answer to the above question that I've come up with is that a straight line is worth 2^n points, where n is the length of the line. If a line equal to the size of the board is drawn, whoever drew it wins. Whoever has more points at the end of the game wins. On a traditional 3x3 board, we see this evaluates correctly. We also now have a way to evaluate drawn games! I will implement functionality to evalutae the game.

The method takeTurn in class Turner is the place to put any AI code.

Compile TicTacLife.java and run.
