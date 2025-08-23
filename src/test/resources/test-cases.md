# ULTIMATE QA TEST ASSIGNMENT (Spring Framework 7.0 API Versioning)

Scope: Annotation-based REST Controllers and Mapping Methods
Out of scope: reactive stack, view controllers, functional routes, security, validation, OpenAPI, ...

## TEST ENVIRONMENT
- IntelliJ IDEA 2025.2 Ultimate Edition (Build #IU-252.23892.409)
- Spring Boot 4.0.0-SNAPSHOT
- Spring Framework 7.0.0-SNAPSHOT
- Java 21

TO-DO
- PUT
- PATCH
- DELETE
- BASIC
- PATH (config test cases)
- HEADER (config test cases)
- MEDIA
- QUERY



### AUTOMATION:

PRE-CONDITIONS:
- Project is built

STEPS:
Run automation tests for:
1.`BasicTest.java` - all versioning configurations must be commented
2.`PathVersioningTest.java` - uncomment `configurer.usePathSegment(1)` in `PathVersioningConfig.java` (other versioning configurations must be commented)
3.`HeaderVersioningTest.java`- uncomment `configurer.useRequestHeader("X-API-Version");` in `HeaderVersioningConfig.java` (other versioning configurations must be commented)

EXPECTED RESULT:
All tests must pass

ACTUAL RESULT: 
Functionality used in the following methods in `PathController.java` is not supported yet, so it is expected that corresponding tests will not pass yet:
- getAnimalsDefaultOld
- getAnimalsDefault
- getMultipleVersions


## TEST CASES TO VERIFY IDEA SUPPORT OF API VERSIONING

PRE-CONDITIONS:
- Project is built
- Test controller class - use any `apiversioning-test/src/main/java/com/demo/apiversioningtest/controller`
- Header versioning strategy: `X-API-Version`


NOTE:
Test cases include only GET HTTP method (@GetMapping), the same should be tested for:
- @PostMapping
- @PutMapping
- @PatchMapping
- @DeleteMapping


### TEST CASE #1: Version attribute code completion
STEPS:
1. Open test controller class
2. Type @GetMapping(
3. Press Ctrl+Space
4. Select 'version'

EXPECTED RESULT:
- Version attribute appears in suggestions
- Autocompleted with @GetMapping(version = )


### TEST CASE #2: Version value code completion
STEPS:
1. Open test controller class
2. Type @GetMapping(version = "
3. Press Ctrl+Space
4. Select 'v1' value

EXPECTED RESULT:
- Version values appear in suggestions (e.g. "v1", "v2", "v1.1")
- Autocompleted with @GetMapping(version = "v1")


### TEST CASE #3: Version value highlighting
STEPS:
1. Open test controller class
2. Add @GetMapping(version = "v1")
2. Observe syntax coloring

EXPECTED RESULT:
- Version value attribute "v1" is highlighted (green but can depend on the idea theme)
- No syntax errors (red)


### TEST CASE #4: Multiple mapping annotations
STEPS:
1. Open test controller class
2. Add @GetMapping(value = "/{id}", version = "v1")

EXPECTED RESULT:
- Both attributes are highlighted (green but can depend on the idea theme)
- No syntax errors (red)


### TEST CASE #5: Duplicated version detection
STEPS:
1. Open test controller class
2. Create two different methods with the same version:

EXPECTED RESULT:
- Error, e.g. "Duplicated version is detected"
- Quick fix suggestions








