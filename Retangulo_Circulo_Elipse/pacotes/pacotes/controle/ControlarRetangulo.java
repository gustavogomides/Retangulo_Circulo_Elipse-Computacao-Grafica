package pacotes.controle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import pacotes.modelo.*;

public class ControlarRetangulo {

	private ControlarReta ctrReta = new ControlarReta();
	private ControlarCirculo ctrCirculo = new ControlarCirculo();

	// *************************************************************************************
	// DESENHAR RETANGULO
	public void desenharRetangulo(Retangulo r, Color c, Graphics g) {
		ctrReta = new ControlarReta();

		Point p1 = new Point(), p2 = new Point();
		p1 = r.getPontoEsquerdo();
		p2 = r.getPontoDireito();

		int n = 0;
		if (Math.abs(p1.getX() - p2.getX()) > 18 && Math.abs(p1.getY() - p2.getY()) > 18) {
			n = 9;
		} else {
			if (Math.abs(p1.getX() - p2.getX()) > 10 && Math.abs(p1.getY() - p2.getY()) > 10) {
				n = 5;
			} else {
				if (Math.abs(p1.getX() - p2.getX()) > 6 && Math.abs(p1.getY() - p2.getY()) > 6) {
					n = 3;
				}
			}
		}

		// Reta de P1 a  P2 sendo o X fixada no P1.
		ctrReta.desenharReta(new Reta(p1, new Point(p1.x, p2.y)), c, n, g);

		// Reta de P1 a  P2 sendo o X fixada no P2.
		ctrReta.desenharReta(new Reta(new Point(p2.x, p1.y), p2), c, n, g);

		// Reta de P1 a P2 sendo o Y fixada no P1
		ctrReta.desenharReta(new Reta(p1, new Point(p2.x, p1.y)), c, n, g);

		// Reta de P1 a  P2 sendo o Y fixada no P2.
		ctrReta.desenharReta(new Reta(new Point(p1.x, p2.y), p2), c, n, g);

	}

	// *************************************************************************************
	// DESENHAR BORDA ARREDONDADA
	public void desenhaBorda(Retangulo r, Graphics g, Color cor) {
		Point p1, p2;
		p1 = r.getPontoEsquerdo();
		p2 = r.getPontoDireito();
		int n = 0;
		if (Math.abs(p1.getX() - p2.getX()) > 18 && Math.abs(p1.getY() - p2.getY()) > 18) {
			n = 9;
		} else {
			if (Math.abs(p1.getX() - p2.getX()) > 10 && Math.abs(p1.getY() - p2.getY()) > 10) {
				n = 5;
			} else {
				if (Math.abs(p1.getX() - p2.getX()) > 6 && Math.abs(p1.getY() - p2.getY()) > 6) {
					n = 3;
				}
			}
		}
		if (p1.getX() < p2.getX() && p1.getY() < p2.getY()) {
			ctrCirculo.desenharBorda(new Circulo((p1.getX() + n), (p1.getY() + n), n), cor, 1, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p1.getX() + n), (int) (p2.getY() - n), n), cor, 2, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p2.getX() - n), (int) (p1.getY() + n), n), cor, 3, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p2.getX() - n), (int) (p2.getY() - n), n), cor, 4, g);
		}
		if (p1.getX() < p2.getX() && p1.getY() > p2.getY()) {
			ctrCirculo.desenharBorda(new Circulo((int) (p1.getX() + n), (int) (p2.getY() + n), n), cor, 1, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p1.getX() + n), (int) (p1.getY() - n), n), cor, 2, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p2.getX() - n), (int) (p2.getY() + n), n), cor, 3, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p2.getX() - n), (int) (p1.getY() - n), n), cor, 4, g);
		}

		if (p1.getX() > p2.getX() && p1.getY() < p2.getY()) {
			ctrCirculo.desenharBorda(new Circulo((int) (p1.getX() - n), (int) (p1.getY() + n), n), cor, 3, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p1.getX() - n), (int) (p2.getY() - n), n), cor, 4, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p2.getX() + n), (int) (p1.getY() + n), n), cor, 1, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p2.getX() + n), (int) (p2.getY() - n), n), cor, 2, g);
		}

		if (p1.getX() > p2.getX() && p1.getY() > p2.getY()) {
			ctrCirculo.desenharBorda(new Circulo((int) (p1.getX() - n), (int) (p2.getY() + n), n), cor, 3, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p1.getX() - n), (int) (p1.getY() - n), n), cor, 4, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p2.getX() + n), (int) (p2.getY() + n), n), cor, 1, g);
			ctrCirculo.desenharBorda(new Circulo((int) (p2.getX() + n), (int) (p1.getY() - n), n), cor, 2, g);
		}
	}
	// *************************************************************************************
}
