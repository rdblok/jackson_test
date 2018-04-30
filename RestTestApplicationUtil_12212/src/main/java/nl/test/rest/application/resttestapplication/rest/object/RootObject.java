
package nl.test.rest.application.resttestapplication.rest.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.LocalDateTime;

public class RootObject {
    @JsonProperty("otherProperty")
    private String ident;
    @JsonProperty("rootName")
    private String name;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    private ComplexObject myObject;

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public ComplexObject getMyObject() {
        return myObject;
    }

    public void setMyObject(ComplexObject myObject) {
        this.myObject = myObject;
    }
    
}
