package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.wizard.Wizard;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

/**
  Understanding of @ManyToOne Relationship :-  [Employee] and [Department]

      Many employee instances belong to the same Department instance
         - [emp1,emp2,emp3] instances belong to  [HR department] .
         - [emp5,emp6] instances belong to  [Software Development  department] .
         - [emp7,emp8,emp9] instances belong to  [Finance department] .

         Employee Entity is the owner of the relationship because Employee table contains the
         foreign key to the Department table.

    Similarly, here many Artifact instances i.e (Employees) can belong to one Wizard i.e (Department) ,
 */

@Entity
public class Artifact implements Serializable {

    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;

     /**
        ManyToOne relationship between Artifact i.e [Employee] and Wizard i.e [Department]
        - Many artifacts [Employee]  can belong to one wizard [Department].*/
    @ManyToOne
    private Wizard wizard;

    public Artifact() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

}
