package pieces;

import utility.CUtil;

/**
 * @author Christopher Rios
 *
 */
public class Queen extends Piece 
{
	public Queen(String location,String color)
	{
		this.location =location;
		this.color = color;
		symbol = color.concat("Q");
		
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

	@Override
	public boolean checkLegal(String prev_pos, String new_pos) {
		String convert_old_pos = CUtil.pos_Finder(prev_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int pfile = Integer.parseInt(convert_old_pos.substring(0,1));
		int prank = Integer.parseInt(convert_old_pos.substring(1));
		int nfile = Integer.parseInt(convert_new_pos.substring(0,1));
		int nrank = Integer.parseInt(convert_new_pos.substring(1));
		//Diagonals
		if(pfile-nfile==prank-nrank)
		{
			//up-right or down-left
			return true;
		}
		if(pfile-nfile<0 && prank-nrank>0)
		{
			//down right
			if(Math.abs(pfile-nfile) ==Math.abs(prank-nrank))
			{
				return true;
			}
			return false;
		}
		if(pfile-nfile>0 && prank-nrank<0)
		{
			//up left
			if(Math.abs(pfile-nfile)==Math.abs(prank-nrank))
			{
				return true;
			}
			
			return false;
		}
		//cross
		if((prev_pos.substring(0,1).equals(new_pos.substring(0,1)))&&(prev_pos.substring(1).equals(new_pos.substring(1))==false))//up down
		{
			return true;
		}
		if((prev_pos.substring(0,1).equals(new_pos.substring(0,1))==false)&&(prev_pos.substring(1).equals(new_pos.substring(1))))//left right
		{
			return true;
		}
		return false;
	}

	
}
