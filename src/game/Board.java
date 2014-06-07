package game;

import pieces.*;
import utility.CUtil;


/**
 * @author Christopher Rios
 *
 */
public class Board 
{
	private Space[][] board = new Space[9][9];
	private String bking_loc;//tracks the kings locations
	private String wking_loc;
	/**
	 *Initial Setup of the Chess Board 
	 */
	public void setup()
	{
		//Setup the back row
		board[0][8] = new Space("  ");
		board[0][8].setPiece(new Rook("A8","b"));
		board[1][8] = new Space("##");
		board[1][8].setPiece(new Knight("B8","b"));
		board[2][8] = new Space("  ");
		board[2][8].setPiece(new Bishop("C8","b"));
		board[3][8] = new Space("##");
		board[3][8].setPiece(new Queen("D8","b"));
		board[4][8] = new Space("  ");
		board[4][8].setPiece(new King("E8","b"));
		bking_loc = "e8";
		board[5][8] = new Space("##");
		board[5][8].setPiece(new Bishop("F8","b"));
		board[6][8] = new Space("  ");
		board[6][8].setPiece(new Knight("G8","b"));
		board[7][8] = new Space("##");
		board[7][8].setPiece(new Rook("H8","b"));
		board[8][8] = new Space("8");
		//Setup Black's the Pawn row
		board[0][7] = new Space("##");
		board[0][7].setPiece(new Pawn("A7", "b"));
		board[1][7] = new Space("  ");
		board[1][7].setPiece(new Pawn("B7", "b"));
		board[2][7] = new Space("##");
		board[2][7].setPiece(new Pawn("C7", "b"));
		board[3][7] = new Space("  ");
		board[3][7].setPiece(new Pawn("D7", "b"));
		board[4][7] = new Space("##");
		board[4][7].setPiece(new Pawn("E7", "b"));
		board[5][7] = new Space("  ");
		board[5][7].setPiece(new Pawn("F7", "b"));
		board[6][7] = new Space("##");
		board[6][7].setPiece(new Pawn("G7", "b"));
		board[7][7] = new Space("  ");
		board[7][7].setPiece(new Pawn("H7", "b"));
		board[8][7] = new Space("7");
		//Setup Row 6
		board[0][6] = new Space("  ");
		board[1][6] = new Space("##");
		board[2][6] = new Space("  ");
		board[3][6] = new Space("##");
		board[4][6] = new Space("  ");
		board[5][6] = new Space("##");
		board[6][6] = new Space("  ");
		board[7][6] = new Space("##");
		board[8][6] = new Space("6");
		//Setup Row 5
		board[0][5] = new Space("##");
		board[1][5] = new Space("  ");
		board[2][5] = new Space("##");
		board[3][5] = new Space("  ");
		board[4][5] = new Space("##");
		board[5][5] = new Space("  ");
		board[6][5] = new Space("##");
		board[7][5] = new Space("  ");
		board[8][5] = new Space("5");
		//Setup Row 4
		board[0][4] = new Space("  ");
		board[1][4] = new Space("##");
		board[2][4] = new Space("  ");
		board[3][4] = new Space("##");
		board[4][4] = new Space("  ");
		board[5][4] = new Space("##");
		board[6][4] = new Space("  ");
		board[7][4] = new Space("##");
		board[8][4] = new Space("4");
		//Setup Row 3
		board[0][3] = new Space("##");
		board[1][3] = new Space("  ");
		board[2][3] = new Space("##");
		board[3][3] = new Space("  ");
		board[4][3] = new Space("##");
		board[5][3] = new Space("  ");
		board[6][3] = new Space("##");
		board[7][3] = new Space("  ");
		board[8][3] = new Space("3");
		//Setup White's Pawn Row
		board[0][2] = new Space("  ");
		board[0][2].setPiece(new Pawn("A2", "w"));
		board[1][2] = new Space("##");
		board[1][2].setPiece(new Pawn("B2", "w"));
		board[2][2] = new Space("  ");
		board[2][2].setPiece(new Pawn("C2", "w"));
		board[3][2] = new Space("##");
		board[3][2].setPiece(new Pawn("D2", "w"));
		board[4][2] = new Space("  ");
		board[4][2].setPiece(new Pawn("E2", "w"));
		board[5][2] = new Space("##");
		board[5][2].setPiece(new Pawn("F2", "w"));
		board[6][2] = new Space("  ");
		board[6][2].setPiece(new Pawn("G2", "w"));
		board[7][2] = new Space("##");
		board[7][2].setPiece(new Pawn("H2", "w"));
		board[8][2] = new Space("2");
		//Setup White's Back Row
		board[0][1] = new Space("##");
		board[0][1].setPiece(new Rook("A1","w"));
		board[1][1] = new Space("  ");
		board[1][1].setPiece(new Knight("B1","w"));
		board[2][1] = new Space("##");
		board[2][1].setPiece(new Bishop ("C1","w"));
		board[3][1] = new Space("  ");
		board[3][1].setPiece(new Queen("D1","w"));
		board[4][1] = new Space("##");
		board[4][1].setPiece(new King("E1","w"));
		wking_loc = "e1";
		board[5][1] = new Space("  ");
		board[5][1].setPiece(new Bishop("F1","w"));
		board[6][1] = new Space("##");
		board[6][1].setPiece(new Knight("G1","w"));
		board[7][1] = new Space("  ");
		board[7][1].setPiece(new Rook("H1","w"));
		board[8][1] = new Space("1");
		//Setup files
		board[0][0] = new Space(" a");
		board[1][0] = new Space(" b");
		board[2][0] = new Space(" c");
		board[3][0] = new Space(" d");
		board[4][0] = new Space(" e");
		board[5][0] = new Space(" f");
		board[6][0] = new Space(" g");
		board[7][0] = new Space(" h");
		board[8][0] = new Space("  ");
		draw();
	}
	/**
	 *draws the board 
	 */
	public void draw()
	{
		
		for(int j = 8;j>=0;j--)
		{
			System.out.print("\n");
			for(int i = 0;i<9;i++)
			{
				System.out.print(" "+board[i][j]);
			
			}
			
		}
		System.out.print("\n");
	}
	public void updateBoard(Piece piece, String old_pos,String new_pos)
	{
		String convert_old_pos = CUtil.pos_Finder(old_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int file = Integer.parseInt(convert_old_pos.substring(0,1));
		int rank = Integer.parseInt(convert_old_pos.substring(1));
		board[file][rank].clearPiece();		//update old position
		file = Integer.parseInt(convert_new_pos.substring(0,1));
		rank = Integer.parseInt(convert_new_pos.substring(1));
		board[file][rank].setPiece(piece);	//update new position
		if(piece instanceof King)
		{
			updateKingLoc((King) piece,new_pos);
		}
		draw();
	}
	public Piece getPiece(String position)
	{
		String convert_position = CUtil.pos_Finder(position);
		int file = Integer.parseInt(convert_position.substring(0,1));
		int rank = Integer.parseInt(convert_position.substring(1));
		Piece piece = board[file][rank].getPiece();
		return piece;
	}
	
	/**
	 * Checks to see if a space is occupied by an allied piece
	 * @param old_pos position of the piece to be moved
	 * @param new_pos intended position of the piece
	 * @return true if occupied by an allied piece
	 */
	public boolean isOccupied(String old_pos,String new_pos)
	{
		String convert_old_pos = CUtil.pos_Finder(old_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);		
		int nfile = Integer.parseInt(convert_new_pos.substring(0,1));
		int nrank = Integer.parseInt(convert_new_pos.substring(1));
		int ofile = Integer.parseInt(convert_old_pos.substring(0,1));
		int orank = Integer.parseInt(convert_old_pos.substring(1));
		if(board[nfile][nrank].isEmpty())
		{
			return false;
		}
		if(!board[nfile][nrank].getPiece().getColor().equals(board[ofile][orank].getPiece().getColor()))//compares colors of two pieces
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if a pieces path is clear
	 * @param old_pos old position of the piece
	 * @param new_pos intended new position of the piece
	 * @param object Object whose path will be checked
	 * @return false if passed the wrong type of object, or if path is blocked, true otherwise
	 */
	public boolean isClear(String old_pos,String new_pos,Object object)
	{
		if(object instanceof Rook )
		{
		
		return isClear(old_pos,new_pos,(Rook) object);
		}
		if(object instanceof Bishop)
		{
			
			return isClear(old_pos,new_pos,(Bishop) object);
		}
		if(object instanceof Queen)
		{
			return isClear(old_pos,new_pos,(Queen) object);
		}
		if(object instanceof Pawn)
		{
			return isClear(old_pos,new_pos,(Pawn) object);
		}
		return true;
		
	}
	public boolean isClear(String old_pos,String new_pos,Queen queen)
	{
		int rank_iterator;
		int file_iterator;
		String convert_old_pos = CUtil.pos_Finder(old_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int pfile = Integer.parseInt(convert_old_pos.substring(0,1));
		int prank = Integer.parseInt(convert_old_pos.substring(1));
		int nfile = Integer.parseInt(convert_new_pos.substring(0,1));
		int nrank = Integer.parseInt(convert_new_pos.substring(1));
		//Diagonals
		if(pfile-nfile==prank-nrank)
		{
			
			if(prank>nrank)//down-left
			{
				rank_iterator = prank-1;
				file_iterator = pfile-1;
				while(rank_iterator>nrank)
				{
					if(!board[file_iterator][rank_iterator].isEmpty())
					{
						return false;
					}
					rank_iterator--;
					file_iterator--;
				}
			}
			if(prank<nrank)//up right
			{
				rank_iterator = prank+1;
				file_iterator = pfile+1;
				while(rank_iterator<nrank)
				{
					if(!board[file_iterator][rank_iterator].isEmpty())
					{
						return false;
					}
					rank_iterator++;
					file_iterator++;
				}
			}
			return true;
		}
		if(pfile-nfile<0 && prank-nrank>0)//down right
		{
			
			file_iterator = pfile+1;
			rank_iterator = prank-1;
			while(file_iterator<nfile)
			{
				if(!board[file_iterator][rank_iterator].isEmpty())
				{
					return false;
				}
				rank_iterator--;
				file_iterator++;
			}
			
		}
		if(pfile-nfile>0 && prank-nrank<0)//up left
		{
			
			file_iterator = pfile-1;
			rank_iterator = prank+1;
			while(rank_iterator<nrank)
			{
				if(!board[file_iterator][rank_iterator].isEmpty())
				{
					return false;
				}
				rank_iterator++;
				file_iterator--;
			}
		}
		//cross
		if((old_pos.substring(0,1).equals(new_pos.substring(0,1)))&&(old_pos.substring(1).equals(new_pos.substring(1))==false))//up down
		{
			
			if(prank-nrank<0)//moves up
			{
				rank_iterator =prank+1;
				while(rank_iterator!=nrank)
				{
					if(!board[pfile][rank_iterator].isEmpty())
					{
						return false;
					}
					rank_iterator++;
				}
			}
			if(prank-nrank>0)//moves down
			{
				rank_iterator =prank-1;
				while(rank_iterator!=nrank)
				{
					if(!board[pfile][rank_iterator].isEmpty())
					{
						return false;
					}
					rank_iterator--;
				}
			}
	
		}
		else if((old_pos.substring(0,1).equals(new_pos.substring(0,1))==false)&&(old_pos.substring(1).equals(new_pos.substring(1))))//left right
		{
			if(pfile-nfile<0)//moves right
			{
				file_iterator =pfile+1;
				
				while(file_iterator!=nfile)
				{
					if(!board[file_iterator][prank].isEmpty())
					{
						return false;
					}
					file_iterator++;
				}
			}
			if(pfile-nfile>0)//moves left
			{
				file_iterator =pfile-1;
				while(file_iterator!=nfile)
				{
					if(!board[file_iterator][prank].isEmpty())
					{
						return false;
					}
					file_iterator--;
				}
			}
			
		}
		return true;
	}
	/**
	 * Checks if the path that rook is moving in is clear
	 * @param old_pos old position of the rook
	 * @param new_pos intended new position of the rook
	 * @param rook rook that is moving
	 * @return
	 */
	public boolean isClear(String old_pos,String new_pos,Rook rook)
	{
		int iterator;
		String convert_old_pos = CUtil.pos_Finder(old_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int pfile = Integer.parseInt(convert_old_pos.substring(0,1));
		int prank = Integer.parseInt(convert_old_pos.substring(1));
		int nfile = Integer.parseInt(convert_new_pos.substring(0,1));
		int nrank = Integer.parseInt(convert_new_pos.substring(1));
		if((old_pos.substring(0,1).equals(new_pos.substring(0,1)))&&(old_pos.substring(1).equals(new_pos.substring(1))==false))//up down
		{
			
			if(prank-nrank<0)//moves up
			{
				iterator =prank+1;
				while(iterator!=nrank)
				{
					if(!board[pfile][iterator].isEmpty())
					{
						return false;
					}
					iterator++;
				}
			}
			if(prank-nrank>0)//moves down
			{
				iterator =prank-1;
				while(iterator!=nrank)
				{
					if(!board[pfile][iterator].isEmpty())
					{
						return false;
					}
					iterator--;
				}
			}
	
		}
		else if((old_pos.substring(0,1).equals(new_pos.substring(0,1))==false)&&(old_pos.substring(1).equals(new_pos.substring(1))))//left right
		{
			if(pfile-nfile<0)//moves right
			{
				iterator =pfile+1;
				
				while(iterator!=nfile)
				{
					if(!board[iterator][prank].isEmpty())
					{
						return false;
					}
					iterator++;
				}
			}
			if(pfile-nfile>0)//moves left
			{
				iterator =pfile-1;
				while(iterator!=nfile)
				{
					if(!board[iterator][prank].isEmpty())
					{
						return false;
					}
					iterator--;
				}
			}
			
		}
		return true;
		
	}
	
	
	public boolean isClear(String old_pos,String new_pos,Bishop bishop)
	{
		int rank_iterator;
		int file_iterator;
		String convert_old_pos = CUtil.pos_Finder(old_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int pfile = Integer.parseInt(convert_old_pos.substring(0,1));
		int prank = Integer.parseInt(convert_old_pos.substring(1));
		int nfile = Integer.parseInt(convert_new_pos.substring(0,1));
		int nrank = Integer.parseInt(convert_new_pos.substring(1));
		if(pfile-nfile==prank-nrank)
		{
			
			if(prank>nrank)//down-left
			{
				rank_iterator = prank-1;
				file_iterator = pfile-1;
				while(rank_iterator>nrank)
				{
					if(!board[file_iterator][rank_iterator].isEmpty())
					{
						return false;
					}
					rank_iterator--;
					file_iterator--;
				}
			}
			if(prank<nrank)//up right
			{
				rank_iterator = prank+1;
				file_iterator = pfile+1;
				while(rank_iterator<nrank)
				{
					if(!board[file_iterator][rank_iterator].isEmpty())
					{
						return false;
					}
					rank_iterator++;
					file_iterator++;
				}
			}
			return true;
		}
		if(pfile-nfile<0 && prank-nrank>0)//down right
		{
			
			file_iterator = pfile+1;
			rank_iterator = prank-1;
			while(file_iterator<nfile)
			{
				if(!board[file_iterator][rank_iterator].isEmpty())
				{
					return false;
				}
				rank_iterator--;
				file_iterator++;
			}
			
		}
		if(pfile-nfile>0 && prank-nrank<0)//up left
		{
			
			file_iterator = pfile-1;
			rank_iterator = prank+1;
			while(rank_iterator<nrank)
			{
				if(!board[file_iterator][rank_iterator].isEmpty())
				{
					return false;
				}
				rank_iterator++;
				file_iterator--;
			}
		}
		return true;
	}
	public boolean isClear(String old_pos,String new_pos,Pawn pawn)
	{
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int nfile = Integer.parseInt(convert_new_pos.substring(0,1));
		int nrank = Integer.parseInt(convert_new_pos.substring(1));
		String convert_old_pos=CUtil.pos_Finder(old_pos);
		int ofile = Integer.parseInt(convert_old_pos.substring(0,1));
		int orank = Integer.parseInt(convert_old_pos.substring(1));
		
		if(pawn.getColor().equals("w")&&Math.abs(orank-nrank)==2)
		{
			if(!board[nfile][nrank-1].isEmpty())
			{
				return false;
			}
		}
		if(pawn.getColor().equals("b")&&Math.abs(orank-nrank)==2)
		{
			if(!board[nfile][nrank+1].isEmpty())
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean isTaken(String new_pos)
	{
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int nfile = Integer.parseInt(convert_new_pos.substring(0,1));
		int nrank = Integer.parseInt(convert_new_pos.substring(1));
		if(board[nfile][nrank].isEmpty())
		{
		return false;
		}
		return true;
	}
	public int checkCheck(String color,Piece moved,String location)//checks if a piece is in check
	{
		int file_iterator=0;
		int rank_iterator;
		String[] movements;
		int check_count=0;
		Piece current;
		String curr_loc;
		int movements_iterator=0;
		boolean[] can_take = new boolean[8];
		int take_iterator=0;
		boolean in_check=false;
		if(color.equals("white"))// checks if white is in check and or checkmate
		{
			
			movements = kingsMovements("white");
			while(file_iterator<8)
			{
				rank_iterator=0;	
				while(rank_iterator<9)
				{
					movements_iterator=0;
					if(!board[file_iterator][rank_iterator].isEmpty()&&board[file_iterator][rank_iterator].getPiece().getColor().equals("b"))
					{
						current = board[file_iterator][rank_iterator].getPiece();
						curr_loc = CUtil.formReturn(file_iterator, rank_iterator);
						while(movements[movements_iterator]!=null)
						{
							if(current.move(curr_loc,movements[movements_iterator])==true&&this.isOccupied(curr_loc,movements[movements_iterator])==false&&this.isClear(curr_loc, movements[movements_iterator], current))//if the piece can move to a location the king can move to
							{
								can_take[movements_iterator]=true;
								
							}
							movements_iterator++;
						}
					    take_iterator =movements_iterator;
					}
					rank_iterator++;
				}
				file_iterator++;
				
			}
			if(moved.checkLegal(location,wking_loc)&&this.isClear(location,wking_loc,moved))
			{
				in_check=true;
			}
		}
		else if(color.equals("black")) //checks if blacks king is in check and or checkmate
		{
			movements =kingsMovements("black");
			while(file_iterator<8)
			{
				rank_iterator=0;
				while(rank_iterator<9)
				{
					movements_iterator=0;
					if(!board[file_iterator][rank_iterator].isEmpty()&&board[file_iterator][rank_iterator].getPiece().getColor().equals("w"))
					{
						current = board[file_iterator][rank_iterator].getPiece();
						curr_loc = CUtil.formReturn(file_iterator, rank_iterator);
						while(movements[movements_iterator]!=null)
						{
							if(current.move(curr_loc,movements[movements_iterator])==true&&this.isOccupied(curr_loc,movements[movements_iterator])==false&&this.isClear(curr_loc, movements[movements_iterator], current))//if the piece can move to a location the king can move to
							{
								can_take[movements_iterator]=true;
								
							}
							movements_iterator++;
						}
					    take_iterator =movements_iterator;
					}
					rank_iterator++;
				}
				file_iterator++;
				
			}
			if(moved.checkLegal(location,bking_loc)&&this.isClear(location,bking_loc,moved))//checks if king is in check
			{
				in_check=true;
				
			}
		}
		for(int i =0;i<take_iterator;i++)//makes sure all possible moves are able to be taken by an opposing color
		{
			if(can_take[i]==true)
			{
				check_count++;
			}
		}
		if(check_count==take_iterator&&in_check==true)//checks if checkmate 
		{
			return 2;
		}
		if(in_check==true)//checks if the king is in check
		{
			return 1;
		}
		return 0;
	}
	public void updateKingLoc(King king, String new_location)
	{
		if(king.getColor().equals("b"))
		{
			bking_loc =new_location;
		}
		else
		{
			wking_loc = new_location;
		}
	}
	
	public String[] kingsMovements(String color)
	{
		String convert_pos;
		int file=0;
		int rank=0;
		String current_move;
		String[] movements= new String[8];
		int movements_iterator=0;
		if(color.equals("white"))//get white kings location
		{
			convert_pos = CUtil.pos_Finder(wking_loc);
			file = Integer.parseInt(convert_pos.substring(0,1));
			rank = Integer.parseInt(convert_pos.substring(1));
		}
		if(color.equals("black"))//get black kings location
		{
			convert_pos = CUtil.pos_Finder(bking_loc);
			file = Integer.parseInt(convert_pos.substring(0,1));
			rank = Integer.parseInt(convert_pos.substring(1));
		}
		
		if(color.equals("white"))// all of the white king's possible moves
		{
			if(rank+1<9)//up
			{
				if(board[file][rank+1].isEmpty()||board[file][rank+1].getPiece().getColor().equals("b"))
				{
					current_move=CUtil.formReturn(file, rank+1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
						
			}
			if(rank-1>0)	//down
			{
				if(board[file][rank-1].isEmpty()||board[file][rank-1].getPiece().getColor().equals("b"))
				{
					current_move=CUtil.formReturn(file, rank-1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
			}
			if(file+1<8)				//right
			{
				if(board[file+1][rank].isEmpty()||board[file+1][rank].getPiece().getColor().equals("b"))
				{
					current_move=CUtil.formReturn(file+1, rank);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
			}
			if(file-1>=0)				//left
			{
				if(board[file-1][rank].isEmpty()||board[file-1][rank].getPiece().getColor().equals("b"))
				{
					current_move=CUtil.formReturn(file-1, rank);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
			}				
			if(file+1<8&&rank+1<9)				//up-right
			{
				if(board[file+1][rank+1].isEmpty()||board[file+1][rank+1].getPiece().getColor().equals("b"))
				{
					current_move=CUtil.formReturn(file+1, rank+1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
				
			}
			if(file-1>=0&&rank+1<9)				//up-left
			{
				if(board[file-1][rank+1].isEmpty()||board[file-1][rank+1].getPiece().getColor().equals("b"))
				{
					current_move=CUtil.formReturn(file-1, rank+1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
				
			}				
			if(file+1<8&&rank-1>0)				//down-right
			{
				if(board[file+1][rank-1].isEmpty()||board[file+1][rank-1].getPiece().getColor().equals("b"))
				{
					current_move=CUtil.formReturn(file+1, rank-1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
				
			}				
			if(file+-1>=0&&rank-1>0)				//down-left
			{
				if(board[file-1][rank-1].isEmpty()||board[file-1][rank-1].getPiece().getColor().equals("b"))
				{
					current_move=CUtil.formReturn(file-1, rank-1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
				
			}						
			
		}
		else //all of the black kings possible moves
		{
			if(rank+1<9)//up
			{
				if(board[file][rank+1].isEmpty()||board[file][rank+1].getPiece().getColor().equals("w"))
				{
					current_move=CUtil.formReturn(file, rank+1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
						
			}
			if(rank-1>0)	//down
			{
				if(board[file][rank-1].isEmpty()||board[file][rank-1].getPiece().getColor().equals("w"))
				{
					current_move=CUtil.formReturn(file, rank-1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
			}
			if(file+1<8)				//right
			{
				if(board[file+1][rank].isEmpty()||board[file+1][rank].getPiece().getColor().equals("w"))
				{
					current_move=CUtil.formReturn(file+1, rank);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
			}
			if(file-1>=0)				//left
			{
				if(board[file-1][rank].isEmpty()||board[file-1][rank].getPiece().getColor().equals("w"))
				{
					current_move=CUtil.formReturn(file-1, rank);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
			}				
			if(file+1<8&&rank+1<9)				//up-right
			{
				if(board[file+1][rank+1].isEmpty()||board[file+1][rank+1].getPiece().getColor().equals("w"))
				{
					current_move=CUtil.formReturn(file+1, rank+1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
				
			}
			if(file-1>=0&&rank+1<9)				//up-left
			{
				if(board[file-1][rank+1].isEmpty()||board[file-1][rank+1].getPiece().getColor().equals("w"))
				{
					current_move=CUtil.formReturn(file-1, rank+1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
				
			}				
			if(file+1<8&&rank-1>0)				//down-right
			{
				if(board[file+1][rank-1].isEmpty()||board[file+1][rank-1].getPiece().getColor().equals("w"))
				{
					current_move=CUtil.formReturn(file+1, rank-1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
				
			}				
			if(file+-1>=0&&rank-1>0)				//down-left
			{
				if(board[file-1][rank-1].isEmpty()||board[file-1][rank-1].getPiece().getColor().equals("w"))
				{
					current_move=CUtil.formReturn(file-1, rank-1);
					movements[movements_iterator]=current_move;
					movements_iterator++;
				}
				
			}						
			
		}
		
		return movements;
	}
	public void updateBoardNoDraw(Piece piece, String old_pos,String new_pos) 
	{
		String convert_old_pos = CUtil.pos_Finder(old_pos);
		String convert_new_pos = CUtil.pos_Finder(new_pos);
		int file = Integer.parseInt(convert_old_pos.substring(0,1));
		int rank = Integer.parseInt(convert_old_pos.substring(1));
		board[file][rank].clearPiece();		//update old position
		file = Integer.parseInt(convert_new_pos.substring(0,1));
		rank = Integer.parseInt(convert_new_pos.substring(1));
		board[file][rank].setPiece(piece);	//update new position
		if(piece instanceof King)
		{
			updateKingLoc((King) piece,new_pos);
		}
		
	}
	/**
	 * removes a Piece from the board
	 * @param location location of piece to be removed
	 */
	public void removePiece(String location) 
	{
		String convert_new_pos = CUtil.pos_Finder(location);
		int file = Integer.parseInt(convert_new_pos.substring(0,1));
		int rank = Integer.parseInt(convert_new_pos.substring(1));
		board[file][rank].clearPiece();	
	}
}
