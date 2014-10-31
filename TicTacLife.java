//Author: John Serrano
//Free to use and distribute
//AI code goes in Turner.takeTurn()

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.lang.Math;
import java.util.Scanner;

public class TicTacLife{
    static int boardSize = 5;

    public static void main(String[] args){
	String board[][] = new String[boardSize][boardSize];
	for (int i = 0; i < boardSize; i++){
	    for (int j = 0; j < boardSize; j++){
		board[j][i] = ".";
	    }
	}
        
	myWindow window = new myWindow(board);
    }
}

class myWindow extends JFrame{
    String[][] board;
    String toDisplay;
    JLabel boardL;
    JLabel score;
    JPanel bottom;

    static int boardSize;
    boolean turnX = true;
    
    myWindow(){
	JLabel label = new JLabel("No board to display");
	add(label);
	this.setSize(400, 400);
	setVisible(true);
    }

    myWindow(String[][] board){
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	setArray(board);
	boardSize = board.length;
	toDisplay = "<html>";
        for (int i = 0; i < board.length; i++){
	    for (int j = 0; j < board.length; j++){
		toDisplay = toDisplay + board[j][i] + " ";
	    }
	    toDisplay = toDisplay + "<br>";
	}
	toDisplay = toDisplay + "</html>";
	boardL = new JLabel(toDisplay, SwingConstants.CENTER);
	boardL.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	boardL.setFont(new Font("Courier New", Font.BOLD, 12));
 	add(boardL, BorderLayout.CENTER);

	JLabel header = new JLabel("TIC TAC LIFE\n\n", SwingConstants.CENTER);
	add(header, BorderLayout.PAGE_START);

	score = new JLabel("<html>Player 1 (X):<br>0<br><br>Player 2 (O):<br>0</html>", SwingConstants.CENTER);
	score.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	add(score, BorderLayout.LINE_START);

	bottom = new JPanel();
	
	JTextField entry = new JTextField(4);
	bottom.add(entry);
	
	JButton submit = new JButton("submit");
	submit.addActionListener(new submitListener(this, entry));
	bottom.add(submit);

	add(bottom, BorderLayout.PAGE_END);

	this.setSize(400, 400);
	setVisible(true);
    }
    
    void setArray(String[][] board){
	this.board = board;
    }
    
    void update(int scoreX, int scoreO){
	toDisplay = "<html>";
        for (int i = 0; i < board.length; i++){
	    for (int j = 0; j < board.length; j++){
		toDisplay = toDisplay + board[j][i] + " ";
	    }
	    toDisplay = toDisplay + "<br>";
	}
	toDisplay = toDisplay + "</html>";
	
	this.remove(boardL); //delete old board
	boardL = new JLabel(toDisplay, SwingConstants.CENTER);
	boardL.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	boardL.setFont(new Font("Courier New", Font.BOLD, 12));
	add(boardL, BorderLayout.CENTER);

	this.remove(score);
	String scoreLabel = "<html>Player 1 (X):<br>" + scoreX + "<br><br>Player 2 (O):<br>" + scoreO + "</html>";
	score = new JLabel(scoreLabel, SwingConstants.CENTER);
	score.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	add(score, BorderLayout.LINE_START);

	this.setSize(400, 400);
	setVisible(true);
    }
    
    public void takeTurn(String coords){

	//parse string
	Scanner scan = new Scanner(coords);
	int x = scan.nextInt();
	int y = scan.nextInt();
	//handle exceptions: out of bounds, not an integer, occupied position
	//todo

	//update turnx, make label in window to display
	turnX = !turnX;
	
	if (turnX){
	    board[x][y] = "O";
	}
	else if (!turnX){
	    board[x][y] = "X";
	}

	this.update(score("X", board), score("O", board));
    }

