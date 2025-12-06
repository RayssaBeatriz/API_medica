package com.clinic.clinic_api.controller;

import com.clinic.clinic_api.modelo.*;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class Controlador {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Dr> doutores = new ArrayList<>();
    private List<Horario> horarios = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();

    private AtomicLong pacienteIdCounter = new AtomicLong(1);
    private AtomicLong doutorIdCounter = new AtomicLong(1);
    private AtomicLong horarioIdCounter = new AtomicLong(1);
    private AtomicLong consultaIdCounter = new AtomicLong(1);

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

    // -------------------- HORÁRIOS --------------------
    @PostMapping("/horarios")
    public Horario adicionarHorario(@RequestParam Long idDoutor,
                                    @RequestParam String dataHora) {

        // validar se doutor existe
        if (doutores.stream().noneMatch(d -> d.getId().equals(idDoutor))) {
            return null; // ou lançar exceção / retornar mensagem de erro
        }

        LocalDateTime dt = LocalDateTime.parse(dataHora);

        Horario horario = new Horario(
                horarioIdCounter.getAndIncrement(),
                idDoutor,
                dt,
                false
        );

        horarios.add(horario);
        return horario;
    }

    @GetMapping("/horarios")
    public List<Horario> listarHorarios() {
        return horarios;
    }

    @GetMapping("/horarios/disponiveis")
    public List<Horario> listarHorariosLivres(@RequestParam Long idDoutor) {
        return horarios.stream()
                .filter(h -> h.getIdDoutor().equals(idDoutor) && !h.isReservado())
                .toList();
    }

    // -------------------- CONSULTAS --------------------
    @PostMapping("/consultas")
    public Object marcarConsulta(
            @RequestParam Long idPaciente,
            @RequestParam Long idDoutor,
            @RequestParam Long idHorario
    ) {
        // validar paciente
        if (pacientes.stream().noneMatch(p -> p.getId().equals(idPaciente))) {
            return "Paciente não existe.";
        }

        // validar doutor
        if (doutores.stream().noneMatch(d -> d.getId().equals(idDoutor))) {
            return "Doutor não existe.";
        }

        // validar horário
        Horario horario = horarios.stream()
                .filter(h -> h.getId().equals(idHorario))
                .findFirst()
                .orElse(null);

        if (horario == null) return "Horário não encontrado.";
        if (!horario.getIdDoutor().equals(idDoutor)) return "Esse horário não pertence a esse médico.";
        if (horario.isReservado()) return "Horário já reservado.";

        // marcar consulta
        Consulta consulta = new Consulta(
                consultaIdCounter.getAndIncrement(),
                idPaciente,
                idDoutor,
                horario.getDataHora()
        );

        consultas.add(consulta);
        horario.setReservado(true);

        return consulta;
    }

    @GetMapping("/consultas")
    public List<Consulta> listarConsultas() {
        return consultas;
    }

}
