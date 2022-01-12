package dk.lunar.businesslogic.domain.commands

import dk.lunar.businesslogic.domain.CountableResourceActor
import groovy.transform.CompileStatic
import groovy.transform.Immutable

@Immutable
@CompileStatic
class IncrementCommand implements CountableResourceActor.CountableResourceActorMsg {

}
