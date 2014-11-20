package br.ufc.quixada.smas.comportamento.iniciante;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.Cupom;
import br.ufc.quixada.smas.objetos.ListaDeCupons;
import jade.core.behaviours.Behaviour;

public class FinalizarAgente extends Behaviour {
	
	private AgenteIniciante agente;
	private boolean done = false;
	
	public FinalizarAgente(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		
		if(agente.getPasso() == 9 && agente.getCuponsCompradosComSucesso().size() > 1){
			System.out.println("PASSO 9 AI");
			
			for(ListaDeCupons lista : agente.getCuponsCompradosComSucesso()){
				for(Cupom cupom : lista){
					System.out.println("***************************************");
					System.out.println("Comprei o cupom : " + cupom.toString());
					System.out.println("Do agente : " + lista.getAID().getName());
					System.out.println("Pelo valor de : R$" + cupom.getValor());
				}
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

}
