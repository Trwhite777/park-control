**PARKCONTROL**

# Problema
En los parqueaderos tradicionales que operan con registros manuales en papel o planillas básicas, se presentan constantes errores en el cálculo de tarifas por fracción o tipo de vehículo, desconocimiento en tiempo real de los cupos disponibles, falta de trazabilidad ante reclamos por ausencia de comprobantes de ingreso y demoras significativas en el cierre de caja diario.

# Solución Propuesta
 Implementar un sistema de software estructurado en capas (Modelo, Servicio, Presentación) que centralice la operación mediante validaciones estrictas de placas, asignación automática de tickets con marca de tiempo (LocalDateTime), cálculo polimórfico de tarifas y manejo controlado de excepciones personalizadas.

# Roles
*1.Líder Técnico y Arquitecto:* Diseño de arquitectura multicapa y diagramas UML; administración de GitHub, ramas y revisión de Pull Requests.
*2.Desarrollador de Modelo (POO):* Implementación de jerarquía (Vehiculo, Carro, Moto, Bicicleta, Ticket), encapsulamiento y polimorfismo.
*3.Desarrollador de Servicio y Datos:* Lógica de negocio en ParqueaderoService, estructuras de datos (Map, List), validaciones regex y excepciones personalizadas.
*4.Ingeniero QA y Documentación:* Interfaz de usuario (ui), pruebas unitarias con JUnit 5, documento SRS y manual técnico (README.md).

# Objetivo General
Construir un aplicativo de consola en Java (ParkControl) bajo arquitectura multicapa y Programación Orientada a Objetos, que permita automatizar el registro de ingresos y salidas, el control de cupos y la liquidación exacta de tarifas de un parqueadero, garantizando la integridad de los datos mediante validaciones, pruebas unitarias y control de versiones en Git/GitHub.

# Alcance 
*1.Registro de ingreso de vehículos:* Captura de placa, marca y tipo de vehículo (Carro, Moto o Bicicleta), validación del formato de placa, verificación de cupos disponibles y generación de un ticket único con marca de tiempo (LocalDateTime).
*2.Registro de salida y liquidación de tarifas:* Búsqueda del vehículo activo por placa, cálculo automático del tiempo de permanencia, aplicación polimórfica de tarifas diferenciadas por tipo de vehículo (con cobro mínimo de 1 hora) y liberación inmediata del cupo.
*3.Consulta de disponibilidad en tiempo real:* Visualización de la capacidad máxima total, espacios ocupados y cupos libres del parqueadero.
*4.Búsqueda de vehículos estacionados:* Consulta individual por placa para verificar si un vehículo está dentro del recinto, mostrando su hora de ingreso, tiempo transcurrido y valor parcial acumulado.
*5.Listado de vehículos activos:* Despliegue en consola de todos los vehículos que se encuentran actualmente estacionados con el detalle de su ticket.
*6.Generación de reporte de recaudo:* Consolidación del historial de salidas del turno para informar el número total de vehículos atendidos y el dinero total recaudado.
*7.Validación y manejo de excepciones:* Control de errores ante entradas inválidas por teclado, placas mal formateadas, vehículos duplicados o parqueadero lleno mediante excepciones personalizadas (ParqueaderoException).

# Fuera de Alcance 
*Interfaces gráficas o web:* No se desarrollarán ventanas de escritorio (Swing/JavaFX), aplicaciones móviles ni páginas web; toda la interacción se realizará mediante terminal/consola.
*2.Bases de datos externas o en la nube:* No se implementará conexión a motores SQL/NoSQL; los datos se gestionarán en memoria mediante estructuras de datos de Java (HashMap y ArrayList).
*3.Integración con hardware físico:* No incluye lectura automática de placas mediante cámaras, sensores de ocupación por puesto ni control de talanqueras o impresoras térmicas.
*4.Pagos electrónicos y facturación legal:* No se integrarán pasarelas de pago, datáfonos ni generación de facturación electrónica ante la DIAN.
*5.Gestión de mensualidades o convenios:* El sistema opera únicamente bajo modalidad de cobro por tiempo/hora ocasional, sin administración de clientes recurrentes ni tarifas por suscripción mensual.

# Integrantes

***Alba Yirerth Hernández Guerra 0192783 - Ingeniero QA y Documentación y apoyo a los demás.***
***Emmanuel David Blanco Cardenas 0192809 – Desarrollador de Modelo (POO).***
***Diego Alejandro Sánchez Peñaranda 0192877 – Desarrollador de Servicio y Datos.***
***Kener Leandro Becerra Quintero 0192790 - Líder Técnico y Arquitecto.***

