package pacotes.controle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pacotes.modelo.*;
import pacotes.view.*;

public class ControlarAplicativo implements ActionListener {

	private MontarPainelInicial pnCenario;
	private Graphics desenho;

	private ArrayList<Retangulo> retangulos = new ArrayList<>();
	private ArrayList<Circulo> circulos = new ArrayList<>();
	private ArrayList<Elipse> elipses = new ArrayList<>();

	private ControlarRetangulo controleRetangulo;
	private ControlarCirculo controleCirculo = new ControlarCirculo();
	private ControlarElipse controleElipse = new ControlarElipse();

	private boolean desenharelipse = false;
	private boolean desenharcirculo = false;

	// ****************************************************************************
	// CONSTRUTOR DA CLASSE ControlarAplicativo
	public ControlarAplicativo() {
		pnCenario = new MontarPainelInicial(this);
		pnCenario.showPanel();
		pnCenario.mensagemInicial();
		desenho = pnCenario.iniciarGraphics();
		controleRetangulo = new ControlarRetangulo();
	}

	// ****************************************************************************
	// ACTION PERFORMED - CAPTURAR E TRATAR CLIQUE DOS BOTÕES
	public void actionPerformed(ActionEvent e) {
		String comando;
		comando = e.getActionCommand();

		// DESENHAR CIRCULO METODO CLASSICO
		if (comando.equals("botaoClassico")) {
			desenharCirculosCircunscritos(desenho, Color.BLUE, 1, true, true);
		}

		// DESENHAR CIRCULO METODO POLAR SIMPLES
		if (comando.equals("botaoPolarSimples")) {
			desenharCirculosCircunscritos(desenho, Color.BLUE, 2, true, true);
		}

		// DESENHAR CIRCULO METODO POLAR INCREMENTAL
		if (comando.equals("botaoPolarIncremental")) {
			desenharCirculosCircunscritos(desenho, Color.BLUE, 3, true, true);
		}

		// DESENHAR CIRCULO METODO DDA
		if (comando.equals("botaoDDA")) {
			desenharCirculosCircunscritos(desenho, Color.BLUE, 4, true, true);
		}

		// DESENHAR ELIPSE CLASSICO
		if (comando.equals("botaoElipseClassico")) {
			desenharElipse(desenho, Color.RED, 1, true);
		}

		// DESENHAR ELIPSE DDA
		if (comando.equals("botaoElipseDDA")) {
			desenharElipse(desenho, Color.RED, 2, true);
		}

		// EXCLUIR CIRCULOS CIRCUNSCRITOS
		if (comando.equals("botaoExcluirCirculo")) {
			if (circulos.size() != 0) {
				excluirCirculosCircunscritos(desenho);
			}
		}

		// EXCLUIR ELIPSES
		if (comando.equals("botaoExcluirElipse")) {
			if (elipses.size() != 0) {
				excluirElipsesCircunscritas(desenho);
			}
		}

		// LIMPAR TELA
		if (comando.equals("botaoLimpar")) {
			if (retangulos.size() != 0) {
				int option;

				option = JOptionPane.showConfirmDialog(null, "Deseja limpar a tela (excluir o desenho)?", "Limpar",
						JOptionPane.YES_NO_OPTION);

				if (option == JOptionPane.YES_OPTION) {
					pnCenario.ocultarDesenho();
					retangulos.clear();
					circulos.clear();
					elipses.clear();
				}
			}
		}

		// FIM DO PROGRAMA
		if (comando.equals("botaoFim")) {
			int option;

			option = JOptionPane.showConfirmDialog(null, "Deseja sair da aplicação?", "Sair",
					JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	// ****************************************************************************
	// DESENHAR RETANGULOS DA LISTA
	public void desenharRetanguloLista(Color cor, Graphics g) {
		for (Retangulo r : retangulos) {
			controleRetangulo.desenharRetangulo(r, cor, g);
			desenharBordas(r, cor, g);
		}
	}

	// ****************************************************************************
	// DESENHAR RETANGULOS POR MEIO DE DOIS PONTOS
	public void desenharRetanguloPontos(Point pontoEsquerdo, Point pontoDireito, Color cor, Graphics g) {
		Retangulo r = new Retangulo(pontoEsquerdo, pontoDireito);
		retangulos.add(r);
		controleRetangulo.desenharRetangulo(r, cor, g);
		for (Retangulo rec : retangulos) {
			desenharBordas(rec, Color.BLACK, desenho);
		}
	}

	// ****************************************************************************
	// DESENHAR BORDAS
	public void desenharBordas(Retangulo retangulo, Color cor, Graphics g) {
		controleRetangulo.desenhaBorda(retangulo, g, cor);
	}

	// ****************************************************************************
	// DESENHAR CÍRCULO MARCAÇÃO
	public void desenharCirculoMarcacao(Color cor, Graphics g, double x, double y) {
		controleCirculo.desenharCirculo(g, cor, 3, new Circulo(x, y, 3));
	}

	// ****************************************************************************
	// DESENHAR CIRCULOS CIRCUNSCRITOS
	// add = true -> adicionar na lista
	// add = false -> não adicionar na lista
	// temporal = true -> clicou no botão de desenhar círculo
	// temporal = false -> clicou no botão desenhar elipses
	private void desenharCirculosCircunscritos(Graphics g, Color cor, int opcao, boolean add, boolean temporal) {
		if (temporal) {
			desenharcirculo = true;
			pnCenario.ocultarDesenho();
			desenharRetanguloLista(Color.BLACK, desenho);

			if (desenharelipse && elipses.size() != 0) {
				desenharElipse(g, Color.RED, 2, false);
			}

			if (add) {
				for (Retangulo r : retangulos) {
					addListaCirculosElipses(r.getPontoEsquerdo(), r.getPontoDireito(), 1);
				}
			}

			for (Circulo c : circulos) {
				controleCirculo.desenharCirculo(g, cor, opcao, c);
			}
		} else {
			for (Circulo c : circulos) {
				controleCirculo.desenharCirculo(g, cor, opcao, c);
			}
		}
	}

	// ****************************************************************************
	// EXCLUIR CIRCULOS CIRCUNSCRITOS
	private void excluirCirculosCircunscritos(Graphics g) {

		pnCenario.ocultarDesenho();

		if (elipses.size() != 0) {
			for (Elipse e : elipses) {
				controleElipse.desenharElipse(Color.RED, g, e, 2);
			}
		}
		for (int i = 0; i < retangulos.size(); i++) {
			controleRetangulo.desenharRetangulo(retangulos.get(i), Color.BLACK, g);
			desenharBordas(retangulos.get(i), Color.BLACK, g);
		}

		circulos.clear();
	}

	// ****************************************************************************
	// DESENHAR ELIPSES
	// opcao -> método de desenho da elipse, clássico ou DDA
	// temporal = true -> clicou no botão de desenhar elipse
	// temporal = false -> clicou no botão desenhar círculo
	private void desenharElipse(Graphics g, Color cor, int opcao, boolean temporal) {
		if (temporal) {
			pnCenario.ocultarDesenho();
			desenharelipse = true;
			desenharRetanguloLista(Color.BLACK, desenho);

			if (desenharcirculo && circulos.size() != 0) {
				desenharCirculosCircunscritos(g, Color.BLUE, 4, false, false);
			}

			for (Retangulo r : retangulos) {
				addListaCirculosElipses(r.getPontoEsquerdo(), r.getPontoDireito(), 2);
			}

			for (Elipse e : elipses) {
				controleElipse.desenharElipse(cor, g, e, opcao);
			}
		} else {
			for (Elipse e : elipses) {
				controleElipse.desenharElipse(cor, g, e, opcao);
			}
		}
	}

	// ****************************************************************************
	// EXCLUIR ELIPSES CIRCUNSCRITAS
	private void excluirElipsesCircunscritas(Graphics g) {
		pnCenario.ocultarDesenho();

		if (circulos.size() != 0) {
			for (Circulo c : circulos) {
				controleCirculo.desenharCirculo(g, Color.BLUE, 3, c);
			}
		}
		for (Retangulo r : retangulos) {
			controleRetangulo.desenharRetangulo(r, Color.BLACK, g);
			desenharBordas(r, Color.BLACK, g);
		}
		elipses.clear();
	}

	// ****************************************************************************
	// ADICIONAR NA LISTA OS CIRCULOS E ELIPSES CIRCUNSCRITOS
	// opcao = 1 -> círculo
	// opcao = 2 -> elipse
	public void addListaCirculosElipses(Point pontoEsquerdo, Point pontoDireito, int opcao) {
		Point pCentro = new Point();
		int raio1, raio2;

		pCentro.x = (int) ((pontoEsquerdo.getX() + pontoDireito.getX()) / 2);
		pCentro.y = (int) ((pontoEsquerdo.getY() + pontoDireito.getY()) / 2);
		raio1 = (int) Math.abs(pCentro.getX() - pontoEsquerdo.getX());
		raio2 = (int) Math.abs(pCentro.getY() - pontoEsquerdo.getY());

		if (opcao == 1) {
			if (raio1 < raio2) {
				circulos.add(new Circulo(pCentro.getX(), pCentro.getY(), raio1));
			} else {
				circulos.add(new Circulo(pCentro.getX(), pCentro.getY(), raio2));
			}
		} else {
			elipses.add(new Elipse(pCentro.getX(), pCentro.getY(), raio2, raio1));
		}

	}
}
