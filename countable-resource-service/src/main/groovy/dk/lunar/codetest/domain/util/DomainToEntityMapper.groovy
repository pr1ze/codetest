package dk.lunar.codetest.domain.util

import dk.lunar.codetest.domain.CountableResource
import dk.lunar.codetest.infrastructure.ports.entities.CountableResourceEntity

class DomainToEntityMapper {

    static CountableResourceEntity toEntity(CountableResource entity) {
        return new CountableResourceEntity(
                name: entity.name,
                count: entity.count,
                version: Integer.parseInt(entity.eTag) // Casting should be more defensive in a real world example
        )
    }

}
