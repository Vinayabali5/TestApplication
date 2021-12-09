package uk.ac.reigate.onlineapplications.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.onlineapplications.domain.Debug
import uk.ac.reigate.onlineapplications.repository.DebugRepository

@Service
class DebugService {
    
    @Autowired
    DebugRepository debugRepository
    

    /**
     * This method is used to save a Debug object to the the database. 
     * 
     * @param debug a Debug object to save.
     * @return the saved Debug object.
     */
    Debug save(Debug debug) {
        return debugRepository.save(debug)
    }
	
	/**
	 * This method is used to create the debug data information into the database.
	 * 
	 * @param debugData
	 * @return
	 */
	Debug create(String debugData) {
		Debug debugToSave = new Debug()
		debugToSave.data = debugData;
		return save(debugToSave)
	}
        
}
