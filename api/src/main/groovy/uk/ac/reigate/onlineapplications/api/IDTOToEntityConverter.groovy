package uk.ac.reigate.onlineapplications.api

/**
 * Classes that implement this interface can convert a DTO object into an Entity class.
 * 
 * @author Michael Horgan
 *
 * @param <DTO> The DTO class for the interface
 * @param <E> The Entity class for the interface
 */
interface IDTOToEntityConverter<DTO, E> {
	
	/**
	 * This method is used to convert a DTO object into an Entity object.
	 * 
	 * @param dto the DTO to convert
	 * @return the Entity object
	 */
	public E convertToEntity(DTO dto) 
	
}
