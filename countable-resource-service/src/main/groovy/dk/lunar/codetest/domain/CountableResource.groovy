package dk.lunar.codetest.domain

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@Immutable
@CompileStatic
class CountableResource {
    String name
    long count
    String version

    def md5() {
        return "$name$count$version".md5()
    }

}
