# Requisitos

# ¿Qué debe hacer el sistema?
El sistema **ParkControl** debe automatizar el ciclo completo de atención de vehículos en un parqueadero operado desde la terminal: registrar el ingreso validando la placa y asignando un ticket con hora exacta, controlar los cupos disponibles en tiempo real, permitir la búsqueda y listado de vehículos estacionados, calcular el cobro diferenciado por tipo de vehículo al momento de la salida y generar el reporte consolidado del dinero recaudado en el turno.

## Requisitos Funcionales 
| Código | Requisito | Descripción | Prioridad | Criterio de aceptación |
| :----- | :-------- | :---------- | :-------: | :--------------------- |
| **RF-01** | Registrar ingreso | El sistema debe permitir registrar placa, marca, tipo de vehículo (Carro, Moto, Bicicleta) y hora exacta de entrada generando un ticket único. | Alta | El vehículo queda registrado como activo, se descuenta 1 cupo disponible y el sistema rechaza placas duplicadas, inválidas o ingresos sin cupo. |
| **RF-02** | Registrar salida y cobro | El sistema debe calcular el tiempo de permanencia por placa, liquidar la tarifa según el tipo de vehículo (mínimo 1 hora completa) y liberar el cupo. | Alta | Calcula el valor exacto a pagar, elimina el vehículo de los activos y guarda el ticket pagado en el historial de salidas. |
| **RF-03** | Consultar disponibilidad | El sistema debe mostrar en la terminal la capacidad total, los espacios ocupados y los cupos libres en tiempo real. | Alta | Los cupos libres coinciden exactamente con la resta entre la capacidad máxima y los vehículos activos en ese momento. |
| **RF-04** | Buscar vehículo | El sistema debe permitir consultar por placa si un vehículo está adentro, mostrando su ticket, hora de ingreso, tiempo transcurrido y valor parcial. | Media | Muestra todos los datos del vehículo si está estacionado o lanza un aviso controlado si la placa no se encuentra en el recinto. |
| **RF-05** | Listar vehículos activos | El sistema debe desplegar en consola todos los vehículos estacionados actualmente con los datos de su ticket. | Media | Imprime todos los vehículos activos registrados o informa que el parqueadero está vacío sin generar errores. |
| **RF-06** | Generar reporte de recaudo | El sistema debe calcular el total de vehículos atendidos y la sumatoria de dinero recaudado durante el turno. | Media | El monto total mostrado corresponde exactamente a la suma de los pagos registrados en el historial de salidas. |

## Requisitos No Funcionales
| Código | Aspecto | Descripción | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| **RNF-01** | Usabilidad y claridad de mensajes | El sistema debe ofrecer un menú numérico por terminal intuitivo con mensajes claros tanto para confirmaciones como para errores de validación. | El usuario navega sin confusiones y ante datos inválidos recibe un aviso descriptivo sin que el programa se cierre inesperadamente. |
| **RNF-02** | Organización y mantenimiento | El código debe estructurarse en arquitectura multicapa (`model`, `service`, `exception`, `ui`) aplicando principios de Programación Orientada a Objetos. | Las clases de `model` y `service` están separadas de `Scanner` y `System.out`, permitiendo modificar tarifas o agregar vehículos fácilmente. |
| **RNF-03** | Compatibilidad y tiempo de respuesta | El programa debe ejecutarse en Java 17 o superior utilizando únicamente librerías estándar y responder al instante en cada consulta. | Compila y corre en la terminal con JDK 17+ sin dependencias externas y resuelve búsquedas por placa en menos de 1 segundo. |

# Análisis

