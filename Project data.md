UISpringBootMongoDB - Frontend application based on React
BESpringBootMongoDB - Backend application based on Java and MongoDB


1. MongoDB

	- Create account and create a cluster
		userName: root
		password: root
	- Create DB (jobListing) and collection (JobPost) [web url for DB & collection(table) creation in cluster: https://cloud.mongodb.com/v2/669cb00451ed8d405bf0b3e6#/clusters]
	- Insert JSON data in the collection
	
2. SpringBoot (2.5.7)

    - Create SpringBoot project using Spring Initializer [In start.spring.io create project with maven, spring-web and spring data mongoDB dependencies]
    - Go to https://mvnrepository.com/ and get dependencies for swagger ui (SpringFox Swagger UI) and swagger 2 (SpringFox Swagger2)
    - In application properties add mongoDB connection details
    - to get mongoDB uri, go to the website and then navigate to overview and then click connect (Connecting with MongoDB Driver and select programming  language and version) replace <password> with password that was set before
    - if using swagger, enable swagger using the below code in the main application.java file above void main()
        ```java
        @EnableSwagger2
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2).select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                    .build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
        }

        @Bean
        public ApiInfo apiInfo() {
            final ApiInfoBuilder builder = new ApiInfoBuilder();
            return builder.build();
        }
        ```
    - Create a controller class and annotate with @RestController
    - if using swagger, when someone request for home page call swagger api, @ApiIgnore will ignore all the predefined APIs in swagger
        ```java
      @ApiIgnore
      @RequestMapping(value="/")
        public void redirect(HttpServletResponse response) throws IOException {
              response.sendRedirect("/swagger-ui.html");
        }
        ```
    - Create Pojo for the collection
    - to get all the job list, by making use of spring data, create an interface - repository that extends mongoRepository, this MongoRepository will take care of all the CRUD operations and no need of writing code in interface
      
3. MongoDB Compass
    - Acts as GUI, can connect to local or cloud
    - Download (https://www.mongodb.com/try/download/compass)
    - Go to MongoDB website where collections were created and connect with connect method as compass
    - Copy the uri it provides and paste it in compass and also mention database in uri (all these steps are performed as MongoDB is not setup in local)
    - MongoDB creates 2 replicas for every database know as replica set

4. MongoDB Atlas search
    From cloud
    - To perform Search operation use MongoDB Atlas search
    - Indexing - every field and text will be indexed to make search faster
    - Go to MongoDB website and click on search
    - Index fields -> dynamic, by default it will try to index everything, and will consume lot of memory and will take time for large DB
    - we can use static mapping to index specific text or field
    - Aggregation search,sort,limit
    - code can also be extracted from here based on the conditions specified for search (Used in SearchRepositoryImpl)

5. React
   - use npm start to start react app