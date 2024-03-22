package com.netlify.lggdev.crudsistemamedico.model.medico;

import com.netlify.lggdev.crudsistemamedico.enums.Especialidade;
import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoEntity;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.util.List;
import java.util.Objects;

@Entity(name = "Medico")
@Table(name = "medico")
public class MedicoEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "codigo_crm", nullable = false, updatable = false)
    private String CRM;

    @Column(name = "especialidade_medica", nullable = false)
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @OneToMany(
            mappedBy = "medico",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<EnderecoEntity> listaEnderecos;

    @OneToMany(
            mappedBy = "medico",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<EnderecoEntity> consultas;

    public MedicoEntity() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return (getId() == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicoEntity medicoEntity = (MedicoEntity) o;
        return id.equals(medicoEntity.id) && CRM.equals(medicoEntity.CRM);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, CRM);
    }
}
