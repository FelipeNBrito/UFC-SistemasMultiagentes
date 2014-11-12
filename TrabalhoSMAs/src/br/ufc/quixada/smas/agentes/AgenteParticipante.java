package br.ufc.quixada.smas.agentes;

import br.ufc.quixada.smas.comportamento.BuscarSistemaDeReputacaoBehavior;
import br.ufc.quixada.smas.comportamento.ContractNetIniciatorBehavior;
import jade.core.behaviours.Behaviour;

public class AgenteParticipante extends AgenteContractNet{

	protected void setup(){
		
		addBehaviour(new BuscarSistemaDeReputacaoBehavior(this));
		
		
		//addBehaviour(new ContractNetIniciatorBehavior(this, cfp));
	}
	
}
