package br.ufc.quixada.smas.agentes;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.smas.comportamento.BuscarSistemaDeReputacaoBehavior;
import br.ufc.quixada.smas.comportamento.ReceberMensagemCFP;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class AgenteParticipante extends AgenteContractNet{

	private List<ACLMessage> mensagens;
	private double MIN_REPUTACAO_ACEITACAO = 5.7;
	
	protected void setup(){
		
		mensagens = new ArrayList<ACLMessage>();
		
		// Criando uma entrada no DF 
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		//Criando um servico
		ServiceDescription sd = new ServiceDescription();
		sd.setType("venda-de-cupons");
		sd.setName("nome-do-servico"); //TODO
		
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd); // Registra o servico
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addBehaviour(new BuscarSistemaDeReputacaoBehavior(this));
		addBehaviour(new ReceberMensagemCFP(this));
		
	}
	
	protected void takeDown(){
		
		try{
			DFService.deregister(this);
		} catch(FIPAException e){
			e.printStackTrace();
		}
	}
	
	public void addMensagem(ACLMessage mensagem){
		this.mensagens.add(mensagem);
	}
	
	public void removerMensagem(ACLMessage mensagem){
		this.mensagens.remove(mensagem);
	}
	
	public ACLMessage pegarProximaMensagem(){
		if(mensagens.size() > 0){
			return mensagens.get(0);
		}else{
			return null;
		}
	}
	
}
