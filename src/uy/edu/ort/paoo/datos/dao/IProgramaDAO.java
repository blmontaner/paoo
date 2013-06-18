package uy.edu.ort.paoo.datos.dao;

import java.util.List;
import uy.edu.ort.paoo.datos.DatosPaooException;
import uy.edu.ort.paoo.datos.dominio.Pagina;

import uy.edu.ort.paoo.datos.dominio.Programa;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public interface IProgramaDAO extends IDAO<Programa> {

    List<Programa> getTop10MasPaginas() throws DatosPaooException;

    List<Programa> getTop10MasPesados() throws DatosPaooException;
    
    List<Pagina> getPaginasPrograma(long idProg) throws DatosPaooException;
}
