# Poc Consumer Driven Contract multi Language 

## Contexto General Arquitecturas microservicio

En la Actualidad a menudo pensamos en microservicios a la hora de hacer nuevas implementaciones o desarrollos. Este tipo de arquitecturas tiene bastantes ventajas a comparacione de aplicaciones monoliticas, pero asi mismo tambien tiene varios problemas que si no sabemos manejarlos podrian ocasionar problemas importantes en la alta disponibilidad de los Microservicios

![alt text](https://raw.githubusercontent.com/jjoc007/poc-consumer-driven-contract-multi-language/master/media/msa.JPG "Arquitecturas Microservicios")

Mayor Informacion: https://youtu.be/VHVPOonGK6s

## El problema: Acuerdo de contratos

![alt text](https://raw.githubusercontent.com/jjoc007/poc-consumer-driven-contract-multi-language/master/media/2th.JPG "th")

Imaginemos que tenemos dos equipos A y B:

A necesita la informacion de los usuarios para la realizacion de pagos en el sistema. Para esto el equipo B debe crear un servicio el cual va a devolver esta informacion.

Entre los dos equipos hablean y llegan a nste acuerdo entre los servicios.

![alt text](https://raw.githubusercontent.com/jjoc007/poc-consumer-driven-contract-multi-language/master/media/3.JPG "3")

El tiempo pasa y esta integracion sigue funcionando bien. Hasta que un dia un desarrollador nuevo llega al equipo B. y piensa que el campo ID de ususario deberia ser un campo tipo int, en vez de un string, convence al equipo de esto, hace el desarrollo y hacen el despliegue. Como no comunicaron el cambio al equipo A. Cuando el servicio engine-service empuieza a hacer transacciones al servicio user-service empiezan a generarse errores 500. porque el tipo de dato de este campo no concuerda con el que habian acordado. Esto genero indisponibilidad y perdidas de mucho dinero en el lapso de tiempo en que se genero el error, se descubrio la causa y se reverso el cambio.

![alt text](https://raw.githubusercontent.com/jjoc007/poc-consumer-driven-contract-multi-language/master/media/4.JPG "4")