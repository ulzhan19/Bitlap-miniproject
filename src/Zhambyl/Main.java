package Zhambyl;

import java.util.*;
import java.io.*;
import java.net.*;

public class Main {
    public static MainFrame frame;
    public static Socket socket;
    public static ObjectOutputStream outStream;
    public static ObjectInputStream inputStream;
    public static Student st;
    public static ArrayList<Student>students;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 1998);
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame = new MainFrame();
        frame.setVisible(true);

        Scanner sc = new Scanner(System.in);

//
    }

    public static void addStudent(Student st) {

        PackageData pd = new PackageData();
        pd.setOperationType("ADD");
        pd.setStudent(st);


        try {
            outStream.writeObject(pd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static  void getList()
    {
        PackageData pd=new PackageData();
        pd.setOperationType("LIST");
        try {
            outStream.writeObject(pd);
            inputStream.readObject();
            students=pd.getStudentList();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }



}

