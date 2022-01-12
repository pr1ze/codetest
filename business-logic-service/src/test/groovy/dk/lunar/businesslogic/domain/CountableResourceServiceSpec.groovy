package dk.lunar.businesslogic.domain

import io.swagger.client.api.CountableResourceApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootTest
class CountableResourceServiceSpec extends Specification {

    @Autowired
    CountableResourceService service

    @Autowired
    CountableResourceApi countableResourceApi

    ExecutorService threadPool = Executors.newFixedThreadPool(10)

    @Unroll
    void "Bevis at samtidighed håndteres korrekt, og at ingen opdatering bliver tabt"() {
        given: 'Opret #incrementersCount incrementers og #decrementersCount decrementers'
        int countBeforeStarting = getCurrentCount()

        Collection<Callable> incrementers = []
        Collection<Callable> decrementers = []

        incrementersCount.times {
            incrementers << new Callable() {
                @Override
                Object call() throws Exception {
                    Thread.sleep((long) (Math.random() * 1000)) //Sleep random
                    service.increment()
                }
            }
        }

        decrementersCount.times {
            decrementers << new Callable() {
                @Override
                Object call() throws Exception {
                    Thread.sleep((long) (Math.random() * 1000)) //Sleep random
                    service.decrement()
                }
            }
        }

        when: 'Udfør alle samtidigt'
        def futures = threadPool.invokeAll(incrementers + decrementers)

        and: 'Vent på at alle er færdige'
        futures.each { it.get() }

        then: 'Så passer forrige count + expected - som beviser at samtidigheden er håndteret korrekt'
        getCurrentCount() == countBeforeStarting + expected

        where:
        incrementersCount | decrementersCount | expected
        50                | 20                | 30
        70                | 100               | -30
        10                | 5                 | 5
    }

    private int getCurrentCount() {
        try {
            return countableResourceApi.getCountableResourceByName('countableresourceABC').count
        } catch (e) {
            return 0
        }
    }

}