# Cronograma 
*1.Análisis y Diseño (23 nov – 25 nov):* Documento SRS, Diagrama UML y repositorio inicializado. Líder Técnico y Ingeniero QA.
*2.Desarrollo del Modelo y Datos (26 nov – 28 nov):* Codificación de la jerarquía POO, implementación de encapsulamiento, herencia y polimorfismo en tarifas y configuración de excepciones personalizadas. Desarrollador de Modelo, Desarrollador de Servicio y Líder Técnico.
*3.Lógica de Negocio y Consola (29 nov – 01 dic):* Sistema funcional ejecutable por terminal en rama de integración. Todos los integrantes.
*4.Pruebas, Documentación y Cierre (02 dic – 04 dic):* Entrega Final (v1.0.0) en main con pruebas en verde. Ingeniero QA.

# Riesgos Principales


Requisitos Funcionales
El sistema ParkControl operará mediante seis requisitos funcionales esenciales. El RF-01 (Registrar ingreso) tiene prioridad alta y consiste en permitir el registro de la entrada de un vehículo capturando su placa, marca y tipo (Carro, Moto o Bicicleta), asignándole automáticamente la fecha y hora actual (LocalDateTime.now()) y generando un ticket único; su criterio de aceptación establece que se cree el objeto Ticket en memoria, se descuente un cupo disponible y se rechace el ingreso con un mensaje controlado si la placa ya está adentro, tiene formato inválido o no hay cupos libres. El RF-02 (Registrar salida y liquidar tarifa), también de prioridad alta, debe permitir registrar la salida de un vehículo mediante su placa, calcular los minutos exactos de permanencia, aplicar la tarifa correspondiente según su tipo (mínimo 1 hora completa) y liberar el espacio ocupado; se aceptará cuando calcule correctamente el valor a pagar (Carro $4.000/h, Moto $2.000/h, Bicicleta $1.000/h), remueva el vehículo de los activos, incremente los cupos disponibles y guarde el ticket finalizado en el historial. El RF-03 (Consultar disponibilidad de cupos) posee prioridad alta y requiere mostrar en la terminal el estado actual de ocupación detallando la capacidad total, los espacios ocupados y los cupos libres, cumpliéndose cuando los valores mostrados coincidan exactamente con la diferencia entre la capacidad máxima y los tickets activos. Por su parte, el RF-04 (Buscar vehículo estacionado) es de prioridad media y permite consultar por placa si un vehículo está dentro del parqueadero mostrando los datos de su ticket, hora de ingreso, tiempo transcurrido y costo parcial acumulado; su criterio de aceptación exige desplegar su información completa y cobro estimado si está activo, o mostrar una alerta si no se encuentra en el recinto. El RF-05 (Listar vehículos activos) cuenta con prioridad media y debe desplegar en consola un listado ordenado de todos los vehículos estacionados actualmente con su identificador de ticket, tipo, placa, marca y hora de ingreso, aceptándose cuando imprima todos los registros activos o informe que el parqueadero está vacío sin generar errores. Finalmente, el RF-06 (Generar reporte de recaudo) tiene prioridad media y busca calcular y mostrar en terminal el consolidado financiero del turno indicando la cantidad total de vehículos atendidos y la sumatoria de dinero recaudado, validándose cuando el monto mostrado corresponda exactamente a la suma del atributo valorPagado de todos los tickets del historial de salidas.

Requisitos No Funcionales
En cuanto a los criterios de calidad y restricciones técnicas, el sistema define tres requisitos no funcionales fundamentales. El RNF-01 (Usabilidad y Claridad de Mensajes) establece que la interfaz por terminal debe presentar un menú numérico intuitivo con mensajes claros, estructurados y comprensibles tanto para confirmar operaciones exitosas como para explicar errores de validación; su criterio de aceptación garantiza que el usuario navegue por todas las opciones sin ambigüedades y que ante cualquier dato inválido (texto en vez de número o placa errónea) reciba un mensaje descriptivo sin que el programa se cierre inesperadamente. El RNF-02 (Organización y Mantenibilidad) exige que el código fuente siga estrictamente una arquitectura multicapa (model, service, exception, ui) y principios de Programación Orientada a Objetos (encapsulamiento, herencia y polimorfismo), aceptándose cuando las clases de model y service no contengan instrucciones de entrada/salida (Scanner o System.out), permitiendo modificar tarifas o agregar nuevos tipos de vehículos sin alterar la lógica principal. Por último, el RNF-03 (Compatibilidad y Rendimiento) determina que el sistema debe funcionar en Java SE 17 o superior utilizando únicamente librerías estándar (java.time, java.util) sin dependencias externas y responder al instante en cada operación; su criterio de aceptación verifica que el proyecto compile y ejecute correctamente desde terminal en entornos con JDK 17+ y resuelva cualquier búsqueda por placa (HashMap) en menos de 1 segundo.

