package br.ufc.quixada.smas.agentes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufc.quixada.smas.comportamento.BuscarAgentesVendedores;
import br.ufc.quixada.smas.comportamento.BuscarSistemaDeReputacaoBehavior;
import br.ufc.quixada.smas.comportamento.ContractNetIniciatorBehavior;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class AgenteIniciante extends AgenteContractNet {
	
	private List<AID> agentesVendedores;
	
	protected void setup(){
		
		agentesVendedores = new ArrayList<AID>();
		addBehaviour(new BuscarSistemaDeReputacaoBehavior(this));
		addBehaviour(new BuscarAgentesVendedores(this));
	
		ACLMessage mensagem = new ACLMessage(ACLMessage.CFP);
		
		Iterator it = agentesVendedores.iterator();
		
		while(it.hasNext()){
			mensagem.addReceiver( (AID) it.next());
		}
		//addBehaviour(new ContractNetIniciatorBehavior(a, cfp));
	}
	
	public void addAgenteVendedor(AID agenteAID){
		this.agentesVendedores.add(agenteAID);
	}
}
