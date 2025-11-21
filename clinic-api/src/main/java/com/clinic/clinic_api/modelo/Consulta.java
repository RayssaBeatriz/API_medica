package com.clinic.clinic_api.modelo;

import java.time.LocalDateTime;

public class Consulta {

    private Long id;
    private Paciente paciente;
    private Dr doutor;
    private LocalDateTime dataHora;

    public Consulta() {
    }

    public Consulta(Long id, Paciente paciente, Dr doutor, LocalDateTime dataHora) {
        this.id = id;
        this.paciente = paciente;
        this.doutor = doutor;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Dr getDoutor() {
        return doutor;
    }

    public void setDoutor(Dr doutor) {
        this.doutor = doutor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
