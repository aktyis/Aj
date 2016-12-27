package home;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.Iterator;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.*;

public class commonGUI extends Agent 
{
	private showGui myGui;
	// timing interval to refresh
	final int interval =10000; 
	private Boolean check = true;
	//store all the devices 
	private AID[] allHomogeneousAgents;
	private AID[] allHeterogeneousAgents;

		
	private ServiceDescription sd = new ServiceDescription();
	
	private final String deviceName ;
	private final String deviceType;
	
	public commonGUI(String dN,String dT)
	{
		this.deviceName = dN;
		this.deviceType = dT;
	}
	//Lang specs -- used for dev searching
	private final String Language ="EN-US";
	
	
	protected void setup() 
	{
		System.out.println("Agent --"+getAID().getName()+"-- is ready.");
		
		// Register the book-selling service in the yellow pages
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		sd.setType(deviceType);
		sd.setName(deviceName);
		sd.addLanguages(Language);
		
		
		dfd.addServices(sd);
		try 
		{
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) 
		{
			fe.printStackTrace();
		}
		
		addBehaviour(new TickerBehaviour(this, interval) 
		{
			protected void onTick() 
			{
				//myGui.close();
				//new RequestsServer();
				System.out.println("Trying Find the Agents ");
				
			    SearchConstraints sc = new SearchConstraints();
			    // We want to receive 10 results at most
			    sc.setMaxResults(new Long(100));
				
				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription findSD = new ServiceDescription();
				findSD.addLanguages(Language);
				findSD.setType(deviceType);
				template.addServices(findSD);
				
				try 
				{	
					DFAgentDescription[] result = DFService.search(myAgent, template,sc); 
					System.out.println("searching for Homogeneous Agents:");
					allHomogeneousAgents = new AID[result.length];
					
					//System.out.println("******"+result[1].get +"****");
					for (int i = 0; i < result.length; ++i) 
					{
						allHomogeneousAgents[i] = result[i].getName();
					}
					
					
				}
				catch (FIPAException fe) 
				{
					fe.printStackTrace();
				}
				
				//-------------- all Heterogeneous Agents 
				DFAgentDescription template2 = new DFAgentDescription();
				ServiceDescription findSD2 = new ServiceDescription();
				findSD2.addLanguages(Language);
				template2.addServices(findSD2);
				
				try 
				{
					
					DFAgentDescription[] result = DFService.search(myAgent, template2,sc); 
					System.out.println("searching for Heterogeneous Agents:");
					allHeterogeneousAgents = new AID[result.length];
					
					//System.out.println("******"+result[1].get +"****");
					for (int i = 0; i < result.length; ++i) 
					{
						allHeterogeneousAgents[i] = result[i].getName();						
					}
					
					
				}
				catch (FIPAException fe) 
				{
					fe.printStackTrace();
				}
				
				System.out.println("\n\n");
				for (int i = 0; i < allHeterogeneousAgents.length; ++i) 
				{
					for (int j = 0; j < allHomogeneousAgents.length; ++j) 
					{
						if(allHeterogeneousAgents[i].getName().equals(allHomogeneousAgents[j].getName()))
						{
							allHeterogeneousAgents[i] = new AID();
						}
					}
					System.out.println("Heterogeneous----" + allHeterogeneousAgents[i].getName() + "-----");
				}
				for (int i = 0; i < allHomogeneousAgents.length; ++i) 
				{
					if(allHomogeneousAgents[i].getName().equals(this.getAgent().getName()))
					{
						allHomogeneousAgents[i]=new AID();
					}
					System.out.println("Homogeneous^^^^^" + allHomogeneousAgents[i].getName() + "^^^^^");
				}
				
				
				
				System.out.println("\n\n");
				System.out.println("-------------------------------------------------------------------------------");
				
					myGui = new showGui();
					myGui.showList(allHomogeneousAgents,allHeterogeneousAgents);
					myGui.showaGui(this.getAgent().getName());
					check= false;
			}
		} );

	}

	


}
