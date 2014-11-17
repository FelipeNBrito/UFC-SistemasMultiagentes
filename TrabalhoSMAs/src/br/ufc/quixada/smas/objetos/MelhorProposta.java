package br.ufc.quixada.smas.objetos;

import jade.core.AID;

public class MelhorProposta {

	private Cupom cupom;
	private AID vendedorAID;
	
	public MelhorProposta(Cupom cupom) {
		this.cupom = cupom;
		this.vendedorAID = null;
	}
	
	public void setVendedorAID(AID vendedorAID){
		this.vendedorAID = vendedorAID;
	}
	
	public AID getVendedorAID(){
		return vendedorAID;
	}
	
	public void setCupom(Cupom cupom){
		this.cupom = cupom;
	}
	
	public double getValor(){
		return this.cupom.getValor();
	}
}
