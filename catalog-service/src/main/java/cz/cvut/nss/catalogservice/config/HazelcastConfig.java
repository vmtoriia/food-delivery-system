package cz.cvut.nss.catalogservice.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class HazelcastConfig {

    @Bean
    public Config cacheConfig() {
        Config config = new Config();
        config.setInstanceName("catalog-hazelcast-instance");

        // We configure a cache map named "restaurants" that stores data for 1 hour
        config.addMapConfig(new MapConfig()
                .setName("restaurants")
                .setTimeToLiveSeconds(3600));

        return config;
    }
}