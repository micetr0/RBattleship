package ca.bcit.comp2613.battleship;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.bcit.comp2613.battleship.model.Player;
import ca.bcit.comp2613.battleship.model.ScoreBoard;
import ca.bcit.comp2613.battleship.model.SwingPlayerModel;
import ca.bcit.comp2613.battleship.util.PlayerUtil;

@Entity
public class ScoreBoardWindow {
	
	@Id
	private String theId;
	private static JFrame frame;
	private JTable table;
	private JTextField idTextField;
	private JTextField scoreTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField hitRatioTextField;
	private JTextField missRatioTextField;
	private static int count = 0;
	
	
	//Header Names (H)
	
	private JLabel Id;
	private JLabel HScore;
	private JLabel HFname;
	private JLabel HLname;
	private JLabel Hhit;
	private JLabel Hmiss;
	
	
	private SwingPlayerModel swingPlayerModel;
	
	public String [] columnNames = new String [] {"Id",",Score", "First Name", "Last Name", "Hit%", "Miss%"};
	
	@OneToMany
	public static List<Player> players;
	
	//Launch application 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreBoard window = new ScoreBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ScoreBoardWindow() {
		theId = Integer.toString(count);
		initialize();
		initTable();
		count++;
		
	}
	
	private void initTable() {

		// table = new JTable(swingTeacherModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							populateFields();
						}
					}
				});
		refreshTable();
	}
	
	private void populateFields() {
		try {
			
			idTextField.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 0).toString());
			scoreTextField.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 1).toString());
			hitRatioTextField.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 2).toString());
			missRatioTextField.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 3).toString());
			firstNameTextField.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 4).toString());
			lastNameTextField.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 5).toString());
		} catch (Exception e) {}
	}
	
	//TODO modified the doDelete field (D)
	
	public void doDelete() {
		String id = idTextField.getText();
		
		String Dscore = scoreTextField.getText();
		int score = Integer.parseInt(Dscore);
		
		//dependent on endurance
		String Dhit = hitRatioTextField.getText();
		Integer hit = Integer.parseInt(Dhit);
		
		String Dmiss = missRatioTextField.getText();
		Integer miss = Integer.parseInt(Dmiss);
		
		String DfirstName = firstNameTextField.getText();
		
		String DlastName = lastNameTextField.getText();
		
		Player player = new Player(score, hit, miss, DfirstName, DlastName);
		PlayerUtil.delete(players, player);
		refreshTable();
	}
	
	private void refreshTable() {
		// swingTeacherModel = new SwingTeacherModel();
		Object[][] data;

		data = new Object[players.size()][6];
		int i = 0;
		for (Player player : players) {
			data[i][0] = player.getId();
			data[i][1] = player.getScore();
			data[i][2] = player.getHitRatio();
			data[i][3] = player.getMissRatio();
			data[i][4] = player.getFirstName();
			data[i][5] = player.getLastName();
			i++;
		}
		swingPlayerModel.setDataVector(data, columnNames);
		table.repaint();
	}
	
	//initialize()
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 601, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		players = new ArrayList<Player>();
		// table = new JTable();
		swingPlayerModel = new SwingPlayerModel();

		table = new JTable(swingPlayerModel);

		// table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		// table.setBounds(0, 11, 585, 247);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 11, 585, 247);
		frame.getContentPane().add(scrollPane);
		// scrollPane.add(table);
		// frame.getContentPane().add(table);
			


		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});
		btnDelete.setBounds(169, 412, 89, 23);
		frame.getContentPane().add(btnDelete);


		idTextField = new JTextField();
		idTextField.setEditable(false);
		idTextField.setBounds(159, 285, 325, 20);
		frame.getContentPane().add(idTextField);
		idTextField.setColumns(10);
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
}
