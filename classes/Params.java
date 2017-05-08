/**
 * Spencer Brydges 23/12/2014
 * Data structure containing ranking parameters and their user-supplied values
 */

package classes;

import java.io.Serializable;

public class Params implements Serializable 
{
	private static final long serialVersionUID = 6128016096756071380L;
	private String paramName;
	private int paramMin;
	private int paramMax;
	private int paramValue;
	
	/**
	 * Initializes new parameter with given name
	 * @param name Name of parameter
	 */
	
	public Params(String name)
	{
		//paramMin = min;
		//paramMax = max;
		paramName = name;
	}
	
	/**
	 * Returns the name of *this* parameter
	 * @return paramName Name of parameter
	 */
	
	public String getParamName()
	{
		return paramName;
	}
	
	/**
	 * Returns the value of *this* parameter
	 * @return paramValue value of parameter
	 */
	
	public int getValue()
	{
		return paramValue;
	}
	
	/**
	 * 
	 * @param newValue Value to update *this* value to
	 * @return Returns true if user-supplied value was appropriate
	 */
	
	public boolean setValue(int newValue)
	{
		System.out.println("Setting " + paramName + " to " +newValue);
		boolean ret = false;
		if(newValue >= 0)
		{
			paramValue = newValue;
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Updates *this* name with user-supplied name
	 * @param newName New parameter name
	 */
	
	public void setName(String newName)
	{
		paramName = newName;
	}
	
	
}