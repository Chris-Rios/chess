package pieces;


/**
 * @author Christopher Rios
 *
 */
public class Rook extends Piece 
{
	private boolean first_move=true;
	public Rook(String location,String color)
	{
		this.location =location;
		this.color = color;
		symbol = color.concat("R");
		
	}

	@Override
	public boolean move(String prev_pos, String new_pos) 
	{
		if(checkLegal(prev_pos,new_pos)==true)
		{
		location = new_pos;
		return true;
		}
		return false;
	}
	public String toString()
	{
		return symbol;
	}
	public void consumeFirstMove()
	{
		first_move=false;
	}
	public boolean getFirstMove()
	{
		return first_move;
	}
	public boolean checkLegal(String prev_pos, String new_pos) 
	{
		if((prev_pos.substring(0,1).equals(new_pos.substring(0,1)))&&(prev_pos.substring(1).equals(new_pos.substring(1))==false))//up down
			{
				return true;
			}
		else if((prev_pos.substring(0,1).equals(new_pos.substring(0,1))==false)&&(prev_pos.substring(1).equals(new_pos.substring(1))))//left right
			{
				return true;
			}
		return false;
	}

	
}
