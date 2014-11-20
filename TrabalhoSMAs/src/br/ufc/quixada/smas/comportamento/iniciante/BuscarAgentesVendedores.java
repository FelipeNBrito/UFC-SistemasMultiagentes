package br.ufc.quixada.smas.comportamento.iniciante;

import java.util.Iterator;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class BuscarAgentesVendedores extends Behaviour{

	private AgenteIniciante agente;
	private boolean done = false;
	public BuscarAgentesVendedores(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		if(agente.getPasso() == 2){
			System.out.println("Passo 2 AI");
			
			//agente.iniciarMapaMelhorProposta();
		
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("venda-de-cupons");
			template.addServices(sd);
			
			try {
				DFAgentDescription[] result = DFService.search(agente, template);
				
				for(int i = 0; i < result.length; i++){
					System.out.println("Vendedor: " + result[i].getName());
					agente.addAgenteVendedor(result[i].getName());
				}
				
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			done = true;
		}
		
	}

	@Override
	public boolean done() {
		if(done){
			agente.incrementaPasso();
		}
		return done;
	}

}