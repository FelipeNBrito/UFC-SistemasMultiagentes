package br.ufc.quixada.smas.comportamento.iniciante;

import java.io.IOException;
import java.util.Iterator;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.ListaDeCupons;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class EnviarAcceptPropose extends Behaviour {

	private AgenteIniciante agente;
	private boolean done = false;
	
	public EnviarAcceptPropose(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {

		if(agente.getPasso() == 7){
			System.out.println("Passo 7 AI - " );
			Iterator<ListaDeCupons> it = agente.getCuponsASeremComprados().iterator();
			
			while(it.hasNext()){
				ListaDeCupons lista = it.next();
				
				ACLMessage mensagem = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				mensagem.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
				mensagem.addReceiver(lista.getAID());
				try {
					mensagem.setContentObject(lista);
					agente.send(mensagem);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			done = true;
		}
	}

	@Override
	public boolean done() {
		if(done)
			agente.incrementaPasso();
		return done;
	}

}
