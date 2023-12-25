package com.srs.debezium.constant;

import com.srs.debezium.config.PropertyReader;

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

    private String columnIncludeList;

    public DatabaseConfig(String name, String schemaIncludeList, String tableIncludeList, String columnExcludeList) {
        this.name = name;
        this.port = Integer.parseInt(PropertyReader.getProperty("port"));
        this.host = PropertyReader.getProperty("host");
        this.userName = PropertyReader.getProperty("username");
        this.password = PropertyReader.getProperty("password");
        this.schemaIncludeList = schemaIncludeList;
        this.tableIncludeList = tableIncludeList;
        this.columnIncludeList = columnExcludeList;
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
        return columnIncludeList;
    }

    public static DatabaseConfig getDatabaseConfig(String name) {
        switch (name) {
            case ACCOUNT:
                return buildDatabaseConfig(AccountDB, "emarket",
                        new String[]{
                                "emarket.users",
                        },
                        new String[]{
                                "emarket.users.email",
                                "emarket.users.external_id",
                                "emarket.users.first_name",
                                "emarket.users.middle_name",
                                "emarket.users.last_name",
                                "emarket.users.status",
                                "emarket.users.market_codes",
                                "emarket.users.divisions",
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
                        },
                        new String[]{
                                "emarket.market.market_id",
                                "emarket.market.code",
                                "emarket.market.name",

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

    public static DatabaseConfig buildDatabaseConfig(String name, String schemaIncludeList, String[] tableIncludeList, String[] columnIncludeList) {
        return new DatabaseConfig(
                name,
                schemaIncludeList,
                String.join(",", tableIncludeList), String.join(",", columnIncludeList)
        );
    }
}
