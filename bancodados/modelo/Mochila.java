
package bancodados.modelo;

public class Mochila {
    private String email;
    private int quantidadePapiro;
    private int quantidadeLampada;

    public Mochila(String email, int quantidadePapiro, int quantidadeLampada) {
        this.email = email;
        this.quantidadePapiro = quantidadePapiro;
        this.quantidadeLampada = quantidadeLampada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getQuantidadePapiro() {
        return quantidadePapiro;
    }

    public void setQuantidadePapiro(int quantidadePapiro) {
        this.quantidadePapiro = quantidadePapiro;
    }

    public int getQuantidadeLampada() {
        return quantidadeLampada;
    }

    public void setQuantidadeLampada(int quantidadeLampada) {
        this.quantidadeLampada = quantidadeLampada;
    }
    
    
}
