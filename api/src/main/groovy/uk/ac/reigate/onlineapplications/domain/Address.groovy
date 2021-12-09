package uk.ac.reigate.onlineapplications.domain

import groovy.transform.EqualsAndHashCode

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "address")
@AttributeOverrides([
    @AttributeOverride(name = "id", column = @Column(name = "address_id"))
])
@DynamicInsert(true)
@DynamicUpdate(true)
@EqualsAndHashCode(includeFields = true)
class Address extends BaseEntityIdentity {
    
    /**
     * The first address line for the address.
     */
    @Column(name = "line1")
    String line1
    
    /**
     * The second address line for the address.
     */
    @Column(name = "line2")
    String line2
    
    /**
     * The third address line for the address (rarely used).
     */
    @Column(name = "line3")
    String line3
    
    /**
     * The fourth address line for the address (rarely used).
     */
    @Column(name = "line4")
    String line4
    
    /**
     * The fifth address line for the address (rarely used).
     */
    @Column(name = "line5")
    String line5
    
    /**
     * The town for the address.
     */
    @Column(name = "town")
    String town
    
    /**
     * The county for the address.
     */
    @Column(name = "county")
    String county
    
    /**
     * The postcode for the address.
     */
    @Column(name = "postcode", nullable = false)
    String postcode
    
    /**
     * The full address label for the address.
     */
    @Column(name = "FULLADDRESS", insertable = false, updatable = false)
    String label
    
    /**
     * Default No Args constructor
     */
    Address() {}
    
    /**
     * The toString method that is used to display the address as a comma separated address block.
     */
    String toString() {
        String out = ''
        out += (line1 != null && line1 != '') ? line1 : ''
        out += (line2 != null && line2 != '') ? ', ' + line2 : ''
        out += (line3 != null && line3 != '') ? ', ' + line3 : ''
        out += (line4 != null && line4 != '') ? ', ' + line4 : ''
        out += (line5 != null && line5 != '') ? ', ' + line5 : ''
        if (county != null || county == '') {
            out += ', ' + county
        }
        out += ', ' + postcode
        return out
    }
    
    /**
     * This method is used to return an address in a pre-formatted HTML code block.
     * 
     * @return a pre-formatted HTML block with the address
     */
    String toHTML() {
        String out = ''
        out += (line1 != null && line1 == '') ? line1 : ''
        out += (line2 != null && line2 == '') ? '<br/>' + line2 : ''
        out += (line3 != null && line3 == '') ? '<br/>' + line3 : ''
        out += (line4 != null && line4 == '') ? '<br/>' + line4 : ''
        out += (line5 != null && line5 == '') ? '<br/>' + line5 : ''
        if (county != null ) {
            out += '<br/>' + county
        }
        out += '<br/>' + postcode
        return out
    }
}
