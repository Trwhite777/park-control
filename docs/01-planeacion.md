# PARKCONTROL

## Problema
En los parqueaderos tradicionales que operan con registros manuales en papel o planillas básicas, se presentan constantes errores en el cálculo de tarifas por fracción o tipo de vehículo, desconocimiento en tiempo real de los cupos disponibles, falta de trazabilidad ante reclamos por ausencia de comprobantes de ingreso y demoras significativas en el cierre de caja diario.

## Solución Propuesta
 Implementar un sistema de software estructurado en capas (Modelo, Servicio, Presentación) que centralice la operación mediante validaciones estrictas de placas, asignación automática de tickets con marca de tiempo (LocalDateTime), cálculo polimórfico de tarifas y manejo controlado de excepciones personalizadas.

## Roles
1. *Líder Técnico y Arquitecto:* Diseño de arquitectura multicapa y diagramas UML; administración de GitHub, ramas y revisión de Pull Requests.
2. *Desarrollador de Modelo (POO):* Implementación de jerarquía (Vehiculo, Carro, Moto, Bicicleta, Ticket), encapsulamiento y polimorfismo.
3. *Desarrollador de Servicio y Datos:* Lógica de negocio en ParqueaderoService, estructuras de datos (Map, List), validaciones regex y excepciones personalizadas.
4. *Ingeniero QA y Documentación:* Interfaz de usuario (ui), pruebas unitarias con JUnit 5, documento SRS y manual técnico (README.md).

## Objetivo General
Construir un aplicativo de consola en Java (ParkControl) bajo arquitectura multicapa y Programación Orientada a Objetos, que permita automatizar el registro de ingresos y salidas, el control de cupos y la liquidación exacta de tarifas de un parqueadero, garantizando la integridad de los datos mediante validaciones, pruebas unitarias y control de versiones en Git/GitHub.

## Alcance 
1. *Registro de ingreso de vehículos:* Captura de placa, marca y tipo de vehículo (Carro, Moto o Bicicleta), validación del formato de placa, verificación de cupos disponibles y generación de un ticket único con marca de tiempo (LocalDateTime).
2. *Registro de salida y liquidación de tarifas:* Búsqueda del vehículo activo por placa, cálculo automático del tiempo de permanencia, aplicación polimórfica de tarifas diferenciadas por tipo de vehículo (con cobro mínimo de 1 hora) y liberación inmediata del cupo.
3. *Consulta de disponibilidad en tiempo real:* Visualización de la capacidad máxima total, espacios ocupados y cupos libres del parqueadero.
4. *Búsqueda de vehículos estacionados:* Consulta individual por placa para verificar si un vehículo está dentro del recinto, mostrando su hora de ingreso, tiempo transcurrido y valor parcial acumulado.
5. *Listado de vehículos activos:* Despliegue en consola de todos los vehículos que se encuentran actualmente estacionados con el detalle de su ticket.
6. *Generación de reporte de recaudo:* Consolidación del historial de salidas del turno para informar el número total de vehículos atendidos y el dinero total recaudado.
7. *Validación y manejo de excepciones:* Control de errores ante entradas inválidas por teclado, placas mal formateadas, vehículos duplicados o parqueadero lleno mediante excepciones personalizadas (ParqueaderoException).

## Fuera de Alcance 
1. *Interfaces gráficas o web:* No se desarrollarán ventanas de escritorio (Swing/JavaFX), aplicaciones móviles ni páginas web; toda la interacción se realizará mediante terminal/consola.
2. *Bases de datos externas o en la nube:* No se implementará conexión a motores SQL/NoSQL; los datos se gestionarán en memoria mediante estructuras de datos de Java (HashMap y ArrayList).
3. *Integración con hardware físico:* No incluye lectura automática de placas mediante cámaras, sensores de ocupación por puesto ni control de talanqueras o impresoras térmicas.
4. *Pagos electrónicos y facturación legal:* No se integrarán pasarelas de pago, datáfonos ni generación de facturación electrónica ante la DIAN.
5. *Gestión de mensualidades o convenios:* El sistema opera únicamente bajo modalidad de cobro por tiempo/hora ocasional, sin administración de clientes recurrentes ni tarifas por suscripción mensual.

## Integrantes

1. ***Alba Yirerth Hernández Guerra 0192783 - Ingeniero QA y Documentación y apoyo a los demás.***
2. ***Emmanuel David Blanco Cardenas 0192809 – Desarrollador de Modelo (POO).***
3. ***Diego Alejandro Sánchez Peñaranda 0192877 – Desarrollador de Servicio y Datos.***
4. ***Kener Leandro Becerra Quintero 0192790 - Líder Técnico y Arquitecto.***

