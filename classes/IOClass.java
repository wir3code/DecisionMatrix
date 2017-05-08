
/**
 * Spencer Brydges 23/12/2014
 * Driver class for saving serialized data structure and for deserializing and loading data structure
 * Main purpose of class is to handle file I/O that strictly concerns the data structure that is being manipulated
 * in the main driver program
 */

package classes;
import java.io.*;

/**
 * 
 * @author Spencer Brydges
 * Main I/O class
 * @param fileStream File stream for loading data into given input file
 * @param objectStream Stream for converting data into object bytes
 * @param fileInStream File stream for loading data from file
 * @param objectInStream File stream for converting and loading object back into main memory
 *
 */

public class IOClass
{
	private FileOutputStream fileStream;
	private ObjectOutputStream objectStream;
	
	private FileInputStream fileInStream;
	private ObjectInputStream objectInStream;
	
	/**
	 * No immediate loading/reading is needed
	 */
	
	public IOClass()
	{
		fileStream = null;
		objectStream = null;
		fileInStream = null;
		objectInStream = null;
	}
	
	/**
	 * Method takes a given data structure and converts it into serialized data, storing
	 * the data on disk until it needs to be loaded back into memory
	 * Useful debugging point
	 * @param file File to save object code to
	 * @param data Object to convert to object code
	 * @throws IOException Throw exception if an issue is encounter upon opening or writing to the file
	 */
	
	public void savedSerializedFile(String file, Structure data) throws IOException
	{
		try
		{
			/*
			 * Debug dump
			 */
			for(int i = 0; i < data.getSize(); i++)
			{
				System.out.print("Params for option " + data.getOption(i).getOptionName() + " ");
				for(int j = 0; j < data.getOption(i).getSize(); j++)
				{
					System.out.print(data.getOption(i).getParam(j).getParamName());
					if(j != data.getOption(i).getSize() - 1)
					{
						System.out.print(", ");
					}
				}
				System.out.println();
			}
			
			for(int i = 0; i < data.getSize(); i++)
			{
				for(int j = 0; j < data.getOption(i).getSize(); j++)
				{
					System.out.print(data.getOption(i).getParam(j).getValue());
					if(j != data.getOption(i).getSize() - 1)
					{
						System.out.print(", ");
					}
				}
				System.out.println();
			}
			
			
			fileStream = new FileOutputStream(file); //Create file stream to given input file
			objectStream = new ObjectOutputStream(fileStream); //Prepare to convert data to object code
	        objectStream.writeObject(data); //Write out object code
	        objectStream.close();
	        fileStream.close();
	        System.out.println("Wrote successfully");
		}
		catch(IOException e)
	    {
			throw new IOException("Failed to write data!");
	    }
	}
	
	/**
	 * Method converts serialized data in a given file back to its actual code, which is then
	 * loaded into memory
	 * @param file File containing serialized code
	 * @return Returns deserialized structure if no exception occurred
	 * @throws IOException Exception occurs if an error is encountered upon opening or loading the file
	 */
	
	public Structure decodeFile(String file) throws IOException
	{
		Structure ret = null;
		fileInStream = new FileInputStream(file);
		objectInStream = new ObjectInputStream(fileInStream);
		try
		{
			ret = (Structure) objectInStream.readObject();
		}
		catch(ClassNotFoundException e) {}
		return ret;
	}
}