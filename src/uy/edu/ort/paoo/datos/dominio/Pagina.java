package uy.edu.ort.paoo.datos.dominio;

public class Pagina {

    private String nombre;
    private String body;
    private long peso;
    private long lineas;

    public Pagina() {
    }

    /**
     * @return lineas
     */
    public long getLineas() {
        return lineas;
    }

    /**
     * @param lineas lineas to set
     */
    public void setLineas(long lineas) {
        this.lineas = lineas;
    }

    /**
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return peso in bytes
     */
    public long getPeso() {
        return peso;
    }

    /**
     * @param peso peso to set in bytes
     */
    public void setPeso(long peso) {
        this.peso = peso;
    }
}
