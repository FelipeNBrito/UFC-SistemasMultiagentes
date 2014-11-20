package br.ufc.quixada.smas.comportamento.iniciante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.Cupom;
import br.ufc.quixada.smas.objetos.MelhorPropostaCupom;
import br.ufc.quixada.smas.objetos.Proposta;
import jade.core.behaviours.Behaviour;
import jade.domain.introspection.ACLMessage;
import jade.lang.acl.UnreadableException;

public class AnalisarPropostas extends Behaviour{

	private AgenteIniciante agente;
	private HashMap<String,MelhorPropostaCupom> melhoresPropostas;
	private boolean done = false;
	
	public AnalisarPropostas(AgenteIniciante agente) {
		this.agente = agente;
		
	}
	
	@Override
	public void action() {

		if(agente.getPasso() == 5 && agente.getPropostas().size() > 0 && agente.isRecebiReputacao()){
			
			melhoresPropostas = agente.getHashMapMelhoresPropostas();
			Iterator<Cupom> it = agente.getListaDeCuponsDesejados().iterator();

			while(it.hasNext()){
				Cupom tmp = it.next();
				melhoresPropostas.put(tmp.toString(), new MelhorPropostaCupom(tmp));
			}
			
			System.out.println("Passo 5 AI");
			Iterator<Proposta> propostas = agente.getPropostas().iterator();
			
			while(propostas.hasNext()){
				System.out.println("Passo 5 AI WHILE");
				Proposta proposta = propostas.next();
				System.out.println(agente.validarReputacao(proposta.getReputacao()));
				if(!agente.validarReputacao(proposta.getReputacao())){ // se a reputacao nao for valida, ele continua com a proxima iteracao
					continue;
				}
				System.out.println("Passo 5 AI WHILE");
				try {
					Iterator<Cupom> cuponsVendedor = proposta.getListaDeCupons().iterator();
					
					while(cuponsVendedor.hasNext()){
						
						Iterator<Cupom> meusCupons = agente.getListaDeCuponsDesejados().iterator();
						
						Cupom cupomVendedor = cuponsVendedor.next();
						
						while(meusCupons.hasNext()){
							Cupom meuCupom = meusCupons.next();
							
							if(cupomVendedor.toString().equalsIgnoreCase(meuCupom.toString())){ // Se o cupom for o mesmo
								
								MelhorPropostaCupom melhorProposta = melhoresPropostas.get(meuCupom.toString());
								
								if(melhorProposta.getVendedorAID() == null){
									melhorProposta = new MelhorPropostaCupom(cupomVendedor);
									System.out.println("ASASSA" + proposta.getSender());
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
			done = true;
		}
	}

	@Override
	public boolean done() {
		if(done){
			agente.incrementaPasso();
			System.out.println("FIM do PASSO 5 " + agente.getPasso() );
		}
		return done;
	}

}
