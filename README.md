## Alfresco Quick Rest API Example
This is a very basic example of how to start developing a Rest API on the Alfresco Platform that conforms to the api standards.

It uses the [Alfresco SDK](https://github.com/Alfresco/alfresco-sdk).

#### Step by step instructions of everything I did to create this project
* Typed : `mvn archetype:generate -DarchetypeGroupId=org.alfresco.maven.archetype -DarchetypeArtifactId=alfresco-platform-jar-archetype -DarchetypeVersion=3.0.0 -DinteractiveMode=false -DgroupId=org.alfresco.demo -DartifactId=quick-api`
* Git init and added exclusions
* Imported as a Maven Project into Intellij
* Deleted unused files, see [commit](https://github.com/covolution/quick-api/commit/db629a9bda291d5d95ef2c1161081fc4946555ca)
* Updated the pom.xml, Switched to Java 8 and added remote-api as a dependency, see [commit](https://github.com/covolution/quick-api/commit/eb1cc3c72d9fe29a0bce7dca049ea02093c02ea5)
* Added an endpoint for GET `/alfresco/api/-default-/private/mycompany/versions/1/categories`, see [commit](https://github.com/covolution/quick-api/commit/3a9a8c8a6fec61e14c8dc1f033cfff09a0562bc6)
* Added a `categories/[categoryId]/children` endpoint, see [commit](https://github.com/covolution/quick-api/commit/6331607318023607d20786194718fc66b79790b7)

* Added CREATE using POST endpoints for a root category and children, see [commit](https://github.com/covolution/quick-api/commit/b85cdf9c3d395fbbd7f25dab6985612031b4f16a)
* Added category DELETE endpoint, see [commit](https://github.com/covolution/quick-api/commit/4c2b868d1589c18081496e80507c9a82d771c1e3)
* Ran `mvn clean install -DskipTests=true alfresco:run`
* In a browser : [/alfresco/api/-default-/private/mycompany/versions/1/categories](http://localhost:8080/alfresco/api/-default-/private/mycompany/versions/1/categories)  **Alfresco is up and running :)**

### License
Copyright (C) 2017 Alfresco Software Limited

This file is part of an unsupported extension to Alfresco.

Alfresco Software Limited licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
