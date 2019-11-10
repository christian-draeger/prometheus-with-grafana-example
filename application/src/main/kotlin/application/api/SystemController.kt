package application.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SystemController {

    @GetMapping("/")
    fun getLocations() = "hello"
}