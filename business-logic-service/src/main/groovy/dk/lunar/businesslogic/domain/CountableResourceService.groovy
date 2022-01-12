package dk.lunar.businesslogic.domain

import akka.actor.typed.ActorSystem
import dk.lunar.businesslogic.domain.commands.DecrementCommand
import dk.lunar.businesslogic.domain.commands.IncrementCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CountableResourceService {

    private final ActorSystem actorSystem

    @Autowired
    CountableResourceService(ActorSystem actorSystem) {
        this.actorSystem = actorSystem
    }

    def increment() {
        actorSystem.tell(new IncrementCommand())
    }

    def decrement() {
        actorSystem.tell(new DecrementCommand())
    }

}
