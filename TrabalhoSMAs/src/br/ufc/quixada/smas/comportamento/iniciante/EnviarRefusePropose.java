package br.ufc.quixada.smas.comportamento.iniciante;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.ListaDeCupons;
import br.ufc.quixada.smas.objetos.Proposta;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class EnviarRefusePropose extends Behaviour{

	private AgenteIniciante agente;
	
	public EnviarRefusePropose() {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		// TODO delay e passos
		
		ArrayList<AID> agentesRejeitados = new ArrayList<AID>();
		
		ArrayList<Proposta> propostasRecusadasPelaReputacao = agente.getPropostasRejeitadasPelaReputacao();
		
		for(Proposta proposta : propostasRecusadasPelaReputacao){
			agentesRejeitados.add(proposta.getSender());
		}
		
		Iterator<Proposta> it = agente.getPropostas().iterator();
		ArrayList<ListaDeCupons> cuponsASeremComprados = agente.getCuponsASeremComprados();
		
		while(it.hasNext()){
			AID tmpAID = it.next().getSender();
			boolean flag = false;
			
			for(ListaDeCupons lista : cuponsASeremComprados){
				if(lista.getAID().equals(tmpAID)){
					flag = true;
					break;
				}
			}
			if(!flag){
				agentesRejeitados.add(tmpAID);
			}
		}
		
		ACLMessage mensagem = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
		mensagem.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
		
		for(AID aid : agentesRejeitados){
			mensagem.addReceiver(aid);
		}
		
		agente.send(mensagem);
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
