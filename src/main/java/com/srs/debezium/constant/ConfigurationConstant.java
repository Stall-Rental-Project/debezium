package com.srs.debezium.constant;

import static com.srs.debezium.constant.Constant.*;

public class ConfigurationConstant {
    // See https://debezium.io/documentation/reference/stable/connectors/postgresql.html

    //Default Value Connector Configuration
    public static final String defaultConnectorClass = "io.debezium.connector.postgresql.PostgresConnector";
    public static final String defaultPluginName = "pgoutput"; //The pgoutput plugin is another logical decoding plugin for PostgreSQL that outputs changes in a structured and human-readable format
    public static final String defaultSlotName = "debezium"; //specify the name of the replication slot that Debezium will use to capture and stream changes from the PostgreSQL database
    public static final boolean defaultSlotDropOnStop = false;//whether the replication slot should be automatically dropped (deleted) when the Debezium connector is stopped
    public static final String defaultPublicationName = "dbz_publication"; //When configuring a Debezium connector for PostgreSQL with the pgoutput plugin, you use the publication.name setting to specify the name of the PostgreSQL publication to which you want the connector to subscribe. This tells the connector which set of tables and changes it should capture and stream.
    public static final boolean defaultSkipMessagesWithoutChange = false; //whether to skip publishing messages when there is no change in included columns. Only works when REPLICA IDENTITY of the table is set to FULL
    public static final String defaultPublicationAutoCreateMode = "filtered"; //Applies only when streaming changes by using the pgoutput plug-in. The setting determines how creation of a publication should work
    public static final String defaultSnapshotMode = SnapshotModeAlways; //Specifies the criteria for performing a snapshot when the connector starts:
    public static final String defaultFailureHandlingMode = FailureHandlingModeWarn; //Specifies how the connector should react to exceptions during processing of events:
    public static final int defaultHeartbeatIntervalMs = 5000; //Frequency for sending replication connection status updates to the server, given in milliseconds/ The property also controls how frequently the database status is checked to detect a dead connection in case the database was shut down.
    public static final boolean defaultFlushLsnSource = true; //Determines whether the connector should commit the LSN of the processed records in the source postgres database so that the WAL logs can be deleted
    public static final String defaultSkippedOperations = OperationTruncate; //A comma-separated list of operation types that will be skipped during streaming
    public static final String defaultTopicNamingStrategy = "io.debezium.schema.SchemaTopicNamingStrategy";//used to determine the topic name for data change, schema change, transaction, heartbeat event etc.,
    //you have tables public.customers and public.orders in the mydb database, the SchemaTopicNamingStrategy will generate topic names based on the schema and table names. The generated topics would look like this:
//my-db-server.public.customers
    public static final String defaultTopicDelimiter = "."; //Specify the delimiter for topic name
    public static final String defaultTopicHeartbeatPrefix = "debezium-heartbeat"; //Controls the name of the topic to which the connector sends heartbeat messages
    public static final String defaultTopicTransactionPrefix = "debezium-transaction"; //Controls the name of the topic to which the connector sends transaction metadata messages
    public static final boolean defaultKeyConverterSchemasEnable = false;
    public static final boolean defaultValueConverterSchemasEnable = false;
    public static final boolean defaultIncludeSchemaChanges = false; //whether schema change events should be included in the captured change events. Set to true to include schema change events, or false to exclude them.
    public static final String defaultKeyConverter = "org.apache.kafka.connect.json.JsonConverter"; //specify the converter classes that are used to serialize (encode) data when sending messages to Kafka and to deserialize (decode) data when consuming messages from Kafka.
    public static final String defaultValueConverter = "org.apache.kafka.connect.json.JsonConverter";
}
