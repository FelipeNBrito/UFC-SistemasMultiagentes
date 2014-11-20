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

	final private long delay = 500;
	private AgenteIniciante agente;
	
	public BuscarAgentesVendedores(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		block(delay);
		
		if(agente.getPasso() == 2){
		
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("venda-de-cupons");
			template.addServices(sd);
			
			try {
				DFAgentDescription[] result = DFService.search(agente, template);
				
				for(int i = 0; i < result.length; i++){
					//System.out.println(result[i].getName());
					agente.addAgenteVendedor(result[i].getName());
				}
				
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean done() {
		agente.incrementaPasso();
		return true;
	}

}