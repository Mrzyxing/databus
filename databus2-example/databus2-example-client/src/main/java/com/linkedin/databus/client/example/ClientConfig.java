
package com.linkedin.databus.client.example;

public class ClientConfig {

    private String[] args;
    private String relayName;
    private String sourceName;
    private String tableName;
    private String peekColumnName;
    private Integer containHttpPort;

    public String[] getArgs() {
        return args;
    }

    public String getRelayName() {
        return relayName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPeekColumnName() {
        return peekColumnName;
    }

    public Integer getContainHttpPort() {
        return containHttpPort;
    }

    public static final class ClientConfigBuilder {

        private String[] args;
        private String relayName;
        private String sourceName;
        private String tableName;
        private String peekColumnName;
        private Integer containHttpPort;

        private ClientConfigBuilder() {
        }

        public static ClientConfigBuilder aClientConfig() {
            return new ClientConfigBuilder();
        }

        public ClientConfigBuilder withArgs(String[] args) {
            this.args = args;
            return this;
        }

        public ClientConfigBuilder withRelayName(String relayName) {
            this.relayName = relayName;
            return this;
        }

        public ClientConfigBuilder withSourceName(String sourceName) {
            this.sourceName = sourceName;
            return this;
        }

        public ClientConfigBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public ClientConfigBuilder withPeekColumnName(String peekColumnName) {
            this.peekColumnName = peekColumnName;
            return this;
        }

        public ClientConfigBuilder withContainHttpPort(Integer containHttpPort) {
            this.containHttpPort = containHttpPort;
            return this;
        }

        public ClientConfigBuilder but() {
            return aClientConfig().withArgs(args).withRelayName(relayName)
                .withSourceName(sourceName).withTableName(tableName)
                .withPeekColumnName(peekColumnName).withContainHttpPort(containHttpPort);
        }

        public ClientConfig build() {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.relayName = this.relayName;
            clientConfig.sourceName = this.sourceName;
            clientConfig.tableName = this.tableName;
            clientConfig.containHttpPort = this.containHttpPort;
            clientConfig.args = this.args;
            clientConfig.peekColumnName = this.peekColumnName;
            return clientConfig;
        }
    }
}

