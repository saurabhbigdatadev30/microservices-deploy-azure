package edu.tcu.cs.hogwartsartifactsonline.artifact;

public class ArtifactNotFoundException extends RuntimeException{

    public ArtifactNotFoundException(String id){
        super(String.format("Could not find artifact with Id %s", id));
       // super("Could not find artifact with Id " + id + " :(");
    }

}
