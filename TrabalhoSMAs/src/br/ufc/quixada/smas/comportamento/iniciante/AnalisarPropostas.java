package br.ufc.quixada.smas.comportamento.iniciante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.Cupom;
import br.ufc.quixada.smas.objetos.MelhorProposta;
import br.ufc.quixada.smas.objetos.Proposta;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.UnreadableException;

public class AnalisarPropostas extends Behaviour{

	private AgenteIniciante agente;
	final private long delay = 30000;
	private HashMap<String,MelhorProposta> melhoresPropostas;
	
	public AnalisarPropostas(AgenteIniciante agente) {
		this.agente = agente;
		melhoresPropostas = agente.getMelhoresPropostas();
		Iterator<Cupom> it = agente.getListaDeCupons().iterator();

		while(it.hasNext()){
			Cupom tmp = it.next();
			melhoresPropostas.put(tmp.toString(), new MelhorProposta(it.next()));
		}
	}
	
	@Override
	public void action() {
		block(delay);
		
		Iterator<Proposta> propostas = agente.getPropostas().iterator();
		
		while(propostas.hasNext()){
			
			Proposta proposta = propostas.next();
			
			if(!agente.validarReputacao(proposta.getReputacao())){ // se a reputacao nao for valida, ele continua com a proxima iteracao
				continue;
			}
			
			try {
				Iterator<Cupom> cuponsVendedor = proposta.getListaDeCupons().iterator();
				
				while(cuponsVendedor.hasNext()){
					
					Iterator<Cupom> meusCupons = agente.getListaDeCupons().iterator();
					
					Cupom cupomVendedor = cuponsVendedor.next();
					
					while(meusCupons.hasNext()){
						Cupom meuCupom = meusCupons.next();
						
						if(cupomVendedor.toString().equalsIgnoreCase(meuCupom.toString())){ // Se o cupom for o mesmo
							
							MelhorProposta melhorProposta = melhoresPropostas.get(meuCupom.toString());
							
							if(melhorProposta.getVendedorAID() == null){
								melhorProposta = new MelhorProposta(cupomVendedor);
								melhorProposta.setVendedorAID(proposta.getSender());
								
								melhoresPropostas.put(cupomVendedor.toString(), melhorProposta);
							
							}else if(cupomVendedor.getValor() < melhorProposta.getValor()){
								melhorProposta.setVendedorAID(proposta.getSender());
								melhorProposta.setCupom(cupomVendedor);
								
								melhoresPropostas.put(cupomVendedor.toString(), melhorProposta);
							}
						}
					}
				}
			} catch (UnreadableException e) {
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
