package br.ufc.quixada.smas.comportamento;

import java.util.Vector;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

public class ContractNetIniciatorBehavior extends ContractNetInitiator{

	public ContractNetIniciatorBehavior(Agent a, ACLMessage cfp) {
		super(a, cfp);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void handlePropose(ACLMessage propose, Vector acceptances) {
		// TODO Auto-generated method stub
		super.handlePropose(propose, acceptances);
	}

}
