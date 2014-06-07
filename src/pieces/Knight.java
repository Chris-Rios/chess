package pieces;

import utility.CUtil;

/**
 * @author Christopher Rios
 *
 */
public class Knight extends Piece 
{
	public Knight(String location,String color)
	{
		this.location =location;
		this.color = color;
		symbol = color.concat("N");
		
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
	public boolean checkLegal(String prev_pos, String new_pos) 
	{
		String convert_old_pos = CUtil.pos_Finder(prev_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int pfile = Integer.parseInt(convert_old_pos.substring(0,1));
		int prank = Integer.parseInt(convert_old_pos.substring(1));
		int nfile = Integer.parseInt(convert_new_pos.substring(0,1));
		int nrank = Integer.parseInt(convert_new_pos.substring(1));
		if(Math.abs(pfile-nfile)==1&&Math.abs(prank-nrank)==2)
		{
			return true;
		}
		if(Math.abs(pfile-nfile)==2&&Math.abs(prank-nrank)==1)
		{
			return true;
		}
		return false;
	}

	
}
