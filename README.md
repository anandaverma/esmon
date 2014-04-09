esmon
=====

ElasticSearch Monitoring and Alert App

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
