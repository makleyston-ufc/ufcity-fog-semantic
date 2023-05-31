# UFCity Semantic

UFCity Semantic is a software component that integrates the UFCity solution for smart cities. This component belongs to Fog Computing and has the following characteristics:

* Semantic annotation of edge's data
* Manage semantic data on the Fuseki server.

## How to use?
Create a configuration file `ufcity-semantic.config`:
- ./
    - ufcity-semantic.jar
    - config.yaml

Example of a `config.yaml` file:
```
fog-computing:
  - address: 172.100.100.2
  - port: 1883
semantic:
 - address: 172.100.100.5
 - port: 3030
 - username: admin
 - password: admin
```

Note: Into the Docker environment can use the hostname instead IP.

#### Download UFCity Semantic
Download: [ufcity-fog-semantic-1.0-SNAPSHOT.jar](build%2Flibs%2Fufcity-fog-semantic-1.0-SNAPSHOT.jar)

#### Running the UFCity Semantic
`java -jar ufcity-fog-semantic-1.0-SNAPSHOT.jar`