package br.ufc.quixada.smas.comportamento.iniciante;

import br.ufc.quixada.smas.agentes.AgenteContractNet;
import br.ufc.quixada.smas.agentes.AgenteIniciante;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class BuscarSistemaDeReputacaoBehavior extends Behaviour{
	
	private AgenteIniciante agente;
	private long delay = 9000000;
	
	public BuscarSistemaDeReputacaoBehavior(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		
			block(delay);
			
			if(agente.getPasso() == 1){
			
		
				DFAgentDescription template = new DFAgentDescription();
				
				ServiceDescription sd = new ServiceDescription();
				sd.setType("Reputacao-Agentes");
				sd.setName("Reputacao-Agentes");
				
				template.addServices(sd);
			
				try {
					DFAgentDescription[] result = DFService.search((Agent) agente, template);
					System.out.println(result.length);
					if( result.length > 0 && result[0].getName() != null){
						agente.setSistemaDeReputacaoAID(result[0].getName());
					}
					
				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	@Override
	public boolean done() {
		
		if(agente.getSistemaDeReputacaoAID() != null){
			agente.incrementaPasso();
			return true;
		}
		return false;
	}

}