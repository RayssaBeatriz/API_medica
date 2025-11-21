package com.clinic.clinic_api.controller;

import com.clinic.clinic_api.modelo.Consulta;
import com.clinic.clinic_api.modelo.Dr;
import com.clinic.clinic_api.modelo.Paciente;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class Controlador {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Dr> doutores = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();

    private AtomicLong pacienteIdCounter = new AtomicLong(1);
    private AtomicLong doutorIdCounter = new AtomicLong(1);
    private AtomicLong consultaIdCounter = new AtomicLong(1);

    // -------------------- PACIENTES --------------------
    @PostMapping("/pacientes")
    public Paciente criarPaciente(@RequestParam String nome) {
        Paciente paciente = new Paciente(pacienteIdCounter.getAndIncrement(), nome);
        pacientes.add(paciente);
        return paciente;
    }

    @GetMapping("/pacientes")
    public List<Paciente> listarPacientes() {
        return pacientes;
    }

    // -------------------- DOUTORES --------------------
    @PostMapping("/doutores")
    public Dr criarDoutor(@RequestParam String nome, @RequestParam String especialidade) {
        Dr doutor = new Dr(doutorIdCounter.getAndIncrement(), nome, especialidade);
        doutores.add(doutor);
        return doutor;
    }

    @GetMapping("/doutores")
    public List<Dr> listarDoutores() {
        return doutores;
    }

    // -------------------- CONSULTAS --------------------
    @PostMapping("/consultas")
    public Consulta marcarConsulta(@RequestParam Long pacienteId,
                                   @RequestParam Long doutorId,
                                   @RequestParam String dataHora) {

        Paciente paciente = pacientes.stream()
                .filter(p -> p.getId().equals(pacienteId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Dr doutor = doutores.stream()
                .filter(d -> d.getId().equals(doutorId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));

        // 🔹 Novo formato desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        LocalDateTime dt = LocalDateTime.parse(dataHora, formatter);

        Consulta consulta = new Consulta(consultaIdCounter.getAndIncrement(), paciente, doutor, dt);
        consultas.add(consulta);

        return consulta;
    }

    @GetMapping("/consultas")
    public List<Consulta> listarConsultas() {
        return consultas;
    }
}
