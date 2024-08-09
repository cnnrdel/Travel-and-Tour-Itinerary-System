// package test;

// import java.awt.EventQueue;

// import javax.swing.JFrame;
// import javax.swing.JButton;
// import java.awt.Color;
// import javax.swing.JLabel;
// import javax.swing.JTextField;
// import javax.swing.JPanel;
// import java.awt.Panel;
// import java.awt.Canvas;
// import javax.swing.SwingConstants;
// import java.awt.GridBagLayout;
// import java.awt.GridBagConstraints;
// import java.awt.Insets;
// import java.awt.Font;

// public class ItineraryGUI extends JFrame {

// 	private JFrame frame;
// 	private JTextField textField;
// 	private JTextField txtItinerary;

// 	/**
// 	 * Launch the application.
// 	 */
// 	public static void main(String[] args) {
// 		// EventQueue.invokeLater(new Runnable() {
// 		// 	public void run() {
// 		// 		try {
// 		// 			ItineraryGUI window = new ItineraryGUI();
// 		// 			window.frame.setVisible(true);
// 		// 		} catch (Exception e) {
// 		// 			e.printStackTrace();
// 		// 		}
// 		// 	}
// 		// });
// 		System.out.println("running Itinerary GUI");
// 	}

// 	/**
// 	 * Create the application.
// 	 */
// 	public ItineraryGUI() {
// 		System.out.println("Initializing!");
// 		initialize();
// 		System.out.println("Finished Initializaing");
// 	}

// 	/**
// 	 * Initialize the contents of the frame.
// 	 */
// 	private void initialize() {
// 		frame = new JFrame();
// 		frame.setBounds(100, 100, 450, 300);
// 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 		frame.getContentPane().setLayout(null);
		
// 		JButton btnBackButton = new JButton("←");
// 		btnBackButton.setBounds(296, 26, 47, 21);
// 		frame.getContentPane().add(btnBackButton);
		
// 		JButton btnDelete = new JButton("DELETE");
// 		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 6));
// 		btnDelete.setForeground(Color.RED);
// 		btnDelete.setBounds(352, 26, 67, 21);
// 		frame.getContentPane().add(btnDelete);
		
// 		JLabel lblNotes = new JLabel("Notes:");
// 		lblNotes.setBounds(28, 72, 45, 13);
// 		frame.getContentPane().add(lblNotes);
		
// 		textField = new JTextField();
// 		textField.setBounds(28, 95, 380, 96);
// 		frame.getContentPane().add(textField);
// 		textField.setColumns(10);
		
// 		JPanel panel = new JPanel();
// 		panel.setBounds(28, 201, 380, 52);
// 		frame.getContentPane().add(panel);
// 		GridBagLayout gbl_panel = new GridBagLayout();
// 		gbl_panel.columnWidths = new int[]{85, 0, 0, 0, 0};
// 		gbl_panel.rowHeights = new int[]{34, 0, 21, 0};
// 		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
// 		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
// 		panel.setLayout(gbl_panel);
		
// 		JButton btnPlace1 = new JButton("P1");
// 		btnPlace1.setHorizontalAlignment(SwingConstants.LEFT);
// 		GridBagConstraints gbc_btnPlace1 = new GridBagConstraints();
// 		gbc_btnPlace1.insets = new Insets(0, 0, 5, 5);
// 		gbc_btnPlace1.anchor = GridBagConstraints.NORTHWEST;
// 		gbc_btnPlace1.gridx = 0;
// 		gbc_btnPlace1.gridy = 1;
// 		panel.add(btnPlace1, gbc_btnPlace1);
		
// 		JLabel lblArrow = new JLabel("→");
// 		GridBagConstraints gbc_lblArrow = new GridBagConstraints();
// 		gbc_lblArrow.insets = new Insets(0, 0, 5, 5);
// 		gbc_lblArrow.gridx = 1;
// 		gbc_lblArrow.gridy = 1;
// 		panel.add(lblArrow, gbc_lblArrow);
		
// 		JButton btnAddPlace = new JButton("+");
// 		GridBagConstraints gbc_btnAddPlace = new GridBagConstraints();
// 		gbc_btnAddPlace.insets = new Insets(0, 0, 5, 0);
// 		gbc_btnAddPlace.gridx = 3;
// 		gbc_btnAddPlace.gridy = 1;
// 		panel.add(btnAddPlace, gbc_btnAddPlace);
		
// 		txtItinerary = new JTextField();
// 		txtItinerary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
// 		txtItinerary.setText("Itinerary");
// 		txtItinerary.setBounds(28, 25, 189, 42);
// 		frame.getContentPane().add(txtItinerary);
// 		txtItinerary.setColumns(10);
// 	}
// }


// // import java.awt.EventQueue;

// // import javax.swing.JFrame;
// // import javax.swing.JButton;
// // import java.awt.Color;
// // import javax.swing.JLabel;
// // import javax.swing.JTextField;
// // import javax.swing.JPanel;
// // import java.awt.Panel;
// // import java.awt.Canvas;
// // import javax.swing.SwingConstants;
// // import java.awt.GridBagLayout;
// // import java.awt.GridBagConstraints;
// // import java.awt.Insets;
// // import java.awt.Font;

