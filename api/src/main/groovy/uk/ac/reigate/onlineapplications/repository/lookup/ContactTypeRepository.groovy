package uk.ac.reigate.onlineapplications.repository.lookup

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.onlineapplications.domain.lookup.ContactType

interface ContactTypeRepository extends CrudRepository<ContactType, Integer> {
}
