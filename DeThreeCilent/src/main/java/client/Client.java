//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;

import java.util.Objects;
import java.util.Scanner;


public class Client {
    public Client() {
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("DESKTOP-RB92ON5", 6591);

            try {
                Scanner sc = new Scanner(System.in);

                try {
                    System.out.println("Connected to server");
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    while(true) {
                        while(true) {
                            System.out.println("Enter your choice: ");
                            System.out.println("1. Liệt kê danh sách các vị trí công việc khi biết tên vị trí và mức lương");
                            System.out.println("2. Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm.");
                            System.out.println("3. Tìm danh sách các ứng viên đã làm việc trên một vị trí công việc nào đó có thời gian làm lâu nhất.");
                            System.out.println("4. Thêm mới một ứng viên.");
                            System.out.println("5. Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng viên.");
                            System.out.println("6. Liệt kê thông tin ứng viên và chứng chỉ của họ.");
                            int chosing = sc.nextInt();
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
//                                    List<Position> list = (List)in.readObject();
//                                    Stream var10000 = list.stream();
                                    PrintStream var10001 = System.out;
                                    Objects.requireNonNull(var10001);
//                                    var10000.forEach(var10001::println);
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
                                    out.writeUTF(gender);
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
//                                    System.out.println("Enter candidate id: ");
//                                    String candidateId = sc.next();
//                                    out.writeUTF(candidateId);
//                                    Map<Position, Integer> map = (Map)in.readObject();
//                                    map.forEach((k, v) -> {
//                                        PrintStream var10000 = System.out;
//                                        String var10001 = k.getName();
//                                        var10000.println(var10001 + " " + v);
//                                    });
//                                    break;
                                case 6:
//                                    Map<Candidate, Set<Certificate>> map1 = (Map)in.readObject();
//                                    map1.forEach((k, v) -> {
//                                        PrintStream var10000 = System.out;
//                                        String var10001 = k.getFullName();
//                                        var10000.println(var10001 + " " + v);
//                                    });
                            }
                        }
                    }
                } catch (Throwable var24) {
                    try {
                        sc.close();
                    } catch (Throwable var23) {
                        var24.addSuppressed(var23);
                    }

                    throw var24;
                }
            } catch (Throwable var25) {
                try {
                    socket.close();
                } catch (Throwable var22) {
                    var25.addSuppressed(var22);
                }

                throw var25;
            }
        } catch (Exception var26) {
            var26.printStackTrace();
        }
    }
}

