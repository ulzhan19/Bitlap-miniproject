package Zhambyl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends Container {
    public JButton add;
    public JButton list;
    public JButton third;
    public Menu(){
        setSize(700,700);
        setLayout(null);

        add=new JButton("ADD");
        add.setLocation(100,150);
        add.setSize(300,30);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.frame.menu.setVisible(false);
                Main.frame.addStudent.setVisible(true);


            }
        });
        add(add);

        list=new JButton("List");
        list.setLocation(100,190);
        list.setSize(300,30);
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.getList();//лист всех данных студентов
                Main.frame.listFrame.updateList();
                Main.frame.menu.setVisible(false);
                Main.frame.listFrame.setVisible(true);

            }
        });
        add(list);

        third = new JButton("EXIT");
        third.setSize(300,30);
        third.setLocation(100,230);
        add(third);
        third.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

    }


}