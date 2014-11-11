package br.ufc.quixada.smas.comportamento;

import br.ufc.quixada.smas.agentes.AgenteReputacao;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class EsperarReputacaoCyclicBehavior extends CyclicBehaviour {

	private AgenteReputacao agenteReputacao;
	
	public EsperarReputacaoCyclicBehavior(AgenteReputacao agente) {
		this.agenteReputacao = agente;
	}
	@Override
	public void action() {
		
		ACLMessage mensagem = this.agenteReputacao.receive();
		
		if(mensagem != null){
			
			//TODO : Receber mensagem
			
			ACLMessage mensagemDeRetorno = mensagem.createReply();
			
		} else{
			block();
		}
		
	}

	
}
