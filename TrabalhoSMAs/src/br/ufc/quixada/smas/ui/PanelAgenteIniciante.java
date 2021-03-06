package br.ufc.quixada.smas.ui;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufc.quixada.smas.agentes.AgenteIniciante;
import br.ufc.quixada.smas.objetos.Cupom;

public class PanelAgenteIniciante extends JPanel implements ActionListener {

	private AgenteIniciante agente;
	private JLabel lableCupom;
	private JButton botaoAddCupom;
	private JButton botaoFinalizar;
	private JButton botaoVerResultados;
	private JTextField textoCupomNome;
	private JTextField textoCupomPreco;
	private JLabel labelCuponsAdicionados;
	
	public PanelAgenteIniciante(AgenteIniciante agente) {
		this.agente = agente;
		
		lableCupom = new JLabel("Inserir cupom");
		textoCupomNome = new JTextField("Nome do Cupom");
		textoCupomPreco = new JTextField("Preço do Cupom");
		botaoAddCupom = new JButton("Add Cupom");
		botaoFinalizar = new JButton("Finalizar");
		botaoVerResultados = new JButton("Ver Resultados");
		labelCuponsAdicionados = new JLabel("");
		
		lableCupom.setBounds(100,100,100,20);
		textoCupomNome.setBounds(200, 100, 100, 20);
		textoCupomPreco.setBounds(300, 100, 100, 20);
		botaoAddCupom.setBounds(400,100,100,20);
		botaoFinalizar.setBounds(100,200,100,20);
		//labelCuponsAdicionados.setBounds(400, 400, 200, 400);
		botaoVerResultados.setBounds(100, 400, 100, 20);
		
	    //Adiciona o 
	    this.add(this.lableCupom);
	    this.add(this.textoCupomNome);
	    this.add(this.textoCupomPreco);
	    this.add(this.botaoAddCupom);
	    this.add(this.botaoFinalizar);
	    this.add(botaoVerResultados);
	    //this.add(this.labelCuponsAdicionados);
	    
	    this.botaoAddCupom.addActionListener(this);
	    this.botaoFinalizar.addActionListener(this);
	    this.botaoVerResultados.addActionListener(this);
	    this.textoCupomNome.addMouseListener(this.addEventoMouse());
	    this.textoCupomPreco.addMouseListener(this.addEventoMouse());

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==this.botaoAddCupom){
			 this.agente.addCupomDesejado(textoCupomNome.getText(),Double.parseDouble(textoCupomPreco.getText()));
		      JOptionPane.showMessageDialog(null,agente.getQuantidadeDeCuponsDesejados());
		     
		 } else if(e.getSource()==this.botaoFinalizar){
			 agente.incrementaPasso();
			 this.botaoAddCupom.setEnabled(false);
		 }else if(e.getSource()==this.botaoVerResultados){
			 agente.verResultados();
		 }
	}
	
	private MouseListener addEventoMouse(){
		return new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				JTextField campo = (JTextField) e.getSource();
				campo.setText("");
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
	}


}
