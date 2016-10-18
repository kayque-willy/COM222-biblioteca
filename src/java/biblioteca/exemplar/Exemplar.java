package biblioteca.exemplar;

public class Exemplar {

    /*---------- Atributos ----------*/
    private int id;
    private int numero;
    private int publicacao_isbn;
    private Double preco;
    private String status;

    /*---------- Métodos ----------*/
    public void exemplar() {

    }

    /*---------- Get e Set ----------*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPublicacao_isbn() {
        return publicacao_isbn;
    }

    public void setPublicacao_isbn(int publicacao_isbn) {
        this.publicacao_isbn = publicacao_isbn;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
