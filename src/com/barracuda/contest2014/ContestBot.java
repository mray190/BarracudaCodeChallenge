/**
 * sample Java implementation for 2014 Barracuda Networks Programming Contest
 *
 */
package com.barracuda.contest2014;

import GodzillasToothbrush.Algorithms.Algorithm;
import GodzillasToothbrush.Algorithms.DeltaVolume;
import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;
import com.barracuda.visualize.TerminalPrinter;
import java.io.IOException;

public class ContestBot {
	private static final int RECONNECT_TIMEOUT = 15; // seconds

	private final String host;
	private final int port;
	private int game_id = -1;

        //Godzilla Variables
        //----------------------------------------------------------------------
        private static final TerminalPrinter printer = new TerminalPrinter();
        private static final Algorithm algorithm = new DeltaVolume();
        private boolean debug = false;
        
        private Board board;
        private Board previousBoard;
        //----------------------------------------------------------------------
        
	public ContestBot(String host, int port) {
		this.host = host;
		this.port = port;
	}

	private void run() {
		while (true) {
			JsonSocket sock = null;

			// just reconnect upon any failure
			try {
				sock = new JsonSocket(host, port);
				try {
					sock.connect();
				}
				catch (IOException e) {
					throw new Exception("Error establishing connection to server: " + e.toString());
				}

				while (true) {
					Message message = sock.getMessage();
		
					PlayerMessage response = handleMessage(message);
		
					if (response != null) {
						sock.sendMessage(response);
					}
				}
			}
			catch (Exception e) {
				System.err.println("Error: " + e.toString());
				System.err.println("Reconnecting in " + RECONNECT_TIMEOUT + "s");
				try {
					Thread.sleep(RECONNECT_TIMEOUT * 1000);
				}
				catch (InterruptedException ex) {}
			}
			finally {
				if (sock != null) {
					sock.close();
				}
			}
		}
	}

	public PlayerMessage handleMessage(Message message) {
		if (message.type.equals("request")) {
			MoveRequestMessage m = (MoveRequestMessage)message;
                        
			if (game_id != m.game_id) {
				game_id = m.game_id;
				System.out.println("new game " + game_id);
			}

                        
                        //Godzilla Logic
                        //------------------------------------------------------
                        
                        //Print
                        //printer.printDetails(m.state.board);               
                        
                        //Data Mine
                        previousBoard = board;
                        board = new Board(m.state.board, m.state);

                        Point result = algorithm.makeMove(board, previousBoard);
                        
                        //Send Point to Server
                        if (result == null){
                            return new PlayerWaitMessage(m.id);
                        }
                        else{
                            return new PlayerMoveMessage(m.id, result.toMove());
                        }
                        //------------------------------------------------------
		}
		else if (message.type.equals("move_result")) {
			//ResultMessage r = (ResultMessage)message;
			return null;
		}
		else if (message.type.equals("game_over")) {
			//GameOverMessage g = (GameOverMessage)message;
			//System.out.println(g);
                        //System.exit(69);
                        algorithm.endGame();
			return null;
		}
		else if (message.type.equals("greetings_program")) {
			System.out.println("connected to server");
			return null;
		}
		else if (message.type.equals("error")) {
			ErrorMessage e = (ErrorMessage)message;
			System.out.println("Error: " + e.state.error);

			// need to register IP address on the contest server
			if (e.state.seen_host != null) {
				System.exit(1);
			}
			return null;
		}
		throw new RuntimeException("handleMessage(): Should not be reached. You must take action on all messages.");
	}

	public static void main(String[] args) {
		if (args.length > 3) {
			System.err.println("Usage: java -jar ContestBot.jar [ <HOST> <PORT> ]");
			System.exit(1);
		}

		String host = "cuda.contest";
		Integer port = 9999;

		if (args.length > 0)
		    host = args[0];

		if (args.length > 1) {
		    try {
		        port = Integer.parseInt(args[1]);
		    }
		    catch (Exception e) {
		        System.err.println("Couldn't parse port [" + args[1] + "]");
		        System.exit(1);
		    }
		}

		ContestBot cb = new ContestBot(host, port);
		cb.run();
	}
}
