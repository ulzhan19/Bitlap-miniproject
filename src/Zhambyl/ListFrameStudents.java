package Zhambyl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListFrameStudents extends Container {
    private  static JLabel label;
    private static  JButton btn;
    private static JTextArea txt;
    public static ArrayList<Student>st;//он ниодин и тот же студент который в main

    public ListFrameStudents()
    {
        try {
            st = Main.students;
            label = new JLabel("List students");
            label.setLocation(100, 150);
            label.setSize(300, 30);
            add(label);

            setLayout(null);
            setSize(700, 700);


            txt = new JTextArea();



            add(txt);
        }catch(Exception e)
        {
            e.printStackTrace();
        }



        btn = new JButton("Back");
        btn.setLocation(100,200);
        btn.setSize(150,30);
        add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.frame.listFrame.setVisible(false);
                Main.frame.menu.setVisible(true);
                Main.frame.repaint();
            }
        });


    }
    public static void updateList()
    {
        st=Main.students;
        for (int i = 0; i < st.size(); i++) {
            txt.append(st.get(i).toString());
        }

    }




}