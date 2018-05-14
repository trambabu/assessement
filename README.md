# assessement

These are two modules 1)jerseytest 2)webapptest
Jerseytest : This module is a Restful application. Restful service has been developed using jersey library 2.27 version.
Webapptest : This module is a webapplication invoking as a client of Restful service using spring resttemplate library.

Jerseytest application Environment:
Java 1.8_144,tomcat 8.5.40,jersey 2.2.7 library, H2b 1.4.197 as a memory based database, Hibernate 5.2.6.Final for persistance, JPA 2.1. optional mysql 6.0.5 driver also included and the configurations are commented out for mysal in persistence.xml

Webapptest application Environment:
Java 1.8_144,tomcat 8.5.40, spring 4.2.4.RELEASE,httpclient 4.5.5,jstl 1.2 are main supporting libraries.
There was a app authentication used for retrive allUsers functionality from the DB as a demo purpose how to use from the headers.
few JSP pages also used in the application to create,display,update,delete,retrieve usermanagement functionality. These JSP files has been used JSTL scripting including HTML.

All these above applications are just for a assessement or flow of functionalities using in memory database, mysql database for usermanagement using Restful webservice/webappclient.








