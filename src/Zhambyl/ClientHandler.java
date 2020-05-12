package Zhambyl;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    private static Connection connection;


    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            connectDatabase();
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            PackageData pd ;
            while ((pd = (PackageData) inStream.readObject()) != null) {
                if (pd.getOperationType().equals("ADD")) {
                    System.out.println(pd.getStudent().toString());
                    addStudentToDataBase(pd.getStudent());
                } else if (pd.getOperationType().equals("LISt")) {
                    pd.setStudentList(getStudents());
                    try{
                        outputStream.writeObject(pd);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }




                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void connectDatabase()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulzhan_zh?useUnicode=true&serverTimezone=UTC","root","");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public static void addStudentToDataBase(Student student) {
        try {
            Statement st = connection.createStatement();
            //String sql = "insert into student(name,surname,age) values(\"" + student.getName() + "\",\"" + student.getSurname() + "\",\"" + student.getAge() + "\")";
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO students(id,name,surname,age)"+"VALUES(NULL,?,?,?)");
            statement.setString(1,student.getName());
            statement.setString(2,student.getSurname());
            statement.setInt(3,student.getAge());
            int row=statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Student> getStudents() {
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("Select *FROM students");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                studentList.add(new Student(id, name, surname, age));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;


    }
}
