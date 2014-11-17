package br.ufc.quixada.smas.comportamento.iniciante;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.Proposta;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class PedirReputacaoDosAgentesPropostas extends Behaviour{

	final private long delay = 20000;
	private AgenteIniciante agente;
	
	public PedirReputacaoDosAgentesPropostas(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		block(delay);
		
		Collection<Proposta> propostas = agente.getPropostas();
		
		Iterator<Proposta> it =  propostas.iterator();
		
		while(it.hasNext()){
			ACLMessage mensagem = new ACLMessage(ACLMessage.REQUEST);
			mensagem.addReceiver(agente.getSistemaDeReputacaoAID());
			
			try {
				
				mensagem.setContentObject(it.next().getSender());
				agente.send(mensagem);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public boolean done() {
		return true;
	}

}
