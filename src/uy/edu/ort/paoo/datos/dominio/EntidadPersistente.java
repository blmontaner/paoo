package uy.edu.ort.paoo.datos.dominio;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 * 
 * Clase padre que engloba las propiedades comunes 
 * a las entidades que se van a persistir.
 */
@MappedSuperclass
public class EntidadPersistente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;

    public EntidadPersistente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
