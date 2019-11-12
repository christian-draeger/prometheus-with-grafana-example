package application.monitoring

import com.maxmind.geoip2.DatabaseReader
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import java.net.InetAddress
import javax.servlet.http.HttpServletRequest

@Component
class GeoIpDetectionService(
    meterRegistry: MeterRegistry,
    resourceLoader: ResourceLoader
) {

    private val counter = meterRegistry.counter("user", "country", "US")
    private final val db = resourceLoader.getResource("classpath:country.mmdb").file
    val dbReader = DatabaseReader.Builder(db).build()!!

    fun writeMetricBy(request: HttpServletRequest): Double {
        // val countryCode = searchCountryCodeByIp(getIpAddress(request))
        counter.increment()
        return counter.count()
    }

    private fun getIpAddress(request: HttpServletRequest): String {
        return request.getHeader("X-FORWARDED-FOR") ?: request.remoteAddr
    }

    private fun searchCountryCodeByIp(ip: String): String {
        val ipAddress = InetAddress.getByName(ip)
        val country = dbReader.country(ipAddress)
        return country.country.isoCode?: "XX"
    }
}