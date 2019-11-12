package application.api

import application.monitoring.UserMonitoringService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(
    val userMonitoringService: UserMonitoringService
) {

    @GetMapping("/hello")
    fun getLocations(@RequestParam country: String): String {
        val counter = userMonitoringService.counterByCountry(country)
        counter.increment()
        return "hello from $country - ${counter.count()}"
    }
}
