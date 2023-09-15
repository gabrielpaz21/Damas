package Damas;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MarcoPrueba extends JFrame {

	private static final long serialVersionUID = 1L;

	public MarcoPrueba() {

		setBounds(200, 100, 515, 515);
		setTitle("Damas");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Toolkit t = Toolkit.getDefaultToolkit();
		Image icon = t.getImage("Lighthouse.jpg"); //TODO modificar icono superior
		setIconImage(icon);
		Panel panel = new Panel();
		add(panel);
		setVisible(true);
	}

}

class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int ANCHO_TABLERO = Tablero.ANCHO_TABLERO;
	private static final int ALTO_TABLERO = Tablero.ALTO_TABLERO;
	private Boton botonSeleccionado, botonJugada;
	private GridBagConstraints gbc;
	private boolean turno = false;
	private ArrayList<Integer>  pocicionesMover = new ArrayList<Integer>(), 
								pocicionesComer = new ArrayList<Integer>(),
								pocicionesComidas= new ArrayList<Integer>();
	
	private final static String rutaBase = "static";
	
	private final ImageIcon imagenEspacioBlanco = obtenerImagen(rutaBase + "/espacioBlanco.png");
	
	private final ImageIcon imagenEspacioNegro = obtenerImagen(rutaBase + "/espacioNegro.png");
	
	private final ImageIcon imagenEspacioVerde = obtenerImagen(rutaBase + "/espacioVerde.png");
	
	private final ImageIcon imagenPeonRojo = obtenerImagen(rutaBase + "/peonRojo.png");
	
	private final ImageIcon imagenPeonRojoSeleccionado = obtenerImagen(rutaBase + "/peonRojoSeleccionado.png");
	
	private final ImageIcon imagenPeonNegro = obtenerImagen(rutaBase + "/peonNegro.png");
	
	private final ImageIcon imagenPeonNegroSeleccionado = obtenerImagen(rutaBase + "/peonNegroSeleccionado.png");
	

	
	public Panel() {
		setLayout(new GridBagLayout());
		añadirBotones();
		new Tablero();
	}

	public GridBagConstraints atributoBoton(int i, int j) {

		gbc = new GridBagConstraints();
		gbc.gridx = j;
		gbc.gridy = i;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;

		return gbc;
	}

	public Boton getBoton(int i, int j) {
		
		int aux = 0;
		Boton boton = null;
		boolean pase = true;
		
		for (int x = 0; x < ANCHO_TABLERO && pase == true; x++) {
			for (int y = 0; y < ALTO_TABLERO && pase == true; y++) {

				if (x == i && y == j && pase == true) {
					boton = (Boton) getComponent(aux);
					pase = false;
				}
				aux += 1;
			}
		} //TODO Usar el operador break

		return boton;
	}
	
	public static ImageIcon obtenerImagen(String nombreImagen) {
		ImageIcon imagen = new ImageIcon(nombreImagen);
		return imagen;
	}

	public void añadirBotones() {
		int aux;
		for (int i = 0; i < ANCHO_TABLERO; i++) {
			for (int j = 0; j < ALTO_TABLERO; j++) {
				
				GridBagConstraints gbc = atributoBoton(i, j);
				aux = i + j;
				
				if (aux % 2 == 0) {
					
					add(new Boton(gbc, imagenEspacioBlanco),gbc);
					
				}

				if (aux % 2 != 0 && (i == 0 || i == 1 || i == 2)) {
					
					add(new Boton(gbc,imagenPeonRojo),gbc);
						
				}

				if (aux % 2 != 0 && (i == 5 || i == 6 || i == 7)) {
					
					add(new Boton(gbc, imagenPeonNegro),gbc);
					
				}

				if (aux % 2 != 0 && (i == 3 || i == 4)) {
					
					add(new Boton(gbc, imagenEspacioNegro),gbc);
					
				}
			}
		}
	}
		
	public boolean validarBoton(Boton boton) {
		boolean validacion = true;
		int cordenadasSumadas = boton.getI() + boton.getJ();
		final boolean esPar = cordenadasSumadas % 2 == 0;
		final boolean esNula = Tablero.getPieza(boton.getI(), boton.getJ()) == null;
		
		
		if (esPar) {
			validacion = false;
		}
		
		if(!esPar && esNula) {
			validacion = false;
		}
		
		return validacion;
	}
	
	public void removerEscuchadores() {
		
		for (int x = 0; x < ANCHO_TABLERO; x++) {
			for (int y = 0; y < ALTO_TABLERO; y++) {
				Boton boton = getBoton(x, y);
				boton.removeActionListener(boton);
			}
		}
	}
	
	public void cambiarImagenes(ImageIcon imagen,ArrayList<Integer> pociciones) {
		
		for (int x = 0; x < pociciones.size(); x += 2) {
			Boton boton = getBoton(pociciones.get(x), pociciones.get(x + 1));
			boton.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
		}
		
	}
	
	
	
	

	private class Boton extends JButton implements FocusListener, ActionListener {

		private static final long serialVersionUID = 1L;
		
		private int i, j;
		
		public Boton(GridBagConstraints gbc,ImageIcon imagen) {
			this.i = gbc.gridy;
			this.j = gbc.gridx;
			setIcon(new ImageIcon(imagen.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
			addFocusListener(this);
			setOpaque(false);
			setContentAreaFilled(false);
			setBorderPainted(false);
		}
		
		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return j;
		}

		public void setJ(int j) {
			this.j = j;
		}
	
		@Override
		public void focusGained(FocusEvent e) {
			
			removerEscuchadores();

			botonSeleccionado = (Boton) e.getSource();
			
			if (!validarBoton(botonSeleccionado)) {
				 if(botonSeleccionado.getI()!=0 && botonSeleccionado.getJ()!=0) {
					 
//					 JOptionPane.showMessageDialog(null, "Hello World");
				 }
				
				return;
			}
			
			Pieza piezaSeleccionada = Tablero.getPieza(botonSeleccionado.getI(), botonSeleccionado.getJ());
			
			if(!Tablero.comprobarAlineacionDeTurnos(turno,piezaSeleccionada.getColor())) {
				
				return;
			}
			
			if(turno==false && Tablero.puedenComer("roja") && !piezaSeleccionada.puedeComer() || turno==true && Tablero.puedenComer("negra") && !piezaSeleccionada.puedeComer()) {
				return;
			}
			
			
			if(turno) {
				botonSeleccionado.setIcon(new ImageIcon(imagenPeonNegroSeleccionado.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
			}else {
				botonSeleccionado.setIcon(new ImageIcon(imagenPeonRojoSeleccionado.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
			}
				
			pocicionesComidas = piezaSeleccionada.piezasComidas();
			pocicionesMover = piezaSeleccionada.pocicionesParaMover();
			pocicionesComer = piezaSeleccionada.pocicionesParaComer();
			
			if(piezaSeleccionada.puedeComer()){
				
				for (int x = 0; x < pocicionesComer.size(); x += 2) {
					Boton boton = getBoton(pocicionesComer.get(x), pocicionesComer.get(x + 1));
					boton.setIcon(new ImageIcon(imagenEspacioVerde.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
					boton.removeFocusListener(boton);
					boton.addActionListener(boton);
					
				}			
				
			}else if(piezaSeleccionada.puedeMover()){
				
				for (int x = 0; x < pocicionesMover.size(); x += 2) {
					Boton boton = getBoton(pocicionesMover.get(x), pocicionesMover.get(x + 1));
					boton.setIcon(new ImageIcon(imagenEspacioVerde.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
					boton.removeFocusListener(boton);
					boton.addActionListener(boton);

				}
			}

		}
		
		@Override
		public void focusLost(FocusEvent e) {

			botonSeleccionado = (Boton) e.getSource();
			
			if (!validarBoton(botonSeleccionado)) {
				return;
			}
			
			Pieza piezaDeseleccionada = Tablero.getPieza(botonSeleccionado.getI(), botonSeleccionado.getJ());
			
			if(!Tablero.comprobarAlineacionDeTurnos(turno,piezaDeseleccionada.getColor())) {
				
				return;
			}
			
			if(turno) {
				botonSeleccionado.setIcon(new ImageIcon(imagenPeonNegro.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
			}else {
				botonSeleccionado.setIcon(new ImageIcon(imagenPeonRojo.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
			}
			
			pocicionesMover = piezaDeseleccionada.pocicionesParaMover();
			
			cambiarImagenes(imagenEspacioNegro, pocicionesMover);
			
			pocicionesComer = piezaDeseleccionada.pocicionesParaComer();
			
			cambiarImagenes(imagenEspacioNegro, pocicionesComer);

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			botonJugada = (Boton) e.getSource();
			Pieza piezaSeleccionada = Tablero.getPieza(botonSeleccionado.getI(), botonSeleccionado.getJ());
			pocicionesMover = piezaSeleccionada.pocicionesParaMover();
			pocicionesComer = piezaSeleccionada.pocicionesParaComer();
			
			piezaSeleccionada.mover(botonJugada.getI(), botonJugada.getJ());
			
			if(piezaSeleccionada.getColor().equals("roja")) {
				turno=true;
			}else if(piezaSeleccionada.getColor().equals("negra")) {
				turno=false;
			}
			
			
			
			ImageIcon imagenBotonSelecionado = (ImageIcon) botonSeleccionado.getIcon();
			
			botonJugada.setIcon(new ImageIcon(imagenBotonSelecionado.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));

			botonSeleccionado.setIcon(new ImageIcon(imagenEspacioNegro.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
		
			for (int x = 0; x < pocicionesMover.size(); x += 2) {
				Boton boton = getBoton(pocicionesMover.get(x), pocicionesMover.get(x + 1));
				if (boton != botonJugada) {
					boton.setIcon(new ImageIcon(imagenEspacioNegro.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
					boton.removeActionListener(boton);
				} else {
					removeActionListener(botonJugada);
					addFocusListener(botonJugada);
				}
			}
			
			for (int x = 0; x < pocicionesComer.size(); x += 2) {
				Boton boton = getBoton(pocicionesComer.get(x), pocicionesComer.get(x + 1));
				if (boton != botonJugada) {
					boton.setIcon(new ImageIcon(imagenEspacioNegro.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
					boton.removeActionListener(boton);
				} else {
					removeActionListener(botonJugada);
					addFocusListener(botonJugada);
				}
			}
			
			for(int x=0;x<pocicionesComidas.size(); x += 2) {
				Boton boton = getBoton(pocicionesComidas.get(x), pocicionesComidas.get(x + 1));
				boton.setIcon(new ImageIcon(imagenEspacioNegro.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
				boton.removeFocusListener(boton);
				Tablero.anular(Tablero.getPieza(pocicionesComidas.get(x), pocicionesComidas.get(x + 1)));				
			}
		
		}

	}

}
