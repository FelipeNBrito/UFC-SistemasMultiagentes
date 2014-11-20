package br.ufc.quixada.smas.comportamento.iniciante;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class EnviarMensagemCFP extends Behaviour{

	private boolean done;
	private AgenteIniciante agente;
	
	public EnviarMensagemCFP(AgenteIniciante agente) {
		this.agente = agente;
		done = false;
	}
	
	@Override
	public void action() {
		
		if(agente.getPasso() == 3){
			if(agente.getQuantidadeDeAgentesVendedores() > 0){
				
				System.out.println("Passo 3 AI");
				
				ACLMessage mensagem = new ACLMessage(ACLMessage.CFP);
				mensagem.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
				mensagem.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
				
				try {
					mensagem.setContentObject(agente.getListaDeCupons());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Iterator it = agente.getAgentesVendedores();
				
				while(it.hasNext()){
					AID tmp = (AID) it.next();
					System.out.println("Vou enviar mensagem ao: " + tmp.getLocalName());
					mensagem.addReceiver( tmp);
				}
				
				agente.send(mensagem); // Envia CFP para todos os agentes que fazem aquele servico
				done = true;
			}
		}
	}

	@Override
	public boolean done() {
		if(done)
			agente.incrementaPasso();
		return done;
	}

}
