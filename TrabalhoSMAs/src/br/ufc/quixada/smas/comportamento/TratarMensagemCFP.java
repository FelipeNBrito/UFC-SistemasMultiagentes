package br.ufc.quixada.smas.comportamento;

import br.ufc.quixada.smas.agentes.AgenteParticipante;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.MessageTemplate.MatchExpression;

public class TratarMensagemCFP extends Behaviour{
	
	private final long delay = 2000;
	private AgenteParticipante agente;
	private ACLMessage mensagemAtual;
	private Estado_Reputacao estadoAtual;
	
	public enum Estado_Reputacao{
		NAO_VERIFICADA, VERIFICADA_REJEITADA, VERIFICADA_ACEITA, VERIFICADA_DESCONHECIDA;
	}
	
	public TratarMensagemCFP(AgenteParticipante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		block(delay);
		
		mensagemAtual = agente.pegarProximaMensagem();
		estadoAtual = Estado_Reputacao.NAO_VERIFICADA;
		
		if(mensagemAtual != null){
			
			if(estadoAtual.equals(Estado_Reputacao.NAO_VERIFICADA)){
				
				agente.addBehaviour(new CyclicBehaviour(agente) {
					
					@Override
					public void action() {
						
						//MessageTemplate template = new MessageTemplate(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
						//ACLMessage
					}
				});
			}
			
		} else{
			block();
		}
		
	}

	@Override
	public boolean done() {
		return false;
	}

}
