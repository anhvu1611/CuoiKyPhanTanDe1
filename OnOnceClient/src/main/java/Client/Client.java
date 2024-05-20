package Client;

import entity.Food;
import entity.Item;
import enums.Type;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("DESKTOP-RB92ON5", 6591);
            Scanner sc = new Scanner(System.in);
        ) {
            DataOutputStream out = new DataOutputStream((socket.getOutputStream()));
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Client started............");
            int chosing;
            while (true) {
                System.out.println("Enter your choice: \n"
                + "1. Add food\n"
                + "2. List food and cost\n"
                + "3. Item list\n"
                );

                chosing = sc.nextInt();
                out.writeInt(chosing);
                switch (chosing) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Enter food id: ");
                        String id = sc.next();
                        out.writeUTF(id);
                        System.out.println("Enter food type: ");
                        String type = sc.next();
                        out.writeUTF(String.valueOf(type));
                        System.out.println("Enter preparation time: ");
                        int preparationTime = sc.nextInt();
                        out.writeInt(preparationTime);
                        System.out.println("Enter serving time: ");
                        int servingTime = sc.nextInt();
                        out.writeInt(servingTime);

                        String message = in.readUTF();
                        System.out.println(message);
                        break;
                    case 2:

                        System.out.println("List food and cost: ");
                        String name = sc.next();
                        out.writeUTF(name);
                        List<Item> items = (List<Item>) in.readObject();
                        items.stream().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Food list: ");
                        Map<Food, Double> list = (Map<Food, Double>) in.readObject();
                        list.entrySet().stream()
                                .map(e -> e.getKey() + " " + e.getValue())
                                .forEach(System.out::println);
                        break;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
