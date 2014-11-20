package br.ufc.quixada.smas.comportamento.iniciante;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.Proposta;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class PedirReputacaoDosAgentesPropostas extends Behaviour{

	private AgenteIniciante agente;
	private boolean done = false;
	
	public PedirReputacaoDosAgentesPropostas(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {

		
		if(agente.getPasso() == 4){
		
			Iterator<AID> itTMP = agente.getAgentesVendedores();
			int cont = 0;
			while(itTMP.hasNext()){
				itTMP.next();
				cont++;
			}
			
			if(agente.getPropostas().size() > 0 && agente.getPropostas().size() == cont){
			
			
				System.out.println("Passo 4");
				
				Collection<Proposta> propostas = agente.getPropostas();
				
				System.out.println(propostas.size());
				Iterator<Proposta> it =  propostas.iterator();
				
				
				while(it.hasNext()){
					ACLMessage mensagem = new ACLMessage(ACLMessage.REQUEST);
					mensagem.addReceiver(agente.getSistemaDeReputacaoAID());
					
					try {
						Proposta proposta = it.next();
						System.out.println("Vou pedir a reputacao do " + proposta.getSender().getLocalName());
						mensagem.setContentObject(proposta.getSender());
						agente.send(mensagem);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Passo 4");
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
