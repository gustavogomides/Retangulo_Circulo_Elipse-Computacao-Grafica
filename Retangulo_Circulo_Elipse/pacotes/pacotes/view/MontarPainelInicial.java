package pacotes.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pacotes.controle.*;

public class MontarPainelInicial implements MouseListener, MouseMotionListener {

	private JFrame baseFrame;
	private JPanel basePanel;
	private JPanel outputPanel;

	private JButton btEnd;
	private JButton btDesenharCirculoClassico;
	private JButton btDesenharCirculoPolarSimples;
	private JButton btDesenharCirculoPolarIncremental;
	private JButton btDesenharCirculoDDA;
	private JButton btDesenharElipseClassico;
	private JButton btDesenharElipseDDA;
	private JButton btExcluirCirculos;
	private JButton btExcluirElipses;
	private JButton btLimparTela;

	private JLabel labelVisor;

	private Graphics desenho;

	private ControlarAplicativo controlePrograma;

	private int cliques;

	private Point pontoPrimeiroClique;

	// ******************************************************************************************************************
	// MONTAR PAINEL INICIAL
	public MontarPainelInicial(ControlarAplicativo controlePrograma) {
		this.controlePrograma = controlePrograma;
		JPanel buttonPanel;

		// LAYOUT
		baseFrame = new JFrame();
		baseFrame.setLayout(new BoxLayout(baseFrame.getContentPane(), BoxLayout.Y_AXIS));

		baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // FITS PANEL TO THE
															// ACTUAL MONITOR
															// SIZE
		baseFrame.setUndecorated(true); // TURN OFF ALL THE PANEL BORDERS

		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());

		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout());

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 70));
		buttonPanel.setBackground(Color.BLUE);

		// ADICIONAR BOTÕES
		// Desenhar Circulo Metodo Classico
		btDesenharCirculoClassico = addAButton("Desenhar Círculo Método Clássico", "botaoClassico", buttonPanel);
		btDesenharCirculoClassico.addActionListener(controlePrograma);
		btDesenharCirculoClassico.setBackground(Color.CYAN);
		btDesenharCirculoClassico.setForeground(Color.BLACK);

		// Desenhar Circulo Metodo Polar Simples
		btDesenharCirculoPolarSimples = addAButton("Desenhar Círculo Método Polar Simples", "botaoPolarSimples",
				buttonPanel);
		btDesenharCirculoPolarSimples.addActionListener(controlePrograma);
		btDesenharCirculoPolarSimples.setBackground(Color.CYAN);
		btDesenharCirculoPolarSimples.setForeground(Color.BLACK);

		// Desenhar Circulo Metodo Polar Simples
		btDesenharCirculoPolarIncremental = addAButton("Desenhar Círculo Método Polar Incremental",
				"botaoPolarIncremental", buttonPanel);
		btDesenharCirculoPolarIncremental.addActionListener(controlePrograma);
		btDesenharCirculoPolarIncremental.setBackground(Color.CYAN);
		btDesenharCirculoPolarIncremental.setForeground(Color.BLACK);

		// Desenhar Circulo Metodo DDA
		btDesenharCirculoDDA = addAButton("Desenhar Círculo Método DDA", "botaoDDA", buttonPanel);
		btDesenharCirculoDDA.addActionListener(controlePrograma);
		btDesenharCirculoDDA.setBackground(Color.CYAN);
		btDesenharCirculoDDA.setForeground(Color.BLACK);

		// Excluir Circulos Circunscritos
		btExcluirCirculos = addAButton("Excluir Círculos Circunscritos", "botaoExcluirCirculo", buttonPanel);
		btExcluirCirculos.addActionListener(controlePrograma);
		btExcluirCirculos.setBackground(Color.CYAN);
		btExcluirCirculos.setForeground(Color.BLACK);

		// Desenhar Elipse Clássico
		btDesenharElipseClassico = addAButton("Desenhar Elipse Método Clássico", "botaoElipseClassico", buttonPanel);
		btDesenharElipseClassico.addActionListener(controlePrograma);
		btDesenharElipseClassico.setBackground(new Color(103, 103, 103));
		btDesenharElipseClassico.setForeground(Color.WHITE);

		// Desenhar Elipse DDA
		btDesenharElipseDDA = addAButton("Desenhar Elipse Método DDA", "botaoElipseDDA", buttonPanel);
		btDesenharElipseDDA.addActionListener(controlePrograma);
		btDesenharElipseDDA.setBackground(new Color(103, 103, 103));
		btDesenharElipseDDA.setForeground(Color.WHITE);

		// Excluir Elipses
		btExcluirElipses = addAButton("Excluir Elipses", "botaoExcluirElipse", buttonPanel);
		btExcluirElipses.addActionListener(controlePrograma);
		btExcluirElipses.setBackground(new Color(103, 103, 103));
		btExcluirElipses.setForeground(Color.WHITE);

		// Limpar Tela
		btLimparTela = addAButton("Limpar Tela", "botaoLimpar", buttonPanel);
		btLimparTela.addActionListener(controlePrograma);
		btLimparTela.setBackground(new Color(219, 61, 61));
		btLimparTela.setForeground(Color.WHITE);

		// Sair do Programa
		btEnd = addAButton("Sair", "botaoFim", buttonPanel);
		btEnd.addActionListener(controlePrograma);
		btEnd.setBackground(new Color(219, 61, 61));
		btEnd.setForeground(Color.WHITE);

		// OUVINTES DO MOUSE
		outputPanel.addMouseListener(this);
		outputPanel.addMouseMotionListener(this);

		// VISIBLE PANELS
		baseFrame.add(basePanel);
		basePanel.add(outputPanel, BorderLayout.CENTER);
		basePanel.add(buttonPanel, BorderLayout.PAGE_END);

		// LABEL VISOR
		labelVisor = new JLabel("");
		labelVisor.setHorizontalAlignment(SwingConstants.LEFT);
		outputPanel.add(labelVisor, BorderLayout.SOUTH);
		labelVisor.setBackground(Color.WHITE);
		labelVisor.setForeground(Color.BLACK);
		labelVisor.setBorder(new EmptyBorder(5, 5, 5, 5));

		baseFrame.setVisible(true);
	}

	// ******************************************************************************************************************
	// METODO UTILIZADO PARA ADICIONAR UM BOTAO A UM CONTAINER DO PROGRAMA
	// return JButton -> retorna o botão
	private JButton addAButton(String textoBotao, String textoControle, Container container) {
		JButton botao;

		botao = new JButton(textoBotao);
		botao.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(botao);

		botao.setActionCommand(textoControle);

		return (botao);
	}

	// ******************************************************************************************************************
	// METODO PARA MOSTRAR O FRAME BASICO
	public void showPanel() {
		basePanel.setVisible(true);
	}

	// ******************************************************************************************************************
	public void mouseClicked(MouseEvent evento) {
		Point P = new Point(evento.getX(), evento.getY());

		// CONTROLE DE CLIQUE
		if (this.cliques == 0) { // Primeiro clique
			this.cliques = 1;
			this.pontoPrimeiroClique = P;
			controlePrograma.desenharCirculoMarcacao(Color.RED, desenho, P.getX(), P.getY());

		} else { // Segundo clique
			if ((this.pontoPrimeiroClique.getY() != P.getY()) && (this.pontoPrimeiroClique.getX() != P.getX())) {
				this.cliques = 0;
				controlePrograma.desenharRetanguloPontos(this.pontoPrimeiroClique, P, Color.BLACK, desenho);
				controlePrograma.desenharCirculoMarcacao(new Color(238, 238, 238), desenho,
						this.pontoPrimeiroClique.getX(), this.pontoPrimeiroClique.getY());
			}
		}
	}

	// ******************************************************************************************************************
	public void mouseEntered(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mouseMoved(MouseEvent e) {
		Point P = new Point(e.getX(), e.getY());

		this.labelVisor.setText("Ponto: ( " + (int) P.getX() + " ; " + (int) P.getY() + " )");
	}

	// ******************************************************************************************************************
	public void mouseExited(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mousePressed(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mouseReleased(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mouseDragged(MouseEvent e) {
	}

	// ******************************************************************************************************************
	public Graphics iniciarGraphics() {
		desenho = outputPanel.getGraphics();
		return (desenho);
	}

	// ******************************************************************************************************************
	// OCULTAR O DESENHO
	public void ocultarDesenho() {
		Color cinza = new Color(238, 238, 238);
		desenho.clearRect(0, 0, outputPanel.getWidth(), outputPanel.getHeight());
		desenho.setColor(cinza);
	}

	// ******************************************************************************************************************
	// MENSAGEM INICIAL
	public void mensagemInicial() {
		JOptionPane.showMessageDialog(null, "Aulas 04 e 05- Retângulos, Círculos e Elipses", "Bem-vindo!",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// ******************************************************************************************************************
}
