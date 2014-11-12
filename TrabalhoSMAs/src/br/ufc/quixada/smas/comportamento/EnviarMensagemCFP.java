package br.ufc.quixada.smas.comportamento;

import java.util.Date;
import java.util.Iterator;

import br.ufc.quixada.smas.agentes.AgenteContractNet;
import br.ufc.quixada.smas.agentes.AgenteIniciante;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class EnviarMensagemCFP extends Behaviour{

	private long delay = 5000;
	private boolean done;
	private AgenteIniciante agente;
	
	public EnviarMensagemCFP(AgenteIniciante agente) {
		this.agente = agente;
		done = false;
	}
	
	@Override
	public void action() {
		
		block(delay);
		
		if(agente.getQuantidadeDeAgentesVendedores() > 0){
			
			ACLMessage mensagem = new ACLMessage(ACLMessage.CFP);
			mensagem.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
			mensagem.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
			mensagem.setContent("conteudo");
			
			Iterator it = agente.getAgentesVendedores();
			
			while(it.hasNext()){
				mensagem.addReceiver( (AID) it.next());
			}
			
			agente.addBehaviour(new ContractNetIniciatorBehavior(agente,mensagem));
			
			done = true;
			
		}
	}

	@Override
	public boolean done() {
		return done;
	}

}
