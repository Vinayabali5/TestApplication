package uk.ac.reigate.onlineapplications.repository

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.onlineapplications.domain.Address

interface AddressRepository extends CrudRepository<Address, Integer> {
}
