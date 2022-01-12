package dk.lunar.codetest.domain.util

import dk.lunar.codetest.domain.CountableResource
import dk.lunar.codetest.infrastructure.ports.entities.CountableResourceEntity

class EntityToDomainMapper {

    static CountableResource toDomain(CountableResourceEntity entity) {
        return new CountableResource(
                name: entity.name,
                count: entity.count,
                version: entity.version
        )
    }

}
