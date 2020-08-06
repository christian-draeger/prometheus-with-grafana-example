package application.api

import it.skrape.core.fetcher.Mode
import it.skrape.core.htmlDocument
import it.skrape.expect
import it.skrape.matchers.toBe
import it.skrape.selects.html5.h1
import it.skrape.selects.html5.li
import it.skrape.skrape
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension::class)
class SystemControllerIT(
    @LocalServerPort val port: Int
) {

    @Test
    fun `landing page is rendered correctly`() {
        skrape {
            url = "http://localhost:$port"
            mode = Mode.DOM
            expect {
                htmlDocument {
                    h1 {
                        findFirst {
                            text toBe "OCR - Middleware"
                        }
                    }
                    "li a" {
                        findFirst {
                            text toBe "Business metrics"
                            attribute("href") toBe "http://localhost:3000/d/business-metrics"
                        }
                        findSecond {
                            text toBe "Technical metrics"
                            attribute("href") toBe "http://localhost:3000/d/technical-metrics"
                        }
                        findThird {
                            text toBe "Spring-Boot metrics"
                            attribute("href") toBe "http://localhost:3000/d/spring-boot-statistics"
                        }
                    }
                }
            }
        }
    }
}