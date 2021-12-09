package uk.ac.reigate.onlineapplications.repository

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.onlineapplications.domain.Contact

interface ContactRepository extends CrudRepository<Contact, Integer> {
}
