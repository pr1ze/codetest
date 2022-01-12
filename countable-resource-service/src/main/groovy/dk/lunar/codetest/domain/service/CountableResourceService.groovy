package dk.lunar.codetest.domain.service

import dk.lunar.codetest.domain.CountableResource
import dk.lunar.codetest.domain.repository.CountableResourceRepository
import dk.lunar.codetest.infrastructure.ports.entities.CountableResourceEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Service

import javax.persistence.LockModeType
import javax.persistence.OptimisticLockException
import javax.transaction.Transactional

import static dk.lunar.codetest.domain.util.EntityToDomainMapper.toDomain

@Service
class CountableResourceService {

    private final CountableResourceRepository countableResourceRepository

    @Autowired
    CountableResourceService(CountableResourceRepository countableResourceRepository) {
        this.countableResourceRepository = countableResourceRepository;
    }

    CountableResource get(String name) {
        return countableResourceRepository.findByName(name)
                .map((resource) -> toDomain(resource))
                .orElseThrow(() -> new NoSuchElementException())
    }

    CountableResource create(String name, long count) {
        try {
            CountableResourceEntity newEntity = countableResourceRepository.save(new CountableResourceEntity(
                    name: name,
                    count: count,
                    version: 1
            ))

            return toDomain(newEntity)
        } catch (DataIntegrityViolationException exception) {
            boolean isUniqueConstraint = exception as boolean
            // Look into exception whether it's because of unique constraint on name

            if (isUniqueConstraint) {
                throw new DuplicateKeyException(name + " already exists")
            } else {
                throw new RuntimeException("Unknown error")
            }
        }
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE) // Makes a SELECT * FOR UPDATE
    CountableResource update(CountableResource updated) {
        CountableResourceEntity persisted = countableResourceRepository.findByName(updated.name)
                .orElseThrow(() -> new NoSuchElementException())

        if (updated.eTag != toDomain(persisted).md5()) {
            //Catched and handled by GlobalExceptionHandler.groovy - returning 412 - Precondition failed
            throw new OptimisticLockException()
        }

        persisted.count = updated.count
        persisted.name = updated.name

        persisted = countableResourceRepository.save(persisted)

        return toDomain(persisted)
    }

}