// // public class ItineraryGUI extends JFrame {

// // 	private JFrame frame;
// // 	private JTextField textField;
// // 	private JTextField txtItinerary;

// // 	/**
// // 	 * Launch the application.
// // 	 */
// // 	public static void main(String[] args) {
// // 		EventQueue.invokeLater(new Runnable() {
// // 			public void run() {
// // 				try {
// // 					ItineraryGUI window = new ItineraryGUI();
// // 					window.frame.setVisible(true);
// // 				} catch (Exception e) {
// // 					e.printStackTrace();
// // 				}
// // 			}
// // 		});
// // 	}

// // 	/**
// // 	 * Create the application.
// // 	 */
// // 	// public ItineraryGUI() {
// // 	// 	initialize();
// // 	// }

// // 	public ItineraryGUI(Itinerary i) {
// // 		initialize();
// // 	}

// // 	/**
// // 	 * Initialize the contents of the frame.
// // 	 */
// // 	private void initialize() {
// // 		frame = new JFrame();
// // 		frame.setBounds(100, 100, 450, 300);
// // 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// // 		frame.getContentPane().setLayout(null);
		
// // 		JButton btnBackButton = new JButton("←");
// // 		btnBackButton.setBounds(296, 26, 47, 21);
// // 		frame.getContentPane().add(btnBackButton);
		
// // 		JButton btnDelete = new JButton("DELETE");
// // 		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 6));
// // 		btnDelete.setForeground(Color.RED);
// // 		btnDelete.setBounds(352, 26, 67, 21);
// // 		frame.getContentPane().add(btnDelete);
		
// // 		JLabel lblNotes = new JLabel("Notes:");
// // 		lblNotes.setBounds(28, 72, 45, 13);
// // 		frame.getContentPane().add(lblNotes);
		
// // 		textField = new JTextField();
// // 		textField.setBounds(28, 95, 380, 96);
// // 		frame.getContentPane().add(textField);
// // 		textField.setColumns(10);
		
// // 		JPanel panel = new JPanel();
// // 		panel.setBounds(28, 201, 380, 52);
// // 		frame.getContentPane().add(panel);
// // 		GridBagLayout gbl_panel = new GridBagLayout();
// // 		gbl_panel.columnWidths = new int[]{85, 0, 0, 0, 0};
// // 		gbl_panel.rowHeights = new int[]{34, 0, 21, 0};
// // 		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
// // 		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
// // 		panel.setLayout(gbl_panel);
		
// // 		JButton btnPlace1 = new JButton("P1");
// // 		btnPlace1.setHorizontalAlignment(SwingConstants.LEFT);
// // 		GridBagConstraints gbc_btnPlace1 = new GridBagConstraints();
// // 		gbc_btnPlace1.insets = new Insets(0, 0, 5, 5);
// // 		gbc_btnPlace1.anchor = GridBagConstraints.NORTHWEST;
// // 		gbc_btnPlace1.gridx = 0;
// // 		gbc_btnPlace1.gridy = 1;
// // 		panel.add(btnPlace1, gbc_btnPlace1);
		
// // 		JLabel lblArrow = new JLabel("→");
// // 		GridBagConstraints gbc_lblArrow = new GridBagConstraints();
// // 		gbc_lblArrow.insets = new Insets(0, 0, 5, 5);
// // 		gbc_lblArrow.gridx = 1;
// // 		gbc_lblArrow.gridy = 1;
// // 		panel.add(lblArrow, gbc_lblArrow);
		
// // 		JButton btnAddPlace = new JButton("+");
// // 		GridBagConstraints gbc_btnAddPlace = new GridBagConstraints();
// // 		gbc_btnAddPlace.insets = new Insets(0, 0, 5, 0);
// // 		gbc_btnAddPlace.gridx = 3;
// // 		gbc_btnAddPlace.gridy = 1;
// // 		panel.add(btnAddPlace, gbc_btnAddPlace);
		
// // 		txtItinerary = new JTextField();
// // 		txtItinerary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
// // 		txtItinerary.setText("Itinerary");
// // 		txtItinerary.setBounds(28, 25, 189, 42);
// // 		frame.getContentPane().add(txtItinerary);
// // 		txtItinerary.setColumns(10);
// // 	}
// // }

// // // import java.awt.EventQueue;
// // // import javax.swing.JFrame;
// // // import javax.swing.JButton;
// // // import java.awt.Color;
// // // import javax.swing.JLabel;
// // // import javax.swing.JTextField;
// // // import javax.swing.JPanel;
// // // import java.awt.GridBagLayout;
// // // import java.awt.GridBagConstraints;
// // // import java.awt.Insets;
// // // import java.awt.Font;

// // // public class ItineraryGUI extends JFrame {

// // //     private JTextField textField;
// // //     private JTextField txtItinerary;

// // //     // Create the application with an Itinerary object
// // //     public ItineraryGUI(Itinerary itinerary) {
// // //         initialize(itinerary);
// // //     }

