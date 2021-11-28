# Salesman - Warehouse Ordering System

This is a Maven project, including the implementation and testing part of our [mandatory project](https://dm571.lutzen.dk/projects/).

It includes a Java application (using the Maven build-automation tool) that provides several functionalites for salesmen, warehouse staff and manager side in a company's ordering system.

## Prerequisites

Requirements for the software and other tools to build, test and push 
- [Java (jdk-11.0.10)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/packages/)
- [Maven](https://maven.apache.org/download.cgi) (if running the project on the command line)

### How to build and run the project

1. Clone the repository to a local folder:

          git clone https://github.com/marlenachatzigrigoriou/ordering_system.git
2. Open the command line in your cloned reporitory
3. Build the executable Java application with: 
      
          mvn package jacoco:report
4. Browse ordering_system (your current) folder: ..\target\site\jacoco and click on index.html .
   
   Now you can see on your browser the jacoco code coverage report.
5. Run the executable by executing:

          java -jar ./target/mandatory_project-0.0.1-SNAPSHOT-jar-with-dependencies.jar
6. Interact with the system

#### Login
These are some usernames and passwords you can use:

(the underlined ones have already made some orders) 
  - Salesman:
      - lalu21 : swde7$8451RT4Hho
      - <ins>embo20 : 5d845dT85#YhiY</ins>
      - vasa21 : yy8Ae!5g6Top
      - <ins>pant23 : divf8T7!h569R</ins>
  - Warehouse staff:
      - <ins>jach23 : asw85S!k8O0w<ins>
      - <ins>fikre22 : c8vf5frRR47f!d8</ins>
      - nice19 : t7v8s5edfgvkT5G!
      - matj24 : u2bj#f88p85O5Pdx
   - Manager:
      - alch22 : c8Y6N4s!ik8hA


