package net.frostbite.jshredder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Point;
import java.awt.TextArea;

public class Shredder extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static Shredder frame;
	private static List<File> files = new ArrayList<File>();
	private static JButton btnShred;
	private static TextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Shredder();
					frame.setResizable(false);
					frame.setVisible(true);
					if(args.length>0){
						files.add(new File(args[0]));
						btnShred.setEnabled(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private void startShred() {
		textArea.setText(textArea.getText()+"Shred Beginning...\n");
		for(File f : files){
			shred(f);
		}
		textArea.setText(textArea.getText()+"Shred Complete!\n");
	}
	
	private void shred (File f){
		if(f.exists()){
		if(!f.isDirectory()){
		BufferedWriter out = null;
		try  
		{
		    FileWriter fstream = new FileWriter(f.toString(), false); //true tells to append data.
		    out = new BufferedWriter(fstream);
		    out.write(ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom()+ShredRandom.nextRandom());
		}
		catch (IOException e)
		{
		    System.err.println("Error: " + e.getMessage());
		    textArea.setForeground(Color.RED);
		    textArea.setText("Ran into error while deleting a file! Error: " + e.getMessage());
		    try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    System.exit(0);
		}
		finally
		{
		    if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		} else {
			if(f.list().length>0){
				for(File f2: f.listFiles()){
					shred(f2);
				}
			}
		}
		f.delete();
		textArea.setText(textArea.getText()+"Shredded file: "+f.toString()+"\n");
		}
	}
	
	public Shredder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(70,70,70));
		panel.setBounds(0, 0, 504, 75);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblJshredder = new JLabel("jShredder");
		lblJshredder.setBackground(Color.GRAY);
		lblJshredder.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblJshredder.setForeground(Color.WHITE);
		panel.add(lblJshredder, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(65,65,65));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 304, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.showOpenDialog(frame);
				files = Arrays.asList(chooser.getSelectedFiles());
				if(files.size()>0){
					btnShred.setEnabled(true);
				} else {
					btnShred.setEnabled(false);
				}
			}
		});
		btnBrowse.setBounds(312, 11, 79, 20);
		panel_2.add(btnBrowse);
		
		btnShred = new JButton("SHRED");
		btnShred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startShred();
			}
		});
		btnShred.setBounds(20, 42, 125, 33);
		btnShred.setEnabled(false);
		panel_2.add(btnShred);
		
		JLabel lblWarningThisData = new JLabel("Shredded files cannot be recovered!");
		lblWarningThisData.setForeground(Color.WHITE);
		lblWarningThisData.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblWarningThisData.setBounds(155, 42, 242, 33);
		panel_2.add(lblWarningThisData);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 76, 504, 285);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textArea = new TextArea();
		textArea.setEditable(false);
		panel_1.add(textArea, BorderLayout.CENTER);
	}
}