## Actores
* **Operador del Parqueadero (Usuario Principal):** Persona encargada de operar la consola del sistema **ParkControl** para registrar el ingreso y salida de vehículos, consultar cupos disponibles, buscar vehículos activos y generar el cierre de recaudo del turno.
* **Sistema ParkControl (Actor Interno Automatizado):** Componente lógico encargado de validar placas, controlar la capacidad máxima, generar identificadores únicos de tickets, capturar marcas de tiempo (`LocalDateTime`) y liquidar tarifas automáticamente.
* **Conductor** es un actor externo pasivo, ya que no interactúa directamente con la terminal, sino a través del operador.

## Entradas
* **Opción del menú:** Número entero entre `1` y `7` para navegar por las funcionalidades del sistema.
* **Datos de ingreso (RF-01):**
  * Número de placa (`String`).
  * Marca del vehículo (`String`).
  * Categoría del vehículo (`1. Carro`, `2. Moto`, `3. Camioneta`, `4. Bicicleta`, `5. Microbús`).
* **Datos de salida y búsqueda (RF-02 y RF-04):** Número de placa (`String`) del vehículo estacionado.

## Proceso
1. **Registro de Ingreso:** Normaliza la placa, verifica la disponibilidad de cupos y que el vehículo no esté adentro, instancia la subclase correspondiente (`Carro`, `Moto`, `Camioneta`, `Bicicleta` o `Microbús`), crea el `Ticket` con la hora actual del sistema y lo almacena en el mapa de vehículos activos.
2. **Liquidación y Salida:** Localiza el ticket activo mediante la placa, calcula los minutos exactos de permanencia hasta la hora de salida, invoca el método polimórfico `calcularTarifa()` del vehículo, libera el cupo eliminándolo de activos y traslada el ticket finalizado al historial de pagos.
3. **Control de Disponibilidad y Consultas:** Calcula en tiempo real la resta entre la capacidad máxima y los registros activos, filtra un vehículo específico por su placa o recorre la colección completa para listar los estacionados.
4. **Cierre y Reporte de Recaudo:** Recorre el historial de tickets finalizados para contabilizar el total de vehículos atendidos y sumar el dinero recaudado durante el turno.

## Salidas 
* **Ticket de ingreso:** Comprobante impreso en consola con ID único del ticket, placa, tipo de vehículo, marca y fecha/hora exacta de entrada.
* **Recibo de pago:** Detalle de liquidación con tiempo total estacionado, tarifa aplicada por hora, valor total cobrado y confirmación de cupo liberado.
* **Reporte de cupos:** Indicadores de capacidad máxima, espacios ocupados y cupos libres disponibles.
* **Ficha o listado de vehículos:** Información detallada de un vehículo consultado (incluyendo cobro parcial acumulado) o tabla en consola con todos los vehículos activos.
* **Consolidado financiero:** Total de vehículos atendidos y suma total recaudada en pesos (`COP`).
* **Mensajes de retroalimentación:** Confirmaciones claras de éxito o alertas explicativas ante fallos de validación.

## Validaciones
* **V-01 (Formato y normalización de placa):** La placa no puede estar vacía o nula; se convierte automáticamente a mayúsculas, se eliminan espacios y se verifica su longitud/formato válido (o identificador asignado para bicicletas).
* **V-02 (No duplicidad de vehículo):** Antes de registrar un ingreso, se valida que la placa no exista actualmente en la estructura de vehículos activos.
* **V-03 (Disponibilidad de espacio):** Se comprueba que `vehiculosActivos.size() < capacidadMaxima` antes de autorizar cualquier entrada.
* **V-04 (Existencia del vehículo en salida/búsqueda):** Se verifica que la placa consultada corresponda a un vehículo que realmente se encuentre estacionado dentro del recinto.
* **V-05 (Validación de entradas por consola):** Se controla que los datos numéricos ingresados en el menú o selección de vehículo sean enteros válidos dentro del rango permitido.

## Reglas del Negocio
* **RN-01 (Tarifas diferenciadas por categoría):** El cobro por hora se aplica según el tipo de vehículo registrado:

