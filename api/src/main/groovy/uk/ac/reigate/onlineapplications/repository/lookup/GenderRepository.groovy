package uk.ac.reigate.onlineapplications.repository.lookup

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.onlineapplications.domain.lookup.Gender

interface GenderRepository extends CrudRepository<Gender, Integer> {
}
