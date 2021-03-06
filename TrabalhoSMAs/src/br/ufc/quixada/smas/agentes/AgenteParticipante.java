package br.ufc.quixada.smas.agentes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import br.ufc.quixada.smas.comportamento.participante.ReceberMensagensAgenteParticipante;
import br.ufc.quixada.smas.comportamento.participante.TratarMensagemCFP;
import br.ufc.quixada.smas.objetos.Cupom;
import br.ufc.quixada.smas.objetos.ListaDeCupons;
import br.ufc.quixada.smas.objetos.Reputacao;
import br.ufc.quixada.smas.ui.JanelaAgenteParticipante;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class AgenteParticipante extends AgenteContractNet{

	private List<ACLMessage> mensagensCFP;
	private ListaDeCupons cupons;
	private List<ACLMessage> propostas;
	private JFrame janela;
	
	private int passo = 0;
	
	private int probabilidadeDeAcerto = 8;
	
	protected void setup(){
		
		criarJanela();
		
		cupons = new ListaDeCupons();
		mensagensCFP = new ArrayList<ACLMessage>();
		propostas = new ArrayList<ACLMessage>();
		
		// Criando uma entrada no DF 
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		//Criando um servico
		ServiceDescription sd = new ServiceDescription();
		sd.setType("venda-de-cupons");
		sd.setName("nome-do-servico"); //TODO
		
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd); // Registra o servico
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addBehaviour(new ReceberMensagensAgenteParticipante(this));
		addBehaviour(new TratarMensagemCFP(this));
		
	}
	
	protected void takeDown(){
		
		try{
			DFService.deregister(this);
		} catch(FIPAException e){
			e.printStackTrace();
		}
	}

	public void addProposta(ACLMessage mensagem){
		this.addProposta(mensagem);
	}
	
	public Iterator<ACLMessage> getPropostas(){
		return this.propostas.iterator();
	}
	
	public ACLMessage pegarProximaMensagemCFP(){
		if(mensagensCFP.size() > 0){
			ACLMessage mensagem = mensagensCFP.get(0);
			mensagensCFP.remove(mensagem);
			return mensagem;
		}else {
			return null;
		}
	}
	
	public void addMensagemCFP(ACLMessage mensagem){
		this.mensagensCFP.add(mensagem);
	}
	
	public ListaDeCupons getListaDeCupons(){
		return this.cupons;
	}
	
	public void addCupom(String nome, double valor){
		this.cupons.add(new Cupom(nome,valor));
	}
	
	public int getQuantidadeDeCupons(){
		return cupons.size();
	}
	
	public int getProbabilidadeDeAcerto(){
		return this.probabilidadeDeAcerto;
	}
	
	public int getPasso(){
		return passo;
	}
	
	public void incrementaPasso(){
		passo++;
	}
	
	public void criarJanela(){
		this.janela = new JanelaAgenteParticipante(this);
	}
}