| Vehículo | Tarifa por hora |
| :--- | :---: |
| **Carro** | `$15.000 COP` |
| **Moto** | `$10.000 COP` |
| **Camioneta** | `$20.000 COP` |
| **Bicicleta** | `$600 COP` |
| **Microbús** | `$22.000 COP` |

* **RN-02 (Cobro mínimo de 1 hora y redondeo superior):** Toda estadía entre `1` y `60` minutos se cobra como **1 hora completa**. Superada la primera hora, cualquier fracción de minuto adicional se redondea hacia arriba a la siguiente hora completa.
* **RN-03 (Inmutabilidad del historial):** Una vez liquidada una salida, el ticket pasa a estado *Finalizado* y no puede ser modificado ni eliminado manualmente, garantizando la integridad del reporte de recaudo.
* **RN-04 (Liberación condicionada de cupo):** Un espacio del parqueadero únicamente vuelve a estar disponible tras completar exitosamente el proceso de salida y cobro.

## Posibles Eroores
* **`ParqueaderoLlenoException`:** Se lanza cuando el operador intenta registrar un ingreso y los cupos disponibles son iguales a `0`.
* **`VehiculoYaRegistradoException`:** Ocurre al intentar ingresar una placa que ya figura con un ticket activo dentro del parqueadero.
* **`VehiculoNoEncontradoException`:** Se produce al intentar registrar la salida o consultar los datos de una placa que no está estacionada.
* **`PlacaInvalidaException` / `DatoInvalidoException`:** Se genera cuando se ingresa una placa vacía, con caracteres no permitidos o una categoría de vehículo inexistente.
* **`InputMismatchException` / `NumberFormatException`:** Error técnico de captura por teclado cuando el usuario digita letras o símbolos en una opción numérica del menú, el cual es atrapado con `try-catch` sin detener la ejecución del programa.

## Historias de usuario o casos de uso textuales

### HU-01: Registrar ingreso de vehículo (RF-01)
**Como** operador del parqueadero,  
**quiero** registrar la placa, marca y categoría del vehículo (`Carro`, `Moto`, `Camioneta`, `Bicicleta` o `Microbús`) junto con su hora automática de entrada,  
**para** generar un ticket único de control y descontar el cupo disponible sin permitir placas duplicadas ni sobrecupo.

### HU-02: Registrar salida y liquidar tarifa (RF-02)
**Como** operador del parqueadero,  
**quiero** ingresar la placa de un vehículo al momento de retirarse para que el sistema calcule el tiempo exacto de estadía y aplique la tarifa correspondiente según su categoría,  
**para** realizar un cobro exacto sin errores manuales, liberar el espacio y guardar el pago en el historial.

### HU-03: Consultar disponibilidad de cupos (RF-03)
**Como** operador del parqueadero,  
**quiero** visualizar en tiempo real la capacidad total, los espacios ocupados y los cupos libres del recinto,  
**para** saber de inmediato si puedo autorizar el ingreso de nuevos vehículos sin generar congestión.

### HU-04: Buscar vehículo estacionado (RF-04)
**Como** operador del parqueadero,  
**quiero** consultar por número de placa si un vehículo específico se encuentra dentro de las instalaciones y ver su hora de ingreso y costo parcial acumulado,  
**para** responder rápidamente a consultas de conductores o verificar el estado de un vehículo en cualquier momento.

### HU-05: Listar vehículos activos (RF-05)
**Como** operador del parqueadero,  
**quiero** desplegar en consola un listado ordenado de todos los vehículos que permanecen estacionados actualmente con los datos de su ticket,  
**para** llevar un control visual del inventario de vehículos presentes durante mi turno.

### HU-06: Generar reporte de recaudo (RF-06)
**Como** operador del parqueadero,  
**quiero** consultar el consolidado financiero con el total de vehículos atendidos y la sumatoria de dinero recaudado,  
**para** realizar un cierre de caja rápido, transparente y exacto al finalizar la jornada.