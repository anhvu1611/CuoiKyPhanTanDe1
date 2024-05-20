package server;

import dao.FoodService;
import dao.ItemDao;
import dao.impl.FoodImpl;
import dao.impl.ItemImpl;
import entity.Food;
import entity.Item;
import enums.Type;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(6591)) {
            System.out.println("Server started............");
            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connected............");
                System.out.println(socket.getInetAddress().getHostName());


                Server temp = new Server();
                ClientHandle clientHandle = temp.new ClientHandle(socket);
                Thread thread = new Thread(clientHandle);
                thread.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class ClientHandle implements Runnable {
        private Socket socket;
        private ItemDao itemDao;
        private FoodService foodDao;
        public ClientHandle(Socket socket) {
            this.socket = socket;
             itemDao = new ItemImpl();
            foodDao = new FoodImpl();
        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                while (true) {
                    int chosing = in.readInt();
                    switch (chosing) {
                        case 1:
                            System.out.println("Add food");
                            String id = in.readUTF();
                            Type type = Type.valueOf(in.readUTF());
                            int preparationTime = in.readInt();
                            int servingTime = in.readInt();
                            Food food = new Food(id, type, preparationTime, servingTime);
                            if(foodDao.addFood(food)){
                                out.writeUTF("Food added successfully");
                            } else {
                                out.writeUTF("Food not added");
                            }
                            out.flush();
                            break;
                        case 2:
                            System.out.println("List food and cost");
                            String name = in.readUTF();
                            List<Item> listItem = itemDao.listItems(name);
                            out.writeObject(listItem);

                            break;
                        case 3:
                            System.out.println("Item list");
                            Map<Food, Double> list = foodDao.listFoodAndCost();
                            out.writeObject(list);
                            break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
