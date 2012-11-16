package mazeoblig;

import java.awt.*;
import java.applet.*;

import simulator.*;

/**
 *
 * <p>Title: Maze</p>
 *
 * <p>Description: En enkel applet som viser den randomiserte labyrinten</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

/**
 * Tegner opp maze i en applet, basert på definisjon som man finner på RMIServer
 * RMIServer på sin side henter størrelsen fra definisjonen i Maze
 * 
 * @author asd
 * 
 */
@SuppressWarnings("serial")
public class Maze extends Applet {

	private BoxMazeInterface bm;
	private Box[][] maze;
	public static int DIM = 30;
	private int dim = DIM;

	static int xp;
	static int yp;
	static boolean found = false;

	private String server_hostname;
	private int server_portnumber;

	private ClientInterface cliObject;
	PositionInMaze[] possitions;
	private PositionInMaze[] iterations;

	/**
	 * Henter labyrinten fra RMIServer
	 */
	@SuppressWarnings("unused")
	public void init() {
		int size = dim;
		/*
		 * * Kobler opp mot RMIServer, under forutsetning av at disse* kjører på
		 * samme maskin. Hvis ikke må oppkoblingen* skrives om slik at dette
		 * passer med virkeligheten.
		 */
		if (server_hostname == null)
			server_hostname = RMIServer.getHostName();
		if (server_portnumber == 0)
			server_portnumber = RMIServer.getRMIPort();
		try {
			java.rmi.registry.Registry r = java.rmi.registry.LocateRegistry
					.getRegistry(server_hostname, server_portnumber);

			/*
			 * * Henter inn referansen til Labyrinten (ROR)
			 */
			bm = (BoxMazeInterface) r.lookup(RMIServer.MazeName);
			maze = bm.getMaze();

			VirtualUser viritualUser = new VirtualUser(maze);
			possitions = viritualUser.getFirstIterationLoop();
			iterations = viritualUser.getIterationLoop();

		} catch (RemoteException e) {
			System.err.println("Remote Exception: " + e.getMessage());
			System.exit(0);
		} catch (NotBoundException f) {
			/*
			 * * En exception her er en indikasjon på at man ved oppslag
			 * (lookup())* ikke finner det objektet som man søker.* Årsaken til
			 * at dette skjer kan være mange, men vær oppmerksom på* at hvis
			 * hostname ikke er OK (RMIServer gir da feilmelding under*
			 * oppstart) kan være en årsak.
			 */
			System.err.println("Not Bound Exception: " + f.getMessage());
			System.exit(0);
		}

		// registerer Clienten
		registerClientOnServer();

	}

	// Get a parameter value
	public String getParameter(String key, String def) {
		return getParameter(key) != null ? getParameter(key) : def;
	}

	// Get Applet information
	public String getAppletInfo() {
		return "Applet Information";
	}

	// Get parameter info
	public String[][] getParameterInfo() {
		java.lang.String[][] pinfo = { { "Size", "int", "" }, };
		return pinfo;
	}

	@SuppressWarnings("unused")
	public void destroy() {

		unRegisertClientOnServer();

	}

	/**
	 * Registrer en lient på server
	 */
	private void registerClientOnServer() {
		try {
			cliObject = new Client();
			bm.registerClientCallBack(cliObject);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fjerner registrert spiller fra server
	 */
	private void unRegisertClientOnServer() {
		try {
			cliObject = new Client();
			bm.unRegisterClientCallBack(cliObject);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Viser labyrinten / tegner den i applet
	 * 
	 * @param g
	 *            Graphics
	 */
	public void paint(Graphics g) {
		int x, y;

		g.setColor(Color.MAGENTA);
		g.fillOval(getX()*10, getY()*10, 7, 7);

		// Tegner baser på box-definisjonene ....
		for (x = 1; x < (DIM - 1); ++x) {
			g.setColor(Color.BLACK);
			for (y = 1; y < (DIM - 1); ++y) {
				if (maze[x][y].getUp() == null)
					g.drawLine(x * 10, y * 10, x * 10 + 10, y * 10);
				if (maze[x][y].getDown() == null)
					g.drawLine(x * 10, y * 10 + 10, x * 10 + 10, y * 10 + 10);
				if (maze[x][y].getLeft() == null)
					g.drawLine(x * 10, y * 10, x * 10, y * 10 + 10);
				if (maze[x][y].getRight() == null)
					g.drawLine(x * 10 + 10, y * 10, x * 10 + 10, y * 10 + 10);
			}
		} // end forloop
	}// end of paint

}// end of Maze.java

