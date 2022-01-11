package dk.lunar.codetest.domain.repository


import dk.lunar.codetest.infrastructure.ports.entities.CountableResourceEntity

interface CountableResourceRepository {

    Optional<CountableResourceEntity> findByName(String name)

    CountableResourceEntity save(CountableResourceEntity entity)

}