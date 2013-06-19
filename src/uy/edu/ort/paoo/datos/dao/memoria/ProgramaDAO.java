package uy.edu.ort.paoo.datos.dao.memoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.dominio.ProgramaComparator;
import uy.edu.ort.paoo.datos.dominio.ProgramaComparator.EnumProgramaComparator;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProgramaDAO implements IProgramaDAO {

    /**
     * Entidad Programa a salvar en la BD
     *
     * @param entity
     */
    @Override
    public void save(Programa entity) {
        DB.getInstance().getProgramas().add(entity);
    }

    /**
     *
     * @param entity
     */
    @Override
    public void delete(Programa entity) {
        // TODO Auto-generated method stub
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Programa getByPK(Object id) {
        for (Programa p : DB.getInstance().getProgramas()) {
            if (p.getNombre().equals(id)) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Programa> getAll() {
        return DB.getInstance().getProgramas();
    }

    /**
     *
     * @param prop
     * @param val
     * @return
     */
    @Override
    public List<Programa> getByProperty(String prop, Object val) {
        List<Programa> programasRetorno = new ArrayList<Programa>();
        if (prop.equals(Programa.PROPIEDAD_CLIENTE)) {
            for (Programa programa : DB.getInstance().getProgramas()) {
                if (programa.getCliente().getIdentificador().equals(val)) {
                    programasRetorno.add(programa);
                }
            }
        }
        return programasRetorno;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Programa> getTop10MasPaginas() {
        ProgramaComparator comp = new ProgramaComparator();
        comp.setComparator(EnumProgramaComparator.COMPARATOR_PAGINAS);
        List<Programa> progs = DB.getInstance().getProgramas();
        Collections.sort(progs, comp);
        if (progs.size() >= 9) {
            return progs.subList(0, 9);
        } else {
            return progs;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public List<Programa> getTop10MasPesados() {
        ProgramaComparator comp = new ProgramaComparator();
        comp.setComparator(EnumProgramaComparator.COMPARATOR_PESO);
        List<Programa> progs = DB.getInstance().getProgramas();
        Collections.sort(progs, comp);
        if (progs.size() >= 9) {
            return progs.subList(0, 9);
        } else {
            return progs;
        }
    }

    /**
     *
     * @param idProg
     * @return
     */
    @Override
    public List<Pagina> getPaginasPrograma(long idProg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
