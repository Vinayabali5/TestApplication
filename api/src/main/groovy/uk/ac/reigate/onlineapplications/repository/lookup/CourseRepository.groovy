package uk.ac.reigate.onlineapplications.repository.lookup

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.onlineapplications.domain.lookup.Course

interface CourseRepository extends CrudRepository<Course, Integer> {
    
    Course findByRequestCode(String requestCode)
    
}
