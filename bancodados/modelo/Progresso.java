
package bancodados.modelo;

public class Progresso {
    private String email;
    private int missao;
    private int xp;
    private int moedas;
    private int nivel;

    public Progresso(String email, int missao, int xp, int nivel, int moedas) {
        this.email = email;
        this.missao = missao;
        this.xp = xp;
        this.nivel = nivel;
        this.moedas = moedas;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMissao() {
        return missao;
    }

    public void setMissao(int missao) {
        this.missao = missao;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public int getMoedas() {
        return moedas;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
}
