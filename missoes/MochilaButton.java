
package missoes;

import bancodados.modelo.*;
import bancodados.*;
import bancodados.dao.*;
import java.awt.Dimension;
import java.awt.*;
import java.net.URI;
import javax.swing.*;
import trilhaconhecimento.ImagemPanel;
import trilhaconhecimento.TrilhaConhecimento;
import missoes.Missao; 

public class MochilaButton extends JFrame {

    private ImagemPanel mochilaPanel;

    private static final int LARGURA_IMAGEM_ORIGINAL = 1280;
    private static final int ALTURA_IMAGEM_ORIGINAL = 696;

    private static int missaoAtual;
    private static int questaoAtual;
    
    private static String email;

    public MochilaButton(int numeroMissao, int numeroQuestao, String emailUsuario, String dicas) {
        missaoAtual = numeroMissao;
        questaoAtual = numeroQuestao;
        email = emailUsuario;

        setTitle("Mochila - Saber+");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        mochilaPanel = new ImagemPanel("mochila.png");
        mochilaPanel.setLayout(null);

        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        double proporcaoLargura = tamanhoTela.getWidth() / LARGURA_IMAGEM_ORIGINAL;
        double proporcaoAltura = tamanhoTela.getHeight() / ALTURA_IMAGEM_ORIGINAL;

        double fatorEscalaFonte = (proporcaoLargura + proporcaoAltura) / 2.0;

        int x, y, tamanhoX, tamanhoY;
        int tamanho;

        x = (int) (595 * proporcaoLargura);
        y = (int) (195 * proporcaoAltura);
        tamanho = (int) (80 * proporcaoLargura); 

        String papiros = "" + MochilaDAO.verificarPapiro(email);
        JLabel lblPapiros = new JLabel(papiros);
        mochilaPanel.add(lblPapiros);
        int tamanhoFonte = (int) (30 * fatorEscalaFonte);
        lblPapiros.setFont(new Font("Comic Sans", Font.BOLD, tamanhoFonte));
        lblPapiros.setBounds(x, y, tamanho, tamanho);
        
        
        x = (int) (770 * proporcaoLargura);

        String lampadas = "" + MochilaDAO.verificarLampada(email);
        JLabel lblLampadas = new JLabel(lampadas);
        mochilaPanel.add(lblLampadas);
        lblLampadas.setFont(new Font("Comic Sans", Font.BOLD, tamanhoFonte));
        lblLampadas.setBounds(x, y, tamanho, tamanho);

        
        x = (int) (455 * proporcaoLargura);
        y = (int) (350 * proporcaoAltura);
        tamanhoX = (int) (165 * proporcaoLargura);
        tamanhoY = (int) (50 * proporcaoAltura);

        JButton btnUsarPapiro = new JButton();
        btnUsarPapiro.setBounds(x, y, tamanhoX, tamanhoY);
        configurarBotao(btnUsarPapiro);
        btnUsarPapiro.addActionListener(e -> {
            int quantidadePapiro = MochilaDAO.verificarPapiro(email);
            if (questaoAtual == 0) {
                JOptionPane.showMessageDialog(null, "Entre numa missão para usar seu papiro!", "Erro", 0);
            } else {
                if (quantidadePapiro > 0) {
                    Mochila mochila = new Mochila(email, 0, 0);
                    MochilaDAO.usarPapiro(mochila);
                    int novaQuantidadePapiro = MochilaDAO.verificarPapiro(email);
                    lblPapiros.setText(novaQuantidadePapiro + "");
                    lblPapiros.updateUI();
                    JOptionPane.showMessageDialog(null, dicas, "Dica da Questão" + questaoAtual, 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Você não possui papiros!", "Falha de uso", 0);
                }
            }
        });

        mochilaPanel.add(btnUsarPapiro);

 
        x = (int) (640 * proporcaoLargura);

        JButton btnUsarLampada = new JButton();
        btnUsarLampada.setBounds(x, y, tamanhoX, tamanhoY);
        configurarBotao(btnUsarLampada);
        btnUsarLampada.addActionListener(e -> {
            if (questaoAtual == 0) {
                JOptionPane.showMessageDialog(null, "Entre numa missão para usar sua lâmpada!", "Erro", 0);
            } else {
                int quantidadeLampada = MochilaDAO.verificarLampada(email);
                if (quantidadeLampada > 0) {
                    Mochila mochila = new Mochila(email, 0, 0);
                    MochilaDAO.usarLampada(mochila);
                    int novaQuantidadeLampada = MochilaDAO.verificarLampada(email);
                    lblLampadas.setText(novaQuantidadeLampada + "");
                    lblLampadas.updateUI();
                    String url = getURL(missaoAtual);
                    try {
                        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                            Desktop.getDesktop().browse(new URI(url));
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "Seu sistema não suporta a abertura automática de links. Copie o link abaixo:\n" + url,
                                    "Atenção", 0);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                                "Erro ao tentar abrir o link. Verifique o formato da URL ou sua conexão.",
                                "Erro de Acesso", 0);
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Você não possui lâmpadas!", "Falha de Uso", 0);
                }
            }
        });
        mochilaPanel.add(btnUsarLampada);

        
        x = (int) (1070 * proporcaoLargura);
        y = (int) (550 * proporcaoAltura);
        tamanhoX = (int) (163 * proporcaoLargura);
        tamanhoY = (int) (60 * proporcaoAltura);

        JButton btnSair = new JButton();
        btnSair.setBounds(x, y, tamanhoX, tamanhoY);
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
        mochilaPanel.add(btnSair);

        x = (int) (440 * proporcaoLargura);
        y = (int) (85 * proporcaoAltura);
        tamanhoX = (int) (80 * proporcaoLargura);
        tamanhoY = (int) (85 * proporcaoAltura);

        JButton btnLoja = new JButton();
        btnLoja.setBounds(x, y, tamanhoX, tamanhoY);
        btnLoja.addActionListener(e -> {
            dispose();
            Loja loja = new Loja(missaoAtual, questaoAtual, email, dicas);
        });
        mochilaPanel.add(btnLoja);

       
        x = (int) (735 * proporcaoLargura);

        JButton btnMochila = new JButton();
        btnMochila.setBounds(x, y, tamanhoX, tamanhoY);
        mochilaPanel.add(btnMochila);

        configurarBotao(btnLoja);
        configurarBotao(btnMochila);
        
        add(mochilaPanel);

        setVisible(true);

    }

