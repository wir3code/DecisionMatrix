/**
 * Spencer Brydges 23/12/2014
 * Driver logic for table. 
 * Contains most of the engine code for manipulating the matrix and underlying data structure.
 */

package classes;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.TitledBorder;

import java.io.*;

/**
 * 
 * @author Spencer Brydges
 * Rebuilt table model containing all the necessary logic for representing a ranking matrix
 * @member columnNames Array of column headers (represent parameters)
 * @member data Array containing cell data (parameter values)
 * @member struct Copy of data structure that holds user-supplied parameters, options and values for a given matrix
 * @member file Copy of IOClass object for manipulating files
 */

class MyTableModel extends AbstractTableModel 
{
    private static String[] columnNames;
    private static Object[][] data;
    private static Structure struct;
    private static IOClass file;
    private static TextArea textArea;
    
    /**
     * Constructor initializes both the data structure and the IOClass object
     * @param e Copy of structure
     * @param i Copy of IOClass object
     * @param cpy Reference to textArea object
     */
    
    public MyTableModel(Structure e, IOClass i, TextArea cpy)
    {
    	struct = e;
    	file = i;
    	textArea = cpy;
    	if(struct == null)
    	{
    		columnNames = new String[0];
    		data = new Object[0][0];
    	}
    	initializeData();
    }
    
    /**
     * Updates current working matrix
     * @param e New structure/ranking matrix to work with
     */
    
    public void setStructure(Structure e)
    {
    	struct = e;
    	initializeData();
    }
    
    /**
     * Totals parameter values to produce the most favourable decision options
     */
    
    public void computeRankings()
    {
    	int currentTotal = 0; 
    	
    	/*
    	 * Loop through every row value and update corresponding total cell
    	 * Start at column 1 as column 0 contains row headers (option names), not values
    	 */
    	for(int i = 0; i < data.length; i++)
    	{
    		for(int j = 1; j < columnNames.length-1; j++)
    		{
    			if(getValueAt(i, j) != null) //Only add to total if a value was supplied at current cell
    			{
    				currentTotal += Integer.parseInt(getValueAt(i, j).toString());
    			}
    		}
    		data[i][columnNames.length-1] = currentTotal; //Insert total count in last cell for given row
    		fireTableDataChanged(); //Refresh table
    		currentTotal = 0;
    	}
    	analyzeResults();
    }
    
    /**
     * Analyzes computed rankings and structures the results in terms of the following criteria:
     * (1) Groupings of 0-9, 10-19, 20-29, 30-39, n-n+1
     * (2) Middle values
     * (3) Top ranking values
     * (4) Max/Min values
     */
    
    public void analyzeResults()
    {
    	int [] computedRanks = new int[data.length];
    	
    	for(int i = 0; i < computedRanks.length; i++)
    	{
    		computedRanks[i] = Integer.parseInt(data[i][columnNames.length-1].toString());
    	}
    	
    	int maxValue = (int) computedRanks[0];
    	int minValue = (int) computedRanks[0];
    	
    	for(int i = 1; i < computedRanks.length; i++)
    	{
    		int tmp = (int) computedRanks[i];
    		
    		/**
    		 * WHAT THE FUCK is wrong with this line of code???
    		 */
    		
    		if(tmp > maxValue);
    		{
    			maxValue = tmp;
    		}
    		
    		if(tmp < minValue)
    		{
    			minValue = tmp;
    		}
    	}
    	
    	
    	textArea.append("Max = " + maxValue + ", Min = " + minValue + "\n");
    	
    	for(int i = 0, m = ((columnNames.length-2)*10); i < columnNames.length-2; i++, m-=10)
    	{
    		int groupNum = i+1;
    		System.out.println("M =" +m);
    		textArea.append("Group "+groupNum+": {");
    		boolean addComma = false;
	    	for(int j = 0; j < computedRanks.length; j++)
	    	{
	    		if(computedRanks[j] < m && computedRanks[j] >= (m-10))
	    		{
	    			if(!addComma)
	    			{
	    				textArea.append(data[j][0] + "");
	    				addComma = true;
	    			}
	    			else
	    			{
	    				textArea.append(", "+data[j][0]);
	    			}
	    		}
	    	}
	    	
	    	textArea.append("}\n");
    	}
    	
    }
    
    /**
     * Reads through supplied data structure and sets up values, parameters, and options
     * CELLS = VALUS
     * PARAMETERS = COLUMN HEADERS
     * OPTIONS = PSEUDO ROW HEADERS (stored in COLUMN #1)
     */
    
