package dk.lunar.codetest.domain.util

import dk.lunar.codetest.domain.CountableResource
import dk.lunar.codetest.infrastructure.ports.entities.CountableResourceEntity

class DomainToEntityMapper {

    static CountableResourceEntity toEntity(Long id, CountableResource entity) {
        return new CountableResourceEntity(
                id: id,
                name: entity.name,
                count: entity.count
        )
    }

}
