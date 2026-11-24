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
* **Datos de ingreso del vehículo:** Número de placa (`String`), marca (`String`) y tipo de vehículo (`1. Carro`, `2. Moto`, `3. Camioneta`, `4. Bicicleta`, `5. Microbús`).
* **Datos de consulta y salida:** Número de placa (`String`) del vehículo a buscar o liquidar.
* **Comandos de navegación:** Selección numérica de las opciones del menú principal (`1` a `7`).

## Salidas 
* **Comprobante de Ingreso (Ticket en consola):** Código único de ticket, placa, tipo de vehículo, marca y fecha/hora exacta de entrada.
* **Recibo de Liquidación de Salida:** Tiempo total de permanencia (en minutos/horas), tarifa aplicada, valor total a pagar y confirmación de cupo liberado.
* **Panel de Disponibilidad:** Capacidad máxima configurada, cantidad de cupos ocupados y cantidad de cupos libres.
* **Listados y Consultas:** Ficha detallada de un vehículo buscado (con costo parcial acumulado) o tabla en consola con todos los vehículos activos.
* **Reporte de Recaudo del Turno:** Total de vehículos que finalizaron servicio y sumatoria total de dinero recaudado.
* **Mensajes de Estado y Error:** Confirmaciones de éxito o alertas descriptivas ante excepciones de negocio (`ParqueaderoException`).

## Reglas del Negocio
* **RN-01 (Unicidad de placa activa):** No puede existir más de un vehículo activo con la misma placa dentro del parqueadero simultáneamente.
* **RN-02 (Límite de capacidad):** El sistema rechaza cualquier nuevo ingreso cuando la cantidad de vehículos activos alcanza la capacidad máxima definida del parqueadero.
* **RN-03 (Cobro mínimo de 1 hora):** Toda estadía igual o inferior a 60 minutos se cobra como **1 hora completa**; a partir de la primera hora, cada fracción adicional de tiempo se redondea hacia arriba a la siguiente hora completa.
* **RN-04 (Tarifas diferenciadas por tipo de vehículo):** El valor por hora depende estrictamente de la categoría del vehículo registrado mediante polimorfismo:

| Vehículo | Tarifa por hora |
| :---.... | :-------------: |
| **Carro** | `$15.000 COP` |
| **Moto** | `$10.000 COP` |
| **Camioneta** | `$20.000 COP` |
| **Bicicleta** | `$600 COP` |
| **Microbús** | `$22.000 COP` |
* **RN-05 (Normalización de placas):** Toda placa ingresada se convierte automáticamente a mayúsculas y sin espacios en blanco antes de ser validada o almacenada.
* **RN-06 (Trazabilidad del historial):** Un vehículo solo libera su cupo una vez que se liquida su salida, pasando su ticket de estado *Activo* a *Finalizado* sin posibilidad de eliminación manual para evitar alteraciones en el cierre de caja.