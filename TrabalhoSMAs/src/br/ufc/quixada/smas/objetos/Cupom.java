package br.ufc.quixada.smas.objetos;

import jade.util.leap.Serializable;

import java.util.Date;

public class Cupom implements Serializable{

	private String Nome;
	private double valor;
	private Date horario;
	
	@Override
	public String toString() {
		return this.Nome;
	}
	
	public double getValor(){
		return this.valor;
	}
	
}
