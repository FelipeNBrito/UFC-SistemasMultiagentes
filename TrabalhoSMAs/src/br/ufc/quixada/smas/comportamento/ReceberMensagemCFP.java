package br.ufc.quixada.smas.comportamento;

import br.ufc.quixada.smas.agentes.AgenteParticipante;
import br.ufc.quixada.smas.objetos.Reputacao;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceberMensagemCFP extends Behaviour{

	private AgenteParticipante agente;
	private Reputacao reputacao;
	private Estado_Reputacao estadoAtual = Estado_Reputacao.NAO_VERIFICADA;
	
	public enum Estado_Reputacao{
		NAO_VERIFICADA, VERIFICADA_REJEITADA, VERIFICADA_ACEITA, VERIFICADA_DESCONHECIDA;
	}
	
	public ReceberMensagemCFP(AgenteParticipante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		
		block(500);
		
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.CFP);
		ACLMessage mensagem = agente.receive(template);
		
		if(mensagem != null){
			agente.addMensagem(mensagem);
		} else{
			block();
		}
		
	}

	@Override
	public boolean done() {
		return false;
	}

}
