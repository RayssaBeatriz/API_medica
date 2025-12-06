package com.clinic.clinic_api.modelo;

import java.time.LocalDateTime;

public class Horario {

    private Long id;
    private Long idDoutor;
    private LocalDateTime dataHora;
    private boolean reservado;

    public Horario() {}

    public Horario(Long id, Long idDoutor, LocalDateTime dataHora, boolean reservado) {
        this.id = id;
        this.idDoutor = idDoutor;
        this.dataHora = dataHora;
        this.reservado = reservado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdDoutor() { return idDoutor; }
    public void setIdDoutor(Long idDoutor) { this.idDoutor = idDoutor; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public boolean isReservado() { return reservado; }
    public void setReservado(boolean reservado) { this.reservado = reservado; }
}
