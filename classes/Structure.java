/**
 * Spencer Brydges 23/12/2014
 * Primary data structure code. Option data is stored within structure
 */

package classes;
import java.util.*;
import java.io.Serializable;

/**
 * 
 * @author Spencer Brydges
 * @param decision The name of the decision that has to be made (also used as filename)
 * @param opts Options array containing the available decision options
 *
 */

public class Structure implements Serializable 
{
	private static final long serialVersionUID = 6128016096756071380L;
	private String decision;
	private Options [] opts;
	
	/**
	 * 
	 * @param count Number of options structure can initially contain
	 * @param name Decision that must be made
	 */
	
	public Structure(int size, String name)
	{
		opts = new Options[size];
		decision = name;
	}
	
	/**
	 * Method renames decision
	 * @param newDecision New decision value
	 */
	
	public void setDecision(String newDecision)
	{
		decision = newDecision;
	}
	
	/**
	 * Returns name of decision
	 * @return Name of decision
	 */
	
	public String getDecision()
	{
		return decision;
	}
	
	/**
	 * Method sets an option field
	 * @param o Option object containing name of option and its parameter values
	 * @param index Location in option array to set value
	 * @return Returns true if array write is successful
	 */
	
	public boolean setOption(Options o, int index)
	{
		boolean ret = false;
		if(index >= 0 && index <= opts.length) //Only modify array if index exists
		{
			opts[index] = o;
		}
		return ret;
	}
	
	/**
	 * Method returns a given option object
	 * @param index Location in option array to fetch
	 * @return Returns option object at given index
	 */
	
	public Options getOption(int index)
	{
		return opts[index];
	}
	
	/**
	 * Returns available capacity of structure (see: the # of options it can hold)
	 * @return Length of options array
	 */
	
	public int getSize()
	{
		return opts.length;
	}
	
	/**
	 * Method adds a user-supplied option to array
	 * TO-DO: Automatically sort alphabetically
	 * @param optionName Name of option to add
	 * @param count Location to add option (typically at beginning or end of array)
	 */
	
	public void addOption(String optionName, int count)
	{
		if(getSize() > 0) //Options already exist, add to end of array
		{
			Options [] newOpts = new Options[opts.length+1];
			System.arraycopy(opts, 0, newOpts, 0, opts.length);
			newOpts[opts.length] = new Options(count, optionName);
			
			/*
			 * Copy parameters from last option in array to new option
			 */
			
			for(int i = 0; i < opts[opts.length-1].getSize(); i++)
			{
				System.out.println("Debug: setting parameter " + opts[opts.length-1].getParam(i));
				newOpts[opts.length].setParameter(new Params(opts[opts.length-1].getParam(i).getParamName()), i);
			}
			opts = newOpts;
		}
		else //Option array is empty -- no need to sort/move parameters
		{
			opts = new Options[1];
			opts[0] = new Options(count, optionName);
		}
	}
	
	/**
	 * Method deletes a given option from array
	 * @param optionName Option to delete
	 */
	
	public void deleteOption(String optionName)
	{
		boolean resize = false; //If supplied option exists, then the array must be resized
		
		for(int i = 0; i < getSize(); i++)
		{
			if(opts[i].getOptionName().equals(optionName)) //A match was found, array must be resized and option must be deleted
			{
				opts[i] = null;
				resize = true;
			}
		}
		
		if(resize)
		{
			Options [] newOpts = new Options[opts.length-1]; //New array is going to be 1 element smaller as option was deleted
			
			for(int i = 0, j = 0; i < getSize(); i++)
			{
				if(opts[i] != null)
				{
					newOpts[j++] = opts[i];
				}
			}
			opts = newOpts;
		}
	}
	
	/**
	 * Method adds a parameter, thereby updating all options
	 * @param parameterName Parameter to add
	 */
	
	public void addParameter(String parameterName)
	{
		for(int i = 0; i < getSize(); i++)
		{
			opts[i].addParameter(parameterName);
		}
	}
	
	/**
	 * Method deletes a parameter, thereby updating all options
	 * @param parameterName Parameter to delete
	 */
	
	public void deleteParameter(String parameterName)
	{
		for(int i = 0; i < getSize(); i++)
		{
			opts[i].deleteParameter(parameterName);
		}
	}
	
	public void sortData(boolean asc)
	{
		if(asc == false)
		{
			
		}
		else
		{
			
		}
	}
}