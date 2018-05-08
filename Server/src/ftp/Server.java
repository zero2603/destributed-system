/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Pham Anh Thu
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        // TODO code application logic here
        Registry rmiRegistry;
        ServerInterface server;

        rmiRegistry = LocateRegistry.createRegistry(1099);
        // thư mục mặc định trên server
        File defaultFile = new File("E:\\server");
        if (!defaultFile.exists()) {
            defaultFile.mkdir();
        }
        server = new ServerMethod(defaultFile);

        //Kết nối tên với remote object, rebind() để tránh lỗi trong trường hợp "server" đã tồn tại trong RMI Registry
        rmiRegistry.rebind("server", server);
        System.out.println("Server running at: rmi://localhost:1099/server");
    }

}
