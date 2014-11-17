package br.ufc.quixada.smas.agentes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufc.quixada.smas.comportamento.iniciante.BuscarAgentesVendedores;
import br.ufc.quixada.smas.comportamento.iniciante.BuscarSistemaDeReputacaoBehavior;
import br.ufc.quixada.smas.comportamento.iniciante.EnviarMensagemCFP;
import br.ufc.quixada.smas.objetos.ListaDeCupons;
import jade.core.AID;

public class AgenteIniciante extends AgenteContractNet {
	
	private List<AID> agentesVendedores;
	private ListaDeCupons cupons;
	
	protected void setup(){
		
		agentesVendedores = new ArrayList<AID>();
		addBehaviour(new BuscarSistemaDeReputacaoBehavior(this));
		addBehaviour(new BuscarAgentesVendedores(this));
		addBehaviour(new EnviarMensagemCFP(this));
		
		//TODO : Receber resposta das CFPs
		
	}
	
	public void addAgenteVendedor(AID agenteAID){
		this.agentesVendedores.add(agenteAID);
	}
	
	public int getQuantidadeDeAgentesVendedores(){
		return agentesVendedores.size();
	}
	
	public Iterator<AID> getAgentesVendedores(){
		return agentesVendedores.iterator();
	}
	
	public ListaDeCupons getListaDeCupons(){
		return this.cupons;
	}
}
