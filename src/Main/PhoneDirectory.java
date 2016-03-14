package Main;

import java.io.*;



/**
 * The PhoneDirectory method, which is overloaded to take in a path/
 * 
 * @author Alston
 *
 */
public class PhoneDirectory {


	String newfile = null;
	String seperator = null;
	
	/**
	 * Default Constructor
	 * @param stream
	 * @throws FileNotFoundException
	 */
	public PhoneDirectory(String stream) throws FileNotFoundException{
		
		newfile = stream;
	
	}
/**
 * Overloaded constructor.
 * The parse string that is passed in will seperate the name from the number
 * in the phonebook file
 * 
 * @param stream
 * @param parse
 * @throws FileNotFoundException
 */
public PhoneDirectory(String stream, String parse) throws FileNotFoundException{
		
		newfile = stream;
		seperator = parse;
		
	
	}

	
/**
 * The address book is opened, by accessing the properties file.
 * Then the name and number are written to the file. 
 * Close the file, and print updates to the console
 *
 * @param name
 * @param number
 * @throws IOException
 */

	public void addEntry(String name, String number) throws IOException {
		//print name and number
	
	
	
		Writer fileWriter = new FileWriter(newfile, true);  
		fileWriter.write(name + seperator + number);
		fileWriter.write(System.lineSeparator());
		fileWriter.close();
		
		System.out.println("Just added: ");
		System.out.println("Name: " + name);
		System.out.println("Phone number: " + number);
		//pass
	}
	
/**
 *   Two files are opened, the address file and a new temporary file.
 * Every line that does not contain the passed in name is added to the tempoprary file.
 * The main address book is then overridden by the temporary file.
 * @param name
 * @throws IOException
 */
	
	public void deleteEntry(String name) throws IOException {
	
		String line = null;
		     

		try {
		
	        //making path to main
			BufferedReader file = new BufferedReader(new FileReader(newfile));
			
			//creating new file
			
			File temp = new File("/Users/Alston/Documents/CODES/Large-Scale-Programming-/LargeScale/PhoneDirectoryJAR/src/Resources/temp.text");//creating new temporary file
		    BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		       

		    
	        while ((line = file.readLine()) != null) {
	
	        	if (!line.toLowerCase().contains(name.toLowerCase())) { //searching file for entries that do not contain name to delete
	        	
	    			System.out.println("Found: " + name);
	        		writer.write(line); //writing to temporary file
	
	        }
        	file.close();//closing addressbook file
 	        writer.close();//closing temporary file 
 	        writer = new BufferedWriter(new FileWriter(newfile)); //opening writing file
 	        
 	        writer.write(line); // overwriting the file

			System.out.println("Deleted: ");
			System.out.println("Name: " + name);

	        }
		}

	     catch (Exception e) {
	        System.out.println("");
	    }
	}
	
	
  /**
   * Iterate through the addressBook file until the name is found.
   * The last index of the string is the number.
   * Save and return that value.
   * @param name
   * @return
   * @throws IOException
   */
	
	
	public String getNumber(String name) throws IOException {
	
		try {
	        // input the file content to the String "input"
	        BufferedReader file = new BufferedReader(new FileReader((newfile)));
	        String line;
	        
	        while ((line = file.readLine()) != null) {
	        	
	        	if (line.toLowerCase().contains(name.toLowerCase())) {
	        		String[] splitString = line.split(" ");
	        		        		
	    	        file.close();
	    	        int index_of_number = splitString.length;

	    	        return splitString[index_of_number -1];
	        	}
	        }
			file.close();
			
	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
		
		return "That user was not found";
	}	
	
/**
 * Locate the requested name in the addressBook file, then delete it using the deleteEntry
 * function. Afterward add the same name with the updated number.
 * @param name
 * @param number
 * @throws IOException
 */
	
	public void changeEntry(String name, String number) throws IOException{
	
		deleteEntry(name);
		addEntry(name, number);
		
	}
}
	
