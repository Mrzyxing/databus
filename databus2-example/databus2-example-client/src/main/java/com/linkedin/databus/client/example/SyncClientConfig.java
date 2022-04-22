package com.linkedin.databus.client.example;

public class SyncClientConfig {
    private String[] args;
    private String relayName;
    private String sourceName;
    private String tableName;
    private String targetTopic;
    private Integer containerHttpPort;

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String getRelayName() {
        return relayName;
    }

    public void setRelayName(String relayName) {
        this.relayName = relayName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTargetTopic() {
        return targetTopic;
    }

    public void setTargetTopic(String targetTopic) {
        this.targetTopic = targetTopic;
    }

    public Integer getContainerHttpPort() {
        return containerHttpPort;
    }

    public void setContainerHttpPort(Integer containerHttpPort) {
        this.containerHttpPort = containerHttpPort;
    }


    public static final class SyncClientConfigBuilder {
        private String[] args;
        private String relayName;
        private String sourceName;
        private String tableName;
        private String targetTopic;
        private Integer containerHttpPort;

        private SyncClientConfigBuilder() {
        }

        public static SyncClientConfigBuilder aSyncClientConfig() {
            return new SyncClientConfigBuilder();
        }

        public SyncClientConfigBuilder withArgs(String[] args) {
            this.args = args;
            return this;
        }

        public SyncClientConfigBuilder withRelayName(String relayName) {
            this.relayName = relayName;
            return this;
        }

        public SyncClientConfigBuilder withSourceName(String sourceName) {
            this.sourceName = sourceName;
            return this;
        }

        public SyncClientConfigBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public SyncClientConfigBuilder withTargetTopic(String targetTopic) {
            this.targetTopic = targetTopic;
            return this;
        }

        public SyncClientConfigBuilder withContainerHttpPort(Integer containerHttpPort) {
            this.containerHttpPort = containerHttpPort;
            return this;
        }

        public SyncClientConfigBuilder but() {
            return aSyncClientConfig().withArgs(args).withRelayName(relayName).withSourceName(sourceName).withTableName(tableName).withTargetTopic(targetTopic).withContainerHttpPort(containerHttpPort);
        }

        public SyncClientConfig build() {
            SyncClientConfig syncClientConfig = new SyncClientConfig();
            syncClientConfig.setArgs(args);
            syncClientConfig.setRelayName(relayName);
            syncClientConfig.setSourceName(sourceName);
            syncClientConfig.setTableName(tableName);
            syncClientConfig.setTargetTopic(targetTopic);
            syncClientConfig.setContainerHttpPort(containerHttpPort);
            return syncClientConfig;
        }
    }
}
