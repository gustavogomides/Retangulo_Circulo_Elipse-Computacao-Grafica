package pacotes.controle;

import java.awt.Color;
import java.awt.Graphics;

public class Util {

	// *************************************************************************************
	// PLOTAR PONTO
	public void plotarPonto(Color cor, Graphics g, int x, int y) {
		g.setColor(cor);
		g.drawLine(x, y, x, y);
	}

	// *************************************************************************************

}
