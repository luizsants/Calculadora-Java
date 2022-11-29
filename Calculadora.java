package main;

//bibliotecas utilizadas
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Calculadora extends JFrame {

	//variaveis 
    static JTextField textfield1, textfield2, textfield3;
	public String resultF;
	Double aux = 0.0, auxTxt1, auxTxt2;
	JLabel label;

	//função principal
	public Calculadora() {
		
		JFrame f = new JFrame("Operações Basicas");

		//cria painel e campos
		f.add(label = new JLabel());
		f.add(new JLabel("Valor 1: "));
		f.setSize(250, 250);
		textfield1 = new JTextField("",20);
		textfield2 = new JTextField("",20);
		textfield3 = new JTextField("",20);
		
		//adiciona ao painel
		f.getContentPane().add(textfield1);
		f.add(new JLabel("Valor 2: "));
		f.getContentPane().add(textfield2);
		
		JButton b = new JButton("+");
		JButton b1 = new JButton("-");
		JButton b2 = new JButton("*");
		JButton b3 = new JButton("/");
		JButton b4 = new JButton("£");
		
		b.setBounds(100,100,140,40);
		b1.setBounds(100,100,140,40);
		b2.setBounds(100,100,140,40);
		b3.setBounds(100,100,140,40);
		b4.setBounds(100,100,140,40);
		
		f.add(b);	
		f.add(b1);	
		f.add(b2);	
		f.add(b3);	
		f.add(b4);
		
		f.add(new JLabel("Resultado: "));
		f.getContentPane().add(textfield3);
		textfield3.setEditable(false);
		
		f.getContentPane().setLayout(new FlowLayout(3));
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
        
        
        double timePerFrame = 1000000000.0 / 2.0; 	
		double timePerUpdate = 1000000000.0 / 2.0;
		
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
				
    	long now;
        
		//main loop
        while(true) {
        	
        	now = System.nanoTime();
        	
        	
        	if(now - lastFrame >= timePerFrame) {
				lastFrame = now;	
			}
        	
        	if(now - lastUpdate >= timePerUpdate) {
				lastUpdate = now;
				//verifica se foi digitado campo 2
				try{
	        		textfield1 = textField1Verifier(textfield1);
			        auxTxt1 =  Double.valueOf(textfield1.getText());
					System.out.println("Number Digited");
	            }
	            catch (NumberFormatException ex){
	            	System.out.println("Nothing Digited");
	            }
	        	//verifica se foi digitado campo 2
	        	try{
	        		textfield2 = textField2Verifier(textfield2);
	   		        auxTxt2 = Double.valueOf(textfield2.getText());
					System.out.println("Number Digited");
	        	}
	        	catch (NumberFormatException ex){
					System.out.println("Nothing Digited");
	        	}
        	  
				//verifica os botoes que foram pressionados.
				//Operações
		        b.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	if (auxTxt1 != 0.0 && auxTxt2 != 0.0) {
					    	aux = auxTxt1 + auxTxt2; 
					    	zerar();
				    	}
				    }
				});
		        
		        b1.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	if (auxTxt1 != 0.0 && auxTxt2 != 0.0) {
					    	aux = auxTxt1 - auxTxt2;
					    	zerar();
				    	}
				    }
				});
		        
		        b2.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	if (auxTxt1 != 0.0 && auxTxt2 != 0.0) {
					    	aux = auxTxt1 * auxTxt2; 
					    	zerar();
				    	}
				    }
				});
		        
		        b3.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	if (auxTxt1 != 0.0 && auxTxt2 != 0.0) {
					    	aux = auxTxt1 / auxTxt2; 
					    	zerar();
				    	}
				    }
				});
		        
		        b4.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	if (auxTxt1 != 0.0) {
				    		aux = auxTxt1 * 6.3; 
				    		textfield2.setText("");
				    		label.setText("Resultado = real converted to libra £");
				    		zerar();
				    	}
				    }
				});
		        
		        //mostra na tela
		        resultF = Double.toString(aux);
		        textfield3.setText(resultF);
        	}
		}
	}
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	//zera variaveis auxiliares
	public void zerar () {
		auxTxt1 = 0.0;
        auxTxt2= 0.0;
		return;
	}
	//	-----------------------------------------------------------------------------------------------------------------------------------------------------------
	//Verificador de teclas pressionadas - CAMPO 1
	public JTextField textField1Verifier (JTextField txt1) {
		
		textfield1 = txt1;
		
		textfield1.addKeyListener(new KeyAdapter() {
			
	         public void keyPressed(KeyEvent ke) {
	        	 
	            String value = textfield1.getText();
	            
	            int l = value.length();
	            
	            int limit = 10;
	            
	            if (l <= limit){
		            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
		            	textfield1.setEditable(true);
		            	label.setText("");     
		            } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
		            	textfield1.setText(""+textfield1.getText().substring(0, l));
		            	textfield1.setEditable(true);
		            } else {
		            	textfield1.setEditable(false);
		            	label.setText("Digite só valores númericos        ");
		            }
	            } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
	            	textfield1.setText(""+textfield1.getText().substring(0, l));
	            	textfield1.setEditable(true);
	            } else {
	        		textfield1.setEditable(false);
	        		label.setText("Número maximo de digitos atingido");
	            }
	         }
	    });
		return textfield1;
	}
	
	//Verificador de teclas pressionadas - CAMPO 2
	public JTextField textField2Verifier (JTextField txt2) {
		
		textfield2 = txt2;
		
		textfield2.addKeyListener(new KeyAdapter() {
			
	         public void keyPressed(KeyEvent ke) {
	        	 
	            String value = textfield2.getText();
	            
	            int l = value.length();
	            
	            int limit = 10;
	            
	            if (l <= limit){
		            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
		            	textfield2.setEditable(true);
		            	label.setText("");     
		            } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
		            	textfield2.setText(""+textfield2.getText().substring(0, l));
		            	textfield2.setEditable(true);
		            } else {
		            	textfield2.setEditable(false);
		            	label.setText("Digite só valores númericos        "); 
		            }
	            } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
	            	textfield2.setText(""+textfield2.getText().substring(0, l));
	            	textfield2.setEditable(true);
	            } else {
	            	textfield2.setEditable(false);
	            	label.setText("Número maximo de digitos atingido");
	            }
	         }
	    });
		return textfield2;
	}

	//cria a classe - Main method
	public static void main(String[] args) {	
		Calculadora tela = new Calculadora();	
	}
}