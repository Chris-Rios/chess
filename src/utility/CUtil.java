package utility;

/**
 * @author Christopher Rios
 *Utility class for Chess Project
 */
public class CUtil {

	/**
	 * Converts a rank and file position into an Indexable number
	 * @param position old or new position of a piece
	 * @return the numerical position of a piece
	 */
	public static String pos_Finder(String position)
	{
		String num_pos = null;
		int rank = Integer.parseInt(position.substring(1));
		//rank--;
		String file = position.substring(0,1).toLowerCase();
		switch(file)
		{
		case "a": num_pos = "0";
		break;
		case "b": num_pos = "1";
		break;
		case "c": num_pos = "2";
		break;
		case "d": num_pos = "3";
		break;
		case "e": num_pos = "4";
		break;
		case "f": num_pos = "5";
		break;
		case "g": num_pos = "6";
		break;
		case "h": num_pos = "7";
		break;
		default:break;
		}
		
		return num_pos.concat(Integer.toString(rank));
	}
	public static boolean inBounds(String position)
	{
		String file_bounds = "abcdefgh";
		String rank_bounds = "12345678";
		if(file_bounds.contains(position.substring(0,1))&&rank_bounds.contains(position.substring(1)))
		{
			return true;
		}
		return false;
	}
	/**
	 * Utility class, checks if a move is diagonal or not
	 * @param prev_pos previous position
	 * @param new_pos intended new position
	 * @return true if move is diagonal, false if horizontal
	 */
	public static boolean diagonal_check(String prev_pos,String new_pos)
	{
		if((prev_pos.substring(0,1).equals(new_pos.substring(0,1)))&&(prev_pos.substring(1).equals(new_pos.substring(1))==false))//up down
		{
			return false;
		}
		return true;
	}
	public static String formReturn(int file,int rank)
	{
		String converted=null;
		switch(file)
		{
		case 0: converted = "a"+Integer.toString(rank);
				break;
		case 1: converted = "b"+Integer.toString(rank);
				break;
		case 2: converted = "c"+Integer.toString(rank);
				break;
		case 3: converted = "d"+Integer.toString(rank);
				break;
		case 4: converted = "e"+Integer.toString(rank);
				break;
		case 5: converted = "f"+Integer.toString(rank);
				break;
		case 6: converted = "g"+Integer.toString(rank);
				break;
		case 7: converted = "h"+Integer.toString(rank);
				break;
		}
		return converted;
	}
	/**
	 * Utility method, returns if a castle move for a king is being attempted
	 * @param prev_pos king previous position
	 * @param new_pos king new position
	 * @return true if it is a castle move
	 */
	public static boolean castleCheck(String prev_pos, String new_pos) 
	{
		String convert_old_pos = CUtil.pos_Finder(prev_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int pfile = Integer.parseInt(convert_old_pos.substring(0,1));
		int prank = Integer.parseInt(convert_old_pos.substring(1));
		int nfile = Integer.parseInt(convert_new_pos.substring(0,1));
		int nrank = Integer.parseInt(convert_new_pos.substring(1));
		if(Math.abs(pfile-nfile)>1)
		{
			return true;
		}
			
		return false;
		
	}
}
