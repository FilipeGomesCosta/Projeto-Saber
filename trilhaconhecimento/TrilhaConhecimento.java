package trilhaconhecimento;

import missoes.MochilaButton;
import missoes.Loja;
import missoes.Missao;
import bancodados.*;
import bancodados.modelo.*;
import bancodados.dao.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import login.FormularioLogin;

public class TrilhaConhecimento extends JFrame {

    private ImagemPanel mapaPanel;
    private DialogMissao dialogAtual;

    private static final int LARGURA_IMAGEM_ORIGINAL = 1280;
    private static final int ALTURA_IMAGEM_ORIGINAL = 696;
    private static int numeroMissao;

    private static String emailUsuario;
    String quantidadeMoedas = "" + ProgressoDAO.verificarMoedas(emailUsuario);

    private double fatorEscalaFonte;

    JProgressBar barraXP = new JProgressBar();

    JLabel lblNivel = new JLabel();
    JLabel lblMoedas = new JLabel(quantidadeMoedas);
    private final List<Buttons> buttonList = new ArrayList<>();

    public TrilhaConhecimento(int missaoAtual, String email) {
        emailUsuario = email;
        numeroMissao = missaoAtual;
        setTitle("Trilha do Conhecimento - Saber+");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        mapaPanel = new ImagemPanel("trilhaConhecimento" + numeroMissao + ".png");
        mapaPanel.setLayout(null);

        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        double proporcaoLargura = tamanhoTela.getWidth() / LARGURA_IMAGEM_ORIGINAL;
        double proporcaoAltura = tamanhoTela.getHeight() / ALTURA_IMAGEM_ORIGINAL;
        fatorEscalaFonte = (proporcaoLargura + proporcaoAltura) / 2.0;

        int x, y, tamanho;

        tamanho = (int) (70 * proporcaoLargura);
        x = (int) (330 * proporcaoLargura);
        y = (int) (550 * proporcaoAltura);

        int tamanhoFonte = (int) (30 * fatorEscalaFonte);
        lblMoedas.setFont(new Font("Comic Sans", Font.BOLD, tamanhoFonte));
        lblMoedas.setBounds(x, y, tamanho, tamanho);
        mapaPanel.add(lblMoedas);

        int tamanhoX = (int) (230 * proporcaoLargura);
        int tamanhoY = (int) (25 * proporcaoAltura);
        x = (int) (55 * proporcaoLargura);
        y = (int) (570 * proporcaoAltura);

        barraXP.setBounds(x, y, tamanhoX, tamanhoY);
        barraXP.setStringPainted(true);
        int xpAtual = ProgressoDAO.verificarXP(email);
        int progressoXP = xpAtual % 100;

        if (progressoXP == 0 && xpAtual != 0) {
            progressoXP = 100;
        }

        if (xpAtual >= 300) {
            progressoXP = 100;
        }

        barraXP.setValue(progressoXP);
        barraXP.setMaximum(100);
        barraXP.setForeground(new Color(43, 59, 3));
        mapaPanel.add(barraXP);

        lblNivel.setFont(new Font("Comic Sans", Font.BOLD, tamanhoFonte - 15));
        x = (int) (80 * proporcaoLargura);
        y = (int) (577 * proporcaoAltura);
        tamanhoX = (int) (355 * proporcaoLargura);

        lblNivel.setBounds(x, y, tamanhoX, tamanho);

        double nivel = ProgressoDAO.verificarNivel(email);
        if (nivel == 1) {
            lblNivel.setText("Nível 1: Iniciante");
        } else if (nivel == 2) {
            lblNivel.setText("Nível 2: Intermediário");
        } else {
            lblNivel.setText("Nível 3: Avançado");
        }

        mapaPanel.add(lblNivel);
        add(mapaPanel);

        int size = 70;

        buttonList.add(new Buttons(220, 170, size, size, 1, criarActionBotao("Adição com Números Inteiros", "Lição 1: Desafios de soma rápida.", 220, 170, 1, "https://drive.google.com/file/d/1-IDF9EUMI_NVX4hw5v-yRsSRFokz43JD/view?usp=sharing"), "https://drive.google.com/file/d/1-IDF9EUMI_NVX4hw5v-yRsSRFokz43JD/view?usp=sharing"));
        buttonList.add(new Buttons(245, 250, size, size, 2, criarActionBotao("Subtração com Números Inteiros", "Lição 2: Inclui problemas de dar troco.", 245, 250, 2, "https://drive.google.com/file/d/1pUc7FhEpmBhZs0oGWPgS10ilgHJI8X0y/view?usp=sharing"), "https://drive.google.com/file/d/1pUc7FhEpmBhZs0oGWPgS10ilgHJI8X0y/view?usp=sharing"));
        buttonList.add(new Buttons(315, 300, size, size, 3, criarActionBotao("Multiplicação", "Lição 3: Tabuada e multiplicação de 2 dígitos.", 315, 300, 3, "https://drive.google.com/file/d/1cVDHFUVyfthUzx7BKd2x3_pKZrSCH2a3/view?usp=sharing"), "https://drive.google.com/file/d/1cVDHFUVyfthUzx7BKd2x3_pKZrSCH2a3/view?usp=sharing"));
        buttonList.add(new Buttons(403, 320, size, size, 4, criarActionBotao("Divisão", "Lição 4: Divisão exata e problemas de partilha.", 403, 320, 4, "https://drive.google.com/file/d/1YOAyoZNRopkRfjZPHl1ZGxVNM2UDTgSx/view?usp=sharing"), "https://drive.google.com/file/d/1YOAyoZNRopkRfjZPHl1ZGxVNM2UDTgSx/view?usp=sharing"));
        buttonList.add(new Buttons(493, 312, size, size, 5, criarActionBotao("Desafio: Operações Básicas", "Desafio 5: Teste suas operações básicas.", 493, 312, 5, "https://drive.google.com/drive/folders/16A8_FDK4H6Mx8Dxyoyc332Rf1kMGc0dq?usp=sharing"), "https://drive.google.com/drive/folders/16A8_FDK4H6Mx8Dxyoyc332Rf1kMGc0dq?usp=sharing"));
        buttonList.add(new Buttons(558, 251, size, size, 6, criarActionBotao("Frações (Leitura e Representação)", "Lição 6: Leitura e representação de frações.", 558, 251, 6, "https://drive.google.com/file/d/1uKkJf3N6QgEKGjmrHF1udc3FSuKj_czd/view?usp=sharing"), "https://drive.google.com/file/d/1uKkJf3N6QgEKGjmrHF1udc3FSuKj_czd/view?usp=sharing"));
        buttonList.add(new Buttons(597, 173, size, size, 7, criarActionBotao("Operações com Frações", "Lição 7: Soma e subtração de frações.", 597, 173, 7, "https://drive.google.com/file/d/1LlGOTArMzcj_ayi2Ce5ScnNB4iE7l_bC/view?usp=sharing"), "https://drive.google.com/file/d/1LlGOTArMzcj_ayi2Ce5ScnNB4iE7l_bC/view?usp=sharing"));
        buttonList.add(new Buttons(678, 146, size, size, 8, criarActionBotao("Frações", "Lição 8: Multiplicação e divisão de frações.", 678, 146, 8, "https://drive.google.com/file/d/1BF8fWxlg0m1KyalO6BEbubYVyGzLvz-m/view?usp=sharing"), "https://drive.google.com/file/d/1BF8fWxlg0m1KyalO6BEbubYVyGzLvz-m/view?usp=sharing"));
        buttonList.add(new Buttons(766, 142, size, size, 9, criarActionBotao("Potenciação e Radiciação", "Lição 9: Quadrados e raízes exatas.", 766, 142, 9, "https://drive.google.com/file/d/1UhQbUjHpZEF4c6dVbxCKOSB1zK4_MalS/view?usp=sharing"), "https://drive.google.com/file/d/1UhQbUjHpZEF4c6dVbxCKOSB1zK4_MalS/view?usp=sharing"));
        buttonList.add(new Buttons(853, 143, size, size, 10, criarActionBotao("Decimais", "Lição 10: Operações e aplicações em compras", 853, 143, 10, "https://drive.google.com/file/d/1UBp5EcgrrRkO4py70idb7AE4nIzGDReI/view?usp=sharing"), "https://drive.google.com/file/d/1UBp5EcgrrRkO4py70idb7AE4nIzGDReI/view?usp=sharing"));
        buttonList.add(new Buttons(922, 191, size, size, 11, criarActionBotao("Desafio: Operações com Frações e Decimais", "Desafio 11: Teste suas operações fracionárias.", 922, 191, 11, "https://drive.google.com/drive/folders/1hM3V4GHIZYvzvDf999r2uj5cCz-eQqLw?usp=sharing"), "https://drive.google.com/drive/folders/1hM3V4GHIZYvzvDf999r2uj5cCz-eQqLw?usp=sharing"));
        buttonList.add(new Buttons(974, 265, size, size, 12, criarActionBotao("Porcentagem", "Lição 12: Aplicações em compras e juros simples.", 974, 265, 12, "https://drive.google.com/file/d/1S_73FlWR2dkT4sD2BXumaWqWklLt8OKc/view?usp=sharing"), "https://drive.google.com/file/d/1S_73FlWR2dkT4sD2BXumaWqWklLt8OKc/view?usp=sharing"));
        buttonList.add(new Buttons(937, 337, size, size, 13, criarActionBotao("Expressões Númericas", "Lição 13: Respeito à ordem de operações.", 937, 337, 13, "https://drive.google.com/file/d/1KPbOZMciTxrcNc7A_q9GJyUZB2Ixdd2P/view?usp=sharing"), "https://drive.google.com/file/d/1KPbOZMciTxrcNc7A_q9GJyUZB2Ixdd2P/view?usp=sharing"));
        buttonList.add(new Buttons(850, 367, size, size, 14, criarActionBotao("Equações do 1º Grau", "Lição 14: Resolução direta de equações e situações contextualizadas.", 850, 367, 14, "https://drive.google.com/file/d/1qYtOiL36kjkYUaKJq0YSla9p-75xfqMp/view?usp=sharing"), "https://drive.google.com/file/d/1qYtOiL36kjkYUaKJq0YSla9p-75xfqMp/view?usp=sharing"));
        buttonList.add(new Buttons(765, 378, size, size, 15, criarActionBotao("Razão e Proporção", "Lição 15: Noções de Proporção Direta", 765, 378, 15, "https://drive.google.com/file/d/19baNxgeEAznlrK8k7mKn3Xe0sbmIWr5U/view?usp=drive_link"), "https://drive.google.com/file/d/19baNxgeEAznlrK8k7mKn3Xe0sbmIWr5U/view?usp=drive_link"));
        buttonList.add(new Buttons(668, 379, size, size, 16, criarActionBotao("Desafio: Álgebra", "Desafio 16: Múltiplos conceitos de equações e proporção.", 668, 379, 16, "https://drive.google.com/drive/folders/1psq64NO04bXOeo6Fhy2iol3qdRGMMRIc?usp=sharing"), "https://drive.google.com/drive/folders/1psq64NO04bXOeo6Fhy2iol3qdRGMMRIc?usp=sharing"));
        buttonList.add(new Buttons(574, 394, size, size, 17, criarActionBotao("Figuras Planas (Perímetro)", "Lição 17: Cálculo de perímetro.", 574, 394, 17, "https://drive.google.com/file/d/1z6a4Z04aj-C-H9Hwuj9AMWdMn871IaS_/view?usp=sharing"), "https://drive.google.com/file/d/1z6a4Z04aj-C-H9Hwuj9AMWdMn871IaS_/view?usp=sharing"));
        buttonList.add(new Buttons(512, 453, size, size, 18, criarActionBotao("Figuras Planas (Área)", "Lição 18: Área (quadrado, retângulo, triângulo).", 512, 453, 18, "https://drive.google.com/file/d/1JfGaR2Mlt-qxtQVce5XQWzUjvIrEw_Wn/view?usp=sharing"), "https://drive.google.com/file/d/1JfGaR2Mlt-qxtQVce5XQWzUjvIrEw_Wn/view?usp=sharing"));
        buttonList.add(new Buttons(512, 530, size, size, 19, criarActionBotao("Estatística Básica", "Lição 19: Moda, média e mediana.", 512, 530, 19, "https://drive.google.com/file/d/152nxIb80L2Okc3rBLsm-3vq5NgyE0LUB/view?usp=sharing"), "https://drive.google.com/file/d/152nxIb80L2Okc3rBLsm-3vq5NgyE0LUB/view?usp=sharing"));
        buttonList.add(new Buttons(583, 572, size, size, 20, criarActionBotao("Tabelas e Gráficos", "Lição 20: Interpretação de dados.", 583, 572, 20, "https://drive.google.com/file/d/1QFwp1TLg180AXROkG6uJ-8TBKU3pEI7a/view?usp=sharing"), "https://drive.google.com/file/d/1QFwp1TLg180AXROkG6uJ-8TBKU3pEI7a/view?usp=sharing"));
        buttonList.add(new Buttons(682, 580, size, size, 21, criarActionBotao("Desafio: Gráficos, Estatística e Geometria", "Desafio 21: Múltiplos conceitos de geometria, estatística e gráficos.", 682, 580, 21, "https://drive.google.com/drive/folders/18Ij1Xtg6h2Xp_sT-m_-JNAQ_0iy8tBUV?usp=sharing"), "https://drive.google.com/drive/folders/18Ij1Xtg6h2Xp_sT-m_-JNAQ_0iy8tBUV?usp=sharing"));
        buttonList.add(new Buttons(768, 557, size, size, 22, criarActionBotao("Revisão Geral", "Lição 22: Revisão de todos os temas.", 768, 557, 22, "https://drive.google.com/drive/folders/1ibUJRWJUpmQmH8otBI5Zaf9MNhZoPwrb?usp=sharing"), "https://drive.google.com/drive/folders/1ibUJRWJUpmQmH8otBI5Zaf9MNhZoPwrb?usp=sharing"));
        buttonList.add(new Buttons(850, 535, size, size, 23, criarActionBotao("Desafio Final", "Desafio 23: Mescla de operações, álgebra, estatística e geometria.", 850, 535, 23, null), null));
        buttonList.add(new Buttons(1200, 84, size - 10, size - 10, 25, criarActionLoja(), null));
        buttonList.add(new Buttons(1200, 150, size - 10, size - 10, 26, criarActionMochila(), null));
        buttonList.add(new Buttons(1200, 20, size - 10, size - 10, 27, criarActionPessoa(), null));

        for (Buttons dados : buttonList) {
            if (dados.id > numeroMissao && dados.id != 25 && dados.id != 26 && dados.id != 27) {
                continue;
            }

            JButton button = new JButton();
            button.putClientProperty("buttonId", dados.id);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            if (dados.action != null) {
                button.addActionListener(dados.action);
            }
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            mapaPanel.add(button);
        }

        mapaPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                reposicionarButtons();
            }
        });

        SwingUtilities.invokeLater(this::reposicionarButtons);
        setVisible(true);

        if (numeroMissao == 24) {
            String opcoes[] = {"Continuar", "Recomeçar", "Sair"};
            var fimJogo = JOptionPane.showOptionDialog(null, "Fim de Jogo!", "Fim de Jogo! \nEscolha uma opção:", 0, 3, null, opcoes, opcoes[0]);
            if (fimJogo == 1) {
                Progresso progresso = new Progresso(emailUsuario, 1, 0, 1, 0);
                ProgressoDAO.zerarProgresso(progresso);
                Mochila mochila = new Mochila(emailUsuario, 0, 0);
                MochilaDAO.zerarMochila(mochila);
                this.dispose();
                new TrilhaConhecimento(1, emailUsuario);
            } else if (fimJogo == 2) {
                this.dispose();
                SwingUtilities.invokeLater(FormularioLogin::new);
            }
        }
    }

    private void reposicionarButtons() {
        int larguraPanel = mapaPanel.getWidth();
        int alturaPanel = mapaPanel.getHeight();

        if (larguraPanel == 0 || alturaPanel == 0) {
            return;
        }

        double escalaX = (double) larguraPanel / LARGURA_IMAGEM_ORIGINAL;
        double escalaY = (double) alturaPanel / ALTURA_IMAGEM_ORIGINAL;
        fatorEscalaFonte = (escalaX + escalaY) / 2.0; 

        int tamanho = (int) (70 * escalaX);
        int x = (int) (330 * escalaX);
        int y = (int) (590 * escalaY);
        int tamanhoFonte = (int) (30 * fatorEscalaFonte);
        lblMoedas.setFont(new Font("Comic Sans", Font.BOLD, tamanhoFonte));
        lblMoedas.setBounds(x, y, tamanho, tamanho);

        int tamanhoX_XP = (int) (230 * escalaX);
        int tamanhoY_XP = (int) (25 * escalaY);
        x = (int) (55 * escalaX);
        y = (int) (613 * escalaY);
        barraXP.setBounds(x, y, tamanhoX_XP, tamanhoY_XP);

        x = (int) (80 * escalaX);
        y = (int) (618 * escalaY);
        tamanhoX_XP = (int) (355 * escalaX);
        int tamanhoFonteNivel = (int) (tamanhoFonte - 15); 
        lblNivel.setFont(new Font("Comic Sans", Font.BOLD, tamanhoFonteNivel));
        lblNivel.setBounds(x, y, tamanhoX_XP, tamanho); 

        for (Component comp : mapaPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton buttonAtual = (JButton) comp;
                Object idObject = buttonAtual.getClientProperty("buttonId");
                if (!(idObject instanceof Integer)) {
                    continue;
                }
                int buttonId = (Integer) idObject;
                Buttons dados = buttonList.stream().filter(b -> b.id == buttonId).findFirst().orElse(null);
                if (dados == null) {
                    continue;
                }

                int novoX = (int) (dados.x1 * escalaX);
                int novoY = (int) (dados.y1 * escalaY);
                int novaLargura = (int) (dados.x2 * escalaX);
                int novaAltura = (int) (dados.y2 * escalaY);
                comp.setBounds(novoX, novoY, novaLargura, novaAltura);
            }
        }
    }

    private ActionListener criarActionBotao(final String titulo, final String licao, final int x1, final int y1, final int numeroMissaoClicada, final String urlMaterial) {
        final ActionListener iniciarLicaoAction = e -> {
            dispose();
            new Missao(emailUsuario, numeroMissaoClicada, 1);
        };

        return e -> mostrarDialogoMissao(titulo, licao, urlMaterial, (JButton) e.getSource(), x1, y1, iniciarLicaoAction);
    }

    private ActionListener criarActionLoja() {
        return e -> {
            dispose();
            new Loja(numeroMissao, 0, emailUsuario, null);
        };
    }

    private ActionListener criarActionMochila() {
        return e -> {
            dispose();
            new MochilaButton(numeroMissao, 0, emailUsuario, "");
        };
    }

    private ActionListener criarActionPessoa() {
        return e -> {
            String opcoes[] = {"Continuar", "Sair"};
            String nome = UsuarioDAO.verificarNome(emailUsuario);
            nome = "Olá, " + nome;
            var pessoa = JOptionPane.showOptionDialog(null, "Área do Usuário", nome, 0, 3, null, opcoes, opcoes[0]);
            if (pessoa == 1) {
                dispose();
                SwingUtilities.invokeLater(FormularioLogin::new);
            }
        };
    }

    private void mostrarDialogoMissao(String titulo, String licao, String urlMaterial, JButton buttonClicado, int x1, int y1, ActionListener iniciarLicaoAction) {
        if (dialogAtual != null) {
            dialogAtual.dispose();
        }

        DialogMissao dialog = new DialogMissao(this, titulo, licao, urlMaterial, iniciarLicaoAction);
        Point buttonLocalizacao = SwingUtilities.convertPoint(mapaPanel, buttonClicado.getLocation(), this);

        Dimension dialogTamanho = dialog.getPreferredSize();
        int dialogX = buttonLocalizacao.x + buttonClicado.getWidth() / 2 - dialogTamanho.width / 2;
        int dialogY = buttonLocalizacao.y - dialogTamanho.height - 20;

        if (x1 < 280) {
            dialogX = buttonLocalizacao.x + buttonClicado.getWidth() + 20;
        } else if (x1 > 950 || x1 == 558 || x1 == 493) {
            dialogX = buttonLocalizacao.x - dialogTamanho.width - 20;
        }

        if (dialogX < 10) {
            dialogX = 10;
        } else if (dialogX + dialogTamanho.width > getWidth() - 10) {
            dialogX = getWidth() - dialogTamanho.width - 10;
        }

        if (dialogY < 10) {
            dialogY = buttonLocalizacao.y + buttonClicado.getHeight() + 20;
        }

        dialog.setLocation(dialogX, dialogY);
        dialog.setVisible(true);
        dialogAtual = dialog;
    }
}
