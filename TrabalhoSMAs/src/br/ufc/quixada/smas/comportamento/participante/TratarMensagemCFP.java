package br.ufc.quixada.smas.comportamento.participante;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.swing.tree.AbstractLayoutCache;

import br.ufc.quixada.smas.agentes.AgenteParticipante;
import br.ufc.quixada.smas.objetos.Cupom;
import br.ufc.quixada.smas.objetos.ListaDeCupons;
import br.ufc.quixada.smas.objetos.Reputacao;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class TratarMensagemCFP extends Behaviour{
	
	private AgenteParticipante agente;
	
	
	public TratarMensagemCFP(AgenteParticipante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		
		
		ACLMessage cfp = agente.pegarProximaMensagemCFP();
		ListaDeCupons cuponsEmComum = new ListaDeCupons();
		
		if(cfp != null){
			
			ListaDeCupons cuponsComprador;
			
			try {
				cuponsComprador = (ListaDeCupons) cfp.getContentObject();
				
				for(Cupom cupomComprador : cuponsComprador){
					for(Cupom meuCupons : agente.getListaDeCupons()){
						if(cupomComprador.toString().equalsIgnoreCase(meuCupons.toString())){
							cuponsEmComum.add(meuCupons);
						}
					}
				}
				
				ACLMessage resposta = cfp.createReply();
				
				if(cuponsEmComum.size() > 0){
					System.out.println("Temos Cupons em comum");
					resposta.setPerformative(ACLMessage.PROPOSE);
					resposta.setContentObject(cuponsEmComum);
				}else{
					System.out.println("NAOOOOO Temos Cupons em comum");
					resposta.setPerformative(ACLMessage.REFUSE);
				}
				
				agente.send(resposta);
				
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
	}

	@Override
	public boolean done() {
		return false;
	}

}
