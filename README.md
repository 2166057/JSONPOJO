# JSONPOJO

JSONPOJO is a Java library that generates Plain Old Java Objects (POJOs) from JSON strings.

## Table of Contents

- [JSONPOJO](#jsonpojo)
  - [Table of Contents](#table-of-contents)
  - [Installation](#installation)
    - [Requirements](#requirements)
    - [Maven](#maven)
    - [Manual](#manual)
  - [Usage](#usage)
  - [Contributing](#contributing)
  - [License](#license)

## Installation

### Requirements

To use JSONPOJO in your project, include the following dependency in your Maven `pom.xml` file:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>VERSION</version>
</dependency>
```

### Maven

Soon...

### Manual

#### Method 1

- Download the latest release of JSONPOJO from the github page.
- Include the .jar file as a library in your project
- Congrats you're done.

#### Method 2

- Clone or download this repo and add the src folder to your project.
- Congrats you can now use JSONPOJO.

## Usage

###Creating a parsable class

```java
public class Foo extends JSONPOJO<Foo> {
    public Foo() {
        super(Foo.class, true);
    }
}
```

###Convert POJO to JSON

```java
new Foo().toJsonString();
```

###Convert JSON to POJO

```java
//this is just a JSON String
String foojson = new Foo().toJsonString();

//now parsing the JSON String back into a new Object.
Foo foo = new Foo().fromJSON(foojson);
```

## Contributing

Create a pull request on the original repository and describe your changes.

## License

JSONPOJO is an open-source project feel free to use and modify.

