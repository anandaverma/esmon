esmon
=====

ElasticSearch Monitoring and Alert App

Currently tested with Elastic Search v0.90.9. It uses a rest client to query elastic search so most of it will work with any later version given that the JSON response is same.

###dependencies
esmon uses following libraries
* quartz2.2.1
* javamail1.4.7
* log4j1.2.16
* jackson2.2.3

###Configuration
All configuration files are present in _resources folder

####es-batch-monitor.properties
configure the host and port name to point to your ES cluster
####log4j.properties
configure the logging level and log file location
####mail-client.properties
configure the mail settings -  SMTP host, port etc.
I not using password authentication. You can replace the MailClient to suit your requirments
####quartz.properties
configure quartz schedular

>resources folder contains alert folder which further contains alert files (This is very dirty as of now but I am trying to comeup with a better approach)

###Running 
compile and generate the jar file. Put the resources folder in the same directory.  
