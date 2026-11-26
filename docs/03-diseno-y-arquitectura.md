## Diagrama UML
1. *Presentación (PARKCONTROL): parqueaderoApp inicia el programa y ParqueaderoUI maneja el menú por consola y muestra resultados.*
2. *Servicio (parqueaderoService): Es el núcleo lógico; gestiona los ingresos, salidas, cupos y reportes en memoria (Map y List).*
3. *Modelo (MODELO): Contiene la clase abstracta vehiculo con sus 5 subclases y tarifas (carro, moto, camioneta, bicicleta, microbus), además de Ticket, EstadoTicket, Disponibilidad y ReporteRecaudo.*
4. *Excepciones (ParqueaderoException): Agrupa los errores personalizados del negocio (cupo lleno, placa inválida o duplicada, vehículo no encontrado) para evitar que el sistema se caiga.*

![alt text](<Copia de Diagrama UML-2026.drawio (3).png>)

## Arquitectura Multicapa

