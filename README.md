This project demonstrates a sample restful web service with advanced search features using Spring Data JPA specification and criteria API.

TOC
---
- [0 Introduction](#0-introduction) <br/>
- [1 Setup and running](#1-setup-and-running) <br/>
- [2 Basic CRUD Operations](#2-basic-crud-operations) <br/>
- [3 Advanced Search](#3-advanced-search) <br/>
- [4 Known Bugs and Future Notes For Improvement](#4-known-bugs-and-future-notes-for-improvement)<br/>

 0 Introduction
---------------
The aim of this project is to provide a sample RESTful web service with advanced search features, with a capability of filtering the content with the criterias defined by the user.

The tech stack is as follows;

- Spring Boot
- Spring Data JPA
- Swagger
- Postman
- PostgreSQL
- Flyway
- Docker 

RESTful service is a receipe service, where users can apply CRUD operations to their favourite receipes. In addition, users can search the content with the filters. 


[TOC](#toc)


 1 Setup And Running
--------------------
To run the project;

1. PostgreSQL must be available. You can do so by running the [docker-compose file](https://github.com/bzdgn/receipe-service/blob/main/scripts/docker-compose.yml) in the scripts folder, with ```docker compose up``` command.
2. In eclipse or your choice of IDE, you can just run the [ReceipeApp](https://github.com/bzdgn/receipe-service/blob/main/src/main/java/io/github/bzdgn/receipe/ReceipeApp.java) file which is the entry point of the Spring Boot application.

After you run, the Swagger UI will be available on [localhost:8080/swagger-ui](http://localhost:8080/swagger-ui) as follows;

![swagger_screenshot](https://raw.githubusercontent.com/bzdgn/receipe-service/main/misc/01_swagger.PNG)

Via the Swagger UI, you can see the summary of the endpoints, operations and the models used.

To test the API, you can import the [Postman collection](https://raw.githubusercontent.com/bzdgn/receipe-service/main/misc/Receipe-Service.postman_collection.json) under the misc folder;

![swagger_screenshot](https://raw.githubusercontent.com/bzdgn/receipe-service/main/misc/02_postman.PNG)

If you want to play with database after running docker, you can connect to the database via [PGAdmin](https://www.pgadmin.org/) with the credentials used in the [docker compose file](https://github.com/bzdgn/receipe-service/blob/main/scripts/docker-compose.yml);

| variable  |    value    |
| ----------|-------------|
| host      | localhost   |
| port      | 5432        |
| username  | postgres    |
| password  | receipepass |

Then you can see the database entries in the **Receipe** table;

![swagger_screenshot](https://raw.githubusercontent.com/bzdgn/receipe-service/main/misc/03_postges.PNG)


[TOC](#toc)


 2 Basic CRUD Operations
------------------------
Basic CRUD operations for the API is very straight-forward. It has 4 fields as follows;

```json
{
  "ingredients": "potatoe, tomatoe",
  "instructions": "Cut potatoes into slice, add tomatoes, bake it in the oven for 2 hours.",
  "name": "Mediterrean Potatoe",
  "serving": 3,
  "vegan": false
}
```

In the retrieval operations, the id is also provided as a field.

Important to remember that delete is by **name** as a path parameter for the simplicity, and modifiying the existing receipe is done via the **id** as a path parameter again.


[TOC](#toc)


 3 Advanced Search
------------------
The most important part of this demo app is advanced search, so that the users can search the receipes with the criterias. Let's say, you have several ingredients included or excluded, and also want to make a text search in the instructions, while you want the servings smaller than a certain number and also want to have the subset of the vegan receipes. Any number of criterias can be chained. This service is ready on `/search` endpoint with a POST method;

Example is as follows;

```json
{
    "searchCriteriaList":[
        {
            "filterKey":"serving",
            "operation":"<=",
            "value":2
        },
        {
            "filterKey":"isVegan",
            "operation":"==",
            "value":false
        },
        {
            "filterKey":"ingredients",
            "operation":"contains",
            "value":"meat"
        },
        {
            "filterKey":"instructions",
            "operation":"contains",
            "value":"on the plat"
        }
    ]
}
```

If you do this search in the ```search``` endpoint, you will receive the chain filtered results;

```json
[
    {
        "id": 1,
        "name": "Hamburger",
        "serving": 1,
        "ingredients": "Bread, Meat, Onions",
        "instructions": "Cook meat on the plate for 5 minutes. Cut onions. Combine the cooked meat and onions in between the breads",
        "vegan": false
    },
    {
        "id": 2,
        "name": "Cheeseburger",
        "serving": 1,
        "ingredients": "Bread, Meat, Onions, Cheese",
        "instructions": "Cook meat on the plate for 5 minutes. Cut onions. Combine the cooked meat and onions and the slice of cheese in between the breads",
        "vegan": false
    }
]
```

The search operations are defined in the [SearchOperation](https://github.com/bzdgn/receipe-service/blob/main/src/main/java/io/github/bzdgn/receipe/search/SearchOperation.java) class. The search operations you can apply to filterKeys are as follows;

```
contains
not_contains
==
!=
> 
>=
< 
<=
```

Any filterKey is one of the fields of the Receipe model, which are;

| field         | data type |
| --------------|-----------|
| name          | String    |
| serving       | Integer   |
| ingredients   | String    |
| instructions  | String    |
| isVegan       | boolean   |

So you provide a list of search criteria as a list of the following objects;

```json
{
    "filterKey":"<name_of_the_field_here>",
    "operation":"<operation_literal>",
    "value":<value_here>
}
```

It is important to mention that these criterias are a chain of AND operation, which is defined in the [ReceipeSpecificationBuilder class](https://github.com/bzdgn/receipe-service/blob/1efa63d9a198ccaa11c37525da5dc581a27437b7/src/main/java/io/github/bzdgn/receipe/search/ReceipeSpecificationBuilder.java#L36) line number 35. Here is the snippet of the build method;

```java
    public Specification<Receipe> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<Receipe> result = new ReceipeSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result = Specification.where(result).and(new ReceipeSpecification(criteria));
        }

        return result;
    }
```

In the following line, **and** is used;

```java
result = Specification.where(result).and(new ReceipeSpecification(criteria));
```


[TOC](#toc)


 4 Known Bugs and Future Notes For Improvement
----------------------------------------------
Known bugs;

- Because flyway initializes the database, and the auto-incremented id is behind the actual. So you need to trigger posting a new Receipe 3 times.

Several improvements can be applied;

- For a better performance, in addition to database, [apache solr](https://solr.apache.org/) is a good choice so that huge data can be indexed and database should only be used for storing the actual data that needs to be persisted.
- [OpenAPI](https://www.openapis.org/) specs can be used. RESTful apis can be defined with YAML files. Has good and bad parts. Better documented APIs can be written with OpenAPI but the downside is that the setup of the OpenAPI is a challenge.
- Better Testing: Unit tests and end to end integration tests using [Mock Server](https://www.mock-server.com/) and [Cucumber](https://cucumber.io/)


[TOC](#toc)