    public void configurarBotao(JButton button) {
        button.setOpaque(false);
       button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private String getURL(int missaoAtual) {
        String url = null;

        if (missaoAtual == 1) {
            url = "https://drive.google.com/file/d/1-IDF9EUMI_NVX4hw5v-yRsSRFokz43JD/view?usp=sharing";
        } else if (missaoAtual == 2) {
            url = "https://drive.google.com/file/d/1pUc7FhEpmBhZs0oGWPgS10ilgHJI8X0y/view?usp=sharing";
        } else if (missaoAtual == 3) {
            url = "https://drive.google.com/file/d/1cVDHFUVyfthUzx7BKd2x3_pKZrSCH2a3/view?usp=sharing";
        } else if (missaoAtual == 4) {
            url = "https://drive.google.com/file/d/1YOAyoZNRopkRfjZPHl1ZGxVNM2UDTgSx/view?usp=sharing";
        } else if (missaoAtual == 5) {
            url = "https://drive.google.com/drive/folders/16A8_FDK4H6Mx8Dxyoyc332Rf1kMGc0dq?usp=sharing";
        } else if (missaoAtual == 6) {
            url = "https://drive.google.com/file/d/1uKkJf3N6QgEKGjmrHF1udc3FSuKj_czd/view?usp=sharing";
        } else if (missaoAtual == 7) {
            url = "https://drive.google.com/file/d/1LlGOTArMzcj_ayi2Ce5ScnNB4iE7l_bC/view?usp=sharing";
        } else if (missaoAtual == 8) {
            url = "https://drive.google.com/file/d/1BF8fWxlg0m1KyalO6BEbubYVyGzLvz-m/view?usp=sharing";
        } else if (missaoAtual == 9) {
            url = "https://drive.google.com/file/d/1UhQbUjHpZEF4c6dVbxCKOSB1zK4_MalS/view?usp=sharing";
        } else if (missaoAtual == 10) {
            url = "https://drive.google.com/file/d/1UBp5EcgrrRkO4py70idb7AE4nIzGDReI/view?usp=sharing";
        } else if (missaoAtual == 11) {
            url = "https://drive.google.com/drive/folders/1hM3V4GHIZYvzvDf999r2uj5cCz-eQqLw?usp=sharing";
        } else if (missaoAtual == 12) {
            url = "https://drive.google.com/file/d/1S_73FlWR2dkT4sD2BXumaWqWklLt8OKc/view?usp=sharing";
        } else if (missaoAtual == 13) {
            url = "https://drive.google.com/file/d/1KPbOZMciTxrcNc7A_q9GJyUZB2Ixdd2P/view?usp=sharing";
        } else if (missaoAtual == 14) {
            url = "https://drive.google.com/file/d/1qYtOiL36kjkYUaKJq0YSla9p-75xfqMp/view?usp=sharing";
        } else if (missaoAtual == 15) {
            url = "https://drive.google.com/file/d/19baNxgeEAznlrK8k7mKn3Xe0sbmIWr5U/view?usp=drive_link";
        } else if (missaoAtual == 16) {
            url = "https://drive.google.com/drive/folders/1psq64NO04bXOeo6Fhy2iol3qdRGMMRIc?usp=sharing";
        } else if (missaoAtual == 17) {
            url = "https://drive.google.com/file/d/1z6a4Z04aj-C-H9Hwuj9AMWdMn871IaS_/view?usp=sharing";
        } else if (missaoAtual == 18) {
            url = "https://drive.google.com/file/d/1JfGaR2Mlt-qxtQVce5XQWzUjvIrEw_Wn/view?usp=sharing";
        } else if (missaoAtual == 19) {
            url = "https://drive.google.com/file/d/152nxIb80L2Okc3rBLsm-3vq5NgyE0LUB/view?usp=sharing";
        } else if (missaoAtual == 20) {
            url = "https://drive.google.com/file/d/1QFwp1TLg180AXROkG6uJ-8TBKU3pEI7a/view?usp=sharing";
        } else if (missaoAtual == 21) {
            url = "https://drive.google.com/drive/folders/18Ij1Xtg6h2Xp_sT-m_-JNAQ_0iy8tBUV?usp=sharing";
        } else if (missaoAtual == 22) {
            url = "https://drive.google.com/drive/folders/1ibUJRWJUpmQmH8otBI5Zaf9MNhZoPwrb?usp=sharing";
        } else if (missaoAtual == 23) {
            url = "https://drive.google.com/drive/folders/1ibUJRWJUpmQmH8otBI5Zaf9MNhZoPwrb?usp=sharing";
        }

        return url;
    }
}