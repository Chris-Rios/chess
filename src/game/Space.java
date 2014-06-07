package game;

import pieces.Piece;

/**
 * @author Christopher Rios
 *
 */
public class Space {

	private String color;
	private Piece piece;
	private boolean empty = true;
	public Space(String color)
	{
		this.color = color;
		piece = null;
	}
	public void setPiece(Piece piece)
	{
		this.piece = piece;
		empty = false;
	}
	
	public void clearPiece()
	{
		piece=null;
		empty = true;
	}
	
	public boolean isEmpty()
	{
		return empty;
	}
	public Piece getPiece()
	{
		return piece;
	}
	public String toString()
	{
		if(empty)
		{
			return color;
		}
		return piece.toString();
	}
}
