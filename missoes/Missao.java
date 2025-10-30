package missoes;

import bancodados.modelo.*;
import bancodados.*;
import bancodados.dao.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import trilhaconhecimento.*;

public class Missao extends JFrame implements ActionListener {

    private JPanel pnlAtual = null;

    private final double LARGURA_REFERENCIA = 1920.0;
    private final double ALTURA_REFERENCIA = 1080.0;

    private static final int VALOR_XP = 10;
    private static final int VALOR_XP_DESAFIO = 12;
    private static final int VALOR_XP_BONUS = 20;
    private static final int MOEDAS = 20;
    private static final int MOEDAS_BONUS = 40;
    private static int numeroQuestao = 1;
    private static int missao;

    JButton btnOpcao1 = new JButton();
    JButton btnOpcao2 = new JButton();
    JButton btnOpcao3 = new JButton();
    JButton btnOpcao4 = new JButton();
    JButton btnLoja = new JButton();
    JButton btnMochila = new JButton();

    static String emailUsuario;
    private static final int MAX_QUESTOES = 10;

    private static final Map<Integer, JButton> RESPOSTAS_CORRETAS = new HashMap<>();
    private static String dicasEscritas[];

    public Missao(String email, int missaoClicada, int numeroQuestao) {
        emailUsuario = email;
        missao = missaoClicada;
        Integer missaoInteger = missao;
        this.numeroQuestao = numeroQuestao;

        configurarBotao(btnOpcao1);
        configurarBotao(btnOpcao2);
        configurarBotao(btnOpcao3);
        configurarBotao(btnOpcao4);
        configurarBotao(btnLoja);
        configurarBotao(btnMochila);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        if (missao == 1) {
            inicializarRespostas();
            setTitle("Missão 1 - Adição");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Lembre-se de agrupar as dezenas ao somar 98+45.";
            dicasEscritas[2] = "A soma de um número redondo (60) com um dígito (8) é imediata.";
            dicasEscritas[3] = "É uma simples adição: some 15 moedas coletadas com 8 encontradas.";
            dicasEscritas[4] = "Dívidas representam valores negativos. Some as duas dívidas: -15 + (-10).";
            dicasEscritas[5] = "O valor é a soma dos pontos na peça de dominó, que é 3+4.";
            dicasEscritas[6] = "Pense na reta numérica: subir 8 metros a partir de -6 é avançar no sentido positivo.";
            dicasEscritas[7] = "Se o valor que você deve pagar (-30) é maior que o que você tem (+25), o saldo final será negativo.";
            dicasEscritas[8] = "Você está subtraindo um número maior (14) de um número menor (10). O resultado é negativo.";
            dicasEscritas[9] = "Quando dois valores negativos são somados (-7) + (-3), o resultado é um valor negativo maior.";
            dicasEscritas[10] = "É uma adição simples: some o HP inicial (5) com o HP curado (+8).";

            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 2) {
            inicializarRespostas();
            setTitle("Missão 2 - Subtração");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Subtraia as moedas usadas (12) das moedas iniciais (25).";
            dicasEscritas[2] = "Subtrair um número maior de um menor (8-15) resulta em um valor negativo.";
            dicasEscritas[3] = "Subtrair um negativo é o mesmo que somar o positivo (10 - (-6) = 10 + 6).";
            dicasEscritas[4] = "Cair (subtrair) a partir de um valor negativo (-5 - 4) significa um valor negativo ainda mais distante do zero.";
            dicasEscritas[5] = "Remover uma penalidade de profundidade (-5) deve ser tratado como -15 - 5.";
            dicasEscritas[6] = "Subtrair um positivo de um negativo (-20 - 10) afasta o resultado de zero.";
            dicasEscritas[7] = "Subtrair -2 de -7 é o mesmo que somar 2: -7 + 2.";
            dicasEscritas[8] = "É a subtração simples entre o máximo (100) e o HP atual (82).";
            dicasEscritas[9] = "Subtrair um número de si mesmo sempre resulta em zero, mesmo com números negativos: -1 - (-1).";
            dicasEscritas[10] = "Remover uma penalidade de -15 é o mesmo que adicionar +15. O cálculo é: 20 - 12 + 15.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 3) {
            inicializarRespostas();
            setTitle("Missão 3 - Multiplicação");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Multiplicação simples: 4 feitiços x 6 monstros.";
            dicasEscritas[2] = "Multiplique as poções por missão pelas missões concluídas: 3 x 5.";
            dicasEscritas[3] = "Um dano de -8 em 7 golpes resulta em (-8) x 7.";
            dicasEscritas[4] = "A perda de -9 cristais por 3 dias é uma multiplicação simples: (-9) x 3.";
            dicasEscritas[5] = "Multiplique os baús por barco (3) pelo número de barcos (5).";
            dicasEscritas[6] = "Perder -45 pontos em 3 partidas é uma multiplicação simples: (-45) x 3.";
            dicasEscritas[7] = "O custo total é o custo por caixa (-25) vezes o número de caixas (8).";
            dicasEscritas[8] = "A variação total é a multiplicação da queda diária (-9) pelo número de dias (3).";
            dicasEscritas[9] = "Perder -8 unidades por 7 rodadas é uma multiplicação simples: (-8) x 7.";
            dicasEscritas[10] = "Cristais perdidos é uma multiplicação de 6 minas por 4 cristais quebrados, resultando: 6 x 4.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 4) {
            inicializarRespostas();
            setTitle("Missão 4 - Divisão");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Para distribuir igualmente as moedas, você deve dividir o total de 24 pelo número de amigos (6).";
            dicasEscritas[2] = "Para encontrar a distância entre as paradas, divida a distância total (42 km) pelo número de paradas (7).";
            dicasEscritas[3] = "Divida o total de maçãs (90) pelo número de cestos (9).";
            dicasEscritas[4] = "Divida o número total de frutas coletadas (735) pelo número de caixas (15).";
            dicasEscritas[5] = "Para calcular a velocidade média, divida a distância percorrida (468 km) pelo tempo total (13 horas).";
            dicasEscritas[6] = "Para a média de perda, divida os pontos perdidos (96) pelo número de fases (12).";
            dicasEscritas[7] = "Divida o número total de caixas descarregadas (252) pelo número de portos (12).";
            dicasEscritas[8] = "Divida o total de moedas (125) pelo número de pessoas (5) para uma distribuição igual.";
            dicasEscritas[9] = "Divida a quantidade de peixes (56) pelo número de barris (8).";
            dicasEscritas[10] = "Divida o total de joias encontradas (270) pelo número de exploradores (18).";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 5) {
            inicializarRespostas();
            setTitle("Missão 5 - Desafio das Operações Básicas");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Comece com o total (12), subtraia o que foi dado (5) e adicione a recompensa (3).";
            dicasEscritas[2] = "Some o deslocamento de volta (+6) à posição negativa inicial (-18).";
            dicasEscritas[3] = "Primeiro, multiplique o que foi pescado (8 por 5 dias), depois subtraia a venda (10).";
            dicasEscritas[4] = "Considere a dívida paga como negativa (-6) e o recebimento como positivo (+12).";
            dicasEscritas[5] = "Para a distância entre paradas, divida a distância total (420) pelo número de paradas (6).";
            dicasEscritas[6] = "Calcule os pontos de vitória (4x3), os pontos de derrota (3x-2) e depois some os resultados.";
            dicasEscritas[7] = "Divida a produção total (256) pelo número de horas (8).";
            dicasEscritas[8] = "O gasto inicial é negativo (-250), e o valor recebido (+80) é positivo. Após isso, o valor gasto (45) é negativo. Calcule o saldo: -250 + 80 - 45.";
            dicasEscritas[9] = "Multiplique a quantidade de moedas totais, isso é, a quantidade de moedas pela quantidade de mochilas, e subtraia esse resultado com a quantidade de moedas gastas.";
            dicasEscritas[10] = "Divida a quantidade de frutas pela quantidade de cestas, e adicione 3 ao resultado.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 6) {
            inicializarRespostas();
            setTitle("Missão 6 - Frações");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "A fração é a parte bebida (3) sobre o total de partes (4).";
            dicasEscritas[2] = "O numerador (5) é lido como número cardinal e o denominador (6) como número ordinal (sextos).";
            dicasEscritas[3] = "1/2 representa a porção 'Um meio'.";
            dicasEscritas[4] = "O numerador é a quantidade (dois) e o denominador é o todo (terços).";
            dicasEscritas[5] = "O denominador 8 é lido como oitavos.";
            dicasEscritas[6] = "Denominadores 10, 100, 1000 são lidos como décimo, centésimo e milésimo.";
            dicasEscritas[7] = "O denominador 100 é lido como centésimos.";
            dicasEscritas[8] = "A leitura do denominador maior que 10 finaliza com 'avos'.";
            dicasEscritas[9] = "A leitura do denominador maior que 10 finaliza com 'avos'.";
            dicasEscritas[10] = "A leitura do denominador maior que 10 finaliza com 'avos'.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 7) {
            inicializarRespostas();
            setTitle("Missão 7 - Soma e Subtração com Frações");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Some apenas os numeradores (2+1), pois os denominadores (5) são iguais.";
            dicasEscritas[2] = "Subtraia a soma das porções comidas (4/8 + 3/8) do total (1 inteiro).";
            dicasEscritas[3] = "Subtraia apenas os numeradores (9-5), pois os denominadores (11) são iguais.";
            dicasEscritas[4] = "Faça a técnica 'cruzada' que estava no material entre 3 e 6 para somar as frações.";
            dicasEscritas[5] = "Faça a técnica 'cruzada' que estava no material entre 4 e 2 para subtrair frações com denominadores diferentes.";
            dicasEscritas[6] = "Mantenha o denominador (7) e some todos os numeradores.";
            dicasEscritas[7] = "Subtraia a porção usada (1/5 + 2/5) da porção total (5/5).";
            dicasEscritas[8] = "Faça a técnica 'cruzada' que estava no material, para igualar os denominadores.";
            dicasEscritas[9] = "Subtraia do valor inicial, e com esse resultado, depois some.";
            dicasEscritas[10] = "A leitura do denominador maior que 10 finaliza com 'avos'.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 8) {
            inicializarRespostas();
            setTitle("Missão 8 - Multiplicação e Divisão de Frações");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Multiplique o numerador (2) pelo número de refeições (3) e mantenha o denominador (5).";
            dicasEscritas[2] = "Multiplique o numerador (3) por 2 e depois simplifique a fração resultante.";
            dicasEscritas[3] = "Multiplique o numerador (1) por 4 e mantenha o denominador (3).";
            dicasEscritas[4] = "Para dividir por um número inteiro (5), multiplique a fração pelo inverso desse número (1/5).";
            dicasEscritas[5] = "Multiplique o numerador (2) por 3.";
            dicasEscritas[6] = "Mantenha a primeira fração (4/5) e multiplique pelo inverso da segunda (10/1).";
            dicasEscritas[7] = "A palavra 'de' indica multiplicação. Multiplique 3 por 10 e divida o resultado por 4.";
            dicasEscritas[8] = "Multiplique numerador por numerador e denominador por denominador.";
            dicasEscritas[9] = "Multiplique por duas vezes a quantidade utilizada em somente uma receita.";
            dicasEscritas[10] = "Divida todo o canteiro (1, ou 9/9) por 2/9.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 9) {
            inicializarRespostas();
            setTitle("Missão 9 - Potenciação e Radiciação");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "O volume do cubo é a aresta elevada ao cubo (a³).";
            dicasEscritas[2] = "A base (5) é multiplicada por si mesma o número de vezes do expoente (2).";
            dicasEscritas[3] = "A área do quadrado é o lado elevado ao quadrado (L²).";
            dicasEscritas[4] = "O expoente (3) indica que a base (2) é multiplicada por si mesma 3 vezes (2x2x2).";
            dicasEscritas[5] = "Encontre o número que, multiplicado por si mesmo, resulta em 49.";
            dicasEscritas[6] = "Encontre o número cuja multiplicação por si mesmo seja 81.";
            dicasEscritas[7] = "A raiz quadrada de 16 é o número que, elevado ao quadrado, resulta em 16.";
            dicasEscritas[8] = "A quantidade de zeros no resultado é igual ao valor do expoente.";
            dicasEscritas[9] = "Valor multiplicado por ele mesmo que resulte em 36.";
            dicasEscritas[10] = "Potência é um valor multiplicado por ele mesmo por determinadas vezes.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 10) {
            inicializarRespostas();
            setTitle("Missão 10 - Decimais");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Para somar decimais, alinhe a vírgula (1,25 + 0,50).";
            dicasEscritas[2] = "Para subtrair decimais, alinhe a vírgula e subtraia as colunas.";
            dicasEscritas[3] = "Multiplique como números inteiros e use uma casa decimal no resultado.";
            dicasEscritas[4] = "Multiplique os números sem vírgula e conte duas casas decimais no resultado final.";
            dicasEscritas[5] = "Divida o total (9,0) pelo número de NPCs (2).";
            dicasEscritas[6] = "Complete com zero (2,50 + 1,25) para alinhar as casas decimais antes de somar.";
            dicasEscritas[7] = "Alinhe as vírgulas e subtraia (5,00 - 1,50).";
            dicasEscritas[8] = "'Inteiros' vêm antes da vírgula; 'centésimos' são duas casas depois dela.";
            dicasEscritas[9] = "Divida o total pela quantidade usada em cada porção.";
            dicasEscritas[10] = "Multiplique o valor do aumento pela quantidade de vezes que é aplicado.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 11) {
            inicializarRespostas();
            setTitle("Missão 11 - Desafio das Operações com Frações e Decimais");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "O denominador 100 é lido como centésimos.";
            dicasEscritas[2] = "Alinhe as vírgulas e some (pode reescrever 0,7 como 0,70).";
            dicasEscritas[3] = "Faça a técnica cruzada entre 4 e 8 para somar as frações.";
            dicasEscritas[4] = "Multiplique 0,1 por 0,1 por 0,1 e conte o total de casas decimais (três).";
            dicasEscritas[5] = "Divida o comprimento total (1,2) pelo número de pedaços (4).";
            dicasEscritas[6] = "Multiplique numerador por numerador e denominador por denominador.";
            dicasEscritas[7] = "Pense em qual número multiplicado por ele mesmo dá 1,21.";
            dicasEscritas[8] = "Dividir por uma fração é o mesmo que multiplicar pelo inverso dela.";
            dicasEscritas[9] = "Subtraia os valores gastos, um de cada vez, do total inicial.";
            dicasEscritas[10] = "Faça a potência e subtraia com 1,5. Alinhe vírgula com vírgula.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 12) {
            inicializarRespostas();
            setTitle("Missão 12 - Porcentagem");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Para transformar % em decimal, divida 35 por 100 (desloque a vírgula duas casas para a esquerda).";
            dicasEscritas[2] = "Porcentagem significa 'por cem', ou seja, o denominador é sempre 100.";
            dicasEscritas[3] = "Calcule 10% de R$ 80,00 (multiplique 80 por 0,10 ou divida 80 por 10).";
            dicasEscritas[4] = "Calcule o valor do desconto (25% de 120) e, em seguida, subtraia-o do preço original.";
            dicasEscritas[5] = "Na fórmula J=C.i.t, 'C' representa o valor inicial ou principal.";
            dicasEscritas[6] = "Use a fórmula J=C.i.t. Lembre-se de converter a taxa 'i' (5%) para decimal (0,05).";
            dicasEscritas[7] = "Converta a taxa percentual em forma decimal (divida por 100).";
            dicasEscritas[8] = "É o valor total ao final da aplicação, capital inicial mais os juros.";
            dicasEscritas[9] = "Some o juro ao capital para encontrar o montante. (M = C + J).";
            dicasEscritas[10] = "Compare o numerador com o denominador: quanto é 25 em relação a 100?";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 13) {
            inicializarRespostas();
            setTitle("Missão 13 - Expressões Numéricas");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Multiplicação tem prioridade sobre a adição: (3x4) + 2.";
            dicasEscritas[2] = "Parênteses têm prioridade: primeiro some (6+4), depois divida por 2.";
            dicasEscritas[3] = "Multiplicação tem prioridade: 8 - (3x2).";
            dicasEscritas[4] = "Parênteses têm prioridade: (10+2), e depois multiplique por 3.";
            dicasEscritas[5] = "Parênteses têm prioridade: 20 ÷ (5-3).";
            dicasEscritas[6] = "Potência tem prioridade: (4²) + 6.";
            dicasEscritas[7] = "Resolva ambos os parênteses primeiro: (9-3) x (8÷2). Depois multiplique.";
            dicasEscritas[8] = "Primeiro calcule a potência (5²), depois divida o resultado por 10.";
            dicasEscritas[9] = "Siga a ordem das operações: divisão e multiplicação antes da adição.";
            dicasEscritas[10] = "Resolva primeiro o que está entre parênteses, depois entre os colchetes, depois a potência, e só então some tudo.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 14) {
            inicializarRespostas();
            setTitle("Missão 14 - Equações do Primeiro Grau");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Monte a equação: x + 12 = 30. Isole 'x' subtraindo 12 de 30.";
            dicasEscritas[2] = "Monte a equação: x + 6 = 20. Isole 'x' subtraindo 6 de 20.";
            dicasEscritas[3] = "Monte a equação: 3x = 24. Isole 'x' dividindo 24 por 3.";
            dicasEscritas[4] = "A equação é 2x + 5 = 15. Primeiro, subtraia 5 de 15, depois divida por 2.";
            dicasEscritas[5] = "A equação é x - 9 = 20. Isole 'x' adicionando 9 a 20.";
            dicasEscritas[6] = "A equação é 3x + 4 = 19. Subtraia 4 de 19 e, em seguida, divida por 3.";
            dicasEscritas[7] = "A equação é 3x = 21. Isole 'x' e divida 21 por 3.";
            dicasEscritas[8] = "A equação é x + x/2 = 18. Faça a técnica cruzada para somar os 'x'. Passe o denominador '2' multiplicando 18, e divida os valores para encontrar o valor de 'x'.";
            dicasEscritas[9] = "A equação é x/2 = 14. Passe o 2 multiplicando e encontre o valor de 'x'.";
            dicasEscritas[10] = "A equação é 4x - 8 = 20. Passe o 8 somando a 20, divida esse resultado por 4.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 15) {
            inicializarRespostas();
            setTitle("Missão 15 - Razão e Proporção");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Razão é a divisão entre turistas (15) e guias (5). Simplifique o resultado.";
            dicasEscritas[2] = "Monte a proporção: 2 ovos / 500g = x ovos / 1500g. Use a multiplicação cruzada.";
            dicasEscritas[3] = "Monte a proporção: 100 km / 2 horas = 300 km / t horas. Use a multiplicação cruzada.";
            dicasEscritas[4] = "A multiplicação cruzada (Propriedade Fundamental) é a multiplicação da diagonal (a.d = b.c).";
            dicasEscritas[5] = "A razão 4 para 1 significa que o maior (x) é 4 vezes o menor (5). Multiplique 4 por 5.";
            dicasEscritas[6] = "Na proporção 1/2 = x/10, use a multiplicação cruzada (1 * 10 = 2 * x).";
            dicasEscritas[7] = "Regra de três simples e inversa: Menos operários (4) leva mais dias. Multiplique 4x8 e divida por 2.";
            dicasEscritas[8] = "Use a multiplicação cruzada (produto dos meios = produto dos extremos).";
            dicasEscritas[9] = "Some as partes da razão (3 + 5) e veja quanto vale 1 parte; depois multiplique pelo número de partes da menor.";
            dicasEscritas[10] = "Se o picolé representa '1 parte', o sorvete vale 3 vezes esse valor.";

            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 16) {
            inicializarRespostas();
            setTitle("Missão 16 - Desafio de Álgebra");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "A ordem de prioridade dos símbolos de agrupamento é: Parênteses ( ), Colchetes [ ], Chaves { }.";
            dicasEscritas[2] = "Calcule 10% de R$ 200,00 (multiplique 200 por 0,10 ou divida 200 por 10).";
            dicasEscritas[3] = "Para transformar % em decimal, divida 60 por 100 (desloque a vírgula duas casas para a esquerda).";
            dicasEscritas[4] = "Isole 'x' dividindo 30 pelo coeficiente (5).";
            dicasEscritas[5] = "Isole 'x' adicionando 7 a 18 (operação inversa).";
            dicasEscritas[6] = "Montante (M) é a soma do Capital (C) mais os Juros (J).";
            dicasEscritas[7] = "Multiplique o número de pessoas pelo tempo; o produto deve ser igual nas duas situações.";
            dicasEscritas[8] = "Resolva primeiro o que está entre parênteses, depois a divisão, e por último a subtração.";
            dicasEscritas[9] = "Use a fórmula J = C × i × t, lembrando de converter a taxa para decimal (2% = 0,02).";
            dicasEscritas[10] = "Comece pelos parênteses ( ), depois colchetes [ ], e por último chaves { }.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 17) {
            inicializarRespostas();
            setTitle("Missão 17 - Perímetro");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "O perímetro é a soma de todos os lados (P = L + L + L + L).";
            dicasEscritas[2] = "Multiplique o lado por 4 para achar o perímetro do quadrado.";
            dicasEscritas[3] = "O perímetro é uma medida linear (metros, centímetros, etc.).";
            dicasEscritas[4] = "Retângulo: P = 2 × (base + altura).";
            dicasEscritas[5] = "Para triângulo, some os três lados: 3 + 4 + 5.";
            dicasEscritas[6] = "Perímetro do quadrado: P = 4 × L.";
            dicasEscritas[7] = "Perímetro do retângulo: P = 2 × (b + h).";
            dicasEscritas[8] = "Para polígonos regulares, multiplique o número de lados pelo comprimento de um lado.";
            dicasEscritas[9] = "Divida o perímetro por 4 para achar o lado do quadrado.";
            dicasEscritas[10] = "Triângulo equilátero: divida o perímetro por 3 para achar o lado.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 18) {
            inicializarRespostas();
            setTitle("Missão 18 - Área");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "A área de um quadrado é encontrada multiplicando o lado por ele mesmo (A = L × L).";
            dicasEscritas[2] = "Multiplique o lado pelo próprio lado: 7 × 7 = 49.";
            dicasEscritas[3] = "Para retângulos, multiplique base pela altura (A = b × h).";
            dicasEscritas[4] = "Base × altura: 10 × 3 = 30.";
            dicasEscritas[5] = "A área do triângulo é base × altura ÷ 2.";
            dicasEscritas[6] = "Use a fórmula A = (b × h) ÷ 2: 10 × 4 ÷ 2.";
            dicasEscritas[7] = "Multiplique base pela altura: 9 × 2 = 18.";
            dicasEscritas[8] = "Área do quadrado = L × L = 11 × 11 = 121.";
            dicasEscritas[9] = "Triângulo: base × altura ÷ 2 → 8 × 6 ÷ 2.";
            dicasEscritas[10] = "Retângulo: base × altura → 14 × 5 = 70.";
            pnlAtual = criarPanel(numeroQuestao);

        } else if (missao == 19) {
            inicializarRespostas();
            setTitle("Missão 19 - Estatística Básica");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "A moda é o valor que mais se repete no conjunto.";
            dicasEscritas[2] = "A média é a soma dos valores dividida pela quantidade de termos.";
            dicasEscritas[3] = "A mediana é o valor central quando os números estão em ordem.";
            dicasEscritas[4] = "Moda é o número que aparece com maior frequência.";
            dicasEscritas[5] = "Some todos os valores e divida pela quantidade para obter a média.";
            dicasEscritas[6] = "Mediana: coloque em ordem e pegue o valor do meio.";
            dicasEscritas[7] = "Moda: o número que mais se repete nas notas.";
            dicasEscritas[8] = "Média: some todos os lucros e divida por 5.";
            dicasEscritas[9] = "Mediana: número central em uma sequência ordenada.";
            dicasEscritas[10] = "Moda: valor que aparece mais vezes no conjunto.";
            pnlAtual = criarPanel(numeroQuestao);
        } else if (missao == 20) {
            inicializarRespostas();
            setTitle("Missão 20 - Tabelas e Gráficos");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Observe qual linha ou coluna tem o maior número: essa representa o item mais vendido.";
            dicasEscritas[2] = "Compare as notas: o maior número indica a maior nota.";
            dicasEscritas[3] = "Verifique qual mês tem o valor mais alto nas colunas.";
            dicasEscritas[4] = "Metade da pizza equivale a 50%.";
            dicasEscritas[5] = "Compare os percentuais e veja qual é o maior.";
            dicasEscritas[6] = "A linha subindo indica aumento no valor observado.";
            dicasEscritas[7] = "O menor número da tabela mostra o menor lucro.";
            dicasEscritas[8] = "Compare as porcentagens: a maior representa a parte maior do gráfico.";
            dicasEscritas[9] = "Barras subindo mostram aumento ou crescimento.";
            dicasEscritas[10] = "Some todos os valores da tabela para obter o total.";
            pnlAtual = criarPanel(numeroQuestao);
        } else if (missao == 21) {
            inicializarRespostas();
            setTitle("Missão 21 - Desafio de Múltiplos Conceitos de Geometria e Gráficos");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Área do quadrado: multiplique o lado por ele mesmo.";
            dicasEscritas[2] = "Perímetro do retângulo: P = 2 × (base + altura).";
            dicasEscritas[3] = "Média aritmética: some os valores e divida pela quantidade.";
            dicasEscritas[4] = "Área do triângulo: base × altura ÷ 2.";
            dicasEscritas[5] = "Moda: valor que mais aparece no conjunto.";
            dicasEscritas[6] = "Perímetro do quadrado: P = 4 × L.";
            dicasEscritas[7] = "Some os lados iguais e a base: 2x + 6 = 20 → isole x.";
            dicasEscritas[8] = "Perímetro do retângulo: P = 2 × (b + h).";
            dicasEscritas[9] = "Perímetro é a soma dos três lados.";
            dicasEscritas[10] = "Linha subindo em gráfico de linhas indica crescimento.";
            pnlAtual = criarPanel(numeroQuestao);
        } else if (missao == 22) {
            inicializarRespostas();
            setTitle("Missão 22 - Revisão");
            dicasEscritas = new String[11];
            dicasEscritas[1] = "Sinais diferentes: subtraia e mantenha o sinal do maior número absoluto.";
            dicasEscritas[2] = "Multiplique numerador e denominador por um mesmo número para obter frações equivalentes.";
            dicasEscritas[3] = "Para achar a porcentagem, divida a parte pelo total e multiplique por 100.";
            dicasEscritas[4] = "O perímetro do quadrado é 4 vezes o lado.";
            dicasEscritas[5] = "A raiz quadrada é o número que multiplicado por si mesmo gera o valor.";
            dicasEscritas[6] = "Isole x dividindo o número pelo coeficiente que o acompanha.";
            dicasEscritas[7] = "Faça as potências e some os resultados.";
            dicasEscritas[8] = "Razão é a divisão entre dois valores: primeiro dividido pelo segundo.";
            dicasEscritas[9] = "Área do retângulo: base × altura.";
            dicasEscritas[10] = "Calcule 60% de 50 multiplicando 50 × 0,6.";
            pnlAtual = criarPanel(numeroQuestao);
        } else {
            inicializarRespostas();
            setTitle("Missão 23 - Desafio Final");
            dicasEscritas = new String[26];
            dicasEscritas[1] = "Sinais diferentes na multiplicação resultam em número negativo.";
            dicasEscritas[2] = "Alinhe as casas decimais e subtraia normalmente.";
            dicasEscritas[3] = "Divida o número normalmente: 108 ÷ 9.";
            dicasEscritas[4] = "Somando frações com mesmo denominador: mantenha o denominador e some os numeradores.";
            dicasEscritas[5] = "Simplifique dividindo numerador e denominador por um mesmo número.";
            dicasEscritas[6] = "Potência: multiplique o número por ele mesmo o número de vezes do expoente.";
            dicasEscritas[7] = "A raiz quadrada é o número que, multiplicado por si, gera o radicando.";
            dicasEscritas[8] = "Resolva primeiro o parêntese, depois a multiplicação e, por fim, a soma.";
            dicasEscritas[9] = "Siga a ordem das operações: potência, divisão e soma.";
            dicasEscritas[10] = "Isole x aplicando a operação inversa: some 8 a ambos os lados.";
            dicasEscritas[11] = "Divida ambos os lados pelo número que multiplica o x.";
            dicasEscritas[12] = "Isole x invertendo a subtração: x = 10 − 2.";
            dicasEscritas[13] = "Razão: divida o primeiro valor pelo segundo.";
            dicasEscritas[14] = "Proporção inversa: metade dos trabalhadores → dobro do tempo.";
            dicasEscritas[15] = "Use regra de três simples direta: mais tempo → mais distância.";
            dicasEscritas[16] = "Para transformar % em decimal, divida por 100.";
            dicasEscritas[17] = "Calcule o desconto multiplicando o preço pelo percentual (80 × 0,10).";
            dicasEscritas[18] = "Juros simples: J = C × i × t.";
            dicasEscritas[19] = "A área é 36, então o lado é √36. Depois multiplique o lado por 4 para achar o perímetro.";
            dicasEscritas[20] = "Área do retângulo: base × altura.";
            dicasEscritas[21] = "Perímetro: soma de todos os lados (2 × (100 + 50)).";
            dicasEscritas[22] = "Área do quadrado: lado × lado = 11 × 11.";
            dicasEscritas[23] = "Média aritmética: some e divida pela quantidade de números.";
            dicasEscritas[24] = "Moda: o valor que mais se repete no conjunto.";
            dicasEscritas[25] = "Mediana: número do meio em um conjunto ordenado.";
            pnlAtual = criarPanel(numeroQuestao);
        }
        getContentPane().add(pnlAtual, BorderLayout.CENTER);
        setVisible(true);
    }

