package trilhaconhecimento;

import java.net.URI;
import java.awt.Desktop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class DialogMissao extends JDialog {

    private final String urlMaterial;

    public DialogMissao(JFrame parent, String titulo, String licao, String urlMaterial, ActionListener comecarAction) {
        super(parent, false);
        setTitle(titulo);
        setUndecorated(true);
        this.urlMaterial = urlMaterial;

        JPanel panelMissao = new JPanel();
        panelMissao.setLayout(new BoxLayout(panelMissao, BoxLayout.Y_AXIS));
        panelMissao.setBackground(new Color(240, 240, 240));
        panelMissao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMissao.add(lblTitulo);

        panelMissao.add(Box.createVerticalStrut(5));

        JLabel lblLicao = new JLabel("<html><p style='width:250px;'>" + licao + "</p></html>");
        lblLicao.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblLicao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMissao.add(lblLicao);

        panelMissao.add(Box.createVerticalStrut(15));

        JPanel pnlBotoes = new JPanel();
        pnlBotoes.setLayout(new GridLayout(1, 2, 10, 0));
        pnlBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlBotoes.setOpaque(false);

        JButton btnComecar = new JButton("COMEÇAR +10XP");
        if (urlMaterial != null) {
            if (urlMaterial.equals("https://drive.google.com/file/d/1cVDHFUVyfthUzx7BKd2x3_pKZrSCH2a3/view?usp=sharing")
                    || urlMaterial.equals("https://drive.google.com/file/d/1LlGOTArMzcj_ayi2Ce5ScnNB4iE7l_bC/view?usp=sharing")
                    || urlMaterial.equals("https://drive.google.com/file/d/1UBp5EcgrrRkO4py70idb7AE4nIzGDReI/view?usp=sharing")
                    || urlMaterial.equals("https://drive.google.com/file/d/1KPbOZMciTxrcNc7A_q9GJyUZB2Ixdd2P/view?usp=sharing")
                    || urlMaterial.equals("https://drive.google.com/file/d/1z6a4Z04aj-C-H9Hwuj9AMWdMn871IaS_/view?usp=sharing")
                    || urlMaterial.equals("https://drive.google.com/file/d/1QFwp1TLg180AXROkG6uJ-8TBKU3pEI7a/view?usp=sharing")) {
                btnComecar.setText("COMEÇAR +20XP");
            } else if(urlMaterial.equals("https://drive.google.com/drive/folders/16A8_FDK4H6Mx8Dxyoyc332Rf1kMGc0dq?usp=sharing")
                    ||urlMaterial.equals("https://drive.google.com/drive/folders/1hM3V4GHIZYvzvDf999r2uj5cCz-eQqLw?usp=sharing")
                    || urlMaterial.equals("https://drive.google.com/drive/folders/1psq64NO04bXOeo6Fhy2iol3qdRGMMRIc?usp=sharing")
                    || urlMaterial.equals("https://drive.google.com/drive/folders/18Ij1Xtg6h2Xp_sT-m_-JNAQ_0iy8tBUV?usp=sharing")){
                btnComecar.setText("COMEÇAR +12XP");
            }
        } else{
            btnComecar.setText("COMEÇAR +22XP");
        }

        btnComecar.setBackground(new Color(60, 179, 113));
        btnComecar.setForeground(Color.WHITE);
        btnComecar.setFocusPainted(false);
        btnComecar.setFont(new Font("Verdana", Font.BOLD, 12));
        btnComecar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnBaixarMaterial = null;
        if(urlMaterial != null){
         btnBaixarMaterial = new JButton("ACESSAR MATERIAL");
        btnBaixarMaterial.setBackground(new Color(255, 165, 0));
        btnBaixarMaterial.setForeground(Color.WHITE);
        btnBaixarMaterial.setFocusPainted(false);
        btnBaixarMaterial.setFont(new Font("Verdana", Font.BOLD, 12));
        btnBaixarMaterial.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        btnComecar.addActionListener(e -> {
            dispose();
            comecarAction.actionPerformed(e);
        });

        if(urlMaterial != null){
        btnBaixarMaterial.addActionListener(e -> {
            abrirUrl(this.urlMaterial);
        });
        }
        pnlBotoes.add(btnComecar);
        if (this.urlMaterial != null) {
            pnlBotoes.add(btnBaixarMaterial);
        }
        panelMissao.add(pnlBotoes);

        setContentPane(panelMissao);
        pack();

    }

    private void abrirUrl(String url) {
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
    }
}
