package br.ufc.quixada.smas.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufc.quixada.smas.agentes.AgenteParticipante;
import br.ufc.quixada.smas.agentes.AgenteReputacao;
import br.ufc.quixada.smas.objetos.RepositorioReputacaoAgente;
import br.ufc.quixada.smas.objetos.Reputacao;

public class PanelAgenteReputacao extends JPanel implements ActionListener{

	private AgenteReputacao agente;
	private JButton botaoImprimir;
	
	public PanelAgenteReputacao(AgenteReputacao agente) {
		this.agente = agente;
		
		botaoImprimir = new JButton("Imprimir Reputacoes");
		botaoImprimir.setBounds(100,100,100,20);
		this.add(botaoImprimir);
	    this.botaoImprimir.addActionListener(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== this.botaoImprimir){
			ArrayList<Reputacao> reputacoes = agente.getTodasReputacoes();
			System.out.println("****************************");
			System.out.println("Imprimindo todas as reputacoes");
			System.out.println("********************************");
			
			for(Reputacao reputacao : reputacoes){
				if(reputacao != null){
					System.out.println("Agente : " + reputacao.getAidAgente().getLocalName());
					System.out.println("Reputacao: " + reputacao.getValor());
					System.out.println("******************************");
				}
				
			}
		}
		
	}

	
}
