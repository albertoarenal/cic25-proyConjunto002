package es.cic.curso25.proyConjunto002.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "Barco")
public class Barco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Version
    private long version;

    @Column(name = "tipoBarco")
    private String tipoBarco;
    @Column(name = "anio")
    private int anio;
    @Column(name = "material")
    private String material;
    @Column(name = "capacidad")
    private int capacidad;

    //Relación One to One con entidad Capitán
    @OneToOne(mappedBy = "barco", cascade = CascadeType.PERSIST)
    private Capitan capitan;

    //Constructores
    public Barco() {

    }


    public Barco(Long id, String tipoBarco, int anio, String material, int capacidad) {
        this.id = id;
        this.tipoBarco = tipoBarco;
        this.anio = anio;
        this.material = material;
        this.capacidad = capacidad;
    }


    //Getters & Setters
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTipoBarco() {
        return tipoBarco;
    }


    public void setTipoBarco(String tipoBarco) {
        this.tipoBarco = tipoBarco;
    }


    public int getAnio() {
        return anio;
    }


    public void setAnio(int anio) {
        this.anio = anio;
    }


    public String getMaterial() {
        return material;
    }


    public void setMaterial(String material) {
        this.material = material;
    }


    public int getCapacidad() {
        return capacidad;
    }


    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }


    public Capitan getCapitan() {
        return capitan;
    }


    public void setCapitan(Capitan capitan) {
        this.capitan = capitan;
    }
    
    //Método toString
    @Override
    public String toString() {
        return "Barco [id=" + id + ", tipoBarco=" + tipoBarco + ", anio=" + anio + ", material=" + material
                + ", capacidad=" + capacidad + "]";
    }
    


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Barco other = (Barco) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
