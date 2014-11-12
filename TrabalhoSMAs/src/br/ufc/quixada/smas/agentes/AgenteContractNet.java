package br.ufc.quixada.smas.agentes;

import jade.core.AID;
import jade.core.Agent;

public abstract class AgenteContractNet extends Agent{
	
	private AID sistemaDeReputacaoAID;
	
	public AID getSistemaDeReputacaoAID(){
		return this.sistemaDeReputacaoAID;
	}
	
	public void setSistemaDeReputacaoAID(AID aid){
		this.sistemaDeReputacaoAID = aid;
	}
}
