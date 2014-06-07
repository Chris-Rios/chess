package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
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
		boolean white_pas=false;
		boolean black_pas=false;
		int check_count;
		boolean check=false;
		Piece current=null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String move;
		String[] positions;
		String white_pas_loc=null;
		String black_pas_loc=null;
		boolean white_turn =true;
		game_over = false;
		white_victory = false;
		black_victory = false;
		String promotion = "NRBQ";
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
					if(board.getPiece(positions[0])!=null&&CUtil.inBounds(positions[0])&&CUtil.inBounds(positions[1]))//check if this is a valid move
					{
						current = board.getPiece(positions[0]);
						if((current.getColor().equals("b")&&white_turn==true)||(current.getColor().equals("w")&&white_turn==false))//make sure players can not move opposing players pieces
						{
							System.out.println("Illegal move, try again");
							continue;
						}
						else if(current.move(positions[0], positions[1])==true&&board.isOccupied(positions[0], positions[1])==false&&board.isClear(positions[0], positions[1], current))
						{
							if(!(current instanceof Pawn ))//check to reset the passant
							{
								if(white_turn)
								{
									white_pas =false;
								}
								else
								{
									black_pas=false;
								}
							}
									
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
									if(white_turn&&white_pas)
									{
										
										if(white_pas_loc.equals(positions[0]))//successful passant!
										{
											String new_pos = CUtil.pos_Finder(positions[1]);
											int pfile = Integer.parseInt(new_pos.substring(0,1));
											int prank = Integer.parseInt(new_pos.substring(1));
											int nfile = pfile;
											int nrank = prank-1;
											String passant_space=CUtil.formReturn(nfile, nrank);
											if(board.isTaken(passant_space)&&board.getPiece(passant_space).getColor().equals("b"))
											{
												board.removePiece(passant_space);
												white_pas=false;
											}
										}
										else
										{
											System.out.println("Illegal move,try again");
										}
									}
									else if(!white_turn&&black_pas)
									{
										if(black_pas_loc.equals(positions[0]))//successful passant!
										{
											String new_pos = CUtil.pos_Finder(positions[1]);
											int pfile = Integer.parseInt(new_pos.substring(0,1));
											int prank = Integer.parseInt(new_pos.substring(1));
											int nfile = pfile;
											int nrank = prank+1;
											String passant_space=CUtil.formReturn(nfile, nrank);
											if(board.isTaken(passant_space)&&board.getPiece(passant_space).getColor().equals("w"))
											{
												board.removePiece(passant_space);
												black_pas=false;
											}
										}
										else
										{
											System.out.println("Illegal move,try again");
										}
									}
									else
									{
									System.out.println("Illegal move, try again");
									continue;
									}
								}
								if(current.getColor().equals("w")&&positions[1].substring(1).equals("8"))//promotion check
								{
									current=new Queen(positions[1],"w");
								}
								else if(current.getColor().equals("b")&&positions[1].substring(1).equals("1"))
								{
									current=new Queen(positions[1],"b");
								}
								else
								{
									if(Math.abs((Integer.parseInt(positions[0].substring(1)))-(Integer.parseInt(positions[1].substring(1))))==2)
									{
										((Pawn) current).consumeFirstMove();
										if(white_turn)
										{
											white_pas = true;
											white_pas_loc = positions[1];
										}
										else
										{
											black_pas = true;
											black_pas_loc = positions[1];
										}
									}
									else
									{
										((Pawn) current).consumeFirstMove();
									}
										
								
								}
							}
							
							else if(board.getPiece(positions[1]) instanceof King)//check if someone captures the king, is that a thing?
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
							else if(current instanceof Rook)//consume the first move of a rook so it cant castle
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
							draw_count=0;
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
						else if(board.getPiece(positions[0])!=null&&CUtil.inBounds(positions[0])&&CUtil.inBounds(positions[1]))//check if this is a valid move
						{
							current = board.getPiece(positions[0]);
							if((current.getColor().equals("b")&&white_turn==true)||(current.getColor().equals("w")&&white_turn==false))//make sure players can not move opposing players pieces
							{
								System.out.println("Illegal move, try again");
								continue;
							}
							else if(current.move(positions[0], positions[1])==true&&board.isOccupied(positions[0], positions[1])==false&&board.isClear(positions[0], positions[1], current))
							{
								if(!(current instanceof Pawn ))//check to reset the passant
								{
									if(white_turn)
									{
										white_pas =false;
									}
									else
									{
										black_pas=false;
									}
								}
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
										{
											if(white_turn&&white_pas)
											{
												
												if(white_pas_loc.equals(positions[0]))//successful passant!
												{
													String new_pos = CUtil.pos_Finder(positions[1]);
													int pfile = Integer.parseInt(new_pos.substring(0,1));
													int prank = Integer.parseInt(new_pos.substring(1));
													int nfile = pfile;
													int nrank = prank-1;
													String passant_space=CUtil.formReturn(nfile, nrank);
													if(board.isTaken(passant_space)&&board.getPiece(passant_space).getColor().equals("b"))
													{
														board.removePiece(passant_space);
														white_pas=false;
													}
												}
												else
												{
													System.out.println("Illegal move,try again");
												}
											}
											else if(!white_turn&&black_pas)
											{
												if(black_pas_loc.equals(positions[0]))//successful passant!
												{
													String new_pos = CUtil.pos_Finder(positions[1]);
													int pfile = Integer.parseInt(new_pos.substring(0,1));
													int prank = Integer.parseInt(new_pos.substring(1));
													int nfile = pfile;
													int nrank = prank+1;
													String passant_space=CUtil.formReturn(nfile, nrank);
													if(board.isTaken(passant_space)&&board.getPiece(passant_space).getColor().equals("w"))
													{
														board.removePiece(passant_space);
														black_pas=false;
													}
												}
												else
												{
													System.out.println("Illegal move,try again");
												}
											}
											else
											{
											System.out.println("Illegal move, try again");
											continue;
											}
										}
									}
									if(current.getColor().equals("w")&&positions[1].substring(1).equals("8"))//promotion check for white
									{
										current=new Queen(positions[1],"w");
									}
									else if(current.getColor().equals("b")&&positions[1].substring(1).equals("1"))//promotion check for black
									{
										current=new Queen(positions[1],"b");
									}
									else
									{

										if(Math.abs((Integer.parseInt(positions[0].substring(1)))-(Integer.parseInt(positions[1].substring(1))))==2)
										{
											((Pawn) current).consumeFirstMove();
											if(white_turn)
											{
												white_pas = true;
												white_pas_loc = positions[1];
											}
											else
											{
												black_pas = true;
												black_pas_loc = positions[1];
											}
										}
										else
										{
											((Pawn) current).consumeFirstMove();
										}
									
									}
								}
								
								else if(board.getPiece(positions[1]) instanceof King)//check if someone captures the king, is that a thing?
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
								else if(current instanceof Rook)//consume the first move of a rook so it cant castle
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
					 draw_count++;	
					}
					else if(promotion.contains(positions[2]))
					{
						current = board.getPiece(positions[0]);
						System.out.println("someone wants a promotion");
						System.out.println(positions[2]);
						System.out.println(current.toString());
						if(current instanceof Pawn)
						{
							if(current.getColor().equals("w")&&positions[1].substring(1).equals("8"))//promotion check
							{
								if(positions[2].equals("N"))
								{
									current=new Knight(positions[1],"w");
								}
								else if(positions[2].equals("R"))
								{
									current=new Rook(positions[1],"w");
								}
								else if(positions[2].equals("B"))
								{
									current=new Bishop(positions[1],"w");								
								}
								else if(positions[2].equals("Q"))
								{
									current=new Queen(positions[1],"w");								
								}
								
							}
							else if(current.getColor().equals("b")&&positions[1].substring(1).equals("1"))
							{
								if(positions[2].equals("N"))
								{
									current=new Knight(positions[1],"b");
								}
								else if(positions[2].equals("R"))
								{
									current=new Rook(positions[1],"b");
								}
								else if(positions[2].equals("B"))
								{
									current=new Bishop(positions[1],"b");								
								}
								else if(positions[2].equals("Q"))
								{
									current=new Queen(positions[1],"b");								
								}
								
							}
							System.out.println("updating");
							board.updateBoard(current, positions[0], positions[1]);
						}
						else
						{
							System.out.println("Illegal move, try again");
							continue;  //back to the top of the loop
						}
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
								System.out.print("\n");
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
								System.out.print("\n");
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
