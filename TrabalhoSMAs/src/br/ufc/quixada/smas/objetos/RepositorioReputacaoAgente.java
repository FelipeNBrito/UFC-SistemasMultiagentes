package br.ufc.quixada.smas.objetos;

import jade.core.AID;

import java.util.ArrayList;
import java.util.Iterator;

public class RepositorioReputacaoAgente {
	
	public RepositorioReputacaoAgente(AID aid) {
		reputacoes = new ArrayList<Integer>();
		this.agenteID = aid;
	}

	private ArrayList<Integer> reputacoes;
	private AID agenteID;
	
	public int pegarTotalDeTransacoes(){
		return this.reputacoes.size();
	}
	
	public void adicionarReputacao(int num){
		reputacoes.add(num);
	}
	
	public Reputacao pegarReputacao(){
		
		System.out.println(reputacoes.size());
		if(reputacoes.size() < 5){ // Ainda nao tem iteracoes suficientes
			return null;
		}
		
		double  soma = 0.0;
		
		for(Integer reputacao : reputacoes){
			soma += reputacao;
		}
			
		double total = soma/reputacoes.size();
		
		return new Reputacao(this.agenteID, total);
	}
} 
