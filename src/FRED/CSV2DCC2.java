package FRED;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// This version now includes a logfile output which shows which substitutions have been done
// TreeMap definitions have been refined

public class CSV2DCC2 {
	
	
	public String csvFileString;
	public String parFileString;
	public String outFileString;
	
	// private static Map subsTable;					// P
	private static TreeMap<String, String> subsTable;	// P
	private static Map subsCount;	//M
	
	public CSV2DCC2()
	{
		// Empty constructor
	}
	
	public int Go() throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		// Create map		
		subsTable = new TreeMap();
		subsCount = new TreeMap();
		TreeMap<String, Integer> subsCount2 = new TreeMap<String, Integer>();
		
		// Read through CSV
	    
		try
		{
		    String line;
		    BufferedReader in = new BufferedReader(new FileReader(csvFileString));   
		    // reading files in specified directory
		    
		    //  int noofFields = 0;
			int x= 0;
			int y = 0;

		    // This assigns the data to the 2D array 
		    // The program keeps looping through until the line read in by the console contains no data in it i.e. the end of the file. 
		    while  (( line = in.readLine()) != null )
		    {
		    	y = 0;
		    	
		        String[] current_Record = line.split(";");
		        if(x == 0) 
		        {
		            // Counts the number of fields in the csv file. 
		            int noofFields = current_Record.length;
		            
		            // There should max 2 parts per line
		            // TODO Add check noofFields == 2
		        }
	        
		        // Store information in Map table
				subsTable.put(current_Record[0], current_Record[1]);
				// Set up count table									//M
				subsCount.put(current_Record[0], "0");					//M
		        
		        
		        // The record index variable, x is incremented in every loop. 
		        x = x + 1;

		    }
		    
			// This frees up the BufferedReader file descriptor resources 
			in.close();
		}
		
		
	    //  If an error occurs, it is caught by the catch statement and an error message 
	    //   is generated and displayed to the user. 
		catch( IOException ioException ) 
		{
		    System.out.println("Exception: "+ioException);
		}
		
		
		//// Scan through TemperatureParameter.xml and replace
	    //Get Document Builder
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	       
	    //Build Document
	    // Document document = builder.parse(new File("employees.xml"));
	    Document document = builder.parse(new File(parFileString));
	       
	    //Normalise the XML Structure; It's just too important !!
	    document.getDocumentElement().normalize();
	      
	      
	    // Print the document from the DOM tree
	    printNode(document);
	    
	     // create the xml file
	     // transform the DOM Object to an XML File
	     TransformerFactory transformerFactory = TransformerFactory.newInstance();
	     Transformer transformer = transformerFactory.newTransformer();
	     DOMSource domSource = new DOMSource(document);
	     StreamResult streamResult = new StreamResult(new File(outFileString));

	     // If you use
	     // StreamResult result = new StreamResult(System.out);
	     // the output will be pushed to the standard output ...
	     // You can use that for debugging 

	     transformer.transform(domSource, streamResult);

	     System.out.println("Done creating XML File");	    
	     
	     subsCount.forEach((key, value)->System.out.println(
	                "Code : " + key + "\t\t\t"
	                        + "Replacements : " + value));
		
		return 0;		// Replace 0 with suitable return code
	}
	
	private static void printNode(Node node)
	{
		// Determine the type of node
		// Print the node
		// Recurse on children
		// Determine action based on node type
		switch(node.getNodeType())
		{
		case Node.DOCUMENT_NODE:
			// Print the contents of the Document object
			System.out.println("<xml version=\"1.0\">\n");
			Document doc = (Document)node;
			printNode(doc.getDocumentElement());
			break;
			
		case Node.ELEMENT_NODE:
			// Print the element and its attributes
			String name = node.getNodeName();
			System.out.print("<" + name);
			
			if( name.contains("si:value") )
			{
				// Found measurement node
				int zzz = 1;
			}
	
			// Print out attributes			
			NamedNodeMap attributes = node.getAttributes();
			for (int i=0; i<attributes.getLength(); i++)
			{
				
				Node current = attributes.item(i);
				
				//// Check if NodeValue of attribute contains a subs string	//N
				//if(subsTable.containsKey(current.getNodeValue()))			//N
				//{															//N
				//	current.setNodeValue(name);								//N
				//}															//N
				
				String aname = current.getNodeValue();
				if(aname.contains("XYX01"))
				{
					// Found attribute
					int zzz = 1;
				}
						
				if (subsTable.containsKey(aname))
				{
					int yyy = 1;
					
					// Get the value string
					String strValue = String.valueOf(subsTable.get(aname));
					
					// Substitute into the XML DOM					
					current.setNodeValue(strValue);
					
					//// Update the relevant subsCount table item				//M
					// Get current value										//M
					String strcount = String.valueOf(subsCount.get(aname));		//m	
					
					// Update the string "+1" and replace in subsCount
					int icount = Integer.valueOf(strcount);		//m
					icount++;									//m
					strcount = String.valueOf(icount);			//m
					subsCount.put(aname, strcount);		//m
					
				}
										
				System.out.print(" " + current.getNodeName() + 
						         "=\"" + current.getNodeValue() +
						         "\"");
				
			}
			System.out.println(">");
			
			// recurse on each child
			NodeList children = node.getChildNodes();
			
			if (children != null)
			{
				for ( int i = 0; i < children.getLength(); i++)
				{

					// Get the node String
					String nodeString = String.valueOf(children.item(i).getNodeValue());
					
					// Is the node string in subsTable
					if (subsTable.containsKey(nodeString))
					{
						// Get the value string
						String strValue = String.valueOf(subsTable.get(nodeString));
						
						// Substitute into the XML DOM
						children.item(i).setNodeValue(strValue);
												
						//// Update the relevant subsCount table item				//M
						// Get current value										//M
						String strcount = String.valueOf(subsCount.get(nodeString));			//M

						// Update the string "+1" and replace in subsCount
						int icount = Integer.valueOf(strcount);		//m
						icount++;									//m
						strcount = String.valueOf(icount);			//m
						subsCount.put(nodeString, strcount);		//m

					}
					
					// printTree(children.item(i));
					printNode(children.item(i));
				}
			}
			System.out.println("</" + name + ">");
			break;
			
		case Node.TEXT_NODE:
		case Node.CDATA_SECTION_NODE:
			// Print the textual data
			if (node.getNodeValue() == "BTMIN")
			{
				int zzz=1;
			}
			System.out.print(node.getNodeValue());
			break;
			
		case Node.PROCESSING_INSTRUCTION_NODE:
			//Print the processing instruction
			break;
			
		case Node.ENTITY_REFERENCE_NODE:
			// Print the entity reference
			break;
			
		case Node.DOCUMENT_TYPE_NODE:
			// Print the DTD declaration
			break;
		}
		
	}

}
