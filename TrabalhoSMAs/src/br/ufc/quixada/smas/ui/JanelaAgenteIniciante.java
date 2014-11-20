package br.ufc.quixada.smas.ui;

import javax.swing.JFrame;

import br.ufc.quixada.smas.agentes.AgenteIniciante;

public class JanelaAgenteIniciante extends JFrame{

	private AgenteIniciante agente;
	
	public JanelaAgenteIniciante(AgenteIniciante agente) {
		this.agente = agente;
		
		this.setTitle("Agente Iniciante : " + this.agente.getLocalName() + 
				" | " + "Passo: " + agente.getPasso());
        this.setSize(500,200);
        this.setLocationRelativeTo(null);
        this.add(new PanelAgenteIniciante(agente));
        this.setVisible(true);
       
	}
}
