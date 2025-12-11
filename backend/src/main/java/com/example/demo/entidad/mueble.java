package com.example.demo.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "mueble")
public class mueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer ID_mueble;
    private String nombre_mueble;
    private String tipo;
    private Double precio_base;
    private Integer stock;
    private String estado;
    private String tamaño;
    private String material;

   public Integer getID_mueble() {
        return ID_mueble;
    }


    public void setID_mueble(Integer ID_mueble) {
        this.ID_mueble=ID_mueble;
    }

    public String getNombre_mueble() {
        return nombre_mueble;
    }

    public void setNombre_mueble(String nombre_mueble) {
        this.nombre_mueble=nombre_mueble;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo=tipo;
    }

    public Double getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(double precio_base) {
        this.precio_base=precio_base;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock=stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado=estado;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño=tamaño;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material=material;
    }
}
