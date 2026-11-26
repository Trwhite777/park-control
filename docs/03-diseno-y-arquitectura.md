## Diagrama UML
1. *Presentación (PARKCONTROL): parqueaderoApp inicia el programa y ParqueaderoUI maneja el menú por consola y muestra resultados.*
2. *Servicio (parqueaderoService): Es el núcleo lógico; gestiona los ingresos, salidas, cupos y reportes en memoria (Map y List).*
3. *Modelo (MODELO): Contiene la clase abstracta vehiculo con sus 5 subclases y tarifas (carro, moto, camioneta, bicicleta, microbus), además de Ticket, EstadoTicket, Disponibilidad y ReporteRecaudo.*
4. *Excepciones (ParqueaderoException): Agrupa los errores personalizados del negocio (cupo lleno, placa inválida o duplicada, vehículo no encontrado) para evitar que el sistema se caiga.*

![alt text](<Copia de Diagrama UML-2026.drawio (3).png>)

## Arquitectura Multicapa

```text
park-control/
├── docs/
│   ├── evidencias/
│   ├── uml/
|   |   ├──Copia de Diagrama UML-2026.drawio (1).png
|   |   └──diagrama-clase-editable
|   |
│   ├── 01-planeacion.md
│   ├── 02-requisitos-y-analisis.md
│   ├── 03-diseno-y-arquitectura.md
│   ├── 04-pruebas.md
│   └── 05-mantenimiento.md
├── src/
│   ├── app/
│   │   └── ParqueaderoApp.java
│   ├── model/
│   │   ├── Vehiculo.java
│   │   ├── Bicicleta.java
│   │   ├── Camioneta.java
│   │   ├── Carro.java
│   │   ├── Microbus.java
│   │   ├── Moto.java
│   │   ├── Ticket.java
│   │   ├── EstadoTicket.java
│   │   ├── Disponibilidad.java
│   │   └── ReporteRecaudo.java
│   ├── repository/
│   │   └── ParqueaderoRepository.java
│   ├── service/
│   │   └── ParqueaderoService.java
│   ├── view/
│   │   └── ParqueaderoUI.java
│   └── exception/
│       ├── ParqueaderoException.java
│       ├── ParqueaderoLlenoException.java
│       ├── VehiculoYaRegistradoException.java
│       ├── VehiculoNoEncontradoException.java
│       └── PlacaInvalidaException.java
├── .gitignore
└── README.md
```

