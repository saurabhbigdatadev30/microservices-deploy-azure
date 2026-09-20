package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.artifact.converter.ArtifactDtoToArtifactConverter;
import edu.tcu.cs.hogwartsartifactsonline.artifact.converter.ArtifactToArtifactDtoConverter;
import edu.tcu.cs.hogwartsartifactsonline.artifact.dto.ArtifactDto;
import edu.tcu.cs.hogwartsartifactsonline.system.Result;
import edu.tcu.cs.hogwartsartifactsonline.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/artifacts")
public class ArtifactController {

    private final ArtifactService artifactService;

    private final ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter;

    private final ArtifactDtoToArtifactConverter artifactDtoToArtifactConverter;


    public ArtifactController(ArtifactService artifactService,
                              ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter,
                              ArtifactDtoToArtifactConverter artifactDtoToArtifactConverter)
    {
        this.artifactService = artifactService;
        this.artifactToArtifactDtoConverter = artifactToArtifactDtoConverter;
        this.artifactDtoToArtifactConverter = artifactDtoToArtifactConverter;
    }

   /**
      @GetMapping("/{artifactId}")   We replace this with functional style using Stream API below
    public Result findArtifactById(@PathVariable String artifactId){
        Artifact foundArtifact = this.artifactService.findById(artifactId);
        ArtifactDto artifactDto = this.artifactToArtifactDtoConverter.convert(foundArtifact);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", artifactDto);
    }*/


   // @GetMapping("/api/v1/artifacts/{artifactId}") :: @PathVariable("artifactId") String artifactId
    @GetMapping("/{artifactId}")
    public Result findArtifactById(@PathVariable String artifactId) {
    return Stream.of(artifactId)
            .map(this.artifactService::findById)
            .map(this.artifactToArtifactDtoConverter::convert)
            .map(artifactDto -> new Result(true, StatusCode.SUCCESS, "Find One Success", artifactDto))
            .findFirst().get();

}

    @GetMapping
    public Result findAllArtifacts(){
        List<Artifact> foundArtifacts = this.artifactService.findAll();
        // Convert foundArtifacts to a list of artifactDtos
        List<ArtifactDto> artifactDtos = foundArtifacts.stream()
                .map(this.artifactToArtifactDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", artifactDtos);
    }


// Convert the above method to a more functional programming style using Stream API
    @PostMapping
    public Result addArtifact(@Valid @RequestBody ArtifactDto artifactDto)
        {
        return Stream.of(artifactDto)
                .map(this.artifactDtoToArtifactConverter::convert)
                .filter(Objects::nonNull)
                .map(this.artifactService::save)
                .map(this.artifactToArtifactDtoConverter::convert)
                .map(savedArtifactDto -> new Result(true, StatusCode.SUCCESS, "Add Success", savedArtifactDto))
                .findFirst()
                .orElseThrow();
       }




   /** @PutMapping("/{artifactId}")
    public Result updateArtifact(@PathVariable String artifactId, @Valid @RequestBody ArtifactDto artifactDto){
        Artifact update = this.artifactDtoToArtifactConverter.convert(artifactDto);
        Artifact updatedArtifact = this.artifactService.update(artifactId, update);
        ArtifactDto updatedArtifactDto = this.artifactToArtifactDtoConverter.convert(updatedArtifact);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedArtifactDto);
    }*/

    @PutMapping("/{artifactId}")
    public Result updateArtifact(@PathVariable String artifactId, @Valid @RequestBody ArtifactDto artifactDto)
    {
        return Stream.of(artifactDto)
                .map(this.artifactDtoToArtifactConverter::convert)
                .map(artifact -> this.artifactService.update(artifactId, artifact))
                .map(this.artifactToArtifactDtoConverter::convert)
                .map(updatedArtifactDto -> new Result(true, StatusCode.SUCCESS, "Update Success", updatedArtifactDto))
                .findFirst()
                .orElseThrow();
    }



    @DeleteMapping("/{artifactId}")
    public Result deleteArtifact(@PathVariable String artifactId){
        this.artifactService.delete(artifactId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
