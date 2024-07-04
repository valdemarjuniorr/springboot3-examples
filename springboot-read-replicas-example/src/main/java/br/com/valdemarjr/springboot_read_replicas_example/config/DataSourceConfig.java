package br.com.valdemarjr.springboot_read_replicas_example.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

  private final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

  private final ReadReplicaDataSourceConfiguration readReplicaDSCfg;

  private final WriteReplicaDataSourceConfiguration writeReplicaDSCfg;

  public DataSourceConfig(
      ReadReplicaDataSourceConfiguration readReplicaDSCfg,
      WriteReplicaDataSourceConfiguration writeReplicaDSCfg) {
    this.readReplicaDSCfg = readReplicaDSCfg;
    this.writeReplicaDSCfg = writeReplicaDSCfg;
  }

  @Primary
  @Bean
  public DataSource dataSource() {
    var routingDataSource = new RoutingDataSource();

    var writeDS =
        buildDataSource(
            writeReplicaDSCfg.getUrl(),
            writeReplicaDSCfg.getUsername(),
            writeReplicaDSCfg.getPassword());

    var replicaDS =
        buildDataSource(
            readReplicaDSCfg.getUrl(),
            readReplicaDSCfg.getUsername(),
            readReplicaDSCfg.getPassword());

    var targetDataSources = new HashMap<>();
    targetDataSources.put(RoutingDataSource.Route.PRIMARY, writeDS);
    targetDataSources.put(RoutingDataSource.Route.REPLICA, replicaDS);

    routingDataSource.setTargetDataSources(targetDataSources);
    routingDataSource.setDefaultTargetDataSource(writeDS);

    return routingDataSource;
  }

  private DataSource buildDataSource(String url, String username, String password) {
    return DataSourceBuilder.create()
        .url(url)
        .username(username)
        .password(password)
        .driverClassName("org.postgresql.Driver")
        .build();
  }
}
