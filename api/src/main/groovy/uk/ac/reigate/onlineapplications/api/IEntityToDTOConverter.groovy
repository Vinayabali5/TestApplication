package uk.ac.reigate.onlineapplications.api

/**
 * Classes that implement this interface can convert an Entity object into a DTO object. 
 * 
 * @author Michael Horgan
 *
 * @param <E> The Entity class for the interface
 * @param <DTO> The DTO class for the interface
 */
interface IEntityToDTOConverter<E, DTO> {
	
	public DTO convertToDto(E entity)
}