    private void configurarBotao(JButton button) {
        button.addActionListener(this);

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public JPanel criarPanel(int numeroQuestao) {
        String nomeImagem = null;
        ImagemPanel panelQuestao;
        double nivelAntigo = ProgressoDAO.verificarNivel(emailUsuario);
        if (numeroQuestao > MAX_QUESTOES && missao != 23) {
            JOptionPane.showMessageDialog(this, "Missão Concluída! Parabéns!", "Fim da Missão", JOptionPane.INFORMATION_MESSAGE);
            missao++;

            if (missao > ProgressoDAO.verificarMissao(emailUsuario)) {
                if (missao == 3 || missao == 7 || missao == 10 || missao == 13 || missao == 17 || missao == 20) {
                    Progresso progresso = new Progresso(emailUsuario, missao, VALOR_XP_BONUS, 1, MOEDAS_BONUS);
                    ProgressoDAO.atualizarProgresso(progresso);
                } else if (missao == 5 || missao == 11 || missao == 16 || missao == 21) {
                    Progresso progresso = new Progresso(emailUsuario, missao, VALOR_XP_DESAFIO, 1, MOEDAS_BONUS);
                    ProgressoDAO.atualizarProgresso(progresso);

                } else {
                    Progresso progresso = new Progresso(emailUsuario, missao, VALOR_XP, 1, MOEDAS);
                    ProgressoDAO.atualizarProgresso(progresso);
                }
            }
            this.dispose();
            if (ProgressoDAO.verificarNivel(emailUsuario) > nivelAntigo) {
                JOptionPane.showMessageDialog(this, "Seu nível aumentou! Parabéns!", "Subiu de Nível", JOptionPane.INFORMATION_MESSAGE);
            }
            TrilhaConhecimento trilhaConhecimento = new TrilhaConhecimento(ProgressoDAO.verificarMissao(emailUsuario), emailUsuario);

            numeroQuestao = 1;
        } else if (missao == 23 && numeroQuestao > 25) {
            JOptionPane.showMessageDialog(this, "Missão Concluída! Parabéns!", "Fim da Missão", JOptionPane.INFORMATION_MESSAGE);
            Progresso progresso = new Progresso(emailUsuario, 24, 22, 1, MOEDAS_BONUS);
            ProgressoDAO.atualizarProgresso(progresso);

            this.dispose();
            if (ProgressoDAO.verificarNivel(emailUsuario) > nivelAntigo) {
                JOptionPane.showMessageDialog(this, "Seu nível aumentou! Parabéns!", "Subiu de Nível", JOptionPane.INFORMATION_MESSAGE);
            }
            TrilhaConhecimento trilhaConhecimento = new TrilhaConhecimento(24, emailUsuario); // Abre o mapa na Missão 24 (Fim)

            numeroQuestao = 1;
        }

        if (missao == 1) {
            nomeImagem = "questaoAdicao" + numeroQuestao + ".png";
        } else if (missao == 2) {
            nomeImagem = "questaoSubtracao" + numeroQuestao + ".png";
        } else if (missao == 3) {
            nomeImagem = "questaoMultiplicacao" + numeroQuestao + ".png";
        } else if (missao == 4) {
            nomeImagem = "questaoDivisao" + numeroQuestao + ".png";
        } else if (missao == 5) {
            nomeImagem = "questaoMissaoOperacoesBasicas" + numeroQuestao + ".png";
        } else if (missao == 6) {
            nomeImagem = "questaoFracoes" + numeroQuestao + ".png";
        } else if (missao == 7) {
            nomeImagem = "questaoFracoesSomaSubtracao" + numeroQuestao + ".png";
        } else if (missao == 8) {
            nomeImagem = "questaoFracoesMultDivisao" + numeroQuestao + ".png";
        } else if (missao == 9) {
            nomeImagem = "questaoPotenciaRadiciacao" + numeroQuestao + ".png";
        } else if (missao == 10) {
            nomeImagem = "questaoDecimais" + numeroQuestao + ".png";
        } else if (missao == 11) {
            nomeImagem = "questaoMissaoOperacoesFracoesDecimais" + numeroQuestao + ".png";
        } else if (missao == 12) {
            nomeImagem = "questaoPorcentagem" + numeroQuestao + ".png";
        } else if (missao == 13) {
            nomeImagem = "questaoExpressao" + numeroQuestao + ".png";
        } else if (missao == 14) {
            nomeImagem = "questaoEquacao" + numeroQuestao + ".png";
        } else if (missao == 15) {
            nomeImagem = "questaoRazaoProporcao" + numeroQuestao + ".png";
        } else if (missao == 16) {
            nomeImagem = "questaoMissaoAlgebra" + numeroQuestao + ".png";
        } else if (missao == 17) {
            nomeImagem = "questaoPerimetro" + numeroQuestao + ".png";
        } else if (missao == 18) {
            nomeImagem = "questaoArea" + numeroQuestao + ".png";
        } else if (missao == 19) {
            nomeImagem = "questaoEstatistica" + numeroQuestao + ".png";
        } else if (missao == 20) {
            nomeImagem = "questaoTabelasGraficos" + numeroQuestao + ".png";
        } else if (missao == 21) {
            nomeImagem = "questaoMissaoEstatisticaGeometria" + numeroQuestao + ".png";
        } else if (missao == 22) {
            nomeImagem = "questaoRevisao" + numeroQuestao + ".png";
        } else if (missao == 23) {
            nomeImagem = "questaoDesafioFinal" + numeroQuestao + ".png";
        }

        panelQuestao = new ImagemPanel(nomeImagem);
        panelQuestao.setLayout(null);
        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        double proporcaoLargura = tamanhoTela.getWidth() / LARGURA_REFERENCIA;
        double proporcaoAltura = tamanhoTela.getHeight() / ALTURA_REFERENCIA;
        int x1, y1, x2, y2, largura, altura;

        largura = (int) (380 * proporcaoLargura);
        altura = (int) (90 * proporcaoAltura);
        x1 = (int) (560 * proporcaoLargura);
        x2 = (int) (960 * proporcaoLargura);

        if (missao == 1) {
            if (numeroQuestao < 3) {
                y1 = (int) (500 * proporcaoAltura);
                y2 = (int) (600 * proporcaoAltura);
            } else if (numeroQuestao == 5) {
                y1 = (int) (590 * proporcaoAltura);
                y2 = (int) (690 * proporcaoAltura);
            } else {
                y1 = (int) (522 * proporcaoAltura);
                y2 = (int) (625 * proporcaoAltura);
            }

        } else if (missao == 2) {
            if (numeroQuestao < 3) {
                y1 = (int) (500 * proporcaoAltura);
                y2 = (int) (600 * proporcaoAltura);
            } else if (numeroQuestao == 5) {
                y1 = (int) (570 * proporcaoAltura);
                y2 = (int) (670 * proporcaoAltura);
            } else if (numeroQuestao == 10) {
                y1 = (int) (570 * proporcaoAltura);
                y2 = (int) (680 * proporcaoAltura);
            } else {
                y1 = (int) (522 * proporcaoAltura);
                y2 = (int) (625 * proporcaoAltura);
            }

        } else if (missao == 3) {
            if (numeroQuestao == 5) {
                y1 = (int) (590 * proporcaoAltura);
                y2 = (int) (690 * proporcaoAltura);
            } else {
                y1 = (int) (512 * proporcaoAltura);
                y2 = (int) (612 * proporcaoAltura);
            }

        } else if (missao == 4) {
            if (numeroQuestao == 3) {
                y1 = (int) (587 * proporcaoAltura);
                y2 = (int) (690 * proporcaoAltura);
            } else {
                y1 = (int) (522 * proporcaoAltura);
                y2 = (int) (625 * proporcaoAltura);
            }
        } else if (missao == 6) {
            if (numeroQuestao == 3) {
                y1 = (int) (500 * proporcaoAltura);
                y2 = (int) (610 * proporcaoAltura);
            } else if (numeroQuestao == 4) {
                y1 = (int) (510 * proporcaoAltura);
                y2 = (int) (620 * proporcaoAltura);
            } else {
                y1 = (int) (500 * proporcaoAltura);
                y2 = (int) (605 * proporcaoAltura);
            }
        } else if (missao == 7) {
            if (numeroQuestao > 2 && numeroQuestao != 10) {
                y1 = (int) (540 * proporcaoAltura);
                y2 = (int) (645 * proporcaoAltura);
            } else {
                y1 = (int) (500 * proporcaoAltura);
                y2 = (int) (605 * proporcaoAltura);
            }
        } else if (missao == 8 || missao == 9) {
            y1 = (int) (525 * proporcaoAltura);
            y2 = (int) (635 * proporcaoAltura);
        } else if (missao == 10 || missao == 11 || missao == 12) {
            y1 = (int) (490 * proporcaoAltura);
            y2 = (int) (600 * proporcaoAltura);
        } else if (missao == 13) {
            y1 = (int) (520 * proporcaoAltura);
            y2 = (int) (625 * proporcaoAltura);
        } else if (missao == 14) {
            y1 = (int) (500 * proporcaoAltura);
            y2 = (int) (600 * proporcaoAltura);
        } else if (missao == 15) {
            y1 = (int) (500 * proporcaoAltura);
            y2 = (int) (600 * proporcaoAltura);
        } else if (missao == 16) {
            y1 = (int) (500 * proporcaoAltura);
            y2 = (int) (600 * proporcaoAltura);
        } else if (missao == 17) {
            y1 = (int) (500 * proporcaoAltura);
            y2 = (int) (600 * proporcaoAltura);
        } else if (missao == 20) {
            if (numeroQuestao == 1 || numeroQuestao == 2 || numeroQuestao == 7 || numeroQuestao == 10) {
                y1 = (int) (535 * proporcaoAltura);
                y2 = (int) (650 * proporcaoAltura);
            } else {
                y1 = (int) (515 * proporcaoAltura);

                y2 = (int) (625 * proporcaoAltura);
            }
        } else if (missao == 21) {
            y1 = (int) (497 * proporcaoAltura);
            y2 = (int) (605 * proporcaoAltura);
        } else if (missao == 22) {
            if (numeroQuestao == 10) {
                y1 = (int) (560 * proporcaoAltura);
                y2 = (int) (680 * proporcaoAltura);
            } else {
                y1 = (int) (497 * proporcaoAltura);
                y2 = (int) (605 * proporcaoAltura);
            }
        } else if (missao == 23) {
            y1 = (int) (480 * proporcaoAltura);
            y2 = (int) (600 * proporcaoAltura);
        } else {
            y1 = (int) (515 * proporcaoAltura);
            y2 = (int) (625 * proporcaoAltura);
        }

        btnOpcao1.setBounds(x1, y1, largura, altura);
        panelQuestao.add(btnOpcao1);

        btnOpcao2.setBounds(x2, y1, largura, altura);
        panelQuestao.add(btnOpcao2);

        btnOpcao3.setBounds(x1, y2, largura, altura);
        panelQuestao.add(btnOpcao3);

        btnOpcao4.setBounds(x2, y2, largura, altura);
        panelQuestao.add(btnOpcao4);

        int xLoja = (int) (689 * proporcaoLargura);
        int yLoja = (int) (140 * proporcaoAltura);
        int larguraLoja = (int) (80 * proporcaoLargura);
        int alturaLoja = (int) (80 * proporcaoAltura);
        btnLoja.setBounds(xLoja, yLoja, larguraLoja, alturaLoja);
        panelQuestao.add(btnLoja);

        int xMochila = (int) (1140 * proporcaoLargura);
        int yMochila = yLoja;
        int larguraMochila = larguraLoja;
        int alturaMochila = alturaLoja;

        btnMochila.setBounds(xMochila, yMochila, larguraMochila, alturaMochila);
        panelQuestao.add(btnMochila);

        return panelQuestao;
    }

    private void inicializarRespostas() {
        if (missao == 1) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao4);
            RESPOSTAS_CORRETAS.put(2, btnOpcao2);
            RESPOSTAS_CORRETAS.put(3, btnOpcao3);
            RESPOSTAS_CORRETAS.put(4, btnOpcao2);
            RESPOSTAS_CORRETAS.put(5, btnOpcao3);
            RESPOSTAS_CORRETAS.put(6, btnOpcao1);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao4);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao1);
        } else if (missao == 2) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao1);
            RESPOSTAS_CORRETAS.put(2, btnOpcao4);
            RESPOSTAS_CORRETAS.put(3, btnOpcao4);
            RESPOSTAS_CORRETAS.put(4, btnOpcao4);
            RESPOSTAS_CORRETAS.put(5, btnOpcao1);
            RESPOSTAS_CORRETAS.put(6, btnOpcao3);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao1);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao3);
        } else if (missao == 3) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao3);
            RESPOSTAS_CORRETAS.put(2, btnOpcao2);
            RESPOSTAS_CORRETAS.put(3, btnOpcao4);
            RESPOSTAS_CORRETAS.put(4, btnOpcao1);
            RESPOSTAS_CORRETAS.put(5, btnOpcao1);
            RESPOSTAS_CORRETAS.put(6, btnOpcao1);
            RESPOSTAS_CORRETAS.put(7, btnOpcao4);
            RESPOSTAS_CORRETAS.put(8, btnOpcao1);
            RESPOSTAS_CORRETAS.put(9, btnOpcao1);
            RESPOSTAS_CORRETAS.put(10, btnOpcao1);
        } else if (missao == 4) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao1);
            RESPOSTAS_CORRETAS.put(2, btnOpcao2);
            RESPOSTAS_CORRETAS.put(3, btnOpcao2);
            RESPOSTAS_CORRETAS.put(4, btnOpcao3);
            RESPOSTAS_CORRETAS.put(5, btnOpcao3);
            RESPOSTAS_CORRETAS.put(6, btnOpcao2);
            RESPOSTAS_CORRETAS.put(7, btnOpcao4);
            RESPOSTAS_CORRETAS.put(8, btnOpcao3);
            RESPOSTAS_CORRETAS.put(9, btnOpcao1);
            RESPOSTAS_CORRETAS.put(10, btnOpcao3);
        } else if (missao == 5) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao3);
            RESPOSTAS_CORRETAS.put(2, btnOpcao4);
            RESPOSTAS_CORRETAS.put(3, btnOpcao3);
            RESPOSTAS_CORRETAS.put(4, btnOpcao3);
            RESPOSTAS_CORRETAS.put(5, btnOpcao4);
            RESPOSTAS_CORRETAS.put(6, btnOpcao1);
            RESPOSTAS_CORRETAS.put(7, btnOpcao2);
            RESPOSTAS_CORRETAS.put(8, btnOpcao2);
            RESPOSTAS_CORRETAS.put(9, btnOpcao1);
            RESPOSTAS_CORRETAS.put(10, btnOpcao3);
        } else if (missao == 6) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao1);
            RESPOSTAS_CORRETAS.put(2, btnOpcao1);
            RESPOSTAS_CORRETAS.put(3, btnOpcao3);
            RESPOSTAS_CORRETAS.put(4, btnOpcao2);
            RESPOSTAS_CORRETAS.put(5, btnOpcao3);
            RESPOSTAS_CORRETAS.put(6, btnOpcao2);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao4);
            RESPOSTAS_CORRETAS.put(9, btnOpcao1);
            RESPOSTAS_CORRETAS.put(10, btnOpcao3);
        } else if (missao == 7) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao2);
            RESPOSTAS_CORRETAS.put(2, btnOpcao2);
            RESPOSTAS_CORRETAS.put(3, btnOpcao2);
            RESPOSTAS_CORRETAS.put(4, btnOpcao4);
            RESPOSTAS_CORRETAS.put(5, btnOpcao4);
            RESPOSTAS_CORRETAS.put(6, btnOpcao4);
            RESPOSTAS_CORRETAS.put(7, btnOpcao2);
            RESPOSTAS_CORRETAS.put(8, btnOpcao4);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao3);
        } else if (missao == 8) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao2);
            RESPOSTAS_CORRETAS.put(2, btnOpcao2);
            RESPOSTAS_CORRETAS.put(3, btnOpcao1);
            RESPOSTAS_CORRETAS.put(4, btnOpcao1);
            RESPOSTAS_CORRETAS.put(5, btnOpcao1);
            RESPOSTAS_CORRETAS.put(6, btnOpcao2);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao2);
            RESPOSTAS_CORRETAS.put(9, btnOpcao2);
            RESPOSTAS_CORRETAS.put(10, btnOpcao4);
        } else if (missao == 9) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao4);
            RESPOSTAS_CORRETAS.put(2, btnOpcao3);
            RESPOSTAS_CORRETAS.put(3, btnOpcao2);
            RESPOSTAS_CORRETAS.put(4, btnOpcao2);
            RESPOSTAS_CORRETAS.put(5, btnOpcao3);
            RESPOSTAS_CORRETAS.put(6, btnOpcao4);
            RESPOSTAS_CORRETAS.put(7, btnOpcao4);
            RESPOSTAS_CORRETAS.put(8, btnOpcao2);
            RESPOSTAS_CORRETAS.put(9, btnOpcao3);
            RESPOSTAS_CORRETAS.put(10, btnOpcao2);
        } else if (missao == 10) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao4);
            RESPOSTAS_CORRETAS.put(2, btnOpcao4);
            RESPOSTAS_CORRETAS.put(3, btnOpcao4);
            RESPOSTAS_CORRETAS.put(4, btnOpcao1);
            RESPOSTAS_CORRETAS.put(5, btnOpcao2);
            RESPOSTAS_CORRETAS.put(6, btnOpcao1);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao2);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao1);
        } else if (missao == 11) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao2);
            RESPOSTAS_CORRETAS.put(2, btnOpcao3);
            RESPOSTAS_CORRETAS.put(3, btnOpcao3);
            RESPOSTAS_CORRETAS.put(4, btnOpcao4);
            RESPOSTAS_CORRETAS.put(5, btnOpcao3);
            RESPOSTAS_CORRETAS.put(6, btnOpcao2);
            RESPOSTAS_CORRETAS.put(7, btnOpcao2);
            RESPOSTAS_CORRETAS.put(8, btnOpcao4);
            RESPOSTAS_CORRETAS.put(9, btnOpcao1);
            RESPOSTAS_CORRETAS.put(10, btnOpcao2);
        } else if (missao == 12) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao2);
            RESPOSTAS_CORRETAS.put(2, btnOpcao3);
            RESPOSTAS_CORRETAS.put(3, btnOpcao2);
            RESPOSTAS_CORRETAS.put(4, btnOpcao3);
            RESPOSTAS_CORRETAS.put(5, btnOpcao1);
            RESPOSTAS_CORRETAS.put(6, btnOpcao2);
            RESPOSTAS_CORRETAS.put(7, btnOpcao2);
            RESPOSTAS_CORRETAS.put(8, btnOpcao2);
            RESPOSTAS_CORRETAS.put(9, btnOpcao3);
            RESPOSTAS_CORRETAS.put(10, btnOpcao1);
        } else if (missao == 13) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao1);
            RESPOSTAS_CORRETAS.put(2, btnOpcao2);
            RESPOSTAS_CORRETAS.put(3, btnOpcao4);
            RESPOSTAS_CORRETAS.put(4, btnOpcao2);
            RESPOSTAS_CORRETAS.put(5, btnOpcao2);
            RESPOSTAS_CORRETAS.put(6, btnOpcao3);
            RESPOSTAS_CORRETAS.put(7, btnOpcao1);
            RESPOSTAS_CORRETAS.put(8, btnOpcao4);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao2);
        } else if (missao == 14) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao4);
            RESPOSTAS_CORRETAS.put(2, btnOpcao2);
            RESPOSTAS_CORRETAS.put(3, btnOpcao2);
            RESPOSTAS_CORRETAS.put(4, btnOpcao2);
            RESPOSTAS_CORRETAS.put(5, btnOpcao1);
            RESPOSTAS_CORRETAS.put(6, btnOpcao2);
            RESPOSTAS_CORRETAS.put(7, btnOpcao2);
            RESPOSTAS_CORRETAS.put(8, btnOpcao4);
            RESPOSTAS_CORRETAS.put(9, btnOpcao2);
            RESPOSTAS_CORRETAS.put(10, btnOpcao2);
        } else if (missao == 15) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao4);
            RESPOSTAS_CORRETAS.put(2, btnOpcao3);
            RESPOSTAS_CORRETAS.put(3, btnOpcao2);
            RESPOSTAS_CORRETAS.put(4, btnOpcao3);
            RESPOSTAS_CORRETAS.put(5, btnOpcao4);
            RESPOSTAS_CORRETAS.put(6, btnOpcao3);
            RESPOSTAS_CORRETAS.put(7, btnOpcao4);
            RESPOSTAS_CORRETAS.put(8, btnOpcao4);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao4);
        } else if (missao == 16) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao1);
            RESPOSTAS_CORRETAS.put(2, btnOpcao1);
            RESPOSTAS_CORRETAS.put(3, btnOpcao1);
            RESPOSTAS_CORRETAS.put(4, btnOpcao2);
            RESPOSTAS_CORRETAS.put(5, btnOpcao2);
            RESPOSTAS_CORRETAS.put(6, btnOpcao3);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao3);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao4);
        } else if (missao == 17) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao4);
            RESPOSTAS_CORRETAS.put(2, btnOpcao2);
            RESPOSTAS_CORRETAS.put(3, btnOpcao2);
            RESPOSTAS_CORRETAS.put(4, btnOpcao3);
            RESPOSTAS_CORRETAS.put(5, btnOpcao1);
            RESPOSTAS_CORRETAS.put(6, btnOpcao1);
            RESPOSTAS_CORRETAS.put(7, btnOpcao1);
            RESPOSTAS_CORRETAS.put(8, btnOpcao4);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao1);
        } else if (missao == 18) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao2);
            RESPOSTAS_CORRETAS.put(2, btnOpcao3);
            RESPOSTAS_CORRETAS.put(3, btnOpcao3);
            RESPOSTAS_CORRETAS.put(4, btnOpcao3);
            RESPOSTAS_CORRETAS.put(5, btnOpcao3);
            RESPOSTAS_CORRETAS.put(6, btnOpcao1);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao4);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao2);
        } else if (missao == 19) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao2);
            RESPOSTAS_CORRETAS.put(2, btnOpcao3);
            RESPOSTAS_CORRETAS.put(3, btnOpcao1);
            RESPOSTAS_CORRETAS.put(4, btnOpcao2);
            RESPOSTAS_CORRETAS.put(5, btnOpcao2);
            RESPOSTAS_CORRETAS.put(6, btnOpcao3);
            RESPOSTAS_CORRETAS.put(7, btnOpcao1);
            RESPOSTAS_CORRETAS.put(8, btnOpcao2);
            RESPOSTAS_CORRETAS.put(9, btnOpcao3);
            RESPOSTAS_CORRETAS.put(10, btnOpcao4);
        } else if (missao == 20) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao3);
            RESPOSTAS_CORRETAS.put(2, btnOpcao1);
            RESPOSTAS_CORRETAS.put(3, btnOpcao2);
            RESPOSTAS_CORRETAS.put(4, btnOpcao3);
            RESPOSTAS_CORRETAS.put(5, btnOpcao2);
            RESPOSTAS_CORRETAS.put(6, btnOpcao3);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao3);
            RESPOSTAS_CORRETAS.put(9, btnOpcao4);
            RESPOSTAS_CORRETAS.put(10, btnOpcao3);
        } else if (missao == 21) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao3);
            RESPOSTAS_CORRETAS.put(2, btnOpcao4);
            RESPOSTAS_CORRETAS.put(3, btnOpcao1);
            RESPOSTAS_CORRETAS.put(4, btnOpcao4);
            RESPOSTAS_CORRETAS.put(5, btnOpcao3);
            RESPOSTAS_CORRETAS.put(6, btnOpcao1);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao1);
            RESPOSTAS_CORRETAS.put(9, btnOpcao1);
            RESPOSTAS_CORRETAS.put(10, btnOpcao2);
        } else if (missao == 22) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao3);
            RESPOSTAS_CORRETAS.put(2, btnOpcao1);
            RESPOSTAS_CORRETAS.put(3, btnOpcao3);
            RESPOSTAS_CORRETAS.put(4, btnOpcao3);
            RESPOSTAS_CORRETAS.put(5, btnOpcao2);
            RESPOSTAS_CORRETAS.put(6, btnOpcao4);
            RESPOSTAS_CORRETAS.put(7, btnOpcao3);
            RESPOSTAS_CORRETAS.put(8, btnOpcao2);
            RESPOSTAS_CORRETAS.put(9, btnOpcao3);
            RESPOSTAS_CORRETAS.put(10, btnOpcao2);
        } else if (missao == 23) {
            RESPOSTAS_CORRETAS.put(1, btnOpcao4);
            RESPOSTAS_CORRETAS.put(2, btnOpcao3);
            RESPOSTAS_CORRETAS.put(3, btnOpcao3);
            RESPOSTAS_CORRETAS.put(4, btnOpcao1);
            RESPOSTAS_CORRETAS.put(5, btnOpcao3);
            RESPOSTAS_CORRETAS.put(6, btnOpcao2);
            RESPOSTAS_CORRETAS.put(7, btnOpcao1);
            RESPOSTAS_CORRETAS.put(8, btnOpcao2);
            RESPOSTAS_CORRETAS.put(9, btnOpcao1);
            RESPOSTAS_CORRETAS.put(10, btnOpcao2);
            RESPOSTAS_CORRETAS.put(11, btnOpcao1);
            RESPOSTAS_CORRETAS.put(12, btnOpcao4);
            RESPOSTAS_CORRETAS.put(13, btnOpcao1);
            RESPOSTAS_CORRETAS.put(14, btnOpcao3);
            RESPOSTAS_CORRETAS.put(15, btnOpcao3);
            RESPOSTAS_CORRETAS.put(16, btnOpcao3);
            RESPOSTAS_CORRETAS.put(17, btnOpcao4);
            RESPOSTAS_CORRETAS.put(18, btnOpcao1);
            RESPOSTAS_CORRETAS.put(19, btnOpcao3);
            RESPOSTAS_CORRETAS.put(20, btnOpcao2);
            RESPOSTAS_CORRETAS.put(21, btnOpcao4);
            RESPOSTAS_CORRETAS.put(22, btnOpcao1);
            RESPOSTAS_CORRETAS.put(23, btnOpcao1);
            RESPOSTAS_CORRETAS.put(24, btnOpcao1);
            RESPOSTAS_CORRETAS.put(25, btnOpcao2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoja) {
            dispose();
            Loja telaLoja = new Loja(missao, numeroQuestao, emailUsuario, dicasEscritas[numeroQuestao]);
        }
        if (e.getSource() == btnMochila) {
            dispose();
            MochilaButton mochila = new MochilaButton(missao, numeroQuestao, emailUsuario, dicasEscritas[numeroQuestao]);
        }

        JButton botaoCorreto = RESPOSTAS_CORRETAS.get(numeroQuestao);

        if (e.getSource() == botaoCorreto) {
            JOptionPane.showMessageDialog(this, "Opção Correta, parabéns!", "Opção Correta", JOptionPane.INFORMATION_MESSAGE);
            recriarPanel();
        } else if (e.getSource() != btnLoja && e.getSource() != btnMochila) {
            JOptionPane.showMessageDialog(this, "Opção Incorreta, tente novamente!", "Opção Errada", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void recriarPanel() {
        if (pnlAtual != null) {
            pnlAtual.remove(btnOpcao1);
            pnlAtual.remove(btnOpcao2);
            pnlAtual.remove(btnOpcao3);
            pnlAtual.remove(btnOpcao4);
            pnlAtual.remove(btnLoja);
            pnlAtual.remove(btnMochila);
        }

        getContentPane().remove(pnlAtual);

        numeroQuestao++;

        pnlAtual = criarPanel(numeroQuestao);

        getContentPane().add(pnlAtual, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

}
