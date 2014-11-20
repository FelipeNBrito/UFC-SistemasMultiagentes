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
import br.ufc.quixada.smas.comportamento.iniciante.EnviarAcceptPropose;
import br.ufc.quixada.smas.comportamento.iniciante.EnviarMensagemCFP;
import br.ufc.quixada.smas.comportamento.iniciante.EnviarRefusePropose;
import br.ufc.quixada.smas.comportamento.iniciante.FinalizarAgente;
import br.ufc.quixada.smas.comportamento.iniciante.PedirReputacaoDosAgentesPropostas;
import br.ufc.quixada.smas.comportamento.iniciante.ReceberMensagemAgenteIniciante;
import br.ufc.quixada.smas.comportamento.iniciante.TratarPropostas;
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
	private boolean compraDeAgentesComReputacaoDesconhecida = true; // TODO: 
	private boolean recebiReputacoes = false;
	private int qtdReputacoesRecebidas = 0;
	
	private JanelaAgenteIniciante janela;
	
	private ArrayList<ListaDeCupons> cuponsCompradosComSucesso;
	
	private List<AID> agentesVendedores;
	private ListaDeCupons cuponsDesejados; // Cupons que ele quer adquirir
	private HashMap<AID, Proposta> propostas;
	private ArrayList<Proposta> propostasRejeitadasPelaReputacao; 
	private HashMap<String,MelhorPropostaCupom> melhoresPropostas;
	// TODO :private List<Proposta> propostasRejetadas;
	private List<Cupom> cuponsNaoEncontrados;
	private ArrayList<MelhorPropostaCupom> propostasAceitas; 
	private ArrayList<ListaDeCupons> cuponsASeremComprados; 
 	
	
	protected void setup(){
		
		criarJanela();
		
		cuponsNaoEncontrados = new ArrayList<Cupom>();
		cuponsASeremComprados = new ArrayList<ListaDeCupons>();
		propostasAceitas = new ArrayList<MelhorPropostaCupom>();
		melhoresPropostas = new HashMap<String, MelhorPropostaCupom>();
		propostasRejeitadasPelaReputacao = new ArrayList<Proposta>();
		cuponsDesejados = new ListaDeCupons();
		agentesVendedores = new ArrayList<AID>();
		propostas = new HashMap<AID, Proposta>();
		cuponsCompradosComSucesso = new ArrayList<ListaDeCupons>();
		
		
		addBehaviour(new ReceberMensagemAgenteIniciante(this));
		addBehaviour(new BuscarSistemaDeReputacaoBehavior(this));
		addBehaviour(new BuscarAgentesVendedores(this));
		addBehaviour(new EnviarMensagemCFP(this));
		addBehaviour(new PedirReputacaoDosAgentesPropostas(this));
		addBehaviour(new AnalisarPropostas(this));
		addBehaviour(new TratarPropostas(this));
		addBehaviour(new EnviarAcceptPropose(this));
		addBehaviour(new EnviarRefusePropose(this));
		//addBehaviour(new FinalizarAgente(this));
		
	}
	
	public int getQtdReputacoesRecebidas(){
		return qtdReputacoesRecebidas;
	}
	
	public void incrementaReputacoesRecebidas(){
		qtdReputacoesRecebidas++;
	}
	
	public void addCupomCompradoComSucesso(ListaDeCupons lista){
		this.cuponsCompradosComSucesso.add(lista);
	}
	
	public ArrayList<ListaDeCupons> getCuponsCompradosComSucesso(){
		return this.cuponsCompradosComSucesso;
	}
	
	public void addCupomDesejado(String nome, double valor){
		 this.cuponsDesejados.add(new Cupom(nome,valor));
	}
	
	public boolean isRecebiReputacao(){
		return this.recebiReputacoes;
	}
	
	public void addCuponsASeremComprados(Cupom cupom){
		
	}
	
	public void setRecebiReputacao(boolean valor){
		this.recebiReputacoes = valor;
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
		if(reputacao.getValor() == -1){
			return this.compraDeAgentesComReputacaoDesconhecida;
		}
		return reputacao.getValor() >= 7.00;
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
	
	public void verResultados(){
		//ArrayList<Cupom> desejados = (ArrayList<Cupom>) this.getListaDeCuponsDesejados().clone();
		
		System.out.println("**********************************************************************");
		for(ListaDeCupons lista : this.getCuponsCompradosComSucesso()){
			for(Cupom cupom : lista){
				/*for(Cupom tmp : desejados){
					if(tmp.toString().equals(cupom.toString())){
						desejados.remove(tmp);
					}
				}*/
				System.out.println("***************************************");
				System.out.println("Comprei o cupom : " + cupom.toString());
				System.out.println("Do agente : " + lista.getAID().getName());
				System.out.println("Pelo valor de : R$" + cupom.getValor());
			}
		}
		
		/*for(Cupom cupom : desejados){
			System.out.println("***************************************");
			System.out.println("Nao consegui comprar o cupom : " + cupom.toString());
		}*/
	}
}