package br.ufc.quixada.smas.comportamento.agentedereputacao;

import java.io.IOException;

import br.ufc.quixada.smas.agentes.AgenteReputacao;
import br.ufc.quixada.smas.objetos.RepositorioReputacaoAgente;
import br.ufc.quixada.smas.objetos.Reputacao;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class EsperarReputacaoCyclicBehavior extends CyclicBehaviour {

	private AgenteReputacao agenteReputacao;
	
	public EsperarReputacaoCyclicBehavior(AgenteReputacao agente) {
		this.agenteReputacao = agente;
	}
	@Override
	public void action() {
		
		//System.out.println("Executando comportamento esperarReputacao");
		
		ACLMessage mensagem = this.agenteReputacao.receive();
		
		if(mensagem != null){
			
			System.out.println("Recebi Mensagem");
			ACLMessage mensagemDeRetorno = mensagem.createReply();
			AID agenteEmissorAid = mensagem.getSender();
			
			if(mensagem.getPerformative() == ACLMessage.INFORM){ // O agente esta informando uma reputacao
			
				try {
					System.out.println("Enviando reputacao");
					Reputacao reputacao = (Reputacao) mensagem.getContentObject();
					
					RepositorioReputacaoAgente repositorioReputacaoAgente = this.agenteReputacao.getRepositorioReputacao(reputacao.getAidAgente());

					reputacao = repositorioReputacaoAgente.pegarReputacao();
					
					mensagemDeRetorno.setContentObject(reputacao);
					
					this.agenteReputacao.send(mensagemDeRetorno);
					
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(mensagem.getPerformative() == ACLMessage.REQUEST){
				
				try {
					
					
					AID agenteAid = (AID) mensagem.getContentObject();
					RepositorioReputacaoAgente repositorioReputacao = this.agenteReputacao.getRepositorioReputacao(agenteAid);
					
					Reputacao reputacao = repositorioReputacao.pegarReputacao();
					
					if(reputacao == null){
						reputacao = new Reputacao(agenteAid, -1);
					}
					
					mensagemDeRetorno.setContentObject(reputacao);
					mensagemDeRetorno.setPerformative(ACLMessage.INFORM);
					
					this.agenteReputacao.send(mensagemDeRetorno);
				
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} else{
			block();
		}
	}
}
