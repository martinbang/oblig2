package mazeoblig;

import java.rmi.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public interface BoxMazeInterface extends Remote {
    public Box [][] getMaze() throws RemoteException;
    
    /**
     * Callback for og registrer en client på server
     * @param clientCallBackObject
     * @throws RemoteException
     */
    public void registerClientCallBack(ClientInterface clientCallBackObject) throws RemoteException;
    
    /**
     * Callback for og unRegister en client på serveren
     * @param clientCallRemoveBackObject
     * @throws RemoteException
     */
    public void unRegisterClientCallBack(ClientInterface clientCallRemoveBackObject) throws RemoteException;
    
   
}
