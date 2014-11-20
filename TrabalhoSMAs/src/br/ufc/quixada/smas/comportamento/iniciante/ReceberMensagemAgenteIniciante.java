package br.ufc.quixada.smas.comportamento.iniciante;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.Proposta;
import br.ufc.quixada.smas.objetos.Reputacao;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ReceberMensagemAgenteIniciante extends Behaviour{

	private AgenteIniciante agente;
	
	public ReceberMensagemAgenteIniciante(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	
	@Override
	public void action() {
		
		ACLMessage mensagem = agente.receive();
		
		if(mensagem != null){
			
			if(mensagem.getProtocol().equals(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET)){
				
				if(mensagem.getPerformative() == ACLMessage.PROPOSE){
					Proposta proposta = new Proposta(mensagem);
					agente.addProposta(proposta);
				} else if(mensagem.getPerformative() == ACLMessage.INFORM){
					
				}else if(mensagem.getPerformative() == ACLMessage.FAILURE){
					
				}
				
			}else if(mensagem.getPerformative() == ACLMessage.INFORM &&
						mensagem.getSender().equals(agente.getSistemaDeReputacaoAID())){
				
				Reputacao reputacao;
				
				try {
					
					reputacao = (Reputacao) mensagem.getContentObject();
					Proposta proposta = agente.getProposta(reputacao.getAidAgente());
					proposta.setReputacao(reputacao);
					agente.addProposta(proposta);
					
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else{
			block();
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
