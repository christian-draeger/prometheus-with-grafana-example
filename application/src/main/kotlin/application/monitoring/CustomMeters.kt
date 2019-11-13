package application.monitoring

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import io.prometheus.client.GaugeMetricFamily
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class CustomMeters(private val registry: MeterRegistry) {

    val timer = Timer.builder("middleware")
        .description("custom function timer")
        .tags("custom", "timer")
        .register(registry)

    fun counterByCountry(countryCode: String) = Counter
        .builder("user")
        .tag("country", countryCode)
        .register(registry)

}
