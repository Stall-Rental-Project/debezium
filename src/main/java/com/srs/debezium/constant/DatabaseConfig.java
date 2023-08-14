package com.srs.debezium.constant;

import com.srs.debezium.config.PropertyReader;
import org.springframework.stereotype.Component;

public class DatabaseConfig {
    public static final String ACCOUNT = "account";
    public static final String RENTAL = "rental";
    public static final String MARKET = "market";
    public static final String RATE = "rate";
    public static final String LOGGING = "logging";

    public static final String AccountDB = "emarketaccount";
    public static final String RentalDB = "emarketrental";
    public static final String LoggingDB = "emarketlogging";
    public static final String MarketDB = "emarketmarket";
    public static final String RateDB = "emarketrate";


    private String host;

    private int port;

    private String userName;

    private String password;

    private String name;

    private String schemaIncludeList;

    private String tableIncludeList;

    private String columnExcludeList;

    public DatabaseConfig(String name, String schemaIncludeList, String tableIncludeList, String columnExcludeList) {
        this.name = name;
        this.port = Integer.parseInt(PropertyReader.getProperty("port"));
        this.host = PropertyReader.getProperty("host");
        this.userName = PropertyReader.getProperty("username");
        this.password = PropertyReader.getProperty("password");
        this.schemaIncludeList = schemaIncludeList;
        this.tableIncludeList = tableIncludeList;
        this.columnExcludeList = columnExcludeList;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSchemaIncludeList() {
        return schemaIncludeList;
    }

    public String getTableIncludeList() {
        return tableIncludeList;
    }

    public String getColumnExcludeList() {
        return columnExcludeList;
    }

    public static DatabaseConfig getDatabaseConfig(String name) {
        switch (name) {
            case ACCOUNT:
                return buildDatabaseConfig(AccountDB, "emarket",
                        new String[]{
                                "emarket.notification_view",
                        },
                        new String[]{
                                "emarket.notification_view.viewed_notification_ids",
                                "emarket.notification_view.deleted_notification_ids",
                                "emarket.notification_view.seen_notification_ids",

                        });
            case RENTAL:
                return buildDatabaseConfig(RentalDB, "emarket",
                        new String[]{
                                "emarket.application",
                                "emarket.hawker_application",
                                "emarket.users",
                                "emarket.hawker_user",
                                "emarket.soa",
                                "emarket.hawker_vending_fee",
                                "emarket.application_progress",
                        },
                        new String[]{

                        });
            case MARKET:
                return buildDatabaseConfig(MarketDB, "emarket",
                        new String[]{
                                "emarket.market",
                                "emarket.stall",
                                "emarket.floorplan",
                                "emarket.floor_stall_index",
                                "emarket.vending_shift",
                                "emarket.section",
                        },
                        new String[]{

                        });
            case LOGGING:
                return buildDatabaseConfig(LoggingDB, "emarket",
                        new String[]{
                                "emarket.changelog",
                        },
                        new String[]{

                        });
            case RATE:
                return buildDatabaseConfig(RateDB, "emarket",
                        new String[]{
                                "emarket.issuance",
                                "emarket.violation_instance",
                                "emarket.violation",
                                "emarket.issuance_comment",
                        },
                        new String[]{

                        });
            default:
                new RuntimeException("Unknown Service " + name);
        }
        return null;
    }

    public static DatabaseConfig buildDatabaseConfig(String name, String schemaIncludeList, String[] tableIncludeList, String[] columnExcludeList) {
        return new DatabaseConfig(
                name,
                schemaIncludeList,
                String.join(",", tableIncludeList), String.join(",", columnExcludeList)
        );
    }
}
