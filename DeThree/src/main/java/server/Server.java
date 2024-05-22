
package server;

import dao.CandidateDao;
import dao.PositionDao;
import dao.impl.CandidateImpl;
import dao.impl.PositionImpl;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server() {
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6591);

            try {
                System.out.println("Server is listening on port 6591");

                while(true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("New client connected");
                    new Server();
                }
            } catch (Throwable var5) {
                try {
                    serverSocket.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }
        } catch (IOException var6) {
            System.out.println("Server exception: " + var6.getMessage());
            var6.printStackTrace();
        }
    }

    private class ClientHandle implements Runnable {
        private Socket socket;
        private PositionDao positionDao;
        private CandidateDao candidateDao;

        public ClientHandle(Socket socket) {
            this.socket = socket;
            this.positionDao = new PositionImpl();
            this.candidateDao = new CandidateImpl();
        }

        public void run() {
            try {
                DataInputStream in = new DataInputStream(this.socket.getInputStream());
                new ObjectOutputStream(this.socket.getOutputStream());

                while(true) {
                    int choice = in.readInt();
                    switch (choice) {
                        case 1:
                        case 2:
                    }
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }
    }
}
