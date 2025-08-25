# ULTIMATE QA TEST ASSIGNMENT (Spring Framework 7.0 API Versioning)

**Task: Create test cases that can be used to test Spring Web API Versioning support in IntelliJ IDEA.**

These test cases should include:
- all possible declarative ways to configure the versioning; the custom implementations are out of scope
- the controller classes
- the set of HTTP Requests that can be used to test these controller mappings.
- optionally: some client (RestClient, WebClient, HTTP Service clients, some testing frameworks) classes that can test these controller mappings


Scope: Annotation-based REST Controllers and Mapping Methods

Out of scope: reactive stack, view controllers, functional routes, security, validation, OpenAPI, ...

**Documentation:**
- [Spring-Framework-7.0-Release-Notes: API Versioning (GitHub)](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-7.0-Release-Notes#api-versioning)
- [API Versioning (Spring docs)](https://docs.spring.io/spring-framework/reference/7.0-SNAPSHOT/web/webmvc-versioning.html)
- [ApiVersionConfigurer (Spring docs)](https://docs.spring.io/spring-framework/docs/current-SNAPSHOT/javadoc-api/org/springframework/web/servlet/config/annotation/ApiVersionConfigurer.html)

### TEST ENVIRONMENT
- IntelliJ IDEA 2025.2 Ultimate Edition (Build #IU-252.23892.409)
- Spring Boot 4.0.0-SNAPSHOT
- Spring Framework 7.0.0-SNAPSHOT
- Java 21


## AUTOMATION:

**PATH:** src/test/java/com/demo/apiversioningtest

**PRE-CONDITIONS:**
- Test project is built (`mvn spring-boot:run`)


**STEPS:**  
Run automation tests for:

1. `BasicTest.java` - uncomment configs in `HeaderVersioningConfig.java`, other versioning configurations (path, query, media type) must be commented
2. `PathVersioningTest.java` - uncomment configs in `PathVersioningConfig.java`, other versioning configurations (header, query, media type) must be commented
3. `HeaderVersioningTest.java`- uncomment configs in `HeaderVersioningConfig.java`, other versioning configurations (path, query, media type) must be commented
4. `QueryVersioningTest.java`- uncomment configs in `QueryVersioningConfig.java`, other versioning configurations (path, header, media type) must be commented
5. `MediaTypeVersioningTest.java`- uncomment configs in `MediaTypeVersioningConfig.java`, other versioning configurations (path, header, query) must be commented

**EXPECTED RESULT:**  
All tests must pass

**ACTUAL RESULT:**
1. All tests passed
2. It seems `usePathSegment(1)` that this config does not work properly yet, therefore, the tests with no version (default version) failed
3. All tests passed
4. It seems that `addSupportedVersions` and `setVersionRequired` do not work properly, therefore, the test `testGetAnimalsV3` should return 400 (v3 is not in the list of supported versions), but actually returns 200; and the test `testDefault` should return 400 (default version is required), but actually returns 200;
5. All tests passed


## MANUAL TESTING:

**PATH:** apiversioning-test/http

Contains set of HTTP Requests to test endpoints manually, if needed, because alL these endpoints are automated.



## ADDITIONAL TEST CASES TO VERIFY IDEA SUPPORT OF API VERSIONING

**PRE-CONDITIONS:**
- Test project is built (`mvn spring-boot:run`)
- Test controller class - use any `apiversioning-test/src/main/java/com/demo/apiversioningtest/controller`

**NOTE:**  
Test cases include only GET HTTP method (@GetMapping), the same should be tested for:
- @PostMapping
- @PutMapping
- @PatchMapping
- @DeleteMapping


### TEST CASE #1: Version attribute code completion
**STEPS:**
1. Open test controller class
2. Type @GetMapping(
3. Press Ctrl+Space
4. Select 'version'

**EXPECTED RESULT:**
- Version attribute appears in suggestions
- Autocompleted with @GetMapping(version = )


### TEST CASE #2: Version value code completion
**STEPS:**
1. Open test controller class
2. Type @GetMapping(version = "
3. Press Ctrl+Space
4. Select 'v1' value

**EXPECTED RESULT:**
- Version values appear in suggestions (e.g. "v1", "v2", "v1.1")
- Autocompleted with @GetMapping(version = "v1")


### TEST CASE #3: Version value highlighting
**STEPS:**
1. Open test controller class
2. Add @GetMapping(version = "v1")
2. Observe syntax coloring

**EXPECTED RESULT:**
- Version value attribute "v1" is highlighted (green but can depend on the idea theme)
- No syntax errors (red)


### TEST CASE #4: Multiple mapping annotations
**STEPS:**
1. Open test controller class
2. Add @GetMapping(value = "/{id}", version = "v1")

**EXPECTED RESULT:**
- Both attributes are highlighted (green but can depend on the idea theme)
- No syntax errors (red)


### TEST CASE #5: Duplicated version detection
**STEPS:**
1. Open test controller class
2. Create two different methods with the same version:

**EXPECTED RESULT:**
- Error, e.g. "Duplicated version is detected"
- Quick fix suggestions

