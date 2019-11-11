package application.api

import io.micrometer.core.annotation.Timed
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Timed
class SystemController {

    @GetMapping("/")
    fun getLocations() = "hello"
}