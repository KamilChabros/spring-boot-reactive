package xyz.reactivespringboot.demo.stockservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom

@SpringBootApplication
class StockserviceApplication

fun main(args: Array<String>) {
	runApplication<StockserviceApplication>(*args)
}

@RestController
class RestController {

	//replacing with array call
	@GetMapping(value = ["/stocks/{symbol}"],
		produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
	fun prices(@PathVariable symbol: String): Flux<StockPrice> {
		return Flux.interval(Duration.ofSeconds(1))
				//In Kotlin We don't need to use keyword "new" - refers to StockPrice
				.map{StockPrice(symbol, randomStockPrice(), LocalDateTime.now())}
	}

	private fun randomStockPrice(): Double {

		return ThreadLocalRandom.current().nextDouble(100.0)
	}
}

data class StockPrice (val symbol: String,
						val price: Double,
						val time: LocalDateTime)
