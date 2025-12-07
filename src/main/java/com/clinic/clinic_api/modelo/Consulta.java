package com.clinic.clinic_api.modelo;

import java.time.LocalDateTime;

public class Consulta {

    private Long id;
    private Long idPaciente;
    private Long idDoutor;
    private LocalDateTime dataHora;

    public Consulta() {}

    public Consulta(Long id, Long idPaciente, Long idDoutor, LocalDateTime dataHora) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idDoutor = idDoutor;
        this.dataHora = dataHora;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }

    public Long getIdDoutor() { return idDoutor; }
    public void setIdDoutor(Long idDoutor) { this.idDoutor = idDoutor; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}
