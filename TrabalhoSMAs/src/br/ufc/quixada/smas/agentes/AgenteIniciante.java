package br.ufc.quixada.smas.agentes;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import br.ufc.quixada.smas.comportamento.iniciante.AnalisarPropostas;
import br.ufc.quixada.smas.comportamento.iniciante.BuscarAgentesVendedores;
import br.ufc.quixada.smas.comportamento.iniciante.BuscarSistemaDeReputacaoBehavior;
import br.ufc.quixada.smas.comportamento.iniciante.EnviarMensagemCFP;
import br.ufc.quixada.smas.comportamento.iniciante.PedirReputacaoDosAgentesPropostas;
import br.ufc.quixada.smas.comportamento.iniciante.ReceberMensagemAgenteIniciante;
import br.ufc.quixada.smas.objetos.Cupom;
import br.ufc.quixada.smas.objetos.ListaDeCupons;
import br.ufc.quixada.smas.objetos.MelhorPropostaCupom;
import br.ufc.quixada.smas.objetos.Proposta;
import br.ufc.quixada.smas.objetos.Reputacao;
import br.ufc.quixada.smas.ui.JanelaAgenteIniciante;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class AgenteIniciante extends AgenteContractNet {
	
	private int passo = 0;
	private boolean compraDeAgentesComReputacaoDesconhecida; // TODO: 
	
	private JanelaAgenteIniciante janela;
	
	private List<AID> agentesVendedores;
	private ListaDeCupons cuponsDesejados; // Cupons que ele quer adquirir
	private HashMap<AID, Proposta> propostas;
	private ArrayList<Proposta> propostasRejeitadasPelaReputacao; // TODO : Inicializar
	private HashMap<String,MelhorPropostaCupom> melhoresPropostas; // TODO : Iniciar com null
	// TODO :private List<Proposta> propostasRejetadas;
	private List<Cupom> cuponsNaoEncontrados; //TODO : incializar
	private ArrayList<MelhorPropostaCupom> propostasAceitas; // TODO : iniciar
	private ArrayList<ListaDeCupons> cuponsASeremComprados; // TODO: iniciar
 	
	
	protected void setup(){
		
		criarJanela();
		
		cuponsDesejados = new ListaDeCupons();
		agentesVendedores = new ArrayList<AID>();
		
		
		addBehaviour(new ReceberMensagemAgenteIniciante(this));
		addBehaviour(new BuscarSistemaDeReputacaoBehavior(this));
		addBehaviour(new BuscarAgentesVendedores(this));
		//addBehaviour(new EnviarMensagemCFP(this));
		//addBehaviour(new PedirReputacaoDosAgentesPropostas(this));
		//addBehaviour(new AnalisarPropostas(this));
		
		
		//TODO : Receber resposta das CFPs
		
	}
	
	public void addCupomDesejado(String nome, double valor){
		 this.cuponsDesejados.add(new Cupom(nome,valor));
	}
	
	public int getQuantidadeDeCuponsDesejados(){
		return this.cuponsDesejados.size();
	}
	
	public ListaDeCupons getListaDeCuponsDesejados(){
		return this.cuponsDesejados;
	}
	public ArrayList<Proposta> getPropostasRejeitadasPelaReputacao(){
		return propostasRejeitadasPelaReputacao;
	}
	
	public void addCuponsASeremComprados(ListaDeCupons lista){
		this.cuponsASeremComprados.add(lista);
	}
	
	public ArrayList<ListaDeCupons> getCuponsASeremComprados(){
		return this.cuponsASeremComprados;
	}
	
	public void addPropostaAceita(MelhorPropostaCupom melhorProposta){
		propostasAceitas.add(melhorProposta);
	}
	
	public ArrayList<MelhorPropostaCupom> getPropostasAceitas(){
		return this.propostasAceitas;
	}
	
	public HashMap<String,MelhorPropostaCupom> getHashMapMelhoresPropostas(){
		return this.melhoresPropostas;
	}
	
	public void addPropostaRejeitadaPelaReputacao(Proposta proposta){
		propostasRejeitadasPelaReputacao.add(proposta);
	}
	
	public boolean validarReputacao(Reputacao reputacao){
		return reputacao.getValor() >= 7.00;
		//TODO: melhorar e ver caso em que nao ha reputacao
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
		return this.cuponsDesejados;
	}
	
	public void addProposta(Proposta proposta){
		this.propostas.put(proposta.getSender(), proposta);
	}
	
	public Proposta getProposta(AID agenteAID){
		return propostas.get(agenteAID);
	}
	
	public void updateProposta(Proposta proposta){
		this.propostas.put(proposta.getSender(), proposta);
	}
	
	public Collection<Proposta> getPropostas(){
		return propostas.values();
	}
	
	public Collection<MelhorPropostaCupom> getMelhoresPropostas(){
		return melhoresPropostas.values();
	}
	
	public void addCupomNaoEncontrado(Cupom cupom){
		cuponsNaoEncontrados.add(cupom);
	}
	
	public int getPasso(){
		return passo;
	}
	
	public void incrementaPasso(){
		passo++;
	}
	
	private void criarJanela(){
		this.janela = new JanelaAgenteIniciante(this);
	}
}
