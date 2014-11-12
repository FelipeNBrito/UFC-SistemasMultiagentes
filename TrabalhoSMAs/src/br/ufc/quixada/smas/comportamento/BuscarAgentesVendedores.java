package br.ufc.quixada.smas.comportamento;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class BuscarAgentesVendedores extends Behaviour{

	final private long delay = 200;
	private AgenteIniciante agente;
	
	public BuscarAgentesVendedores(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		block(delay);
		
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("venda-de-cupons");
		template.addServices(sd);
		
		try {
			DFAgentDescription[] result = DFService.search(agente, template);
			
			for(int i = 0; i < result.length; i++){
				agente.addAgenteVendedor(result[i].getName());
			}
			
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
