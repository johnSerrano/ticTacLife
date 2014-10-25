//Author: John Serrano
//Free to use and distribute
//AI code goes in Turner.takeTurn()
import javax.swing.*;
import java.awt.Font;

public class TicTacLife{
    static int boardSize = 12;

    public static void main(String[] args){
	String board[][] = new String[boardSize][boardSize];
	for (int i = 0; i < boardSize; i++){
	    for (int j = 0; j < boardSize; j++){
		board[j][i] = ".";
	    }
	}
	
	Turner turner = new Turner();

	myWindow window = new myWindow(board);
	
	//main loop
	while(true){

	    mark myMark;
	    myMark = turner.takeTurn(board);

	    if (myMark==null) break;

	    board[myMark.getX()][myMark.getY()] = myMark.c;
	    //does not check legality, do that in takeTurn

	    try{Thread.sleep(1000);}
	    catch (InterruptedException ex){
	    }

	    window.update();
	}
	System.out.println("END");
    }

    private static void showBoard(String[][] board){
	for (int i = 0; i < boardSize; i++){
	    for (int j = 0; j < boardSize; j++){
		System.out.print(board[j][i] + " ");
	    }
	    System.out.print("\n");
	}
	System.out.println("*************");
    }
}

class Turner{

    int turnCount = 0;

    mark takeTurn(String[][] board){
	//process turn and return mark object (square chosen by AI). Todo

	mark result;

	boolean turnX;
	if ((turnCount % 2)==0) turnX = true;
	else turnX = false;

	//first move places a mark generated by default constructor.
	if (turnCount==0){
	    turnCount++;
	    result = new X();
	    result.c="X"; //why is this necessary? probably to do with casting
	    return result;
	}
	//return null if no valid move
	return null;
    }
}
abstract class mark{
    String c;
    abstract int getX();
    abstract int getY();
    abstract void setX(int x);
    abstract void setY(int y);
    abstract boolean equals(mark other);
}
class X extends mark{
    private int x, y;
    String c = "X";
    X(){
	this.x = 2;
	this.y = 2;
    }
    X(int x, int y){
	this.x = x;
	this.y = y;
    }
    int getX(){
	return this.x;
    }
    int getY(){
	return this.y;
    }
    void setX(int x){
	this.x = x;
    }
    void setY(int y){
	this.y = y;
    }
    boolean equals(mark other){
	if (other.c.equals("X")) return true;
	return false;
    }
}
class O extends mark{
    String c = "O";
    private int x, y;
    O(){
	this.x = 2;
	this.y = 2;
    }
    O(int x, int y){
	this.x = x;
	this.y = y;
    }
    int getX(){
	return this.x;
    }
    int getY(){
	return this.y;
    }
    void setX(int x){
	this.x = x;
    }
    void setY(int y){
	this.y = y;
    }
    boolean equals(mark other){
	if (other.c.equals("O")) return true;
	return false;
    }
}

class myWindow extends JFrame{
    String[][] board;
    
    myWindow(){
	JLabel label = new JLabel("No board to display");
	add(label);
	this.setSize(400, 400);
	setVisible(true);
    }

    myWindow(String[][] board){
	setArray(board);
	update();
    }
    
    void setArray(String[][] board){
	this.board = board;
    }
    
    void update(){
	String toDisplay = "<html>";
        for (int i = 0; i < board.length; i++){
	    for (int j = 0; j < board.length; j++){
		toDisplay = toDisplay + board[j][i] + " ";
	    }
	    toDisplay = toDisplay + "<br>";
	}
	toDisplay = toDisplay + "</html>";
	JLabel label = new JLabel(toDisplay, SwingConstants.CENTER);
	label.setFont(new Font("Courier New", Font.BOLD, 12));
	add(label);
	this.setSize(200, 200);
	setVisible(true);
    }
    
}
