package br.ufc.quixada.smas.agentes;

import java.util.HashMap;
import java.util.Map;

import br.ufc.quixada.smas.comportamento.EsperarReputacaoCyclicBehavior;
import br.ufc.quixada.smas.objetos.RepositorioReputacaoAgente;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgenteReputacao extends Agent{

	private Map<AID, RepositorioReputacaoAgente> reputacoes;
	
	public RepositorioReputacaoAgente getRepositorioReputacao(AID agenteAid){
		
		return reputacoes.get(agenteAid);
		
	}
	
	protected void setup(){
		
		// mapa com nome do agente e seu historico de reputacao;
		reputacoes = new HashMap<AID, RepositorioReputacaoAgente>();
		
		// Cria uma entrada no DF 
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID()); // Informa o AID desse agente
		
		// Criacao do servico de reputacao
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Reputacao-Agentes");
		sd.setName("Reputacao");
		dfd.addServices(sd); // Adiciona o servico
		
		
		// Registra o agente nas paginas amarelas
		try{
			DFService.register(this, dfd);
		} catch(FIPAException e){
			e.printStackTrace();
		}	
		
		addBehaviour(new EsperarReputacaoCyclicBehavior(this));
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
