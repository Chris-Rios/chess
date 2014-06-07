package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pieces.King;
import pieces.Pawn;
import pieces.Piece;
import pieces.Rook;
import utility.CUtil;

/**
 * @author Christopher Rios
 *
 */
public class Chess {

	boolean game_over;
	boolean white_victory;
	boolean black_victory;
	int draw_count=0;
	
	public static void main(String[] args)
	{
		Space space;
		Board new_game = new Board();
		new_game.setup();
		Chess game = new Chess();
		try 
		{
			game.play(new_game);
		} catch (IOException e) 
		{
			System.err.println("Fatal Error with input");
			e.printStackTrace();
		}
		
		if(game.white_victory)
		{
			System.out.println("White wins");
		}
		else if(game.black_victory)
		{
			System.out.println("Black wins");
		}
		else
		{
			System.out.println("Draw");
		}
		
		
	}
	
	public void play(Board board) throws IOException
	{
		int check_count;
		boolean check=false;
		Piece current=null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String move;
		String[] positions;
		boolean white_turn =true;
		game_over = false;
		white_victory = false;
		black_victory = false;
		
		while(game_over==false)
		{
			
			System.out.print("\n");
			if(check == true)
			{
				System.out.println("Check");
			}
			if(white_turn)
			{
			System.out.print("White's move:");
			}
			else
			{
				System.out.print("Black's move:");
			}
				move = br.readLine();
				positions=move.split(" ");
				if(positions.length==1)
				{
					if(positions[0].equals("resign"))//checks for resigns
					{
						game_over=true;
						if(white_turn)
						{
							black_victory=true;
						}
						else
						{
							white_victory=true;
						}
						
					}
					else if(positions[0].equals("draw"))//checks for draws
					{
						if(draw_count==1)
						{
							draw_count++;
						}
						else
						{
							System.out.println("Please type in your next move followed by draw?, if you wish to start a draw");
							continue;
						}
					}
					else
					{
						System.out.println("Illegal move, try again");
						continue;
					}
				}
				if(positions.length==2) //normal movement
				{
					if(board.getPiece(positions[0])!=null&&CUtil.inBounds(positions[0])&&CUtil.inBounds(positions[1]))
					{
						current = board.getPiece(positions[0]);
						if((current.getColor().equals("b")&&white_turn==true)||(current.getColor().equals("w")&&white_turn==false))//make sure players can not move opposing players pieces
						{
							System.out.println("Illegal move, try again");
							continue;
						}
						else if(current.move(positions[0], positions[1])==true&&board.isOccupied(positions[0], positions[1])==false&&board.isClear(positions[0], positions[1], current))
						{
							if(current instanceof Pawn)//pawn capture special case
							{
								if(board.isTaken(positions[1]))// checks if the spot is taken
								{
									if(!CUtil.diagonal_check(positions[0], positions[1]))//if spot is taken, checks for diagonal movement
									{
										System.out.println("Illegal move, try again");
										continue;
									}
								}
								else if(CUtil.diagonal_check(positions[0], positions[1]))//fails if spot is not taken and a diagonal is attempted
								{
									System.out.println("Illegal move, try again");
									continue;
								}
								((Pawn) current).consumeFirstMove();
							}
							
							else if(board.getPiece(positions[1]) instanceof King)
							{
								game_over=true;
								if(white_turn)
								{
									white_victory=true;
								}
								else
								{
									black_victory=true;
									
								}
							}
							else if(current instanceof Rook)
							{
								((Rook)current).consumeFirstMove();
							}
							else if(current instanceof King)//check for castling 
							{
								if(CUtil.castleCheck(positions[0],positions[1]))
								{
									String convert_pos = CUtil.pos_Finder(positions[1]);
									String king_pos =CUtil.pos_Finder(positions[0]);
									int nfile = Integer.parseInt(convert_pos.substring(0,1));
									int nrank = Integer.parseInt(convert_pos.substring(1));
									int kfile = Integer.parseInt(king_pos.substring(0,1));
									int krank = Integer.parseInt(king_pos.substring(1));
									if(kfile>nfile)//moving left
									{
										nfile--;
										kfile--;
									}
									else if(kfile<nfile)//moving right;
									{
										nfile++;
										kfile++;
									}
									if(((King)current).getFirstMove()&&board.getPiece(CUtil.formReturn(nfile,nrank)) instanceof Rook&&((Rook)board.getPiece(CUtil.formReturn(nfile,nrank))).getFirstMove())//checks if the king is trying to swap with a rook, and if the rook has moved yet and if the king has moved yet
									{
										
										Piece rook=board.getPiece(CUtil.formReturn(nfile,nrank));
										board.updateBoardNoDraw(rook,CUtil.formReturn(nfile,nrank),CUtil.formReturn(kfile, krank) );
									}
									else
									{
										System.out.println("Illegal move, try again");
										continue;
									}
								}
								((King)current).consumeFirstMove();
							}
							board.updateBoard(current, positions[0], positions[1]);

						}
						else
						{
							System.out.println("Illegal move, try again");
							continue;
						}
					}
					else
					{
						System.out.println("Illegal move, try again");
						continue;  //back to the top of the loop
					}
				}
				if(positions.length==3)//checks for draws or promotions
				{
					if(positions[2].equals("draw?"))
					{
						if(draw_count==1)
						{
							System.out.println("Enter only \'draw\' if you wish to agree to a draw");
							continue;
						}
						if(CUtil.inBounds(positions[0])&&CUtil.inBounds(positions[1]))
						{
							current = board.getPiece(positions[0]);
							if((current.getColor().equals("b")&&white_turn==true)||(current.getColor().equals("w")&&white_turn==false))//make sure players can not move opposing players pieces
							{
								System.out.println("Illegal move, try again");
								continue;
							}
							if(current.move(positions[0], positions[1])==true&&board.isOccupied(positions[0], positions[1])==false&&board.isClear(positions[0], positions[1], current))
							{
								board.updateBoard(current, positions[0], positions[1]);
								
							}
							else
							{
								System.out.println("Illegal move, try again");
								continue;
							}
						}
						else
						{
							System.out.println("Illegal move, try again");
							continue;  //back to the top of the loop
						}
					 draw_count++;	
					}
					else
					{
						System.out.println("Illegal move, try again");
						continue;  //back to the top of the loop
					}
				}
				if(positions.length>3)//checks for invalid amount of inputs
				{
					System.out.println("Illegal move, try again");
					continue;  //back to the top of the loop
				}
				
				if(draw_count==2)//check if a draw has been agreed upon
				{
					game_over=true;
				}
				if(game_over!=true)
				{
					if(!white_turn)
					{
						check_count=board.checkCheck("white", current,positions[1]);
						if(check_count>0)
						{
							if(check_count==1)
							{
								check =true;
							}
							else
							{
								System.out.println("Checkmate");
								black_victory=true;
								game_over=true;
							}
						}
						else
						{
							check=false;
						}
					}
					else
					{
						check_count=board.checkCheck("black", current,positions[1]);
						if(check_count>0)
						{
							if(check_count==1)
							{
								check =true;
							}
							else
							{
								System.out.println("Checkmate");
								white_victory=true;
								game_over=true;
							}
						}
						else
						{
							check=false;
						}
					}
				}
				
				white_turn = !white_turn;
			
		}
	}
}
