package com.srs.debezium.constant;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import static com.srs.debezium.constant.ConfigurationConstant.*;
import static com.srs.debezium.constant.Constant.SnapshotModeAlways;

public class DebeziumConnector {
    @JsonProperty("name")
    private String name;

    @JsonProperty("connector.class")
    private String connectorClass;

    @JsonProperty("plugin.name")
    private String pluginName;

    @JsonProperty("slot.name")
    private String slotName;

    @JsonProperty("slot.drop.on.stop")
    private boolean slotDropOnStop;

    @JsonProperty("publication.name")
    private String publicationName;

    @JsonProperty("database.hostname")
    private String databaseHostname;

    @JsonProperty("database.port")
    private int databasePort;

    @JsonProperty("database.user")
    private String databaseUser;

    @JsonProperty("database.password")
    private String databasePassword;

    @JsonProperty("database.dbname")
    private String databaseDbName;

    @JsonProperty("topic.prefix")
    private String topicPrefix;

    @JsonProperty("schema.include.list")
    private String schemaIncludeList;

    @JsonProperty("table.include.list")
    private String tableIncludeList;

    @JsonProperty("column.include.list")
    private String columnIncludeList;

    @JsonProperty("publication.autocreate.mode")
    private String publicationAutoCreateMode;

    @JsonProperty("skip.messages.without.change")
    private boolean skipMessagesWithoutChange;

    @JsonProperty("snapshot.mode")
    private String snapshotMode;

    @JsonProperty("event.processing.failure.handling.mode")
    private String eventProcessingFailureHandlingMode;

    @JsonProperty("heartbeat.interval.ms")
    private int heartbeatIntervalMs;

    @JsonProperty("heartbeat.action.query")
    private String heartbeatActionQuery;

    @JsonProperty("topic.naming.strategy")
    private String topicNamingStrategy;

    @JsonProperty("topic.delimiter")
    private String topicDelimiter;

    @JsonProperty("flush.lsn.source")
    private boolean flushLsnSource;

    @JsonProperty("skipped.operations")
    private String skippedOperations;

    @JsonProperty("topic.heartbeat.prefix")
    private String topicHeartbeatPrefix;

    @JsonProperty("topic.transaction")
    private String topicTransactionPrefix;

    @JsonProperty("key.converter.schemas.enable")
    private boolean keyConverterSchemasEnable;

    @JsonProperty("value.converter.schemas.enable")
    private boolean valueConverterSchemasEnable;

    @JsonProperty("include.schema.changes")
    private boolean includeSchemaChanges;

    @JsonProperty("key.converter")
    private String keyConverter;

    @JsonProperty("value.converter")
    private String valueConverter;


    public DebeziumConnector(String name, DatabaseConfig cfg) {
        this.name = name;
        this.topicPrefix = name;
        this.databaseHostname= cfg.getHost();
        this.databasePort = cfg.getPort();
        this.databaseUser = cfg.getUserName();
        this.databasePassword = cfg.getPassword();
        this.databaseDbName = cfg.getName();
        this.schemaIncludeList = cfg.getSchemaIncludeList();
        this.tableIncludeList = cfg.getTableIncludeList();
        this.columnIncludeList = cfg.getColumnExcludeList();
        this.connectorClass = defaultConnectorClass;
        this.pluginName = defaultPluginName;
        this.slotName = defaultSlotName+"_"+name;
        this.slotDropOnStop = defaultSlotDropOnStop;
        this.publicationName = defaultPublicationName;
        this.skipMessagesWithoutChange= defaultSkipMessagesWithoutChange;
        this.publicationAutoCreateMode = defaultPublicationAutoCreateMode;
        this.snapshotMode = SnapshotModeAlways;
        this.eventProcessingFailureHandlingMode = defaultFailureHandlingMode;
        this.heartbeatIntervalMs = defaultHeartbeatIntervalMs;
        this.heartbeatActionQuery = getHeartbeatActionQuery(name);
        this.topicNamingStrategy = defaultTopicNamingStrategy;
        this.topicDelimiter = defaultTopicDelimiter;
        this.flushLsnSource = defaultFlushLsnSource;
        this.skippedOperations = defaultSkippedOperations;
        this.topicHeartbeatPrefix = defaultTopicHeartbeatPrefix;
        this.topicTransactionPrefix = defaultTopicTransactionPrefix;
        this.keyConverterSchemasEnable = defaultKeyConverterSchemasEnable;
        this.valueConverterSchemasEnable = defaultValueConverterSchemasEnable;
        this.includeSchemaChanges = defaultIncludeSchemaChanges;
        this.keyConverter = defaultKeyConverter;
        this.valueConverter = defaultValueConverter;
    }

    public String getHeartbeatActionQuery(String connectorName){
        return String.format("INSERT INTO cdc_heartbeat (connector, created_date) VALUES (%s, now()) ON CONFLICT (connector) DO UPDATE SET created_date = now();", connectorName);
    }

    public String getName() {
        return name;
    }

}

