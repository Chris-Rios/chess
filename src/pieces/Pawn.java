package pieces;

import utility.CUtil;

/**
 * @author Christopher Rios
 *
 */
public class Pawn extends Piece 
{

	boolean first_move;
	public Pawn(String location,String color)
	{
		this.location =location;
		this.color = color;
		symbol = color.concat("p");
		first_move=true;
		
	}
	
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
	@Override
	public boolean checkLegal(String prev_pos, String new_pos) 
	{
		String convert_old_pos = CUtil.pos_Finder(prev_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int prev_file=Integer.parseInt(convert_old_pos.substring(0,1));
		int new_file=Integer.parseInt(convert_new_pos.substring(0,1));
		int prev_rank=Integer.parseInt(convert_old_pos.substring(1));
		int new_rank=Integer.parseInt(convert_new_pos.substring(1));
		if(color.equals("b")&&first_move==true&&(prev_pos.substring(0,1).equals(new_pos.substring(0,1)))&&(prev_rank-new_rank==2))//up 2
		{
			return true;
		}
		if(color.equals("w")&&first_move==true&&(prev_pos.substring(0,1).equals(new_pos.substring(0,1)))&&(prev_rank-new_rank==-2))//up 2 spots
		{
			return true;
		}
		if(color.equals("b")&&(prev_pos.substring(0,1).equals(new_pos.substring(0,1)))&&(prev_rank-new_rank==1))//up 
		{
			return true;
		}
		if(color.equals("w")&&(prev_pos.substring(0,1).equals(new_pos.substring(0,1)))&&(prev_rank-new_rank==-1))//up
		{
			return true;
		}
		if(color.equals("w")&&((prev_file-new_file==-1&&prev_rank-new_rank==-1)||(prev_file-new_file==1&&prev_rank-new_rank==-1)))//capture
		{
			return true;
		}
		if(color.equals("b")&&((prev_file-new_file==-1&&prev_rank-new_rank==1)||(prev_file-new_file==1&&prev_rank-new_rank==1)))
		{
			return true;
		}
		return false;
	}

	
}
