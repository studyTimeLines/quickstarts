Introduction
============
This quickstart demonstrates the usage of the JAXB transformer.   No special configuration is 
included here for the transformer configuration - because a CDI Bean Service is used here and 
JAXB types are used in the Service interface, the transformation is automatic. 

![Transform JAXB Quickstart](https://github.com/jboss-switchyard/quickstarts/raw/master/transform-jaxb/transform-jaxb.jpg)


Preqrequisites 
==============
Maven

Running the quickstart
======================

JBoss AS 7
----------
1. Build the quickstart:

        mvn clean install

2. Start JBoss AS 7 in standalone mode:

        ${AS}/bin/standalone.sh

3. Deploy the Quickstart :

        mvn jboss-as:deploy

4. Submit a request with your preferred SOAP client - src/test/resources/xml contains sample 
   requests and the responses that you should see
   Alternatively use the simple bundled SOAP client and the sample request XML e.g.

        mvn exec:java


Expected Output:
================
```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:orderAck xmlns:ns2="urn:switchyard-quickstart:transform-jaxb:1.0">
         <orderId>PO-19838-XYZ</orderId>
         <accepted>true</accepted>
         <status>Order Accepted</status>
      </ns2:orderAck>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


## Further Reading

1. [Transformation Documentation](https://docs.jboss.org/author/display/SWITCHYARD/Transformation)
