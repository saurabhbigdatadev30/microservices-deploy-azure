package edu.tcu.cs.hogwartsartifactsonline.wizard;

import edu.tcu.cs.hogwartsartifactsonline.artifact.Artifact;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 Wizard
 |
 |  One Wizard has many Artifacts
 |
 +---- Artifact
 |
 +---- Artifact
 |
 +---- Artifact

 Wizard.artifactList is NOT the owner of the relationship. Artifact.wizard is the owning side.

 */
@Entity
public class Wizard implements Serializable {
    @Id
    private Integer id;
    private String name;
  /**
   one wizard can own many artifacts, but each artifact belongs to at most one wizard.
   Artifact contains the foreign key to the Wizard table . So :-
     - Artifact is the owner of the relationship,  we need to use mappedBy to tell JPA that the
       relationship is mapped by the wizard field in the Artifact class.
   */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "wizard")
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
        /**
           TODO -   Bidirectional JPA relationship :- addArtifact() method should update both sides.
              1.  We add an artifact to the wizard's list of artifacts i.e (artifactList),
              2.  We also need to set the owner of the artifact to this wizard.

         - If we only do artifactList.add(artifact), we are only updating one side of the relationship.
         - The other side, artifact.setWizard(this), is not updated, so when we do
           artifact.getWizard(), it will return null, because we never set the wizard of the artifact.
         */
        artifact.setWizard(this);
        this.artifactList.add(artifact);
    }

    public Integer getNumberOfArtifacts() {
        return this.artifactList.size();
    }

}
