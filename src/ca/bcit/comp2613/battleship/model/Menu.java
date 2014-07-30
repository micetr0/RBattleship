package ca.bcit.comp2613.battleship.model;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;


import ca.bcit.comp2613.battleship.BattleShip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField firstName;
	private JTextField lastName;
    private JButton submit;
    private JPanel pane;
    //Score panel
    private JLabel topScore;
    
        
    public Menu() {
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textFieldButtons();
        add(pane);
        setVisible(true);
        
        //JPanel score menu

    	ScoreListing();

     }
    
    
    public void textFieldButtons(){
        firstName = new JTextField("Input first name");
        firstName.setColumns(10);
        pane.add(firstName);
        
        lastName = new JTextField("Input last name");
        lastName.setColumns(10);
        pane.add(lastName);
        
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
        String theFirstName = firstName.getText();
        String theLastName = lastName.getText();
        Player player = new Player(theFirstName,theLastName, null,null,null);
        //code to save name
        //code to link to next screen
        //dispose()
        
//        Board board = new Board();
//        JPanel ctrlPanel = new JPanel();
//        ctrlPanel.setSize(850, 150);
//        JFrame f = new JFrame("test");
//        f.setSize(850, 1000);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//        f.add(board);
//        f.add(ctrlPanel);
        SetupBoard setup = new SetupBoard();
        JFrame f = new JFrame("Player Setup");
        f.setSize(850, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.add(setup);
        
    }
    
    public void ScoreListing(){
        topScore = new JLabel("Top Score:");
        pane.add (topScore);
        
    }
}
