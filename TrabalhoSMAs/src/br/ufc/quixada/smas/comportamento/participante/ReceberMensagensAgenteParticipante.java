package br.ufc.quixada.smas.comportamento.participante;

import java.io.IOException;
import java.util.Random;

import br.ufc.quixada.smas.agentes.AgenteParticipante;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

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
			System.out.println("Recebi mensagem AP");
			// Se o protocolo for FIPA CONSTRACT NET
			if(mensagem.getProtocol().equals(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET)){
				// SE for do tipo CFP
				if(mensagem.getPerformative() == ACLMessage.CFP ){
					
					agente.addMensagemCFP(mensagem);
				}else if(mensagem.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
					
					System.out.println("ACLMessage.ACCEPT_PROPOSAL");
					Random rand = new Random();
					ACLMessage resposta = mensagem.createReply();
					resposta.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
					
					if(/*rand.nextInt(9) >= agente.getProbabilidadeDeAcerto()*/true){
						resposta.setPerformative(ACLMessage.INFORM);
						
						try {
							resposta.setContentObject(mensagem.getContentObject());
							agente.send(resposta);
						} catch (IOException | UnreadableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else{
						resposta.setPerformative(ACLMessage.FAILURE);
						agente.send(resposta);
					}
				}else if(mensagem.getPerformative() == ACLMessage.REJECT_PROPOSAL){
					//TODO :
				}
			} else{
				block();
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
