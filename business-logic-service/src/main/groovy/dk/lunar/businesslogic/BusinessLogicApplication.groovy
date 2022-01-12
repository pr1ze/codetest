package dk.lunar.businesslogic

import akka.actor.typed.ActorSystem
import dk.lunar.businesslogic.domain.CountableResourceActor
import groovy.util.logging.Log
import io.swagger.client.api.CountableResourceApi
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@Log
@SpringBootApplication
class BusinessLogicApplication {

    static void main(String[] args) {
        SpringApplication.run(BusinessLogicApplication, args)
    }

    @Bean
    CountableResourceApi getCountableResourceApi() {
        return new CountableResourceApi()
    }

    @Bean
    ActorSystem actorSystem(CountableResourceApi countableResourceApi) {
        //Normalt ville man have 1 actor pr. resource hvis der virkelig er smæk på.
        //For at skalere horizontalt skulle man have noget middlewere som garantere at der kun er 1 af en given actor i clustered.
        //Her laves der bare 1 actor og kun for 1 resource.
        def actor = CountableResourceActor.create('countableresourceABC', countableResourceApi)
        return ActorSystem.create(actor, "codetest")
    }

}
