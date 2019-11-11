package application.api

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(
    meterRegistry: MeterRegistry
) {

    val counter = meterRegistry.counter("my.counter")

    @GetMapping("/hello")
    fun getLocations(): String {
        counter.increment()
        return "hello ${counter.count()}"
    }
}
