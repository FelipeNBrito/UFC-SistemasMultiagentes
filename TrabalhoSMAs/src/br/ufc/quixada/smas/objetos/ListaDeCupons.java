package br.ufc.quixada.smas.objetos;

import jade.core.AID;
import jade.util.leap.Serializable;

import java.util.ArrayList;

public class ListaDeCupons extends ArrayList<Cupom> implements Serializable{

	private AID aidDono;
	
	public void setAid(AID aid){
		this.aidDono = aid;
	}
	
	public AID getAID(){
		return aidDono;
	}
}
