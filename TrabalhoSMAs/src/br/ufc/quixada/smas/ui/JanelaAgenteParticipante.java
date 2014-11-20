package br.ufc.quixada.smas.ui;

import javax.swing.JFrame;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.agentes.AgenteParticipante;

public class JanelaAgenteParticipante extends JFrame{

	private AgenteParticipante agente;
	
	public JanelaAgenteParticipante(AgenteParticipante agente) {
		this.agente = agente;
		
		this.setTitle("Agente Participante : " + this.agente.getLocalName() + 
				" | " + "Passo: " + agente.getPasso());
        this.setSize(500,200);
        this.setLocationRelativeTo(null);
        this.add(new PanelAgenteParticipante(agente));
        this.setVisible(true);
       
	}
}
