package com.pesguicom.estudiantes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // Clase de identidad
// boilerplate- código repititivo
@Data // se agregan los getter y setter
@NoArgsConstructor // se agrega el constructor vacío
@AllArgsConstructor // se agrega el constructor con todos los argumentos
@ToString // con esto se agrega el método toString

public class Estudiante {
    @Id //Significa llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para trabajar con una llave primaria auto incrementable
    private Integer idEstudiante;

    private String nombre;
    private String apellido;
    private String celular;
    private String email;


}
