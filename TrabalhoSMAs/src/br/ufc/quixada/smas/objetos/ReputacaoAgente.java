package br.ufc.quixada.smas.objetos;

import java.util.ArrayList;
import java.util.Iterator;

public class ReputacaoAgente {

	private ArrayList<Integer> reputacoes;
	
	public int pegarTotalDeTransacoes(){
		return this.reputacoes.size();
	}
	
	public void adicionarReputacao(int num){
		reputacoes.add(num);
	}
	
	public double pegarReputacao(){
		double  soma = 0.0;
		
		for(Integer reputacao : reputacoes){
			soma += reputacao;
		}
			
		return soma/reputacoes.size();
	}
} 
