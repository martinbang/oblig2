package mazeoblig;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
	
	
	/**
	 * Brukes til og sende meldinger til clienter.
	 * @param msg
	 * @throws RemoteException
	 */
	public void sendMsg(String msg)throws RemoteException;
	
	/**
	 * Brukes for og sette posisjonene til clientene i mazen
	 * @param positions
	 * @throws RemoteException
	 */
	public void setClientPossitions(boolean[][] positions)throws RemoteException;
	
	/**
	 * Brukes for og hente ut posisjonen til client i mazen
	 * @return
	 * @throws RemoteException
	 */
	public boolean[][] getClientPossitions() throws RemoteException;
	
	
}
	
