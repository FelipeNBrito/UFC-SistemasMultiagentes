package br.ufc.quixada.smas.comportamento.iniciante;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import jade.core.behaviours.Behaviour;

public class ComportamentoAddComportamentosIniciante extends Behaviour{

	private AgenteIniciante agente;
	
	public ComportamentoAddComportamentosIniciante(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	@Override
	public void action() {
		
		int passo = agente.getPasso();
		
		
		
		if(passo == 1){
			System.out.println(passo);
			agente.addBehaviour(new BuscarSistemaDeReputacaoBehavior(agente));
		}else if(passo == 2){
			System.out.println(passo);
			agente.addBehaviour(new BuscarAgentesVendedores(agente));
		}else if(passo == 3){
			System.out.println(passo);
			agente.addBehaviour(new EnviarMensagemCFP(agente));
		}else if(passo == 4 && agente.getPropostas().size() > 0){
			System.out.println(passo);	
			agente.addBehaviour(new PedirReputacaoDosAgentesPropostas(agente));
		}
	}

	@Override
	public boolean done() {
		return false;
	}

}
