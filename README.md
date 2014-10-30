ticTacLife
==========

An extended version of Tic Tac Toe created by John Serrano

Was inspired to make this by the question, "How would you decide who won a tic tac toe game with a board of N size?"

The answer to the above question that I've come up with is that a straight line is worth 2^n points, where n is the length of the line. A single mark does not count as a line with length 1. If a line equal to the size of the board is drawn, whoever drew it wins. Otherwise, whoever has more points at the end of the game wins. On a traditional 3x3 board, we see this evaluates correctly. We also now have a way to evaluate drawn games! The program scores the board according to these rules.

The method takeTurn in class Turner is the place to put any AI code if you are interested in that.

Compile TicTacLife.java and run.
