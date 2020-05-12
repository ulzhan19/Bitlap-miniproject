package Zhambyl;
import javax.swing.*;

public class MainFrame extends JFrame {
    public static AddStudent addStudent;
    public static ListFrameStudents listFrame;
    public static Menu menu;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student application");
        setSize(500, 500);
        setLayout(null);

        menu = new Menu();
        menu.setLocation(0, 0);
        menu.setVisible(true);
        add(menu);


        addStudent = new AddStudent();
        addStudent.setLocation(0, 0);
        addStudent.setVisible(false);
        add(addStudent);



        listFrame = new ListFrameStudents();
        listFrame.setLocation(0, 0);
        listFrame.setVisible(false);
        add(listFrame);

    }
}
