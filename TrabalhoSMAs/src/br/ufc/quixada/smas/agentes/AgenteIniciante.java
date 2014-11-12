package br.ufc.quixada.smas.agentes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.ufc.quixada.smas.comportamento.BuscarAgentesVendedores;
import br.ufc.quixada.smas.comportamento.BuscarSistemaDeReputacaoBehavior;
import br.ufc.quixada.smas.comportamento.ContractNetIniciatorBehavior;
import br.ufc.quixada.smas.comportamento.EnviarMensagemCFP;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;


public class AgenteIniciante extends AgenteContractNet {
	
	private List<AID> agentesVendedores;
	
	protected void setup(){
		
		agentesVendedores = new ArrayList<AID>();
		addBehaviour(new BuscarSistemaDeReputacaoBehavior(this));
		addBehaviour(new BuscarAgentesVendedores(this));
		addBehaviour(new EnviarMensagemCFP(this));
		
	}
	
	public void addAgenteVendedor(AID agenteAID){
		this.agentesVendedores.add(agenteAID);
	}
	
	public int getQuantidadeDeAgentesVendedores(){
		return agentesVendedores.size();
	}
	
	public Iterator<AID> getAgentesVendedores(){
		return agentesVendedores.iterator();
	}
}
