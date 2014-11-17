package br.ufc.quixada.smas.objetos;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Proposta {

	private Reputacao reputacao;
	private ACLMessage proposta;
	
	public Proposta(ACLMessage mensagem) {
		this.proposta = mensagem;
		this.reputacao = null;
	}
	
	public void setReputacao(Reputacao reputacao){
		this.reputacao = reputacao;
	}
	
	public Reputacao getReputacao(){
		return reputacao;
	}
	
	public AID getSender(){
		return proposta.getSender();
	}
	
	public ListaDeCupons getListaDeCupons() throws UnreadableException{
			return (ListaDeCupons) proposta.getContentObject();
	}
}
