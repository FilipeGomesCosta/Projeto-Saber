package trilhaconhecimento;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagemPanel extends JPanel {

    private Image imagemMapa;
    private String nomeImagem;
    private int imagemOriginalLargura = -1;
    private int imagemOriginalAltura = -1;

    private static final int LARGURA_PADRAO = 1280;
    private static final int ALTURA_PADRAO = 720;

    public ImagemPanel(String nomeImagem) {
        this.nomeImagem = nomeImagem;

        URL imagemURL = getClass().getResource("/resource/" + nomeImagem);

        try {
            ImageIcon icon = new ImageIcon(imagemURL);
            this.imagemMapa = icon.getImage();
            imagemOriginalLargura = imagemMapa.getWidth(this);
            imagemOriginalAltura = imagemMapa.getHeight(this);

        } catch (Exception e) {
        }

        if (this.imagemMapa != null && imagemOriginalLargura != -1 && imagemOriginalAltura != -1) {
            setPreferredSize(new Dimension(imagemOriginalLargura, imagemOriginalAltura));
        } else {
            setPreferredSize(new Dimension(LARGURA_PADRAO, ALTURA_PADRAO));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.imagemMapa != null) {
            int panelLargura = getWidth();
            int panelAltura = getHeight();
            g.drawImage(imagemMapa, 0, 0, panelLargura, panelAltura, this);
        } else {
            g.setColor(java.awt.Color.RED);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(java.awt.Color.WHITE);
            g.drawString("ERRO: Imagem Não Carregada", 10, 20);
        }
    }
}