// // //     // Initialize the contents of the frame
// // //     private void initialize(Itinerary itinerary) {
// // //         setTitle("Itinerary Details");
// // //         setBounds(100, 100, 450, 300);
// // //         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
// // //         getContentPane().setLayout(null);

// // //         JButton btnBackButton = new JButton("←");
// // //         btnBackButton.setBounds(296, 26, 47, 21);
// // //         getContentPane().add(btnBackButton);

// // //         JButton btnDelete = new JButton("DELETE");
// // //         btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 6));
// // //         btnDelete.setForeground(Color.RED);
// // //         btnDelete.setBounds(352, 26, 67, 21);
// // //         getContentPane().add(btnDelete);

// // //         JLabel lblNotes = new JLabel("Notes:");
// // //         lblNotes.setBounds(28, 72, 45, 13);
// // //         getContentPane().add(lblNotes);

// // //         textField = new JTextField();
// // //         textField.setBounds(28, 95, 380, 96);
// // //         getContentPane().add(textField);
// // //         textField.setColumns(10);

// // //         JPanel panel = new JPanel();
// // //         panel.setBounds(28, 201, 380, 52);
// // //         getContentPane().add(panel);
// // //         GridBagLayout gbl_panel = new GridBagLayout();
// // //         gbl_panel.columnWidths = new int[]{85, 0, 0, 0, 0};
// // //         gbl_panel.rowHeights = new int[]{34, 0, 21, 0};
// // //         gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
// // //         gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
// // //         panel.setLayout(gbl_panel);

// // //         JButton btnPlace1 = new JButton("P1");
// // //         btnPlace1.setHorizontalAlignment(SwingConstants.LEFT);
// // //         GridBagConstraints gbc_btnPlace1 = new GridBagConstraints();
// // //         gbc_btnPlace1.insets = new Insets(0, 0, 5, 5);
// // //         gbc_btnPlace1.anchor = GridBagConstraints.NORTHWEST;
// // //         gbc_btnPlace1.gridx = 0;
// // //         gbc_btnPlace1.gridy = 1;
// // //         panel.add(btnPlace1, gbc_btnPlace1);

// // //         JLabel lblArrow = new JLabel("→");
// // //         GridBagConstraints gbc_lblArrow = new GridBagConstraints();
// // //         gbc_lblArrow.insets = new Insets(0, 0, 5, 5);
// // //         gbc_lblArrow.gridx = 1;
// // //         gbc_lblArrow.gridy = 1;
// // //         panel.add(lblArrow, gbc_lblArrow);

// // //         JButton btnAddPlace = new JButton("+");
// // //         GridBagConstraints gbc_btnAddPlace = new GridBagConstraints();
// // //         gbc_btnAddPlace.insets = new Insets(0, 0, 5, 0);
// // //         gbc_btnAddPlace.gridx = 3;
// // //         gbc_btnAddPlace.gridy = 1;
// // //         panel.add(btnAddPlace, gbc_btnAddPlace);

// // //         txtItinerary = new JTextField();
// // //         txtItinerary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
// // //         txtItinerary.setText(itinerary.getName()); // Use itinerary details
// // //         txtItinerary.setBounds(28, 25, 189, 42);
// // //         getContentPane().add(txtItinerary);
// // //         txtItinerary.setColumns(10);
// // //     }
// // // }

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class ItineraryGUI extends JFrame {

    private JTextField textField;
    private JTextField txtItinerary;

		public ItineraryGUI() {
			// initialize();
		}

    public ItineraryGUI(Itinerary itinerary) {
        initialize(itinerary);
    }

    private void initialize(Itinerary i) {
        // Set the title and default close operation
        setTitle("Itinerary Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // Use GridBagLayout for better control over component placement
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Back Button
        JButton btnBackButton = new JButton("←");
        gbc.gridx = 1;
        gbc.gridy = 0;
        getContentPane().add(btnBackButton, gbc);

        // Delete Button
        JButton btnDelete = new JButton("DELETE");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 6));
        btnDelete.setForeground(Color.RED);
        gbc.gridx = 2;
        gbc.gridy = 0;
        getContentPane().add(btnDelete, gbc);

        // Notes Label
        JLabel lblNotes = new JLabel("Notes:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        getContentPane().add(lblNotes, gbc);

        // Notes TextField
        textField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        getContentPane().add(textField, gbc);

        // Itinerary Name TextField
        txtItinerary = new JTextField();
        txtItinerary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
        txtItinerary.setText(i.getName()); // Set text based on Itinerary object
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        getContentPane().add(txtItinerary, gbc);

        // Panel for additional controls
        JPanel panel = new JPanel();
        GridBagLayout gbl_panel = new GridBagLayout();
        panel.setLayout(gbl_panel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        getContentPane().add(panel, gbc);

        // Add more controls to the panel if needed
        JButton btnPlace1 = new JButton("P1");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btnPlace1, gbc);

        JLabel lblArrow = new JLabel("→");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(lblArrow, gbc);

        JButton btnAddPlace = new JButton("+");
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(btnAddPlace, gbc);
    }
}
