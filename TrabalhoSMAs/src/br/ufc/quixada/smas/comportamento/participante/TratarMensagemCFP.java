package br.ufc.quixada.smas.comportamento.participante;

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
	
	private final long delay = 2000;
	private AgenteParticipante agente;
	
	
	public TratarMensagemCFP(AgenteParticipante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		block(delay);
		
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
				
				ACLMessage reposta = cfp.createReply();
				
				if(cuponsEmComum.size() > 0){
					
				}else{
					
				}
				
			} catch (UnreadableException e) {
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
