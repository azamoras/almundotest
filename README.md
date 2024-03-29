# AlmundoTest

### Consigna

Existe un call center donde hay 3 tipos de empleados: operador, supervisor y director. El proceso de la atención de una llamada telefónica en primera instancia debe ser atendida por un operador, si no hay ninguno libre debe ser atendida por un supervisor, y de no haber tampoco supervisores libres debe ser atendida por un director.

  #### Requerimientos 
  * Debe existir una clase Dispatcher encargada de manejar las llamadas, y debe contener el método dispatchCall para que las asigne a los empleados disponibles.
  * El método dispatchCall puede invocarse por varios hilos al mismo tiempo.
  * La clase Dispatcher debe tener la capacidad de poder procesar 10 llamadas al mismo tiempo (de modo concurrente).
  * Cada llamada puede durar un tiempo aleatorio entre 5 y 10 segundos.
  * Debe tener un test unitario donde lleguen 10 llamadas.
  
  #### Extra/Plus
  
  * Dar alguna solución sobre qué pasa con una llamada cuando no hay ningún empleado libre.
  * Dar alguna solución sobre qué pasa con una llamada cuando entran más de 10 llamadas concurrentes.
  * Agregar los tests unitarios que se crean convenientes.
  * Agregar documentación de código.


### Solución 

##### Dar alguna solución sobre qué pasa con una llamada cuando no hay ningún empleado libre.
##### Dar alguna solución sobre qué pasa con una llamada cuando entran más de 10 llamadas concurrentes.


Se trabajó con un semáforo para dar solución a la problemática. Esto implica que no importa la cantidad de llamadas que entren todas serán atendida a medida que se vayan liberando empleados de las llamadas anteriores.

#### Documentación 

##### JavaDoc

* [JavaDoc](https://github.com/azamoras/almundotest/tree/develop/docs/apidocs)

##### UML 

* [Diagrama de secuencia](https://github.com/azamoras/almundotest/blob/develop/docs/diagrama%20de%20secuencia.png)
* [Diagrama de clases](https://github.com/azamoras/almundotest/blob/develop/docs/Diagrama%20de%20clases.png)


  
