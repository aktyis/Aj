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

public class op extends Agent 
{
	
	// timing interval to refresh
	final int interval =10000; 
	
	//store all the devices 
	private AID[] allAgents;

		
	private ServiceDescription sd = new ServiceDescription();
	
	private final String deviceName ="Fridger-2568";
	private final String deviceType ="refrigerator";
	
	
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
				//new RequestsServer();
				System.out.println("Trying Find the Agents ");
				
			    SearchConstraints sc = new SearchConstraints();
			    // We want to receive 10 results at most
			    sc.setMaxResults(new Long(100));
				
				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription findSD = new ServiceDescription();
				findSD.addLanguages(Language);
				template.addServices(findSD);
				
				try 
				{
					
					DFAgentDescription[] result = DFService.search(myAgent, template,sc); 
					System.out.println("Found the following seller agents:");
					allAgents = new AID[result.length];
					
					//System.out.println("******"+result[1].get +"****");
					for (int i = 0; i < result.length; ++i) 
					{
						allAgents[i] = result[i].getName();
						System.out.println("Devices " + allAgents[i].getName() + "-----");
					}
					
					
				}
				catch (FIPAException fe) 
				{
					fe.printStackTrace();
				}
				

			}
		} );

	}

	
}
