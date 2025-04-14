package dev.estv.mapAereo.models;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;


import org.springframework.hateoas.RepresentationModel;


@Entity
@Table(name = "products")
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String nome;
    private Integer quantidade;
    private String validade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}