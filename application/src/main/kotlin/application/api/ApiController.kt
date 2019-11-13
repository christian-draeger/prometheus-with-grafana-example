package application.api

import application.monitoring.CustomMeters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(
    val customMeters: CustomMeters
) {
    @GetMapping("/hello")
    fun getLocations(@RequestParam country: String): String {
        val counter = customMeters.counterByCountry(country)
        counter.increment()
        return "hello from $country - ${counter.count()}"
    }
}