    private static int score(String toScore, String[][] board){
	int lineLength = 0;
	int pts = 0;

	//score vertically NOTE: single mark counts for no points
	for (int i = 0; i < boardSize; i++){
	    for (int j = 0; j < boardSize; j++){
		if (board[i][j].equals(toScore) && j<(boardSize-1)){
		    if (board[i][j+1].equals(toScore)){
			lineLength++;
		    }
		    else{
			if (lineLength!=0){
			    pts = (int) Math.pow(2, lineLength) + pts;
			}
			lineLength = 0;
		    }
		}
		else{
		    if (lineLength!=0){
			pts = (int) Math.pow(2, lineLength) + pts;
		    }
		    lineLength = 0;
		}
	    }
	}

	//score horizontally
	for (int i = 0; i < boardSize; i++){
	    for (int j = 0; j < boardSize; j++){
		if (board[j][i].equals(toScore) && j<(boardSize-1)){
		    if (board[j+1][i].equals(toScore)){
			lineLength++;
		    }
		    else if (lineLength!=0){
			    pts = (int) Math.pow(2, lineLength) + pts;
			    lineLength = 0;
			}
		}
		else if (lineLength!=0){
		    pts = (int) Math.pow(2, lineLength) + pts;
		    lineLength = 0;
		}	
	    }
	}
	
	//score diagonally

	int x = 0, y = 0;

	while (x!=boardSize-1 || y!=boardSize-1){
	    if (y==0 && x==0){
		y++;
	    }
	    while (y > 0 && x < boardSize-1){
		
		if (board[x][y].equals(toScore)){
		    if (board[x+1][y-1].equals(toScore)){
			lineLength++;
		    }
		    else if (lineLength!=0){
			    pts = (int) Math.pow(2, lineLength) + pts;
			    lineLength = 0;
			}
		}
		else if (lineLength!=0){
		    pts = (int) Math.pow(2, lineLength) + pts;
		    lineLength = 0;
		}
		x++;
		y--;
	    }
	    if (x < boardSize-1){
		x++;
	    }
	    else if(x == boardSize-1){
		y++;
	    }
	    if (lineLength!=0){
		pts = (int) Math.pow(2, lineLength) + pts;
		lineLength = 0;
	    }
	    while (x > 0 && y < boardSize-1){
		if (board[x][y].equals(toScore)){
		    if (board[x-1][y+1].equals(toScore)){
			lineLength++;
		    }
		    else if (lineLength!=0){
			pts = (int) Math.pow(2, lineLength) + pts;
			lineLength = 0;
			}
		}
		else if (lineLength!=0){
		    pts = (int) Math.pow(2, lineLength) + pts;
		    lineLength = 0;
		}
		x--;
		y++;
	    }
	    if (y < boardSize-1){
		y++;
	    }
	    else if(y == boardSize-1 && x < boardSize-1){
		x++;
	    }
	    if (lineLength!=0){
		pts = (int) Math.pow(2, lineLength) + pts;
		lineLength = 0;
	    }
	}

	//score diagonally other direction
	x = 1; 
	y = boardSize-1;
	while(x < boardSize-1 || y > 0){
	    while (x>0 && y>0){
		if (board[x][y].equals(toScore)){
		    if (board[x-1][y-1].equals(toScore)){
			lineLength++;
		    }
		    else if (lineLength!=0){
			pts = (int) Math.pow(2, lineLength) + pts;
			lineLength = 0;
			}
		}
		else if (lineLength!=0){
		    pts = (int) Math.pow(2, lineLength) + pts;
		    lineLength = 0;
		}
		x--; y--;
	    }
	    if (y > 0){
		y--;
	    }
	    else if (y == 0){
		x++;
	    }

	    while (x < boardSize-1 && y < boardSize-1){
		if (board[x][y].equals(toScore)){
		    if (board[x+1][y+1].equals(toScore)){
			lineLength++;
		    }
		    else if (lineLength!=0){
			pts = (int) Math.pow(2, lineLength) + pts;
			lineLength = 0;
			}
		}
		else if (lineLength!=0){
		    pts = (int) Math.pow(2, lineLength) + pts;
		    lineLength = 0;
		}
		x++; y++;
	    }
	    if (x < boardSize-1){
		x++;
	    }
	    else if (x == boardSize-1 && y > 0){
		y--;
	    }
	    if (lineLength!=0){
		pts = (int) Math.pow(2, lineLength) + pts;
		lineLength = 0;
	    }
	}

	return pts;
    }
}

class submitListener implements ActionListener{
    myWindow subWindow;
    JTextField jtf;

    submitListener(myWindow window, JTextField entry){
	subWindow = window;
	jtf = entry;
    }
    public void actionPerformed(ActionEvent e){
	subWindow.takeTurn(jtf.getText());
	jtf.setText("");
    }
}
