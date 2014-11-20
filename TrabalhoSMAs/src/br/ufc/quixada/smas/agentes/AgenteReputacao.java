package br.ufc.quixada.smas.agentes;

import java.util.HashMap;
import java.util.Map;

import br.ufc.quixada.smas.comportamento.agentedereputacao.EsperarReputacaoCyclicBehavior;
import br.ufc.quixada.smas.objetos.RepositorioReputacaoAgente;
import br.ufc.quixada.smas.objetos.Reputacao;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgenteReputacao extends Agent{

	private Map<AID, RepositorioReputacaoAgente> reputacoes;
	
	public AgenteReputacao() {
		reputacoes = new HashMap<AID, RepositorioReputacaoAgente>();
	}
	
	public RepositorioReputacaoAgente getRepositorioReputacao(AID agenteAid){
		RepositorioReputacaoAgente repositorio = reputacoes.get(agenteAid);
		
		if(repositorio == null){
			repositorio = new RepositorioReputacaoAgente(agenteAid);
			reputacoes.put(agenteAid, repositorio);
		}
		return repositorio;
	}
	
	public void addReputacao(Reputacao reputacao){
		RepositorioReputacaoAgente repositorio = reputacoes.get(reputacao.getAidAgente());
		repositorio.adicionarReputacao((int) reputacao.getValor());
		reputacoes.put(reputacao.getAidAgente(), repositorio);
		
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
		sd.setName("Reputacao-Agentes");
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
