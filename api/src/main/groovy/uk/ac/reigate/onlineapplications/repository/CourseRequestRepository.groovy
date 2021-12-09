package uk.ac.reigate.onlineapplications.repository

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.onlineapplications.domain.CourseRequest

interface CourseRequestRepository extends CrudRepository<CourseRequest, Integer> {
    
    List<CourseRequest> findByApplicationId(Integer id)
}
