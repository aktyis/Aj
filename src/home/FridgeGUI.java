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

public class FridgeGUI extends commonGUI 
{

	private final static String deName ="Fridge-5656";
	private final static String deType ="refrigerator";
	
	public FridgeGUI() 
	{
		super(deName, deType);
		// TODO Auto-generated constructor stub
	}
	
	


}
