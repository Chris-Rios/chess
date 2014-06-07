package pieces;

/**
 * @author Christopher Rios
 *abstract Piece class that all chess pieces must use. Has general fields and methods to use
 *implementation of each is different depending on what type of piece it is.
 */
public abstract class Piece {
	String symbol;
	String location;
	String color;
	public abstract boolean move(String prev_pos, String new_pos);
	public abstract boolean checkLegal(String prev_pos,String new_pos);
	public String getColor()
	{
		return color;
	}
}
