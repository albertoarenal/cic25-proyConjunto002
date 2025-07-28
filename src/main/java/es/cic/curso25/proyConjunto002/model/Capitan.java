package es.cic.curso25.proyConjunto002.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "Capitan")
public class Capitan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Version
    private long version;

    //Atributos
    
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "numeroDeLicencia", length = 8, unique = true)
    private String numeroDeLicencia; //El número de licencia contiene 8 digitos y es único
    
    //Relación One to One con la entidad Barco
    //Capitán es el no propietario de la relación con Barco
    //@JsonIgnore para Jackson

    @JsonIgnore 
    @OneToOne(mappedBy = "capitan", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Barco barco; 

    //Getters y Setters
     public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getNumeroDeLicencia() {
        return numeroDeLicencia;
    }
    public void setNumeroDeLicencia(String numeroDeLicencia) {
        this.numeroDeLicencia = numeroDeLicencia;
    }
    public Barco getBarco() {
        return barco;
    }
    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    //Métodos hashCode() y equals()
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
        Capitan other = (Capitan) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    //Método toString()
    @Override
    public String toString() {
        return "Capitan [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", numeroDeLicencia="
                + numeroDeLicencia + "]";
    }

}
