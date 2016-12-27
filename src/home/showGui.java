package home;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import jade.core.AID;

public class showGui extends JFrame implements ActionListener
{
	Timer time;
	public showGui()
	{
		time = new Timer(10000, this);    // Timer in 10 seconds
		time.start();
	}
	private JList<String> HomogeneousList;
	private JList<String> HeterogeneousList;
	 JLabel label1;
	 JLabel label2;
	 
	 int a;
     JPanel jp = new JPanel();
     
	@SuppressWarnings("deprecation")
	public void showList(AID[] allHomogeneousAgents,AID[] allHeterogeneousAgents) 
	{
        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        DefaultListModel<String> listModel2 = new DefaultListModel<>();    
        
        for (int i = 0; i < allHomogeneousAgents.length; ++i) 
		{
        	 listModel.addElement(allHomogeneousAgents[i].getLocalName());
        	 //listModel.addElement(allHomogeneousAgents[i]);
        	 
		}
        
        for (int i = 0; i < allHeterogeneousAgents.length; ++i) 
		{
        	 listModel2.addElement(allHeterogeneousAgents[i].getLocalName());			
		}
   
        //create the list
        //jp.add(label1);
        label1 = new JLabel("Homogenous Devices ---- >");

        HomogeneousList = new JList<>(listModel);
        jp.add(label1);
        jp.add(HomogeneousList);
        

        label2 = new JLabel("Heterogenous Devices ---->");
        HeterogeneousList = new JList<>(listModel2);
        jp.add(label2);
        jp.add(HeterogeneousList);
        jp.show();
        jp.setLayout(new GridLayout());
        
        add(jp);
        
    }
	void paint()
	{
		
	}
	@SuppressWarnings("deprecation")
	public void closeGui()
	{
		//jp.removeAll();
		//jp.hide();
		//this.removeAll();
		//this.revalidate();
		//this.
		this.repaint();
		//SwingUtilities.updateComponentTreeUI(this);
		this.validate();
		//this.revalidate();
		//this.repaint();
		//setVisible(false); //you can't see me!
		//dispose(); //Destroy the JFrame object
	}
	
	public void showaGui( String agentName)
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(agentName);
        //this.setLayout(new FlowLayout());
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setLocation(100, 100);
        this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) 
	{	
	      this.dispose();
				
    }


}
