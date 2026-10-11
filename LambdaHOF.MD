

1. The method Assertions.catchThrowable(...) in a HOF. It accepts input argument - ThrowingCallable , 
    which is a @FunctionalInterface.

       public static Throwable catchThrowable(ThrowingCallable shouldRaiseThrowable) 
          {
           .......
          }
         
2. This @FunctionalInterface ThrowingCallable defines a SAM call() .

          @FunctionalInterface
           public interface ThrowingCallable {
           void call() throws Throwable;
           }

3. The lambda expression defined in the below code snippet is an implementation of 
   the call() method of the ThrowingCallable interface.

   Assertions.catchThrowable(() → {
          ...
           }
           ); ,
    
   
   Throwable thrown = Assertions.catchThrowable(() → {
   Artifact returnedArtifact = artifactService.findById("1250808601744904192");
   });