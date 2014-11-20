package br.ufc.quixada.smas.objetos;

import jade.util.leap.Serializable;

import java.util.Date;

public class Cupom implements Serializable{

	private String nome;
	private double valor;
	
	public Cupom(String nome, double valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
	
	public double getValor(){
		return this.valor;
	}
	
}
