package uk.ac.reigate.onlineapplications.repository

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.onlineapplications.domain.Application

interface ApplicationRepository extends CrudRepository<Application, Integer> {
}
