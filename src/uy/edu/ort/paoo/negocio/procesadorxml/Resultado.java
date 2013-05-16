/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio.procesadorxml;

/**
 *
 * @author Victor
 */
public class Resultado {
    private int procesados;
    private int errores;
    private int descartados;
    
    public Resultado()
    {
        this.descartados = 0;
        this.procesados = 0;
        this.errores = 0;
    }

    /**
     * @return the procesados
     */
    public int getProcesados() {
        return procesados;
    }

    /**
     * @param procesados the procesados to set
     */
    public void setProcesados(int procesados) {
        this.procesados = procesados;
    }
    
    /**
     * 
     */
    public void aumentarProcesados() {
        this.procesados++;
    }

    /**
     * @return the errores
     */
    public int getErrores() {
        return errores;
    }

    /**
     * @param errores the errores to set
     */
    public void setErrores(int errores) {
        this.errores = errores;
    }
    
    public void aumentarErrores() {
        this.errores++;
    }

    /**
     * @return the descartados
     */
    public int getDescartados() {
        return descartados;
    }

    /**
     * @param descartados the descartados to set
     */
    public void setDescartados(int descartados) {
        this.descartados = descartados;
    }
    
    public void aumentarDescartados() {
        this.descartados++;
    }

    /**
     * @return the exitosos
     */
    public int getExitosos() {
        return (procesados - (descartados + errores));
    }

}
