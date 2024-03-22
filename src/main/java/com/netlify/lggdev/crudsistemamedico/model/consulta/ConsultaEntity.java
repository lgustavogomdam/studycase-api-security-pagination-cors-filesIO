package com.netlify.lggdev.crudsistemamedico.model.consulta;

import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoEntity;
import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "consulta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ConsultaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medico", referencedColumnName = "id", nullable = false)
    private MedicoEntity medico;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id", nullable = false)
    private PacienteEntity paciente;
}
