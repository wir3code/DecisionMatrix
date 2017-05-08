import java.util.*;
import java.io.*;
import java.awt.*;
import classes.*;
import javax.swing.JFrame;

class main implements Serializable
{
	public static void main(String [] args)
	{
		/*
		Scanner in = new Scanner(System.in);
		Scanner str = new Scanner(System.in);
		String decision = "";
		int numOptions = 0;
		int numParams = 0;
		int minParam = 0;
		int MaxParam = 0;
		int paramValue = 0;
		String [] optionNames;
		String [] paramNames;
		Structure mainStruct = null;
		
		
		System.out.print("Enter name of decision: ");
		decision = str.nextLine();
		
		
		System.out.print("# of options: ");
		numOptions = in.nextInt();
		optionNames = new String[numOptions];
		
		
		mainStruct = new Structure(numOptions, decision);
		
		
		System.out.print("# of params: ");
		numParams = in.nextInt();
		
		
		paramNames = new String[numParams];
		for(int i = 0; i < numParams; i++)
		{
			System.out.print("Enter name of parameter #"+i+": ");
			paramNames[i] = str.nextLine();
		}
		
		
		
		for(int i = 0; i < numOptions; i++)
		{
			System.out.print("Enter name of option #"+i+": ");
			optionNames[i] = str.nextLine();
			Options o = new Options(numParams, optionNames[i]);
			
			for(int j = 0; j < numParams; j++)
			{
				System.out.print("Enter value for parameter "+paramNames[j]+": ");
				int value = in.nextInt();
				Params p = new Params(1, 5, paramNames[j]);
				p.setValue(value);
				o.setParameter(p, j);
			}
			
			mainStruct.setOption(o, i);
		}
		
		try
		{
			FileOutputStream fileOut = new FileOutputStream("tmp.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(mainStruct);
	        out.close();
	        fileOut.close();
		}
		catch(IOException i)
	    {
			i.printStackTrace();
	    }*/
	    
		/*
		Structure e = null;
		try
		{
			FileInputStream fileIn = new FileInputStream("tmp.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        e = (Structure) in.readObject();
	        in.close();
	        fileIn.close();
	    }
		catch(IOException i)
	    {
			i.printStackTrace();
	        return;
	    }
		catch(ClassNotFoundException c)
	    {
	        System.out.println("Employee class not found");
	        c.printStackTrace();
	        return;
	    }
		
		int size = e.getSize();
		int [] totals = new int[size];
		for(int i = 0; i < size; i++)
		{
			totals[i] = 0;
			int paras = e.getOption(i).getSize();
			for(int j = 0; j < paras; j++)
			{
				totals[i] += e.getOption(i).getParam(j).getValue();
			}
		}
		
		System.out.println("Totals: ");
		for(int i = 0; i < size; i++)
		{
			System.out.println(e.getOption(i).getOptionName() + ": " + totals[i]);
		}*/
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		GUI g = new GUI(screenSize);
		g.setTitle("Decision Matrix Analysis V1.0");
		g.setSize(screenSize);
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.setVisible(true);
	}
}
