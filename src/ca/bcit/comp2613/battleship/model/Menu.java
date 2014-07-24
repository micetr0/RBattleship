package ca.bcit.comp2613.battleship.model;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.bcit.comp2613.battleship.BattleShip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private JTextField fullName;
    private JButton submit;
    private JPanel pane;
        
    public Menu() {
        pane = new JPanel();
        pane.setLayout(new FlowLayout());
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textFieldButtons();
        add(pane);
        setVisible(true);
     }
    
    public void textFieldButtons(){
        fullName = new JTextField("Input full name");
        fullName.setColumns(10);
        pane.add(fullName);
        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submit();
                dispose();    
            }
        });
        pane.add(submit);
    }
    
    public void submit() {
        String theFullName = fullName.getText();
        //code to save name
        //code to link to next screen
        //dispose()
        
        Board board = new Board();
        JPanel ctrlPanel = new JPanel();
        ctrlPanel.setSize(850, 150);
        JFrame f = new JFrame("test");
        f.setSize(850, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.add(board);
        f.add(ctrlPanel);
        
    }
    
    
}
