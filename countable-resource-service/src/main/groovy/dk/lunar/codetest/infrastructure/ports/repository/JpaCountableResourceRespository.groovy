package dk.lunar.codetest.infrastructure.ports.repository


import dk.lunar.codetest.domain.repository.CountableResourceRepository
import dk.lunar.codetest.infrastructure.ports.entities.CountableResourceEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import javax.transaction.Transactional

@Repository
@Transactional
interface JpaCountableResourceRespository extends CountableResourceRepository, JpaRepository<CountableResourceEntity, String> {

}
