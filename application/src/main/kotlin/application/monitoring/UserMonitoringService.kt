package application.monitoring

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Component

@Component
class UserMonitoringService(
    meterRegistry: MeterRegistry
) {

    private val registry = meterRegistry

    fun counterByCountry(countryCode: String) = Counter
        .builder("user")
        .tag("country", countryCode)
        .register(registry)
}
