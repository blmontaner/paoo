package uy.edu.ort.paoo.datos.dao.memoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.dominio.ProgramaComparator;
import uy.edu.ort.paoo.datos.dominio.ProgramaComparator.EnumProgramaComparator;

public class ProgramaDAO implements IProgramaDAO {

    @Override
    public void save(Programa entity) {
        DB.getInstance().getProgramas().add(entity);
    }

    @Override
    public void delete(Programa entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public Programa getByPK(Object id) {
        for (Programa p : DB.getInstance().getProgramas()) {
            if (p.getNombre().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Programa> getAll() {
        return DB.getInstance().getProgramas();
    }

    @Override
    public List<Programa> getByProperty(String prop, Object val) {
        List<Programa> programasRetorno = new ArrayList<Programa>();
        if (prop == Programa.PROPIEDAD_CLIENTE) {
            for (Programa programa : DB.getInstance().getProgramas()) {
                System.out.println(programa.getCliente().getIdentificador());
                if (programa.getCliente().getIdentificador().equals(val)) {
                    programasRetorno.add(programa);
                }
            }
        }
        return programasRetorno;
    }

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
}
