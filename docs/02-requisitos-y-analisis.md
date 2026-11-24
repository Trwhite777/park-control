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