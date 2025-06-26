import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.awt.*;

public class Klavye implements MouseListener, KeyListener {
	
	private JFrame f;
	private JTextArea area;
	private JPanel panel;
	private Map<String, JButton> butonlar = new HashMap<>();
	
	private String[][] tuslar = {
			{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "*", "-", "Backspace"},
			
			{"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "Ğ", "Ü"},
			 
			{"A", "S", "D", "F", "G", "H", "J", "K", "L", "Ş", "İ", ",", "Enter"},
			    
			{"Z", "X", "C", "V", "B", "N", "M", "Ö", "Ç", "."},
			    
			    {"Space"}
		};


	public Klavye() {
		
		f = new JFrame("Sanal Klavye");
		f.setSize(800,400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		area = new JTextArea();
		area.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		area.addKeyListener(this);
		
		JScrollPane scroll = new JScrollPane(area);
		scroll.setPreferredSize(new Dimension(1000,350));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		Box ykutu = Box.createVerticalBox();
		Box xkutu = Box.createHorizontalBox();
		
		
		xkutu.add(Box.createHorizontalStrut(20));
		xkutu.add(scroll);
		xkutu.add(Box.createHorizontalStrut(20));
		
		ykutu.add(Box.createVerticalStrut(20));
		ykutu.add(xkutu);
		ykutu.add(Box.createVerticalStrut(15));
		ykutu.add(panel);
		ykutu.add(Box.createVerticalStrut(10));
		
		f.setLayout(new BorderLayout());
		f.add(ykutu, BorderLayout.CENTER);
		
		for (String[] satir : tuslar) {
			JPanel satirPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				for(String tus : satir) {
					JButton buton = new JButton(tus);
					
					buton.putClientProperty("defaultRenk", buton.getBackground());
					
					if (tus.equals("Backspace") || tus.equals("Enter"))
						buton.setPreferredSize(new Dimension(100,40));
					else if (tus.equals("Space"))
						buton.setPreferredSize(new Dimension(400,40));
					else
						buton.setPreferredSize(new Dimension(50,40));
					
					buton.addMouseListener(this);
					butonlar.put(tus.toUpperCase(), buton);
					satirPanel.add(buton);
				}
				panel.add(satirPanel);
		}
		
		
		f.setTitle("Sanal Klavye Uygulaması");
		f.setResizable(false);
		f.setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		char karakter = e.getKeyChar();
		String tus = String.valueOf(karakter).toUpperCase();
		
			switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					tus = "ENTER";
					break;
				case KeyEvent.VK_SPACE:
					tus = "SPACE";
					break;
				case KeyEvent.VK_BACK_SPACE:
					tus = "BACKSPACE";
					break;
				default:
					tus = String.valueOf(e.getKeyChar()).toUpperCase();
					
			}
		
		
		JButton buton = butonlar.get(tus);
		
		if(buton == null) {
			return;
		}
		
		buton.setBackground(new Color(255,255,102));
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		char karakter = e.getKeyChar();
		String tus = String.valueOf(karakter).toUpperCase();
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			tus = "ENTER";
			break;
		case KeyEvent.VK_SPACE:
			tus = "SPACE";
			break;
		case KeyEvent.VK_BACK_SPACE:
			tus = "BACKSPACE";
			break;
		default:
			tus = String.valueOf(e.getKeyChar()).toUpperCase();
			
	}
		
		JButton buton = butonlar.get(tus);
		
		if(buton == null) {
			return;
		}

		Color eskiRenk = (Color) buton.getClientProperty("defaultRenk");
		buton.setBackground(eskiRenk);


	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton buton = (JButton) e.getSource();
		String tus = buton.getText();
		
		switch (tus.toUpperCase()) {
		case "SPACE":
			area.append(" ");
			break;
		case "ENTER":
			area.append("\n");
			break;
		case "BACKSPACE":
			String metin = area.getText();
			if(!metin.isEmpty()) {
				area.setText(metin.substring(0, metin.length() - 1));
			}
			break;
		default:
			area.append(tus.toLowerCase());
		}
		area.requestFocusInWindow();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton buton = (JButton) e.getSource();
		buton.setBackground(new Color(255, 255, 102));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton buton = (JButton) e.getSource();
		Color eskiRenk = (Color) buton.getClientProperty("defaultRenk");
		buton.setBackground(eskiRenk);
		
	}

	public static void main(String[] args) {
		new Klavye();
	}

}
