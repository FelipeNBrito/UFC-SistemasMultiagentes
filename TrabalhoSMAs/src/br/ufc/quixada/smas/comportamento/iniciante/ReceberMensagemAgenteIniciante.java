package br.ufc.quixada.smas.comportamento.iniciante;

import java.io.IOException;
import java.util.Random;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.ListaDeCupons;
import br.ufc.quixada.smas.objetos.Proposta;
import br.ufc.quixada.smas.objetos.Reputacao;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ReceberMensagemAgenteIniciante extends Behaviour{

	private AgenteIniciante agente;
	
	public ReceberMensagemAgenteIniciante(AgenteIniciante agente) {
		this.agente = agente;
	}
	
	
	@Override
	public void action() {
		
		ACLMessage mensagem = agente.receive();
		
		if(mensagem != null){
			
			
			
			if(mensagem.getProtocol() != null && mensagem.getProtocol().equals(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET)){
				
				System.out.println("Recebi uma mensagem " +  mensagem.getProtocol());
				
				if(mensagem.getPerformative() == ACLMessage.PROPOSE){
					System.out.println("Recebi uma mensagem " +  mensagem.getPerformative());
					Proposta proposta = new Proposta(mensagem);
					agente.addProposta(proposta);
				} else if(mensagem.getPerformative() == ACLMessage.INFORM){
					try {
						agente.addCupomCompradoComSucesso((ListaDeCupons) mensagem.getContentObject());
						Random random = new Random();
						Reputacao reputacao = new Reputacao(mensagem.getSender(),random.nextInt(3)+ 8);
						ACLMessage salvarReputacao = new ACLMessage(ACLMessage.INFORM);
						salvarReputacao.setContentObject(reputacao);
						salvarReputacao.addReceiver(agente.getSistemaDeReputacaoAID());
						agente.send(salvarReputacao);
					} catch (UnreadableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(mensagem.getPerformative() == ACLMessage.FAILURE){
					
					Reputacao reputacao = new Reputacao(mensagem.getSender(),0);
					ACLMessage salvarReputacao = new ACLMessage(ACLMessage.INFORM);
					try {
						salvarReputacao.setContentObject(reputacao);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					salvarReputacao.addReceiver(agente.getSistemaDeReputacaoAID());
					agente.send(salvarReputacao);
				}
				
			}else if(mensagem.getPerformative() == ACLMessage.INFORM &&
						mensagem.getSender().equals(agente.getSistemaDeReputacaoAID())){
				
				Reputacao reputacao;
				
				try {
					
					System.out.println("Recebi uma Reputacao, vou atualizar");
					agente.incrementaReputacoesRecebidas();
					reputacao = (Reputacao) mensagem.getContentObject();
					Proposta proposta = agente.getProposta(reputacao.getAidAgente());
					proposta.setReputacao(reputacao);
					agente.addProposta(proposta);
					
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else{
			block();
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