    public void initializeData()
    {
    	
    	
    	/*
		 * Debug dump
		 */
    	
		/*for(int i = 0; i < struct.getSize(); i++)
		{
			System.out.print("Params for option " + struct.getOption(i).getOptionName() + " ");
			for(int j = 0; j < struct.getOption(i).getSize(); j++)
			{
				System.out.print(struct.getOption(i).getParam(j).getParamName());
				if(j != struct.getOption(i).getSize() - 1)
				{
					System.out.print(", ");
				}
			}
			System.out.println();
		}
		
		for(int i = 0; i < struct.getSize(); i++)
		{
			for(int j = 0; j < struct.getOption(i).getSize(); j++)
			{
				System.out.print(struct.getOption(i).getParam(j).getValue());
				if(j != struct.getOption(i).getSize() - 1)
				{
					System.out.print(", ");
				}
			}
			System.out.println();
		}
		*/
		
		
    	// There needs to be an additional 2 columns (total column and the first column for options)
    	int colSize = (struct != null && struct.getSize() > 0) ? struct.getOption(0).getSize() + 2 : 2;
    	
    	//The number of options determines the number of rows
		int rowSize = (struct == null || struct.getSize() == 0) ? 0 : struct.getSize();
		
		//Set appropriate column and row lengths
		columnNames = new String[colSize]; //Column headers
		data = new Object[rowSize][colSize]; //Holds cell data
		
		//Need to keep track of column header index when populating
		int counter = 0;
		
		//First column has no header as it is empty -- it is merely a placeholder for row headers (options)
		columnNames[counter++] = ""; 
		
		/*
		 * Set column headers (parameters) and row headers (options) if and only if data exists 
		 * in current data structure
		 */
		
		if(struct != null)
		{
			
			//Begin setting column headers.
			//Keep track of columnNames index in separate counter variable -- populating is not achieved in single loop
			
			for(int i = 0; i < struct.getOption(0).getSize(); i++)
			{
				columnNames[counter] = struct.getOption(0).getParam(i).getParamName();
				counter++;
			}
			
			//Now set row headers
			
			for(int i = 0; i < struct.getSize(); i++)
			{
				data[i][0] = struct.getOption(i).getOptionName();
			}
		}
		
		//Make last column header which will contain the total of weight points
		
		columnNames[counter] = "Total";
		
		/*
		 * Fill table if worksheet was previously populated
		 * **** Optimize conditional logic later ****
		 */
		
		if(struct != null && struct.getSize() > 0)
		{
			//Loop from 0 to n-1 (as 0 contains row headers and n contains total data)
			
			for(int i = 0, a = 0; i < struct.getSize(); i++, a++)
			{
				for(int j = 1, b = 0; j < colSize; j++, b++)
				{
					if((struct.getOption(a).getParam(b)) != null)
					{
						data[i][j] = struct.getOption(a).getParam(b).getValue();
					}
				}
			}
		}
		
		fireTableStructureChanged();
		
		/*for(int i = 0; i < data.length; i++)
    	{
    		for(int j = 1; j < columnNames.length-1; j++)
    		{
    			if(getValueAt(i, j) != null)
    			{
    				data[i][j] = Integer.parseInt(getValueAt(i, j).toString());
    	    		fireTableDataChanged();
    			}
    		}
    	}*/
	
    }
    
    /**
     * Method prepares struct for the addition of a new option
     * @param optionName Name of option to add
     */
    
    public void addOption(String optionName)
    {
    	struct.addOption(optionName, columnNames.length-2);
    	initializeData(); //Refresh table
    }
    
    /**
     * Method prepares struct for the deletion of an option
     * @param optionName Name of option to delete
     */
    
    public void deleteOption(String optionName)
    {
    	struct.deleteOption(optionName);
    	initializeData();
    }
    
    /**
     * Method prepares struct for the addition of a parameter
     * @param optionName Name of parameter to add
     */
    
    public void addParameter(String paramName)
    {
    	struct.addParameter(paramName);
    	initializeData();
    }
    
    /**
     * Method prepares struct for the deletion of a parameter
     * @param optionName Name of parameter to delete
     */
    
    public void deleteParameter(String paramName)
    {
    	struct.deleteParameter(paramName);
    	initializeData();
    }
    
    /**
     * Returns the number of columns
     * @return Column count
     */

    public int getColumnCount() 
    {
        return columnNames.length;
    }
    
    /**
     * Returns the number of row
     * @return Row count
     */

    public int getRowCount() 
    {
        return data.length;
    }
    
    /**
     * Built in java method, has not been modified
     */

    public String getColumnName(int col) 
    {
        return columnNames[col];
    }
    
    /**
     * Built in java method, has not been modified
     */

    public Object getValueAt(int row, int col) 
    {
        return data[row][col];
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
    	if(col == columnNames.length-1)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }

    /**
     * Modified method. In addition to updating cell contents, the data structure is consequently modified as well
     */
    
    public void setValueAt(Object value, int row, int col) 
    {
    	int index = col-1;
    	
    	//Column 0 holds row headers. If column 0 was not hit, data will be input values
    	
    	if(col != 0)
    	{
    		if(value instanceof String)
        	{
        		try
        		{
        			value = Integer.parseInt(value.toString());
        		}
        		catch(NumberFormatException e)
        		{
        			value = 0;
        		}
        	}
    		struct.getOption(row).getParam(index).setValue((int) value);
    	}
    	
    	//An option is being modified
    	
    	else
    	{
    		struct.getOption(row).setOptionName((String) value);
    	}
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    /**
     * Method prepares structure for renaming
     */
    
    public void rename(String newName)
    {
    	struct.setDecision(newName);
    }
    
    /**
     * Method prepares structure for saving
     */
    
    public void saveContents()
    {	
    	try
    	{
    		file.savedSerializedFile(struct.getDecision() + ".ser", struct);
    	}
    	catch(IOException e) {}
    }
}