package uk.ac.reigate.onlineapplications.dto.lookup

class PostcodeLookupDTO {
    
    String id
    
    String text
    
    String description
    
    PostcodeLookupDTO() {}
    
    PostcodeLookupDTO(String id, String text, String description) {
        this.id = id
        this.text = text
        this.description = description
    }
    
    String toString() {
        return "id: " + this.id + ", text: " + this.text + ", description: " + this.description
    }
}
