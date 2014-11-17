package br.ufc.quixada.smas.comportamento.participante;

import br.ufc.quixada.smas.agentes.AgenteParticipante;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceberMensagensAgenteParticipante extends Behaviour{

	final private long delay = 500;
	private AgenteParticipante agente;
	
	public ReceberMensagensAgenteParticipante(AgenteParticipante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		
		block(delay);
		
		ACLMessage mensagem = agente.receive();
		
		if(mensagem != null){
			
			// SE for do tipo CFP
			if(mensagem.equals(ACLMessage.CFP) && 
					mensagem.getProtocol().equals(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET)){
				
				agente.addMensagemCFP(mensagem);
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
