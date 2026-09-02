package edu.tcu.cs.hogwartsartifactsonline.wizard;

import edu.tcu.cs.hogwartsartifactsonline.artifact.Artifact;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wizard implements Serializable {
    @Id
    private Integer id;
    private String name;

    /**
        ### Why do we need mappedBy in a bidirectional @OneToMany / @ManyToOne relationship?

     -  In a bidirectional relationship, both entities have a field referencing each other, but there's only
        **one** foreign key column in the database.

     -  JPA needs to know which side actually controls (writes) that FK — otherwise it can't tell the two mappings
         apart.

     -  Without "mappedBy" , JPA would treat `Wizard.artifacts` and `Artifact.wizard` as two independent
        unrelated relationships**.
    -------------------------------------------------------------------------------------------------------
        [1:M] 1 Wizard can have several Artifacts as similar to  [Department to Employee relationship]
           mappedBy = "owner"  marks
            - Wizard   :   inverse (non-owning) side of the @OneToMany relationship.
            - Artifact :   owning side of the relationship.

     Rule of thumb :
     In a bidirectional @OneToMany / @ManyToOne pair, the @ManyToOne side is always the owner
     (since the FK physically lives on that table), and the @OneToMany side must declare mappedBy pointing
     to that owning field.

     Practical consequence:**

     since only the owning side (`Artifact.owner`) writes to the DB, changes made
     only on the inverse side
     (e.g., `wizard.getArtifacts().add(artifact)`) won't persist to the FK
     */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "owner")
    private List<Artifact> artifactList = new ArrayList<>();

    public Wizard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artifact> getArtifactList() {
        return artifactList;
    }

    public void setArtifactList(List<Artifact> artifactList) {
        this.artifactList = artifactList;
    }

    // Add an artifact to the wizard's list of artifacts and set the owner of the artifact to this wizard.
    public void addArtifact(Artifact artifact) {
        // Set the owner of the artifact to this wizard
        artifact.setOwner(this);
        this.artifactList.add(artifact);
    }

    public Integer getNumberOfArtifacts() {
        return this.artifactList.size();
    }

}
