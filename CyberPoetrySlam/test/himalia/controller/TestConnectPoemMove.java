package himalia.controller;

import java.util.ArrayList;

import himalia.model.Board;
import himalia.model.Poem;
import himalia.model.Position;
import himalia.model.Row;
import himalia.model.Word;
import himalia.model.WordType;
import junit.framework.TestCase;

public class TestConnectPoemMove extends TestCase {
	ConnectPoemMove connectPoemMoveOne;
	ConnectPoemMove connectPoemMoveTwo;
	Word singlePoem1Word;
    Word singlePoem2Word;
    Position poem2Pos;
    Board board;
    boolean fromRedo = false;
    boolean connectedFromTop = false;
    protected void setUp(){
       	Word word = new Word(102,203,0,"Hello", WordType.adj);
		ArrayList<Word> wrds = new ArrayList<Word>();
		wrds.add(word);
		Position posProtect = new Position(1,2,0);
		Position posUnprotect =new Position(100,200,0);
		int heightProtect = 50;
		int heightUnprotect=50; 
		int widthProtect=50; 
		int widthUnprotect=50;
		this.board = new Board(wrds, posProtect,posUnprotect, heightProtect,heightUnprotect,widthProtect, widthUnprotect);
		Word w1=new Word(10,10,10,"a",WordType.adj);
		Word w2=new Word(11,12,10,"b",WordType.adj);
		Poem p1=new Poem();	
		Row r1=new Row(p1, w1);
		p1.addInitialRow(r1);
		Poem p2=new Poem();
		Row r2=new Row(p2,w2);
		p2.addInitialRow(r2);
		this.board.getProtectedRegion().addPoem(p1);
		this.board.getProtectedRegion().addPoem(p2);
		connectPoemMoveOne=new ConnectPoemMove(p1, p2, p1.getPosition(), p2.getPosition());
		connectPoemMoveTwo=new ConnectPoemMove(p2, p1, p2.getPosition(), p1.getPosition());
    }
	public void testexecute(){
		connectPoemMoveOne.execute(board);
		//board.getProtectedRegion().getPoem(0).disconnectPoemFromRowIndex(0, true);
	}
	public void testundo(){
		//connectPoemMoveOne.undo(board);
	}

}
