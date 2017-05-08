/**
 * Spencer Brydges 23/12/2014
 * Data structure containing ranking options and their user-supplied values
 */

package classes;
import java.util.*;
import java.io.Serializable;

public class Options implements Serializable 
{
	private static final long serialVersionUID = 6128016096756071380L;
	private String optionName;
	private Params [] parameters;
	
	/**
	 * Initializes option to user-supplied name
	 * Also initializes associated parameters with option
	 * @param count Initial size of parameters array
	 * @param name Name of option
	 */
	
	public Options(int count, String name)
	{
		optionName = name;
		parameters = new Params[count];
	}
	
	/**
	 * Updates *this* option name with user-supplied name
	 * @param newName New name to set option to
	 */
	
	public void setOptionName(String newName)
	{
		optionName = newName;
	}
	
	/**
	 * Returns name of option
	 * @return Name of option
	 */
	
	public String getOptionName()
	{
		return optionName;
	}
	
	/**
	 * Method adds parameter to array. Repetitively called by addParameter method in structure
	 * @param parameterName Name of parameter to add to array
	 */
	
	public void addParameter(String parameterName)
	{
		Params [] newParams = new Params[parameters.length+1];
		for(int i = 0; i < parameters.length; i++)
		{
			newParams[i] = parameters[i];
		}
		parameters = newParams;
		setParameter(new Params(parameterName), newParams.length-1);
	}
	
	/**
	 * Method deletes a parameter from array. Repetitively called by deleteParameter method in structure
	 * @param parameterName Name of parameter to delete
	 */
	
	public void deleteParameter(String parameterName)
	{
		boolean resize = false;
		for(int i = 0; i < getSize(); i++)
		{
			if(parameters[i].getParamName().equals(parameterName))
			{
				parameters[i] = null;
				resize = true;
			}
		}
		if(resize)
		{
			Params [] newParams = new Params[parameters.length-1];
			for(int i = 0, j = 0; i < getSize(); i++)
			{
				if(parameters[i] != null)
				{
					newParams[j++] = parameters[i];
				}
			}
			parameters = newParams;
		}
	}
	
	/**
	 * Method modifies a parameter
	 * @param p Parameter that replaces existing parameter
	 * @param index Location of parameter that is to be replaced
	 * @return True if parameter location exists
	 */
	
	public boolean setParameter(Params p, int index)
	{
		boolean ret = false;
		if(index >= 0 && index <= parameters.length)
		{
			parameters[index] = p;
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Method returns the parameter object at a given index
	 * @param index Location in parameters array to return
	 * @return Returns the given parameter at location index
	 */
	
	public Params getParam(int index)
	{
		Params ret = null;
		if(index < getSize())
		{
			ret = parameters[index];
		}
		return ret;
	}
	
	/**
	 * Returns the number of associated parameters with *this* option
	 * @return Returns capacity of parameters array
	 */
	
	public int getSize()
	{
		return parameters.length;
	}
}