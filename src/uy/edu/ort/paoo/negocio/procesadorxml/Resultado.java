/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio.procesadorxml;

import java.util.ArrayList;
import java.util.List;
import uy.edu.ort.paoo.exceptions.PaooException;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class Resultado<T> {

    private int procesados;
    private int errores;
    private int descartados;

    public enum TIPO_RESULTADO {
        ERROR, EXCEPTION, OK
    }
    private TIPO_RESULTADO tipo;
    private String mensaje;

    public Resultado() {
        this.descartados = 0;
        this.procesados = 0;
        this.errores = 0;
        tipo = TIPO_RESULTADO.OK;
    }
    
    public Resultado(String mensaje) {
        this.mensaje = mensaje;
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

    @Override
    public String toString() {
        return mensaje !=null ? mensaje : "Resultado: \n\tProcesados: " + getProcesados() + "\n\tErrores: "
                + getErrores() + "\n\tDescartados: " + getDescartados()
                + "\n\tExitosos: " + getExitosos();
    }

    public TIPO_RESULTADO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO_RESULTADO tipo) {
        this.tipo = tipo;
    }

    public boolean getIsExceptio() {
        return tipo.equals(TIPO_RESULTADO.EXCEPTION);
    }

    public boolean getIsOK() {
        return tipo.equals(TIPO_RESULTADO.OK);
    }

    public boolean getIsError() {
        return tipo.equals(TIPO_RESULTADO.ERROR);
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
