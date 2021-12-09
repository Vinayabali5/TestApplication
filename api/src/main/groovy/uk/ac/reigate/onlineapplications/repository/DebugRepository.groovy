package uk.ac.reigate.onlineapplications.repository

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.onlineapplications.domain.Debug

interface DebugRepository extends CrudRepository<Debug, Integer> {
    
}
