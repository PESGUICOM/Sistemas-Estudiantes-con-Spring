package com.pesguicom.estudiantes;

import com.pesguicom.estudiantes.modelo.Estudiante;
import com.pesguicom.estudiantes.servicio.EstudianteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
	@Autowired
	private EstudianteServicio estudianteServicio;

	//para mandar información a consola
	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	//para hacer un salto de línea
	String nl = System.lineSeparator();

	public static void main(String[] args) {
        //para imprimir por consola
		logger.info("Iniciando la aplicación...");
		//Esta línea lo que hace es levantar la fábrica de Spring con todas las inyecciones de dependencias que se necesiten
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicación finalizada...");
	}

	@Override
	public void run(String... args) throws Exception {
       logger.info(nl + "Ejecutando el método run de Spring..." +  nl);
	   logger.info("Hola César" + nl);

	   var salir = false;
	   var consola = new Scanner(System.in);
	   while (!salir){
		   mostrarMenu();
		   salir = ejecutarOpciones(consola);
		   logger.info(nl);
	   } // fin del ciclo while
	}
	private void mostrarMenu() {
		//logger.info(nl);
		logger.info("""
				***Sistemas de Estudiantes***
				1. Listar estudiantes
				2. Buscar estudiante
				3. Agregar estudiante
				4. Modificar estudiante
				5. Eliminar estudiante
				6. Salir
				Elige una opción:
				""");
	}
	private boolean ejecutarOpciones(Scanner consola){
            var opcion = Integer.parseInt(consola.nextLine());
			var salir = false;
            switch (opcion){
				case 1 -> { //Listar estudiantes
				logger.info(nl + "Listado de Estudiantes" + nl);
					List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
					estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
				}
                case 2 -> { //Buscar estudiante por id
					logger.info("Introduce el id estudiante a buscar: ");
					var idEstudiante = Integer.parseInt(consola.nextLine());
					Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
					if (estudiante != null)
						logger.info("Estudiante encontrado: " + estudiante + nl);
					else
						logger.info("Estudiante NO encontrado con id: " + idEstudiante + nl);
				}
				case 3 -> { // Agregar estudiante
					logger.info("Agregar estudiante: " + nl);
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Celular: ");
					var celular = consola.nextLine();
					logger.info("Email: ");
					var email = consola.nextLine();
					//Crear el objeto estudiante sin el id
					var estudiante = new Estudiante();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setCelular(celular);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante agregado: " + estudiante + nl);
				}
				case 4 -> { //Modificar estudiante
					logger.info("Modificar estudiante: " + nl);
					logger.info("Id Estudiante: ");
					var idEstudiante = Integer.parseInt(consola.nextLine());
					//Buscamos el estudiante a modificar
					Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
					if (estudiante != null) {
						var nombre = consola.nextLine();
						logger.info("Apellido: ");
						var apellido = consola.nextLine();
						logger.info("Celular: ");
						var celular = consola.nextLine();
						logger.info("Email: ");
						var email = consola.nextLine();
						estudiante.setNombre(nombre);
						estudiante.setApellido(apellido);
						estudiante.setCelular(celular);
						estudiante.setEmail(email);
						estudianteServicio.guardarEstudiante(estudiante);
						logger.info("Estudiante modificado: " + estudiante + nl);
					}	else {
						logger.info("Estudiante NO encontrado con id: " + idEstudiante + nl);
					}
				}
				case 5 -> { //Eliminar estudiante
					logger.info("Eliminar estudiante: " + nl);
					logger.info("Id Estudiante: ");
					var idEstudiante = Integer.parseInt(consola.nextLine());
					//Buscamos el id a eliminar
					var estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
					if (estudiante != null){
						estudianteServicio.eliminarEstudiante(estudiante);
						logger.info("Estudiante eliminado: " + estudiante + nl);
					} else {
						logger.info("Estudiante NO encontrado con id: " + idEstudiante);
					}
				}
				case 6 -> { //Salir
					logger.info("Hasta pronto!" + nl + nl);
					salir = true;
				}
				default -> logger.info("Opción NO reconocida: " + opcion + nl);
			} //fin switch
			return salir;
	   }
}


