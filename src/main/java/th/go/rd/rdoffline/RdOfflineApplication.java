package th.go.rd.rdoffline;


import com.codahale.metrics.MetricRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RdOfflineApplication {

    @Bean
    MetricRegistry metrics() {
        return new MetricRegistry();
    }

    public static void main(String[] args) {
        SpringApplication.run(RdOfflineApplication.class, args);
    }
}