## Cronograma 
1. *.Análisis y Diseño (23 nov – 25 nov):* Documento SRS, Diagrama UML y repositorio inicializado. Líder Técnico y Ingeniero QA.
2. *Desarrollo del Modelo y Datos (26 nov – 28 nov):* Codificación de la jerarquía POO, implementación de encapsulamiento, herencia y polimorfismo en tarifas y configuración de excepciones personalizadas. Desarrollador de Modelo, Desarrollador de Servicio y Líder Técnico.
3. *Lógica de Negocio y Consola (29 nov – 01 dic):* Sistema funcional ejecutable por terminal en rama de integración. Todos los integrantes.
4. *Pruebas, Documentación y Cierre (02 dic – 04 dic):* Entrega Final (v1.0.0) en main con pruebas en verde. Ingeniero QA.

## Riesgos Principales
| Riesgo | Probabilidad | Impacto | Acción preventiva |
| -------| -------------| -------- | ---------------- |
| Integrar el código únicamente al final | Alta | Alto | Integrar y probar avances durante el desarrollo mediante ramas por funcionalidad y Pull Requests diarios |
| Plazo ajustado de ejecución (11 días al 4 de diciembre) | Alta | Alto | Dividir el trabajo en entregables cortos de 2 a 3 días y congelar el alcance en las 6 funciones esenciales |
| Errores en cálculo de tarifas o entradas por consola | Media | Alto | Validar lecturas de Scanner con try-catch y cubrir casos límite de tiempo con pruebas unitarias en JUnit |
| Cruce de horarios o retrasos de algún integrante | Media | Alto | Definir desde el inicio las firmas exactas de clases y métodos para programar cada capa en paralelo sin bloqueos |

## Requisitos Funcionales
| Código | Requisito | Descripción | Prioridad | Criterio de aceptación |
| ------ | --------- | ----------- | --------- | ---------------------- |
| RF-01 | Registrar ingreso | El sistema debe permitir registrar placa, marca, tipo de vehículo (Carro, Moto, Bicicleta) y hora exacta de entrada generando un ticket | Alta | El vehículo queda registrado como activo, se descuenta 1 cupo y no acepta placas repetidas ni inválidas |
| RF-02 | Registrar salida y cobro | El sistema debe calcular el tiempo de permanencia por placa, liquidar la tarifa según el tipo de vehículo (mínimo 1 hora) y liberar el cupo | Alta | Calcula el valor exacto, elimina el vehículo de activos y guarda el ticket pagado en el historial |
| RF-03 | Consultar disponibilidad | El sistema debe mostrar en la terminal la capacidad total, los espacios ocupados y los cupos libres en tiempo real | Alta | Los cupos libres coinciden exactamente con la resta entre la capacidad máxima y los vehículos activos |
| RF-04 | Buscar vehículo | El sistema debe permitir consultar por placa si un vehículo está adentro, mostrando su ticket, hora de ingreso y valor parcial | Media | Muestra los datos del vehículo si está estacionado o lanza un aviso claro si la placa no se encuentra |
| RF-05 | Listar vehículos activos | El sistema debe desplegar en consola todos los vehículos estacionados actualmente con los datos de su ticket | Media | Imprime todos los vehículos activos o informa que el parqueadero está vacío sin generar errores |
| RF-06 | Generar reporte de recaudo | El sistema debe calcular el total de vehículos atendidos y la sumatoria de dinero recaudado durante el turno | Media | El monto total corresponde exactamente a la suma de los pagos registrados en el historial de salidas |

## Requisitos No Funcionales
| Código | Aspecto | Descripción | Criterio de aceptación |
| ------ | ------- | ----------- | ---------------------- |
| RNF-01 | Usabilidad y claridad de mensajes | El sistema debe ofrecer un menú por terminal intuitivo con mensajes claros para confirmaciones y errores de validación | El usuario navega sin confusiones y ante datos inválidos recibe un aviso descriptivo sin que el programa se cierre |
| RNF-02 | Organización del código | El código debe estructurarse en arquitectura multicapa (model, service, exception, ui) aplicando POO | Las clases de model y service están separadas de Scanner y System.out, facilitando el mantenimiento |
| RNF-03 | Compatibilidad y tiempo de respuesta | El programa debe ejecutarse en Java 17 o superior sin librerías externas y responder al instante en cada consulta | Compila y corre en la terminal con JDK 17+ y resuelve búsquedas por placa en menos de 1 segundo |

