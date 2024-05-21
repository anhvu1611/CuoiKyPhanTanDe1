package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import entity.Position;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("DESKTOP-RB92ON5", 6591);
            Scanner sc = new Scanner(System.in);
        ) {
            System.out.println("Connected to server");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            int chosing;

            while (true) {
                System.out.println("Enter your choice: ");
                System.out.println("1. Liệt kê danh sách các vị trí công việc khi biết tên vị trí và mức lương");
                System.out.println("2. Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm.");
                System.out.println("3. Tìm danh sách các ứng viên đã làm việc trên một vị trí công việc nào đó có thời gian làm lâu nhất.");
                System.out.println("4. Thêm mới một ứng viên.");
                System.out.println("5. Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng viên.");
                System.out.println("6. Liệt kê thông tin ứng viên và chứng chỉ của họ.");

                chosing = sc.nextInt();
                out.writeInt(chosing);
                switch (chosing) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Enter position name: ");
                        String name = sc.nextLine();
                        out.writeUTF(name);
                        System.out.println("Enter min salary: ");
                        double salary = sc.nextDouble();
                        out.writeDouble(salary);
                        System.out.println("Enter max salary: ");
                        double salaryTo = sc.nextDouble();
                        out.writeDouble(salaryTo);
                        List<Position> list = (List<Position>) in.readObject();
                        list.stream().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println(in.readObject());
                        break;
                    case 3:
                        System.out.println(in.readObject());
                        break;
                    case 4:
                        System.out.println("Enter candidate id: ");
                        String id = sc.next();
                        out.writeUTF(id);
                        System.out.println("Enter candidate full name: ");
                        sc.nextLine();
                        String fullName = sc.nextLine();
                        out.writeUTF(fullName);
                        System.out.println("Enter candidate year of birth: ");
                        int yearOfBirth = sc.nextInt();
                        out.writeInt(yearOfBirth);
                        System.out.println("Enter gender: ");
                        sc.nextLine();
                        String gender = sc.nextLine();
                        out.writeUTF(gender)    ;
                        System.out.println("Enter email: ");
                        sc.nextLine();
                        String email = sc.nextLine();
                        out.writeUTF(email);
                        System.out.println("Enter phone: ");
                        String phone = sc.nextLine();
                        out.writeUTF(phone);
                        System.out.println("Enter description: ");
                        String description = sc.nextLine();
                        out.writeUTF(description);

                        System.out.println(in.readUTF());
                        break;
                    case 5:
                        System.out.println("Enter candidate id: ");
                        String candidateId = sc.next();
                        out.writeUTF(candidateId);
                        Map<Position, Integer> map = (Map<Position, Integer>) in.readObject();
                        map.forEach((k, v) -> System.out.println(k.getName() + " " + v));
                        break;
                        case 6:
                            Map<entity.Candidate, Set<entity.Certificate>> map1 = (Map<entity.Candidate, Set<entity.Certificate>>) in.readObject();
                            map1.forEach((k, v) -> System.out.println(k.getFullName() + " " + v));
                            break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
