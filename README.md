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

Esta clase de errores aunque muy simples,pueden generar mucho ruido en el ciclo de vida de desarrollo de las aplicacones. Por lo que es importante encontrar estrategias con las cuales podamos verificar los acuerdos de contratos de una manera automatizada.

Mayor Informacion: https://youtu.be/Mgm8HkG69TU

## Consumer Driven Contract Testing

Tipo de Test que permite verificar la integracion entre dos o mas servicios permitiendo comprobar que la solucion para la evolucion de las APIS es Valida.

CDC divide el test en 2 partes que se pueden ejecutar en momentos distintos:

* Consumidor
* Productor del servicio

![alt text](https://raw.githubusercontent.com/jjoc007/poc-consumer-driven-contract-multi-language/master/media/5.JPG "5")

*Consumidor* : El servicio consumidor define reglas a las cuales debe acoplarse el servicio proveedor para que pueda funcionar de manera correcta. Luego de esto este acuerdo en formato JSON se sube a una locacion comun entre el consumidor y el proveedor.

*Productor del servicio*: El servicio proveedor descarga este acuerdo y empuieza a verificar que las reglas planteadas por el servicio consumidor las cumple, si es asi envia una notificacion a la locacion comun de lo contrario tambien notifica los errores.

Mayor Informacion: https://youtu.be/7z0B396vZXs

## Herramientas para la implementacion de CDC

![alt text](https://raw.githubusercontent.com/jjoc007/poc-consumer-driven-contract-multi-language/master/media/6.JPG "6")

Multiples herramientas y frameworks se han desarrollado para poder solucionar este tipo de problemas los que mas se destacan son:

* Spring Cloud Contract
* Pact

Para el ejemplo implementado sera con pact.

### Pact:

Funcionamiento general de Pact:

![alt text](https://raw.githubusercontent.com/jjoc007/poc-consumer-driven-contract-multi-language/master/media/7.JPG "7")

Mayor Informacion: 
* https://youtu.be/aA4_JPP_4TQ
* https://docs.pact.io/

## Arquitectura a implementar

Para la implementacion de nuestro ejemplo vamos a utilizar la siquiente arquitectura, la cual cuenta con multiples tecnologias e integraciones.

![alt text](https://raw.githubusercontent.com/jjoc007/poc-consumer-driven-contract-multi-language/master/media/8.JPG "8")

Para ver la demo del funcionamiento de esta arquitectura te invo a ver esta serie, donde se muestra paso a paso cada componeente y su interaccion con pact.


https://youtube.com/playlist?list=PL2gu2Qe_CGFmLwRq5kwhBWshFPh2GUB6j


Repositorio ejemplo: https://github.com/jjoc007/poc-consumer-driven-contract-multi-language