package br.ufc.quixada.smas.comportamento.iniciante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.MelhorPropostaCupom;
import br.ufc.quixada.smas.objetos.Proposta;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.introspection.ACLMessage;

public class SepararCuponsNaoEncontrados extends Behaviour{

	private AgenteIniciante agente;
	private List<AID> agentesQueNaoPossuemMelhorProposta;
	
	public SepararCuponsNaoEncontrados(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
	
		//TODO : DELAY AND STEP
		
		Iterator<MelhorPropostaCupom> melhoresPropostas = agente.getMelhoresPropostas().iterator();
		
		while(melhoresPropostas.hasNext()){
			MelhorPropostaCupom melhorProposta = melhoresPropostas.next();
			
			// Caso nao haja proposta para o cupom solicitado
			if(melhorProposta.getVendedorAID().equals(null)){
				agente.addCupomNaoEncontrado(melhorProposta.getCupom());
			} else{
				agente.addPropostaAceita(melhorProposta);			}
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
