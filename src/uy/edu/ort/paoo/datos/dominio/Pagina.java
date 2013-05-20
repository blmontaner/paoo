package uy.edu.ort.paoo.datos.dominio;

public class Pagina {

    private String nombre;
    private String body;
    private long peso;
    private long lineas;

    public Pagina() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getPeso() {
        return peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }

    /**
     * @return the lineas
     */
    public long getLineas() {
        return lineas;
    }

    /**
     * @param lineas the lineas to set
     */
    public void setLineas(long lineas) {
        this.lineas = lineas;
    }

    @Override
    public String toString() {
        return "Pagina [nombre: " + nombre + "]";
    }
}
