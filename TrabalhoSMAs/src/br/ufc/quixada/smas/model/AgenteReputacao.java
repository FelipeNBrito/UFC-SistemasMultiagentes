package br.ufc.quixada.smas.model;

import java.util.HashMap;
import java.util.Map;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgenteReputacao extends Agent{

	private Map<String, ReputacaoAgente> reputacoes;
	
	
	protected void setup(){
		
		// mapa com nome do agente e seu historico de reputacao;
		reputacoes = new HashMap<String, ReputacaoAgente>();
		
		// Cria uma entrada no DF 
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID()); // Informa o AID desse agente
		
		// Criacao do servico de reputacao
		ServiceDescription sd = new ServiceDescription();
		sd.setType("ReputacaoAgentes");
		sd.setName("Reputacao");
		dfd.addServices(sd); // Adiciona o servico
		
		
		// Registra o agente nas paginas amarelas
		try{
			DFService.register(this, dfd);
		} catch(FIPAException e){
			e.printStackTrace();
		}	
	}
	
	protected void takeDown(){
		
		//Remove o agente das paginas amarelas quando o agente encerra
		try{
			DFService.deregister(this);
		} catch(FIPAException e){
			e.printStackTrace();
		}
	}
	
	
	
}
