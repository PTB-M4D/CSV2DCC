package FRED;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GUITest5 extends JFrame implements ActionListener	
{
	private static final int FRAME_WIDTH       =1000;
	private static final int FRAME_HEIGHT      =500;
	private static final int FRAME_X_ORIGIN    =150;
	private static final int FRAME_Y_ORIGIN    =250;	
	
	private JTextField txtCSVFile;
	private JTextField txtParFile;
	private JTextField txtOutFile;	
	
	private JButton openDoc, openLic,  browseCSV, browsePar, browseOut, goButton, closeButton;
	
	private File csvFile, csvDirectory, parFile, parDirectory, outFile, outDirectory, currentDirectory;

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		GUITest5 frame = new GUITest5();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event)					// New function in GUITest2
	{																
		JButton clickedButton = (JButton) event.getSource();		
		
		String buttonText = clickedButton.getText();				
		
		int status;
		
		// this.setTitle("You clicked " + buttonText);					
		
		//default title and icon
		// JOptionPane.showMessageDialog(this, "You clicked " + buttonText);	
		
		if(openDoc == (JButton) event.getSource())
		{
			JOptionPane.showMessageDialog(this, "You clicked Open Document...");
			// Open the document containing the basic Documentation for CSV2DCC

			
		}
		else if((JButton) event.getSource() == openLic)
		{
			JOptionPane.showMessageDialog(this, "You clicked Licence...");
			// Open the document containing the Licence Documentation for CSV2DCC			
		}
		else if((JButton) event.getSource() == browseCSV)
		{
			// Select the CSV File and enter into text field
			
			JFileChooser chooser = new JFileChooser();
		    // Open dialog to select a csv file 
	        chooser.setDialogTitle("Open Parameter Value Pair CSV file");
	        chooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV Documents", "csv"));
	        chooser.setAcceptAllFileFilterUsed(true);
	        chooser.setCurrentDirectory(currentDirectory);		//NN
	        status = chooser.showOpenDialog(null);

	        // Select the csv file
	        if (status == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().exists())
	        {
	            csvFile = chooser.getSelectedFile();
	            csvDirectory = chooser.getCurrentDirectory();
	            
	            // set the currentDirectory to the last folder accessed		//NN
	            currentDirectory = csvDirectory;							//NN
	            
		        // Update Text Field
		        txtCSVFile.setText(csvFile.getAbsolutePath());
	
		        // Check to see if filename contains 'csv' in some format
		        String fname = chooser.getSelectedFile().getName();
		    
		        if(fname.toLowerCase().contains("csv"))
		        {
		        	// Filename looks good, contains "csv"
		        }
		        else
		        {
		        	// Filename does not contain "csv", warn user
		    		JOptionPane.showMessageDialog(this, "Please note:- The filename does not contain \"csv\" is this the right file?");
		        }
		        
	        }
	        else
	        {
	        	// The file does not exist
	        	JOptionPane.showMessageDialog(null, "You must select an existing CSV file!");
	        }
			

		}
		else if((JButton) event.getSource() == browsePar)
		{
			// Select the Parameterised DCC File and enter into text field
			
			JFileChooser chooser = new JFileChooser();
		    // Open dialog to select a parameterised DCC file 
	        chooser.setDialogTitle("Open Parameterised DCC file");
	        chooser.addChoosableFileFilter(new FileNameExtensionFilter("XML Documents", "xml"));
	        chooser.setAcceptAllFileFilterUsed(true);
	        chooser.setCurrentDirectory(currentDirectory);		//NN
	        status = chooser.showOpenDialog(null);

	        // Select the parameterised DCC file
	        if (status == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().exists()) 
	        {
	            parFile = chooser.getSelectedFile();
	            parDirectory = chooser.getCurrentDirectory();
	            
	            // set the currentDirectory to the last folder accessed		//NN
	            currentDirectory = parDirectory;							//NN
	            
		        // Update Text Field
		        txtParFile.setText(parFile.getAbsolutePath());
		        
		        // Check to see if filename contains 'par' in some format
		        String fname = chooser.getSelectedFile().getName();
		    
		        if(fname.toLowerCase().contains("par"))
		        {
		        	// Filename looks good, contains "par"
		        }
		        else
		        {
		        	// Filename does not contain "par", warn user
		    		JOptionPane.showMessageDialog(this, "Please note:- The filename does not contain \"par\" is this the right file?");
		        }
		        
	        }
	        else
	        {
	        	// The file does not exist
	        	JOptionPane.showMessageDialog(null, "You must select an existing Parameterised DCC file!");

	        }
			
		}
		else if((JButton) event.getSource() == browseOut)
		{
			// Use Save As dialog to select a "new" file
			// Select the Parameterised DCC File and enter into text field
			
			JFileChooser chooser = new JFileChooser();
		    // Open dialog to select a parameterised DCC file 
	        chooser.setDialogTitle("Save New DCC file");
	        chooser.addChoosableFileFilter(new FileNameExtensionFilter("XML Documents", "xml"));
	        chooser.setAcceptAllFileFilterUsed(true);
	        chooser.setCurrentDirectory(currentDirectory);		
	        status = chooser.showSaveDialog(null);
	        
	        // Get the selected file into outFile
	        outFile = chooser.getSelectedFile();
	        // Get out filename and force to lower case
	        String outfname = outFile.getName();
	        int loutfname = outfname.length();
	        
	        File UpdOutFile;
	        
        	// Force the extension to be ".xml"
        	// Create new File object with added extension      	
        	// New version will look for "." if there is one and replace everything beyond with ".xml"
        	int pnum = outfname.indexOf(".");
        	if(pnum == -1)
        	{
        		// No "." found so just add ".xml" to end
	        	// Create new File object with added extension
	        	outfname = outfname + ".xml";        		
        	}
        	else
        	{
        		// "." found but extension maybe malformed so replace 
        		outfname = outfname.substring(0, pnum) + ".xml";
        	}
        	
        	UpdOutFile = new File(chooser.getCurrentDirectory() + "/" + outfname);        		
	        
	        
	        boolean okcontinue = true;				// This needs to be true in the case that a completely new file is given
	        
	        // Check to see if file already exists and ask User to confirm continue
	        // if(true == chooser.getSelectedFile().exists())
	        if((UpdOutFile.exists()) == true)
	        {       	
	        	if (JOptionPane.showConfirmDialog(null, "Are you sure you want to overwrite file?", "WARNING",
	        	        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	        	    // yes option
	        		okcontinue = true;
	        	} else {
	        	    // no option
	        		okcontinue = false;
	        	}   	
	        }


	        // Select the Out DCC file
	        if (status == JFileChooser.APPROVE_OPTION && (okcontinue == true))
	        {
	            // outFile = chooser.getSelectedFile();
	        	outFile = UpdOutFile.getAbsoluteFile();
	            outDirectory = UpdOutFile.getParentFile();
	            
	            // set the currentDirectory to the last folder accessed		
	            currentDirectory = outDirectory;								            
	            
		        // Update Text Field
		        txtOutFile.setText(outFile.getAbsolutePath());
		        
		        // Check to see if filename contains 'par' in some format
		        String fname = chooser.getSelectedFile().getName();
		        fname = outFile.getName();
		    
		        if(fname.toLowerCase().contains("par"))
		        {
		        	// Filename looks good, contains "par"
		    		JOptionPane.showMessageDialog(this, "Please note:- The filename contains \"par\" is this the right file? Are you overwriting a Parameterised DCC File?");
		        }
		        else
		        {
		        	// Filename does not contain "par", no need to warn user

		        }
		        
	        }
	        else
	        {
	            // If this executes the program will fail
	        	// Implement Check that all text fields are good in the Go section
	        }
			
		}		
		else if((JButton) event.getSource() == goButton)
		{
			// Do basic sanity checks on filenames
			if(!txtCSVFile.getText().isEmpty() && !txtParFile.getText().isEmpty() && !txtOutFile.getText().isEmpty())
			{
								
				// If data is good, Generate embedded XML DCC
				JOptionPane.showMessageDialog(this, "You clicked Generate...");
				
				// Generate the DCC XML file with embedded data
				
		        FRED.CSV2DCC2 DCCGenerator = new CSV2DCC2();
		        DCCGenerator.csvFileString = csvFile.getAbsolutePath();
		        DCCGenerator.parFileString = parFile.getAbsolutePath();
		        DCCGenerator.outFileString = outFile.getAbsolutePath();		
				
				// Result indicator
		        int result = 0;
				
				try {
					result = DCCGenerator.Go();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(result == 0)
				{
					// It worked
					JOptionPane.showMessageDialog(this, "Generate completed sucessfully");
				}
				else
				{
					// Something went wrong
					JOptionPane.showMessageDialog(this, "Generate Failed with exception code " + String(result));				
				}
			
			}
			else
			{
				// Some filenames are missing
				JOptionPane.showMessageDialog(this, "Please Supply Missing Filenames Before Generating DCC");
			}
			
		}
		else if((JButton) event.getSource() == closeButton)
		{
			// Quit the application
			System.exit(0);
		}
		else
		{
			// Something went wrong
		}


		
	}																//N
	
	
	private String String(int result) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public GUITest5() throws IOException
	{

		JPanel titlePanel, docPanel, docButPanel, licButPanel, csvPanel, parPanel, outPanel, goPanel, closePanel, closeButPanel;
		
		this.setTitle("Embed CSV Results into DCC");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
		
		// Set Background colour to white
		Container contentPane = this.getContentPane();
		contentPane.setBackground(Color.WHITE);
		
		// Set Stacked Grid layout
		contentPane.setLayout(new GridLayout(7,1));
		
		// Title Panel
		titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("CSV2DCC - Embed CSV Results into DCC XML File");
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		titleLabel.setForeground(Color.BLUE);
		titlePanel.add(titleLabel);
		contentPane.add(titlePanel);
		
		// Documentation Panel
		docPanel = new JPanel(new BorderLayout());
		
		docButPanel = new JPanel();
		openDoc = new JButton("Open Documentation...");
		openDoc.setBackground(Color.ORANGE);
		docButPanel.add(openDoc);		
		docPanel.add(docButPanel, BorderLayout.WEST);		

		licButPanel = new JPanel();
		openLic = new JButton("View Licence...");
		openLic.setBackground(Color.ORANGE);
		licButPanel.add(openLic);
		docPanel.add(licButPanel, BorderLayout.EAST);
		
		contentPane.add(docPanel);
		
		//// CSV Panel
		//csvPanel = new JPanel(new BorderLayout());
		//csvPanel.add(new JLabel("1. Please select the  csv FILE (using ; separators)"), BorderLayout.WEST);
		//csvPanel.add(browseCSV = new JButton("Browse..."), BorderLayout.CENTER);
		//csvPanel.add(txtCSVFile = new JTextField(40), BorderLayout.EAST);
		//contentPane.add(csvPanel);
		
		// CSV Panel
		csvPanel = new JPanel();
		csvPanel.add(new JLabel("1. Please select the CSV file (using ; separators)"));
		browseCSV = new JButton("Browse...");
		browseCSV.setBackground(Color.YELLOW);
		csvPanel.add(browseCSV);
		csvPanel.add(txtCSVFile = new JTextField(60));
		txtCSVFile.setEditable(false);
		contentPane.add(csvPanel);
		
		// Parameterised XML Panel
		parPanel = new JPanel();
		parPanel.add(new JLabel("2. Please select Parameterised DCC xml file          "));
		browsePar = new JButton("Browse...");
		browsePar.setBackground(Color.YELLOW);
		parPanel.add(browsePar);
		parPanel.add(txtParFile = new JTextField(60));
		txtParFile.setEditable(false);
		contentPane.add(parPanel);
		
		// Output XML Panel
		outPanel = new JPanel();
		outPanel.add(new JLabel("3. Please select where to save the DCC xml file     "));
		browseOut = new JButton("Browse...");
		browseOut.setBackground(Color.YELLOW);
		outPanel.add(browseOut);
		outPanel.add(txtOutFile = new JTextField(60));
		txtOutFile.setEditable(false);
		contentPane.add(outPanel);
		
		
		// Go Panel
		goPanel = new JPanel();
		goPanel.add(new JLabel("4. Click \"Generate\" button to create the XML file with emebedded data"));
		goButton = new JButton("Generate");
		goButton.setBackground(Color.GREEN);
		goPanel.add(goButton);
		contentPane.add(goPanel);
		
		// Close Panel
		closePanel = new JPanel(new BorderLayout());
		closeButPanel = new JPanel();
		closeButton = new JButton("Close");
		closeButton.setBackground(Color.RED);
		closeButPanel.add(closeButton);
		closePanel.add(closeButPanel, BorderLayout.WEST);		
		contentPane.add(closePanel);
		
		// Add Panels to contentPane
		// Already done in each section


		// Add Action Listeners for all Buttons						// N
		openDoc.addActionListener(this);							// N
		openLic.addActionListener(this);							// N		
		browseCSV.addActionListener(this);							// N	
		browsePar.addActionListener(this);							// N
		browseOut.addActionListener(this);							// N
		goButton.addActionListener(this);							// N
		closeButton.addActionListener(this);							// N	

		// Register "Exit on Closing" as default close operation
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Set default value for the current directory (which is at least sensible and on the machine)
		String currentPath = new java.io.File(".").getCanonicalPath();
		currentDirectory = new File(currentPath);
		
	}

}

