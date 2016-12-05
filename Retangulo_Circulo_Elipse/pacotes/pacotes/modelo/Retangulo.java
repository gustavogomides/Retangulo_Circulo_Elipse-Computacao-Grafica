package pacotes.modelo;

import java.awt.Point;

public class Retangulo {

	private Point pontoEsquerdo;
	private Point pontoDireito;

	// ****************************************************************************************
	public Retangulo(Point pontoEsquerdo, Point pontoDireito) {
		super();
		this.pontoEsquerdo = pontoEsquerdo;
		this.pontoDireito = pontoDireito;
	}

	// ****************************************************************************************
	public Point getPontoEsquerdo() {
		return pontoEsquerdo;
	}

	// ****************************************************************************************
	public void setPontoEsquerdo(Point pontoEsquerdo) {
		this.pontoEsquerdo = pontoEsquerdo;
	}

	// ****************************************************************************************
	public Point getPontoDireito() {
		return pontoDireito;
	}

	// ****************************************************************************************
	public void setPontoDireito(Point pontoDireito) {
		this.pontoDireito = pontoDireito;
	}

	// ****************************************************************************************
}
