package br.ufc.quixada.smas.comportamento.iniciante;

import br.ufc.quixada.smas.agentes.AgenteContractNet;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class BuscarSistemaDeReputacaoBehavior extends Behaviour{
	
	private AgenteContractNet agente;
	private long delay;
	
	public BuscarSistemaDeReputacaoBehavior(AgenteContractNet agente) {
		this.agente = agente;
		this.delay = 200;
	}
	
	@Override
	public void action() {
			block(delay);
			
			DFAgentDescription template = new DFAgentDescription();
			
			ServiceDescription sd = new ServiceDescription();
			sd.setType("Reputacao-Agentes");
		
			try {
				DFAgentDescription[] result = DFService.search((Agent) agente, template);
				
				if( result.length > 0 && result[0].getName() != null){
					agente.setSistemaDeReputacaoAID(result[0].getName());
				}
				
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public boolean done() {
		return agente.getSistemaDeReputacaoAID() != null;
	}

}
