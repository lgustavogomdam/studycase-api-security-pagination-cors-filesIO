package com.netlify.lggdev.crudsistemamedico.model.endereco;

import com.netlify.lggdev.crudsistemamedico.enums.UFBrasil;
import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoEntity;
import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteEntity;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;


import java.util.Objects;

@Entity(name = "Endereco")
@Table(name = "endereco")
public class EnderecoEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "uf", nullable = false)
    @Enumerated(EnumType.STRING)
    private UFBrasil UF;

    @Column(name = "cep", nullable = false)
    private String CEP;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", referencedColumnName = "id")
    private MedicoEntity medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    private PacienteEntity paciente;

    @Override
    public boolean isNew() {
        return (getId() == null);
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        paciente = paciente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public UFBrasil getUF() {
        return UF;
    }

    public void setUF(UFBrasil UF) {
        this.UF = UF;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medicoEntity) {
        this.medico = medicoEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnderecoEntity enderecoEntity = (EnderecoEntity) o;
        return Objects.equals(id, enderecoEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "número='" + numero + '\'' +
                ", CEP='" + CEP + '\'' +
                '}';
    }
}
