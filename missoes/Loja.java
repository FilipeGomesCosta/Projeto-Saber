package missoes;

import bancodados.modelo.*;
import bancodados.dao.*;
import bancodados.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import trilhaconhecimento.ImagemPanel;
import trilhaconhecimento.TrilhaConhecimento;

public class Loja extends JFrame {

    private ImagemPanel lojaPanel;

    private static final int LARGURA_IMAGEM_ORIGINAL = 1280;
    private static final int ALTURA_IMAGEM_ORIGINAL = 696;

    private int quantidadeMoedas;
    private static int missaoAtual;
    private static int questaoAtual;
    private static String email;
    private static String dicas;

    public Loja(int numeroMissao, int numeroQuestao, String emailUsuario, String dica) {
        missaoAtual = numeroMissao;
        questaoAtual = numeroQuestao;
        email = emailUsuario;
        dicas = dica;
        
        setTitle("Loja - Saber+");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        lojaPanel = new ImagemPanel("loja.png");
        lojaPanel.setLayout(null);

        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        double proporcaoLargura = tamanhoTela.getWidth() / LARGURA_IMAGEM_ORIGINAL;
        double proporcaoAltura = tamanhoTela.getHeight() / ALTURA_IMAGEM_ORIGINAL;
        
        double fatorEscalaFonte = (proporcaoLargura + proporcaoAltura) / 2.0;


        String moedas = "" + ProgressoDAO.verificarMoedas(email);
        JLabel lblMoedas = new JLabel(moedas);

        int tamanhoFonte = (int) (30 * fatorEscalaFonte);
        lblMoedas.setFont(new Font("Comic Sans", Font.BOLD, tamanhoFonte));
        
        lblMoedas.setBounds(
            (int) (610 * proporcaoLargura), 
            (int) (160 * proporcaoAltura), 
            (int) (90 * proporcaoLargura), 
            (int) (90 * proporcaoAltura) 
        );
        lojaPanel.add(lblMoedas);


        JButton btnComprarPapiro = new JButton();
        
        btnComprarPapiro.setBounds(
            (int) (350 * proporcaoLargura), 
            (int) (455 * proporcaoAltura), 
            (int) (140 * proporcaoLargura), 
            (int) (60 * proporcaoAltura)
        );
        
        btnComprarPapiro.addActionListener(e -> {
            quantidadeMoedas = ProgressoDAO.verificarMoedas(email);
            if (quantidadeMoedas >= 10 && quantidadeMoedas > 0) {
                Mochila mochila = new Mochila(email, 1, 0);
                MochilaDAO.comprarPapiro(mochila);
                JOptionPane.showMessageDialog(null, "Papiro comprado!", "Compra bem-sucedida", 1);
                quantidadeMoedas = ProgressoDAO.verificarMoedas(email);
                lblMoedas.setText(quantidadeMoedas + "");
                lblMoedas.updateUI();
            } else {
                JOptionPane.showMessageDialog(null, "Quantidade de Moedas Insuficiente.", "Compra mal-sucedida", -1);
            }
        });
        configurarBotao(btnComprarPapiro);
        lojaPanel.add(btnComprarPapiro);

        JButton btnComprarLampada = new JButton();

        btnComprarLampada.setBounds(
            (int) (782 * proporcaoLargura), 
            (int) (455 * proporcaoAltura), 
            (int) (140 * proporcaoLargura), 
            (int) (60 * proporcaoAltura)
        );
        
        btnComprarLampada.addActionListener(e -> {
            quantidadeMoedas = ProgressoDAO.verificarMoedas(email);
            if (quantidadeMoedas >= 15 && quantidadeMoedas > 0) {
                Mochila mochila = new Mochila(email, 0, 1);
                MochilaDAO.comprarLampada(mochila);
                JOptionPane.showMessageDialog(null, "Lâmpada comprada!", "Compra bem-sucedida", 1);
                quantidadeMoedas = ProgressoDAO.verificarMoedas(email);
                lblMoedas.setText(quantidadeMoedas + "");
                lblMoedas.updateUI();
            } else {
                JOptionPane.showMessageDialog(null, "Quantidade de Moedas Insuficiente.", "Compra mal-sucedida", -1);
            }
        });
        configurarBotao(btnComprarLampada);
        lojaPanel.add(btnComprarLampada);

        JButton btnSair = new JButton();

        btnSair.setBounds(
            (int) (1070 * proporcaoLargura), 
            (int) (550 * proporcaoAltura), 
            (int) (163 * proporcaoLargura), 
            (int) (60 * proporcaoAltura)
        );
        
        btnSair.addActionListener(e -> {
            if (questaoAtual == 0) {
                this.dispose();
                TrilhaConhecimento trilhaConhecimento = new TrilhaConhecimento(missaoAtual, email);
            } else {
                this.dispose();
                Missao missao = new Missao(email, missaoAtual, questaoAtual);
            }

        });
        configurarBotao(btnSair);

        lojaPanel.add(btnSair);

        JButton btnLoja = new JButton();
        
        btnLoja.setBounds(
            (int) (500 * proporcaoLargura), 
            (int) (80 * proporcaoAltura), 
            (int) (50 * proporcaoLargura), 
            (int) (50 * proporcaoAltura)
        );
        
        configurarBotao(btnLoja);
        lojaPanel.add(btnLoja);

        JButton btnMochila = new JButton();

        btnMochila.setBounds(
            (int) (735 * proporcaoLargura), 
            (int) (80 * proporcaoAltura), 
            (int) (50 * proporcaoLargura), 
            (int) (50 * proporcaoAltura)
        );
        
        configurarBotao(btnMochila);
        btnMochila.addActionListener(e ->{
            dispose();
        MochilaButton mochilaButton = new MochilaButton(missaoAtual, questaoAtual, email, dicas);
        });
        lojaPanel.add(btnMochila);

        add(lojaPanel);

        setVisible(true);
    }

    public void configurarBotao(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}