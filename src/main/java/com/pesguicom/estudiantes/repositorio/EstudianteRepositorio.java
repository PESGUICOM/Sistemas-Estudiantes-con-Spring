package com.pesguicom.estudiantes.repositorio;

import com.pesguicom.estudiantes.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer> {
}
// Esta clase de Spring equivale a la clase que hicimos con EstudianteDAO