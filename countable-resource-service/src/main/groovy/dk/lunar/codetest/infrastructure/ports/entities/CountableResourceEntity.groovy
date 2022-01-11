package dk.lunar.codetest.infrastructure.ports.entities

import groovy.transform.Canonical
import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Version

@Entity
@Canonical
@CompileStatic
class CountableResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(unique=true, nullable = false)
    String name

    @Column(nullable = false)
    long count

    @Version
    Integer version

}
