package server;

import dao.CandidateDao;
import dao.PositionDao;
import dao.impl.CandidateImpl;
import dao.impl.PositionImpl;
import entity.Candidate;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6591)) {
            System.out.println("Server is listening on port 6591");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                Server temp = new Server();
                ClientHandle clientHandle = temp.new ClientHandle(socket);
                Thread thread = new Thread(clientHandle);
                thread.start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    private class ClientHandle implements Runnable {
        private Socket socket;
        private PositionDao positionDao;
        private CandidateDao candidateDao;
        public ClientHandle(Socket socket) {
            this.socket = socket;
            positionDao = new PositionImpl();
            candidateDao = new CandidateImpl();
        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                while (true) {
                    int choice = in.readInt();
                    switch (choice) {
                        case 1:
                            String name = in.readUTF();
                            double salary = in.readDouble();
                            double salaryTo = in.readDouble();
                            out.writeObject(positionDao.listPositions(name, salary, salaryTo));
                            break;
                        case 2:
                            out.writeObject(candidateDao.listCadidatesByCompanies());
                            break;
                        case 3:
                            out.writeObject(candidateDao.listCandidatesWithLongestWorking());
                            break;
                        case 4:
                            String candidateId = in.readUTF();
                            String fullName = in.readUTF();
                            int birthYear = in.readInt();
                            String gender = in.readUTF();
                            String email = in.readUTF();
                            String phone = in.readUTF();
                            String description = in.readUTF();
                            Candidate candidate = new Candidate(candidateId, fullName, birthYear, gender, email, phone, description);
                            if(candidateDao.addCandidate(candidate)) {
                                out.writeUTF("Candidate added successfully");
                            } else {
                                out.writeUTF("Candidate added failed");
                            }
                            out.flush();
                            break;
                        case 5:
                            String id = in.readUTF();
                            out.writeObject(positionDao.listYearsOfExperienceByPosition(id));
                            out.flush();
                            break;
                        case 6:
                            out.writeObject(candidateDao.listCadidatesAndCertificates());
                            out.flush();
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
