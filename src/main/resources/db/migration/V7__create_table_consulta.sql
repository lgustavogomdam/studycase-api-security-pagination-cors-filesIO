CREATE TABLE consulta (

  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  data TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  id_medico INTEGER NOT NULL,
  id_paciente INTEGER NOT NULL,
  CONSTRAINT fk_consulta_medico FOREIGN KEY (id_medico) REFERENCES medico(id),
  CONSTRAINT fk_consulta_paciente FOREIGN KEY (id_paciente) REFERENCES paciente(id)
);
