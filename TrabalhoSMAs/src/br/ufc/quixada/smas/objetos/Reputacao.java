package br.ufc.quixada.smas.objetos;

import java.io.Serializable;

import jade.core.AID;

public class Reputacao implements Serializable{

	private AID agenteId;
	private double valor;
	
	public Reputacao(AID agenteId, double valor) {
		this.agenteId = agenteId;
		this.valor = valor;
	}
	
	public AID getAidAgente(){
		return agenteId;
	}
	
	public double getValor(){
		return valor;
	}
}