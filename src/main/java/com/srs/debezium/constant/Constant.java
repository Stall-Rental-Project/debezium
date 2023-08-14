package com.srs.debezium.constant;

public class Constant {
    //SnapModeConstant
    public static String SnapshotModeAlways = "always";// default
    public static final String SnapshotModeInitial = "initial";
    public static final String SnapshotModeInitialOnly = "initial_only";


    // FIXME: change the default to Fail once we have a better understanding (and of course, the solution for it) of the consequences
    // Handling Mode Constant
    public static final String FailureHandlingModeWarn = "warn"; // default
    public static final String FailureHandlingModeFail = "fail";
    public static final String FailureHandlingModeSkip = "skip";

    //Opearation Constant
    public static final String OperationCreate = "c";
    public static final String OperationUpdate = "u";
    public static final String OperationDelete = "d";
    public static final String OperationTruncate = "t"; // default
    // See https://debezium.io/documentation/reference/stable/connectors/postgresql.html#postgresql-connector-snapshot-mode-options

    //Db Constant
    public static final String DbUser = "db_user";
    public static final String DbPass = "db_pass";
    public static final String DbName = "db_name";
    public static final String DbHost = "db_host";
    public static final String DbPort = "db_port";
    public static final String DbSchema = "db_schema";
}
