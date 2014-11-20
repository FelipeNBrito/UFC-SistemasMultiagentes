package br.ufc.quixada.smas.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.agentes.AgenteReputacao;

public class JanelaAgenteReputacao extends JFrame{
	
	private AgenteReputacao agente;
	
	public JanelaAgenteReputacao(AgenteReputacao agente) {
		this.agente = agente;
		
		this.setTitle("Agente Reputacao");
        this.setSize(500,200);
        this.setLocationRelativeTo(null);
        this.add(new PanelAgenteReputacao(agente));
        this.setVisible(true);
       
	}
}
