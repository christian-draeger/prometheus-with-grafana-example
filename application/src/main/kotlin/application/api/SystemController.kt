package application.api

import io.micrometer.core.annotation.Timed
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Timed
class SystemController(
    @Value("\${grafana.url}") val grafanaUrl: String
) {

    private val dashboards = mapOf(
        "Business metrics" to "business-metrics",
        "Technical metrics" to "technical-metrics",
        "Spring-Boot metrics" to "spring-boot-statistics"
    )

    @GetMapping("/")
    fun getLocations() = createHTMLDocument().div {
        h1 {
            +"OCR - Middleware"
        }
        ul {
            dashboards.forEach {
                dashboardLink(it.key, it.value)
            }
        }
    }.serialize()

    private fun UL.dashboardLink(text: String, dashboard: String) = li {
        a {
            href = "$grafanaUrl/d/$dashboard"
            target = "_blank"
            +text
        }
    }
}