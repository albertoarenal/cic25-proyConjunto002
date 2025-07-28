package es.cic.curso25.proyConjunto002.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;

@Entity
public class Baca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double ancho;
    private double largo;
    private int peso;

    @Version
    private Long version;
    
    @JsonIgnore
    @OneToOne(mappedBy = "baca", cascade = CascadeType.REMOVE)
    private Coche coche;


    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getAncho() {
        return ancho;
    }
    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
    public double getLargo() {
        return largo;
    }
    public void setLargo(double largo) {
        this.largo = largo;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
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
        Baca other = (Baca) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Baca [id=" + id + ", ancho=" + ancho + ", largo=" + largo + ", peso=" + peso + "]";
    }

}
