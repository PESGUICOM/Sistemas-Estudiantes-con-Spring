package com.pesguicom.estudiantes.servicio;

import com.pesguicom.estudiantes.modelo.Estudiante;
import com.pesguicom.estudiantes.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service //para poder participar en las inyecciones de dependencias
public class EstudianteServicio implements IEstudianteServicio{

    @Autowired  //para inyectar las dependencias necesarias a esta clase
    private EstudianteRepositorio estudianteRepositorio;

    @Override
    public List<Estudiante> listarEstudiantes() {
        //El método findAll() regresa una lista
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
        //en caso que no encuentre el objeto regresa el valor de nulo
        Estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
        return estudiante;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
       //salva o guarda el estudiante
       estudianteRepositorio.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
       //para eliminar un id
       estudianteRepositorio.delete(estudiante);
    }
}
