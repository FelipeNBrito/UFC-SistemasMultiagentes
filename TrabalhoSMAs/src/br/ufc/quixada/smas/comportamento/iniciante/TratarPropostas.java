package br.ufc.quixada.smas.comportamento.iniciante;

import java.util.ArrayList;
import java.util.Iterator;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.ListaDeCupons;
import br.ufc.quixada.smas.objetos.MelhorPropostaCupom;
import jade.core.AID;
import jade.core.behaviours.Behaviour;

public class TratarPropostas extends Behaviour{

	private AgenteIniciante agente;
	private boolean done = false;
	
	
	public TratarPropostas(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		
		if(agente.getPasso() == 6){
		
			Iterator<MelhorPropostaCupom> melhoresPropostas = agente.getMelhoresPropostas().iterator();
			while(melhoresPropostas.hasNext()){
				agente.addPropostaAceita(melhoresPropostas.next());
			}
			
			System.out.println("PASSO 6 AI");
			ArrayList<MelhorPropostaCupom> propostasAceitas =  agente.getPropostasAceitas();
			System.out.println(propostasAceitas.size());
			ArrayList<MelhorPropostaCupom> propostasOrdenadas = ordenarPropostas((ArrayList<MelhorPropostaCupom>)propostasAceitas.clone());
			
			while(propostasOrdenadas.size() > 0){
				
				ListaDeCupons listaDeCupons = new ListaDeCupons();
				MelhorPropostaCupom melhorPropostaCupom = propostasOrdenadas.get(0);
				AID aid = melhorPropostaCupom.getVendedorAID();
				listaDeCupons.setAid(aid);
				listaDeCupons.add(melhorPropostaCupom.getCupom());
				
				
				propostasOrdenadas.remove(melhorPropostaCupom);
				
				while(propostasOrdenadas.size() > 0 && 
						propostasOrdenadas.get(0).getVendedorAID().equals(aid)){
					
					listaDeCupons.add(propostasOrdenadas.get(0).getCupom());
					propostasOrdenadas.remove(0);
				}
				agente.addCuponsASeremComprados(listaDeCupons);
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
	
	private ArrayList<MelhorPropostaCupom> ordenarPropostas(ArrayList<MelhorPropostaCupom> propostasAceitas){
		ArrayList<MelhorPropostaCupom> propostasOrdenadas = new ArrayList<MelhorPropostaCupom>();
		
		int count = propostasAceitas.size();
		AID menorAID;
		
		while(propostasAceitas.size() > 0){
			
			MelhorPropostaCupom melhorProposta = propostasAceitas.get(0);
			menorAID = melhorProposta.getVendedorAID();
			
			Iterator<MelhorPropostaCupom> it = propostasAceitas.iterator();
			
			while(it.hasNext()){
				MelhorPropostaCupom tmp = it.next();
				
				if(/*menorAID != null && */tmp.getVendedorAID().toString().compareTo(menorAID.toString()) < 0){
					melhorProposta = tmp;
					menorAID = tmp.getVendedorAID();
				}
			}
			
			propostasOrdenadas.add(melhorProposta);
			propostasAceitas.remove(melhorProposta);
		}
		
		return propostasOrdenadas;
	}

}
