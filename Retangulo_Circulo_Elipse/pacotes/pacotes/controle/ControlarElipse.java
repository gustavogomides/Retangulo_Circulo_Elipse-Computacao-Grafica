package pacotes.controle;

import java.awt.Color;
import java.awt.Graphics;

import pacotes.modelo.*;

public class ControlarElipse {

	private Util util = new Util();

	// *************************************************************************************
	// DESENHAR ELIPSE
	public void desenharElipse(Color cor, Graphics g, Elipse elipse, int opcao) {
		if (opcao == 1) {
			desenharElipseClassico(cor, g, elipse);
		} else {
			desenharElipseDDA(cor, g, elipse);
		}
	}

	// *************************************************************************************
	// DESENHAR ELIPSE METODO CLASSICO
	private void desenharElipseClassico(Color cor, Graphics g, Elipse elipse) {

		int teta = 0;
		double xc = elipse.getXc();
		double yc = elipse.getYc();
		double rx = elipse.getRx();
		double ry = elipse.getRy();
		int graus = 45;
		double x, y;

		for (int i = 0; i < 360; i++) {
			x = xc + ry * Math.cos(teta);
			y = yc + rx * Math.sin(teta);
			util.plotarPonto(cor, g, (int) x, (int) y);
			teta = graus--;
		}

	}

	// *************************************************************************************
	// DESENHAR ELIPSE METODO DDA
	private void desenharElipseDDA(Color cor, Graphics g, Elipse elipse) {
		int cx, cy, xradius, yradius, x, y, xchange, ychange, erroelipse, a2quadrado, b2quadrado, xstopping, ystopping;
		cx = (int) elipse.getXc();
		cy = (int) elipse.getYc();
		yradius = (int) elipse.getRx();
		xradius = (int) elipse.getRy();
		a2quadrado = 2 * xradius * xradius;
		b2quadrado = 2 * yradius * yradius;
		x = xradius;
		y = 0;
		xchange = yradius * yradius * (1 - 2 * xradius);
		ychange = xradius * xradius;
		erroelipse = 0;
		xstopping = b2quadrado * xradius;
		ystopping = 0;

		while (xstopping >= ystopping) {
			plotar4pontos(cor, g, cx, cy, x, y);
			y++;
			ystopping += a2quadrado;
			erroelipse += ychange;
			ychange += a2quadrado;
			if ((2 * erroelipse + xchange) > 0) {
				x--;
				xstopping -= b2quadrado;
				erroelipse += xchange;
				xchange += b2quadrado;
			}
		}

		x = 0;
		y = yradius;
		xchange = yradius * yradius;
		ychange = xradius * xradius * (1 - 2 * yradius);
		erroelipse = 0;
		xstopping = 0;
		ystopping = a2quadrado * yradius;
		while (xstopping <= ystopping) {
			plotar4pontos(cor, g, cx, cy, x, y);
			x++;
			xstopping += b2quadrado;
			erroelipse += xchange;
			xchange += b2quadrado;
			if ((2 * erroelipse + ychange) > 0) {
				y--;
				ystopping -= a2quadrado;
				erroelipse += ychange;
				ychange += a2quadrado;
			}
		}

	}

	// *************************************************************************************
	// PLOTAR PONTOS SIMÉTRICOS
	private void plotar4pontos(Color cor, Graphics g, int CX, int CY, int X, int Y) {
		util.plotarPonto(cor, g, CX + X, CY + Y);
		util.plotarPonto(cor, g, CX - X, CY + Y);
		util.plotarPonto(cor, g, CX - X, CY - Y);
		util.plotarPonto(cor, g, CX + X, CY - Y);
	}
	// *************************************************************************************
}
