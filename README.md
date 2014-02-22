apache-cxf-example
===========================

Full example of publishing web services(SOAP, REST) using Apache Cxf JAX-WS, JAX-RS and SpringFramework.

It is a translation service, that uses the google, microsoft and systran online translators. 

For instance you can call the following url: 

      http://services.anotes.org/translator/translate/en/es/task 

and get as result:

      {"from":"en","to":"es","googleTranslation":"tarea","microsoftTranslation":"tarea","systranTranslation":"tarea"}

To test after getting the source code execute:

   gradle build idea

And you will have the project configured for Intellij IDE.

Also you can execute in jetty with:

    gradle jettyrun


[More Details](http://www.apprenticeshipnotes.org/2014/02/apache-cxf-jax-ws-jax-rs-example.html)
