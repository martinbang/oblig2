package mazeoblig;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/***
 * Oblig 2 Internett Applikasjoner
 * @author 490427 - Martin Bang Tøllefsen
 *
 */
@SuppressWarnings("serial")
public class Client extends UnicastRemoteObject implements ClientInterface {
	
	boolean[][] positionMaze;
	
	/**
	 * Konstruktør
	 * @throws RemoteException
	 */
	protected Client() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}//end of constructor
	
	/** (non-Javadoc)
	 * @see mazeoblig.ClientInterface#sendMsg(java.lang.String)
	 * Brukes for og sende meldinger til Clienten/er
	 */
	@Override
	public void sendMsg(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Server msg: " + msg);
	}//end of sendMsg()

	
	@Override
	public void setClientPossitions(boolean[][] positions)
			throws RemoteException {
		// TODO Auto-generated method stub
		positionMaze = new boolean[Maze.DIM][Maze.DIM];
		positionMaze = positions;
	}

	@Override
	public boolean[][] getClientPossitions() throws RemoteException {
		// TODO Auto-generated method stub
		return positionMaze;
	}
	
}//end of class client.class.java
