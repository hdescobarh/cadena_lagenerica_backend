# Proyecto Cadena La Genérica - Programa Misión TIC 2022

## Tecnologías empleadas

![Java](https://img.shields.io/badge/-Java-f89820?logo=java)
![Spring-boot](https://img.shields.io/badge/-Spring%20Boot-white?logo=spring-boot)
![Postman](https://img.shields.io/badge/-Postman-00B2D1?logo=postman)

## Descripción

Este repositorio contiene la REST API desarrollada como parte del proyecto final para el ciclo de Desarrollo Web del programa [Misión TIC 2022](https://www.misiontic2022.gov.co/portal/). La parte del frontend se encuentra en el siguiente [enlace](https://github.com/hdescobarh/cadena_lagenerica_frontend)


## Seguridad

### Login de usuarios

Esta implementado con [Spring Security](https://spring.io/projects/spring-security) (autenticación y autorización) en combinación con [JWT.IO](https://jwt.io/). Despues de un login exitoso se retorna un Bearer token que debe aparecer en el header para validar cualquier otro tipo de petición.


### CORS

Está configurado para aceptar peticiones de 127&period;0&period;0&period;1&colon;5500. Para modificarlo cambiar el parametro  *allowedOrigins*  en el *corsConfigurer()* de la clase *BackCadenaLagenericaApplication*.


## Archivos adicionales

- Script SQL para crear el esquema de [base de datos](./make_base_database.sql) e ingresar el usuario inicial. *username* = admininicial, *password* = admin123456.

- Script SQL para ingresar [datos de prueba](./insert_testing_data.sql)

- JSON de Postman para realizar [pruebas a la API ](./tienda_test_API.postman_collection.json)