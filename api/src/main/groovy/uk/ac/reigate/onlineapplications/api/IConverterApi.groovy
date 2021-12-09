package uk.ac.reigate.onlineapplications.api

/**
 * Classes that implement this interface are converters that provide methods to convert 
 * Entity to DTo and DTo to Entity.
 * 
 * @author Michael Horgan
 *
 * @param <E> The Entity class for the interface 
 * @param <DTO> The DTO class for the interface
 */
interface IConverterApi<E, DTO> extends IEntityToDTOConverter<E, DTO>, IDTOToEntityConverter<DTO, E> {
}
