package uy.edu.ort.paoo.datos.dominio;

import java.util.Comparator;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 *
 * Comparador personalizado de Programas
 */
public class ProgramaComparator implements Comparator<Programa> {

    public enum EnumProgramaComparator {

        COMPARATOR_PESO, COMPARATOR_PAGINAS
    }
    private EnumProgramaComparator parametroComparacion;

    public void setComparator(EnumProgramaComparator par) {
        this.parametroComparacion = par;
    }

    @Override
    public int compare(Programa o1, Programa o2) {
        int comparison;
        switch (parametroComparacion) {
            case COMPARATOR_PESO:
                comparison = (int) (o2.getPesoTotal() - o1.getPesoTotal());
                if (comparison != 0) {
                    return comparison;
                }
                break;
            case COMPARATOR_PAGINAS:
                comparison = o2.getCantidadPaginas() - o1.getCantidadPaginas();
                if (comparison != 0) {
                    return comparison;
                }
                break;
        }
        return 0;
    }
}
