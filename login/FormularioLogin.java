package login;

import trilhaconhecimento.TrilhaConhecimento;
import bancodados.*;
import bancodados.modelo.*;
import bancodados.dao.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class FormularioLogin extends JFrame implements ActionListener {

    private JLabel lblBoasVindas = new JLabel("Bem-vindo, estudante!");
    private JLabel lblSemCadastro = new JLabel("Não possui uma conta ainda?");
    private JLabel lblCadastreSe = new JLabel("<html><u>Cadastre-se</u></html>");
    private JLabel lblEsqueceuSenha = new JLabel("<html><u>Esqueceu a Senha?</u></html>");
    private JLabel lblNome = new JLabel("Nome Completo:");
    private JLabel lblEmail = new JLabel("E-mail:");
    private JLabel lblSenha = new JLabel("Senha:");
    private JLabel lblCadastrarEmail = new JLabel("E-mail:");
    private JLabel lblConfirmaSenha = new JLabel("Confirme a Senha:");
    private JLabel lblFacaLogin = new JLabel("<html><u>Fazer Login</u></html>");
    private JLabel lblJaTemConta = new JLabel("Já possui uma conta?");
    private JLabel lblNovaSenha = new JLabel("Nova Senha:");
    private JLabel lblConfirmaNovaSenha = new JLabel("Confirme a Nova Senha:");

    private JButton btnVerificarCodigo = new JButton("Verificar Código e Mudar Senha");
    private JButton btnContinuar = new JButton("Continuar");
    private JButton btnEnviarEmail = new JButton("Enviar Código de Verificação");
    private JButton btnCadastrar = new JButton("Cadastrar");

    private RoundedPasswordField txtNovaSenha = new RoundedPasswordField(20);
    private RoundedPasswordField txtConfirmaNovaSenha = new RoundedPasswordField(20);
    private RoundedPasswordField txtFieldSenha = new RoundedPasswordField(25);
    private RoundedPasswordField txtFieldConfirmarSenha = new RoundedPasswordField(25);
    private RoundedTextField txtFieldNome = new RoundedTextField(25);
    private RoundedTextField txtFieldCodigo = new RoundedTextField(10);
    private RoundedTextField txtFieldEmail = new RoundedTextField(25);

    private JPanel panelFaseEmail;
    private JPanel panelFaseCodigo;
    private JPanel panelDireitoAtual;

    URL logoUrl = getClass().getResource("/resource/logoSaber.png");
    ImageIcon logoOriginal;
    Image imagemOriginal;
    ImageIcon logoExibicao;
    Image logoAjustada;

    private final int TAMANHO_OLHO_BASE = 35;
    private ImageIcon olhoFechadoIcon;
    private ImageIcon olhoAbertoIcon;
    final char ECHO_CHAR_PADRAO = txtFieldSenha.getEchoChar();
    private boolean senhaVisivel = false;

    private final Color COR_BRANCA = new Color(255, 255, 255);
    private final Color COR_CINZA = new Color(235, 235, 235);
    private final Color COR_VERDE = new Color(74, 101, 57);
    private final Color COR_AZUL = new Color(99, 163, 60);

    private final double LARGURA_REFERENCIA = 1920.0;
    private final double ALTURA_REFERENCIA = 1080.0;
    private double fatorEscalaFonte;
    private Font FONTE_PADRAO;

    private UsuarioDAO usuarioDAO;

    public FormularioLogin() {
        super("Login - Saber+");
        this.usuarioDAO = new UsuarioDAO();
        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        double proporcaoLargura = tamanhoTela.getWidth() / LARGURA_REFERENCIA;
        double proporcaoAltura = tamanhoTela.getHeight() / ALTURA_REFERENCIA;
        fatorEscalaFonte = (proporcaoLargura + proporcaoAltura) / 2.0;

        int tamanhoBase = (int) (25 * fatorEscalaFonte);
        if (tamanhoBase < 12) {
            tamanhoBase = 12;
        }
        FONTE_PADRAO = new Font("Verdana", Font.PLAIN, tamanhoBase);

        logoOriginal = new ImageIcon(logoUrl);
        imagemOriginal = logoOriginal.getImage();

        int larguraOriginalLogo = 600;
        int alturaOriginalLogo = 650;

        int novaLarguraLogo = (int) (larguraOriginalLogo * fatorEscalaFonte);
        int novaAlturaLogo = (int) (alturaOriginalLogo * fatorEscalaFonte);

        logoAjustada = imagemOriginal.getScaledInstance(novaLarguraLogo, novaAlturaLogo, Image.SCALE_SMOOTH);
        logoExibicao = new ImageIcon(logoAjustada);

        URL olhoFechadoUrl = getClass().getResource("/resource/olhoFechado.png");
        URL olhoAbertoUrl = getClass().getResource("/resource/olhoAberto.png");

        int novoTamanhoOlho = (int) (TAMANHO_OLHO_BASE * fatorEscalaFonte);
        if (novoTamanhoOlho < 15) {
            novoTamanhoOlho = 15;
        }
        ImageIcon originalFechado = new ImageIcon(olhoFechadoUrl);
        Image imgFechado = originalFechado.getImage().getScaledInstance(novoTamanhoOlho, novoTamanhoOlho, Image.SCALE_SMOOTH);
        olhoFechadoIcon = new ImageIcon(imgFechado);

        ImageIcon originalAberto = new ImageIcon(olhoAbertoUrl);
        Image imgAberto = originalAberto.getImage().getScaledInstance(novoTamanhoOlho, novoTamanhoOlho, Image.SCALE_SMOOTH);
        olhoAbertoIcon = new ImageIcon(imgAberto);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panelGeral = new JPanel(new GridLayout(1, 2));
        setContentPane(panelGeral);

        JPanel panelEsquerdo = criarPanelLogo();
        panelGeral.add(panelEsquerdo);

        panelDireitoAtual = criarPainelLogin();
        panelGeral.add(panelDireitoAtual);

        configurarListenersLogin();

        btnContinuar.addActionListener(this);
        btnCadastrar.addActionListener(this);
        btnEnviarEmail.addActionListener(this);
        btnVerificarCodigo.addActionListener(this);

        setVisible(true);
    }

    private JPanel criarPanelLogo() {
        JPanel panelEsquerdo = new JPanel();
        panelEsquerdo.setBackground(COR_CINZA);
        panelEsquerdo.setLayout(new GridBagLayout());

        JPanel containerEsquerda = new JPanel();
        containerEsquerda.setLayout(new BorderLayout());

        JLabel lblLogo = new JLabel(logoExibicao);
        containerEsquerda.add(lblLogo, BorderLayout.CENTER);

        GridBagConstraints gbcCentral = new GridBagConstraints();
        gbcCentral.weightx = 1.0;
        gbcCentral.weighty = 1.0;
        gbcCentral.anchor = GridBagConstraints.CENTER;
        panelEsquerdo.add(containerEsquerda, gbcCentral);

        return panelEsquerdo;
    }

    private JPanel criarPainelLogin() {
        JPanel panelDireito = new JPanel();
        panelDireito.setBackground(COR_VERDE);
        panelDireito.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 30, 20, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        int altura_campo = (int) (20 * fatorEscalaFonte);

        lblBoasVindas.setText("Bem-vindo, estudante!");
        lblBoasVindas.setFont(new Font("Verdana", Font.BOLD, (int) (55 * fatorEscalaFonte)));
        lblBoasVindas.setForeground(COR_BRANCA);

        lblSemCadastro.setFont(FONTE_PADRAO);
        lblSemCadastro.setForeground(COR_BRANCA);

        lblCadastreSe.setFont(new Font("Verdana", Font.BOLD, (int) (25 * fatorEscalaFonte)));
        lblCadastreSe.setForeground(Color.YELLOW);

        lblEmail.setFont(FONTE_PADRAO);
        lblEmail.setForeground(COR_BRANCA);

        lblSenha.setFont(FONTE_PADRAO);
        lblSenha.setForeground(COR_BRANCA);

        txtFieldEmail.setFont(FONTE_PADRAO);
        txtFieldSenha.setFont(FONTE_PADRAO);

        lblEsqueceuSenha.setFont(new Font("Verdana", Font.BOLD, (int) (20 * fatorEscalaFonte)));
        lblEsqueceuSenha.setForeground(COR_BRANCA);
        lblEsqueceuSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelDireito.add(lblBoasVindas, gbc);

        JPanel panelSemCadastro = new JPanel(new FlowLayout(FlowLayout.LEFT, (int) (5 * fatorEscalaFonte), 0));
        panelSemCadastro.setBackground(COR_VERDE);
        panelSemCadastro.add(lblSemCadastro);
        lblCadastreSe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelSemCadastro.add(lblCadastreSe);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 30, (int) (40 * fatorEscalaFonte), 30);
        panelDireito.add(panelSemCadastro, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets((int) (20 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblEmail, gbc);

        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.ipady = altura_campo;
        panelDireito.add(txtFieldEmail, gbc);
        gbc.ipady = 0;

        gbc.gridy = 4;
        gbc.insets = new Insets((int) (30 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblSenha, gbc);

        gbc.gridy = 5;
        gbc.ipady = altura_campo;
        panelDireito.add(criarPanelSenha(txtFieldSenha), gbc);
        gbc.ipady = 0;

        gbc.gridy = 6;
        gbc.insets = new Insets((int) (15 * fatorEscalaFonte), 30, (int) (40 * fatorEscalaFonte), 30);
        gbc.anchor = GridBagConstraints.EAST;
        panelDireito.add(lblEsqueceuSenha, gbc);

        btnContinuar.setBackground(COR_AZUL);
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Verdana", Font.BOLD, (int) (30 * fatorEscalaFonte)));
        btnContinuar.setPreferredSize(new Dimension((int) (350 * fatorEscalaFonte), (int) (60 * fatorEscalaFonte)));
        btnContinuar.setFocusPainted(false);
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnContinuar.setBorder(BorderFactory.createEmptyBorder((int) (10 * fatorEscalaFonte), (int) (20 * fatorEscalaFonte), (int) (10 * fatorEscalaFonte), (int) (20 * fatorEscalaFonte)));

        gbc.gridy = 7;
        gbc.insets = new Insets((int) (20 * fatorEscalaFonte), 30, (int) (30 * fatorEscalaFonte), 30);
        gbc.anchor = GridBagConstraints.CENTER;
        panelDireito.add(btnContinuar, gbc);

        return panelDireito;
    }

    private JPanel criarPainelCadastro() {
        JPanel panelDireito = new JPanel();
        panelDireito.setBackground(COR_VERDE);
        panelDireito.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 30, 15, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        int altura_campo = (int) (20 * fatorEscalaFonte);

        lblBoasVindas.setText("Crie sua conta!");
        lblBoasVindas.setFont(new Font("Verdana", Font.BOLD, (int) (55 * fatorEscalaFonte)));
        lblBoasVindas.setForeground(COR_BRANCA);

        lblNome.setFont(FONTE_PADRAO);
        lblNome.setForeground(COR_BRANCA);

        lblConfirmaSenha.setFont(FONTE_PADRAO);
        lblConfirmaSenha.setForeground(COR_BRANCA);

        lblJaTemConta.setFont(FONTE_PADRAO);
        lblJaTemConta.setForeground(COR_BRANCA);

        lblFacaLogin.setFont(new Font("Verdana", Font.BOLD, (int) (25 * fatorEscalaFonte)));
        lblFacaLogin.setForeground(Color.YELLOW);
        lblFacaLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelDireito.add(lblBoasVindas, gbc);

        JPanel panelLogin = new JPanel(new FlowLayout(FlowLayout.LEFT, (int) (5 * fatorEscalaFonte), 0));
        panelLogin.setBackground(COR_VERDE);
        panelLogin.add(lblJaTemConta);
        panelLogin.add(lblFacaLogin);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 30, (int) (30 * fatorEscalaFonte), 30);
        panelDireito.add(panelLogin, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets((int) (15 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblNome, gbc);

        txtFieldNome.setFont(FONTE_PADRAO);
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.ipady = altura_campo;
        panelDireito.add(txtFieldNome, gbc);
        gbc.ipady = 0;

        gbc.gridy = 4;
        gbc.insets = new Insets((int) (15 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblEmail, gbc);

        txtFieldEmail.setFont(FONTE_PADRAO);
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.ipady = altura_campo;
        panelDireito.add(txtFieldEmail, gbc);
        gbc.ipady = 0;

        gbc.gridy = 6;
        gbc.insets = new Insets((int) (15 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblSenha, gbc);

        txtFieldSenha.setFont(FONTE_PADRAO);
        gbc.gridy = 7;
        gbc.ipady = altura_campo;
        panelDireito.add(criarPanelSenha(txtFieldSenha), gbc);
        gbc.ipady = 0;

        JLabel lblDescricaoSenha = new JLabel("A senha deve ter entre 5 e 10 caracteres.");
        lblDescricaoSenha.setFont(new Font("Verdana", Font.PLAIN, (int) (18 * fatorEscalaFonte)));
        lblDescricaoSenha.setForeground(COR_BRANCA);
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblDescricaoSenha, gbc);

        gbc.gridy = 9;
        gbc.insets = new Insets((int) (15 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblConfirmaSenha, gbc);

        txtFieldConfirmarSenha.setFont(FONTE_PADRAO);
        gbc.gridy = 10;
        gbc.ipady = altura_campo;
        panelDireito.add(criarPanelSenha(txtFieldConfirmarSenha), gbc);
        gbc.ipady = 0;

        btnCadastrar.setBackground(COR_AZUL);
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Verdana", Font.BOLD, (int) (30 * fatorEscalaFonte)));
        btnCadastrar.setPreferredSize(new Dimension((int) (350 * fatorEscalaFonte), (int) (60 * fatorEscalaFonte)));
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.setBorder(BorderFactory.createEmptyBorder((int) (10 * fatorEscalaFonte), (int) (20 * fatorEscalaFonte), (int) (10 * fatorEscalaFonte), (int) (20 * fatorEscalaFonte)));

        gbc.gridy = 11;
        gbc.insets = new Insets((int) (40 * fatorEscalaFonte), 30, (int) (30 * fatorEscalaFonte), 30);
        gbc.anchor = GridBagConstraints.CENTER;
        panelDireito.add(btnCadastrar, gbc);

        lblFacaLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mostrarPainelLogin();
            }
        });

        return panelDireito;
    }

    private JPanel criarPanelSenha(JPasswordField passwordField) {
        JPanel panelSenha = new JPanel(new BorderLayout());
        panelSenha.add(passwordField, BorderLayout.CENTER);

        JLabel lblOlho = new JLabel(olhoFechadoIcon);

        lblOlho.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblOlho.setOpaque(true);
        lblOlho.setBackground(Color.WHITE);
        lblOlho.addMouseListener(new MouseAdapter() {
            private boolean visivel = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!visivel) {
                    passwordField.setEchoChar((char) 0);
                    lblOlho.setIcon(olhoAbertoIcon);
                    visivel = true;
                } else {
                    passwordField.setEchoChar(ECHO_CHAR_PADRAO);
                    lblOlho.setIcon(olhoFechadoIcon);
                    visivel = false;
                }
            }
        });

        panelSenha.add(lblOlho, BorderLayout.EAST);
        return panelSenha;
    }

    private JPanel criarPainelRedefinicao() {
        JPanel panelDireito = new JPanel();
        panelDireito.setBackground(COR_VERDE);
        panelDireito.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 30, 20, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        int altura_campo = (int) (20 * fatorEscalaFonte);

        lblBoasVindas.setText("Recuperar Senha");
        lblBoasVindas.setFont(new Font("Verdana", Font.BOLD, (int) (55 * fatorEscalaFonte)));
        lblBoasVindas.setForeground(COR_BRANCA);

        lblEmail.setText("Digite seu E-mail:");
        lblEmail.setFont(FONTE_PADRAO);
        lblEmail.setForeground(COR_BRANCA);

        txtFieldEmail.setFont(FONTE_PADRAO);
        txtFieldEmail.setText(null);

        btnEnviarEmail.setBackground(COR_AZUL);
        btnEnviarEmail.setForeground(Color.WHITE);
        btnEnviarEmail.setFont(new Font("Verdana", Font.BOLD, (int) (30 * fatorEscalaFonte)));
        btnEnviarEmail.setPreferredSize(new Dimension((int) (350 * fatorEscalaFonte), (int) (60 * fatorEscalaFonte)));
        btnEnviarEmail.setFocusPainted(false);
        btnEnviarEmail.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEnviarEmail.setBorder(BorderFactory.createEmptyBorder((int) (10 * fatorEscalaFonte), (int) (20 * fatorEscalaFonte), (int) (10 * fatorEscalaFonte), (int) (20 * fatorEscalaFonte)));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelDireito.add(lblBoasVindas, gbc);

        JLabel lblInstrucao = new JLabel("Iremos enviar um código para redefinir sua senha.");
        lblInstrucao.setFont(FONTE_PADRAO);
        lblInstrucao.setForeground(COR_BRANCA);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 30, (int) (40 * fatorEscalaFonte), 30);
        panelDireito.add(lblInstrucao, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets((int) (20 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblEmail, gbc);

        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.ipady = altura_campo;
        panelDireito.add(txtFieldEmail, gbc);
        gbc.ipady = 0;

        gbc.gridy = 4;
        gbc.insets = new Insets((int) (40 * fatorEscalaFonte), 30, (int) (30 * fatorEscalaFonte), 30);
        gbc.anchor = GridBagConstraints.CENTER;
        panelDireito.add(btnEnviarEmail, gbc);

        JPanel panelFacaLogin = new JPanel(new FlowLayout(FlowLayout.LEFT, (int) (5 * fatorEscalaFonte), 0));
        panelFacaLogin.setBackground(COR_VERDE);

        lblJaTemConta.setFont(FONTE_PADRAO);
        lblJaTemConta.setForeground(COR_BRANCA);
        lblJaTemConta.setText("Lembrou da senha?");

        lblFacaLogin.setFont(new Font("Verdana", Font.BOLD, (int) (25 * fatorEscalaFonte)));
        lblFacaLogin.setForeground(Color.YELLOW);
        lblFacaLogin.setText("<html><u>Voltar ao Login</u></html>");

        lblJaTemConta.setFont(FONTE_PADRAO);
        lblJaTemConta.setForeground(COR_BRANCA);

        panelFacaLogin.add(lblJaTemConta);
        panelFacaLogin.add(lblFacaLogin);
        gbc.gridy = 5;
        gbc.insets = new Insets((int) (20 * fatorEscalaFonte), 30, (int) (40 * fatorEscalaFonte), 30);
        panelDireito.add(panelFacaLogin, gbc);
        lblFacaLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblFacaLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mostrarPainelLogin();
            }
        });

        return panelDireito;
    }

    private JPanel criarPainelVerificacao() {
        JPanel panelDireito = new JPanel();
        panelDireito.setBackground(COR_VERDE);
        panelDireito.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 30, 15, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        int altura_campo = (int) (20 * fatorEscalaFonte);

        lblBoasVindas.setText("Definir Nova Senha");
        lblBoasVindas.setFont(new Font("Verdana", Font.BOLD, (int) (55 * fatorEscalaFonte)));
        lblBoasVindas.setForeground(COR_BRANCA);

        JLabel lblCodigo = new JLabel("Código de Verificação:");
        lblCodigo.setFont(new Font("Verdana", Font.BOLD, (int) (25 * fatorEscalaFonte)));
        lblCodigo.setForeground(Color.YELLOW);

        txtFieldCodigo.setFont(FONTE_PADRAO);
        txtNovaSenha.setFont(FONTE_PADRAO);
        txtConfirmaNovaSenha.setFont(FONTE_PADRAO);

        lblNovaSenha.setFont(FONTE_PADRAO);
        lblNovaSenha.setForeground(COR_BRANCA);
        txtNovaSenha.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        lblConfirmaNovaSenha.setFont(FONTE_PADRAO);
        lblConfirmaNovaSenha.setForeground(COR_BRANCA);
        txtConfirmaNovaSenha.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        JLabel lblDescricaoSenha = new JLabel("A senha deve ter entre 5 e 10 caracteres.");
        lblDescricaoSenha.setFont(new Font("Verdana", Font.PLAIN, (int) (18 * fatorEscalaFonte)));
        lblDescricaoSenha.setForeground(COR_BRANCA);

        btnVerificarCodigo.setFont(FONTE_PADRAO);
        btnVerificarCodigo.setForeground(COR_BRANCA);
        btnVerificarCodigo.setBackground(COR_AZUL);
        btnVerificarCodigo.setForeground(Color.WHITE);
        btnVerificarCodigo.setFont(new Font("Verdana", Font.BOLD, (int) (30 * fatorEscalaFonte)));
        btnVerificarCodigo.setPreferredSize(new Dimension((int) (350 * fatorEscalaFonte), (int) (60 * fatorEscalaFonte)));
        btnVerificarCodigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerificarCodigo.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelDireito.add(lblBoasVindas, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets((int) (30 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblCodigo, gbc);

        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.ipady = altura_campo;
        panelDireito.add(txtFieldCodigo, gbc);
        gbc.ipady = 0;

        gbc.gridy = 3;
        gbc.insets = new Insets((int) (30 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblNovaSenha, gbc);

        gbc.gridy = 4;
        gbc.ipady = altura_campo;
        panelDireito.add(criarPanelSenha(txtNovaSenha), gbc);
        gbc.ipady = 0;

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblDescricaoSenha, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets((int) (30 * fatorEscalaFonte), 30, (int) (5 * fatorEscalaFonte), 30);
        panelDireito.add(lblConfirmaNovaSenha, gbc);

        gbc.gridy = 7;
        gbc.ipady = altura_campo;
        panelDireito.add(criarPanelSenha(txtConfirmaNovaSenha), gbc);
        gbc.ipady = 0;

        gbc.gridy = 8;
        gbc.insets = new Insets((int) (50 * fatorEscalaFonte), 30, (int) (30 * fatorEscalaFonte), 30);
        gbc.anchor = GridBagConstraints.CENTER;
        panelDireito.add(btnVerificarCodigo, gbc);

        return panelDireito;
    }

    public void mostrarPainelLogin() {
        getContentPane().remove(panelDireitoAtual);

        panelDireitoAtual = criarPainelLogin();

        getContentPane().add(panelDireitoAtual);

        getContentPane().revalidate();
        getContentPane().repaint();

        txtFieldEmail.requestFocus();
    }

    public void mostrarPainelCadastro() {
        getContentPane().remove(panelDireitoAtual);

        panelDireitoAtual = criarPainelCadastro();

        getContentPane().add(panelDireitoAtual);

        getContentPane().revalidate();
        getContentPane().repaint();
        txtFieldNome.requestFocus();
    }

    public void mostrarPainelRedefinicao() {
        getContentPane().remove(panelDireitoAtual);

        panelDireitoAtual = criarPainelRedefinicao();

        getContentPane().add(panelDireitoAtual);

        getContentPane().revalidate();
        getContentPane().repaint();

        txtFieldEmail.requestFocus();
    }

    public void mostrarPainelVerificacao() {
        getContentPane().remove(panelDireitoAtual);
        panelDireitoAtual = criarPainelVerificacao();
        getContentPane().add(panelDireitoAtual);

        getContentPane().revalidate();
        getContentPane().repaint();

        txtFieldCodigo.setText(null);
        txtNovaSenha.setText(null);
        txtConfirmaNovaSenha.setText(null);

        txtFieldCodigo.requestFocus();
    }

    private void configurarListenersLogin() {
        lblEsqueceuSenha.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mostrarPainelRedefinicao();
            }
        });

        lblCadastreSe.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mostrarPainelCadastro();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnContinuar) {
            String email = txtFieldEmail.getText();
            String senha = txtFieldSenha.getText();

            if (email.isEmpty() || senha.isEmpty()) {
                mostrarMensagem("Por favor, preencha todos os campos.", "Erro de Login", 0);
                return;
            }
            Usuario usuario = new Usuario(email, null, senha);
            boolean verificadorLogin = usuarioDAO.login(usuario);

            if (verificadorLogin) {
                ((JFrame) SwingUtilities.getWindowAncestor(btnContinuar)).dispose();
                int numMissao = ProgressoDAO.verificarMissao(email);
                TrilhaConhecimento trilhaConhecimento = new TrilhaConhecimento(numMissao, email);
            } else {
                mostrarMensagem("E-mail e/ou senha incorretos/inexistentes.", "Login Mal-Sucedido", 0);
                txtFieldEmail.setText(null);
                txtFieldSenha.setText(null);
            }
        } else if (e.getSource() == btnCadastrar) {
            String emailDigitado = txtFieldEmail.getText();

            if (validarSenha(txtFieldSenha.getText()) && txtFieldSenha.getText().equals(txtFieldConfirmarSenha.getText()) && Email.validarEmail(txtFieldEmail.getText())) {
                if (usuarioDAO.verificarEmailExistente(emailDigitado)) {
                    mostrarMensagem("Este e-mail já está cadastrado. Por favor, cadastre outro e-mail ou faça login", "Erro de Cadastro", 0);
                    return;
                }
                Usuario novoUsuario = new Usuario(
                        txtFieldEmail.getText(),
                        txtFieldNome.getText(),
                        txtFieldSenha.getText()
                );

                boolean sucessoCadastro = usuarioDAO.cadastrar(novoUsuario);
                if (sucessoCadastro) {
                    mostrarMensagem("Cadastro Bem-Sucedido!", "Cadastro Bem-Sucedido", 1);
                    limparCampos();
                    mostrarPainelLogin();
                } else {
                    mostrarMensagem("Falha interna ao realizar o cadastro. Tente novamente mais tarde.", "Erro no Servidor", 0);
                }

            } else {
                mostrarMensagem("Dados fornecidos inadequados.", "Cadastro Mal-Sucedido", 0);
                txtFieldSenha.setText(null);
                txtFieldConfirmarSenha.setText(null);
            }
        } else if (e.getSource() == btnEnviarEmail) {
            String email = txtFieldEmail.getText();

            if (Email.validarEmail(email) && usuarioDAO.verificarEmailExistente(email)) {
                btnEnviarEmail.setEnabled(false);

                new Thread(() -> {
                    boolean enviado = Email.mandarEmail(email);

                    SwingUtilities.invokeLater(() -> {
                        btnEnviarEmail.setEnabled(true);
                        if (enviado) {
                            mostrarMensagem("E-mail enviado!", "E-mail enviado", 1);
                            mostrarPainelVerificacao();
                        } else {
                            mostrarMensagem("Erro no envio.", "Erro", 0);
                        }
                    });
                }).start();

            } else {
                mostrarMensagem("Por favor, verifique o e-mail inserido e tente novamente.", "Erro", 0);
            }
        } else if (e.getSource() == btnVerificarCodigo) {

            String codigoDigitado = txtFieldCodigo.getText();
            String novaSenha = new String(txtNovaSenha.getPassword());
            String confirmaNovaSenha = new String(txtConfirmaNovaSenha.getPassword());
            String email = txtFieldEmail.getText();
            
            String codigoGerado = new String(Email.getCodigoVerificacao()); 

            if (Email.getCodigoVerificacao() != null && codigoDigitado.equals(codigoGerado)) {

                if (!validarSenha(novaSenha)) {
                    mostrarMensagem("A senha deve ter entre 5 e 10 caracteres.", "Erro de Senha", 0);
                    txtNovaSenha.setText("");
                    txtNovaSenha.repaint();
                    txtNovaSenha.requestFocusInWindow();
                    return;
                }

                if (novaSenha.equals(confirmaNovaSenha)) {
                    Usuario usuario = new Usuario(email, null, novaSenha);
                    usuarioDAO.atualizarSenha(usuario);
                    mostrarMensagem("Senha alterada com sucesso! Faça login com a nova senha.", "Redefinição de Senha bem-sucedida", 1);
                    
                    mostrarPainelLogin();

                } else {
                    mostrarMensagem("As novas senhas não coincidem.", "Erro de Senha", 2);
                    txtNovaSenha.setText("");
                    txtNovaSenha.repaint();
                    txtNovaSenha.requestFocusInWindow();

                }

            } else {
                mostrarMensagem("Código de verificação incorreto. Tente novamente.", "Erro de Verificação", 0);
                txtFieldCodigo.setText("");
                txtFieldCodigo.repaint();
                txtFieldCodigo.requestFocusInWindow();
            }

        }
    }

    public boolean validarSenha(String senha) {
        return (senha != null && senha.length() > 4 && senha.length() < 11);
    }

    public void mostrarMensagem(String mensagem, String titulo, int numero) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, numero);
    }

    public void limparCampos() {
        txtFieldNome.setText(null);
        txtFieldEmail.setText(null);
        txtFieldSenha.setText(null);
        txtFieldConfirmarSenha.setText(null);
    }

}