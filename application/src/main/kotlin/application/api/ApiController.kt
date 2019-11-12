package application.api

import application.monitoring.GeoIpDetectionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class ApiController(
    val geoIpDetectionService: GeoIpDetectionService
) {

    @GetMapping("/hello")
    fun getLocations(request: HttpServletRequest): String {
        val count = geoIpDetectionService.writeMetricBy(request)
        return "hello $count"
    }
}
