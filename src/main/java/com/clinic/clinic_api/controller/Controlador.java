package com.clinic.clinic_api.controller;

import com.clinic.clinic_api.modelo.Dr;
import com.clinic.clinic_api.modelo.Paciente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/api")
public class Controlador {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Dr> doutores = new ArrayList<>();

    private AtomicLong pacienteIdCounter = new AtomicLong(1);
    private AtomicLong doutorIdCounter = new AtomicLong(1);

    // -------------------- PACIENTES --------------------
    @PostMapping("/pacientes")
    public Paciente criarPaciente(@RequestParam String nome, @RequestParam String cpf) {
        Paciente paciente = new Paciente(pacienteIdCounter.getAndIncrement(), nome, cpf);
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

}
