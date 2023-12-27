# Getting Started
###  Overview
* This project focuses on configuring and registering Debezium connectors to capture and stream changes from multiple specific databases. Debezium is an open-source platform for change data capture (CDC), allowing real-time monitoring and processing of database changes.
### Prepare 
You can use **debezium.yaml** docker Compose configuration file for setting up a multi-container environment for running Apache Kafka and Debezium, ZooKeeper and Kafka UI for monitoring Kafka and Debezium
* Start those containers using following command: `docker-compose up -f debezium.yaml -d`
* Make sure all containers are running on your machine (version 2.4.0 Final)

### Configure Connectors
* Modify configuration files in the project to specify target databases, connector types, and settings.


![image](https://github.com/Stall-Rental-Project/debezium/assets/71142769/d4d5ff17-1ff6-40a1-b8cb-2b968826d354)
