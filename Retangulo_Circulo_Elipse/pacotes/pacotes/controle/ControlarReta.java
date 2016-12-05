package pacotes.controle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import pacotes.modelo.*;

public class ControlarReta {
	private Util util = new Util();

	// *************************************************************************************
	// DESENHAR RETA
	public void desenharReta(Reta r, Color c, int tamBorda, Graphics g) {
		Point p1, p2;
		p1 = r.getPontoInicial();
		p2 = r.getPontoFinal();
		if (p1.getX() != p2.getX()) {
			if (p1.getX() < p2.getX()) {
				for (int i = (int) p1.getX() + tamBorda; i <= p2.getX() - tamBorda; i++) {
					util.plotarPonto(c, g, i, (int) p1.getY());
				}
			} else {
				for (int i = (int) p2.getX() + tamBorda; i <= p1.getX() - tamBorda; i++) {
					util.plotarPonto(c, g, i, (int) p1.getY());
				}
			}
		} else {
			if (p1.getY() < p2.getY()) {
				for (int i = (int) p1.getY() + tamBorda; i <= p2.getY() - tamBorda; i++) {
					util.plotarPonto(c, g, (int) p1.getX(), i);
				}
			} else {
				for (int i = (int) p2.getY() + tamBorda; i <= p1.getY() - tamBorda; i++) {
					util.plotarPonto(c, g, (int) p1.getX(), i);
				}
			}
		}
	}

	// *************************************************************************************
}
