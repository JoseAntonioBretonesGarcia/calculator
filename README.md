# Calculator
Calculator demo with SpringBoot project

# Requisitos técnicos
- Java >= 17
- Maven >= 3.9.1

# Estructura del proyecto

- lib
	- tracer-1.0.0.jar
- src/main/java
  - com.example.calculator
	- configuration
	  - TracerConfiguration.java
    - controllers
      - ControllerAdvice.java
	  - OperacionController.java
    - exceptions
      - CustomIllegalArgumentException.java
    - interfaces
      - Operacion.java
    - models
      - Resta.java
	  - Suma.java
	- CalculadoraApplication.java
- src/main/resources
  - application.properties
- src/test/java
  - com.example.calculator
	- controllers
      - ControllerAdviceTest.java
	  - OperacionControllerTest.java
    - exceptions
      - CustomIllegalArgumentExceptionTest.java
	- models
	  - RestaTest.java
	  - SumaTest.java
	- CalculatorApplicationTests.java
- pom.xml


# Dependencias utilizadas
	- Spring Boot Starter Web
	- Lombok
	- Spring Boot Starter Test
	- SpringDoc OpenAPI Starter WebMVC UI
	- trace-1.0.0
	
	

# Las funcionalidades implementadas
	- Sumar dos números
	- Restar dos números

# Análisis solución implementada
	Se ha decidido realizar una solución en la cual la nueva incorporación de alguna nueva operación en un futuro
	no afecte al código existente y sea bastante ágil le insercción de dicha funcionalidad.
	
	Para ello, el microservicio tiene una interfaz la cual es utilizada por cada modelo de operación.
	
	Al apoyarse en el contenedor de beans de Spring, el controlador va a inyectar todos los beans que implementen
	la interfaz operación, entonces simplemente llamando al servicio propuesto con el nombre de la operacion correcto,
	tendremos el resultado.
	
	Para añadir una nueva operación a la calculadora, simplemente habría que añadir un modelo nuevo
	que implemente dicha interfaz y ya tendríamos todo lo necesario para poder hacer uso de la 
	nueva operación.
	
	Todo esto es gracias al contenedor de beans que nos ofrece Spring.
	

# Alcance del servicio
	Este microservicio contiene una API en la cual el usuario podrá realizar la suma o la resta
	de dos números.

# Primeros pasos
	Este proyecto contiene un archivo jar que se encuentra dentro de la carpeta "lib" en el directorio raíz.
	Cuando clonamos el proyecto, debemos abrir un terminal y ubicarnos dentro de la carpeta raíz para ejecutar
	el siguiente comando: 
	
	Si utilizas Linux o MacOS: 
	.\mvnw install:install-file -Dfile=./lib/tracer-1.0.0.jar -DgroupId=io.corp.calculator -DartifactId=tracer -Dversion=1.0.0 -Dpackaging=jar
	
	Si utilizas Windows:
	.\mvnw.cmd install:install-file -Dfile=./lib/tracer-1.0.0.jar -DgroupId=io.corp.calculator -DartifactId=tracer -Dversion=1.0.0 -Dpackaging=jar
	
	Este comando se va a encargar de instalar en nuestro repositorio local de maven el jar que contiene la carpeta "lib".
	Si no realizamos este paso, la aplicación no va a compilar porque no encontrará la dependencia del jar.
	
# Compilación
	Para compilar el microservicio, nos colocamos desde el terminal en el directorio raíz del proyecto y ejecutamos:
	Si utilizas Linux o MacOS: 
	.\mvnw clean package
	
	Si utilizas Windows:
	.\mvnw.cmd clean package
	
	Este comando generará el empaquetado ".jar" en la carpeta "target".

# Ejecución
	Abrimos el terminal y nos posicionamos en el directorio raíz del proyecto. 
	y ejecutamos:
					java -jar target/calculator-0.0.1-SNAPSHOT.jar
					
	Este comando se va a encargar de arrancar nuesto microservicio.
	

# Ejemplos de llamadas de la API
	Podemos acceder directamente a la interfaz que nos proporciona Swagger para probar los servicios de la aplicación:
					
						"http://localhost:8080/swagger-ui/index.html"
						
						
	Si queremos probar los servicios en un cliente como Postman o simplemente saber 
	el endpoint para hacer el request desde una aplicación frontal, aquí dejo un par de endpoints de ejemplo:
	
	- Comprobar operaciones disponibles:
	
							GET ->	"http://localhost:8080/calculadora/listado/operaciones"
	
	- Para sumar dos números:
						
							GET -> "http://localhost:8080/api/?operador1=2&operador2=2&operacion=suma"
			
	- Para restar dos números:
			
							GET -> "http://localhost:8080/api/?operador1=2&operador2=2&operacion=resta"