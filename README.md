# UFCity Semantic

UFCity Semantic is a software component that integrates the UFCity solution for smart cities. This component belongs to Fog Computing and has the following characteristics:

* Semantic annotation of edge's data
* Manage semantic data on the Fuseki server.

## How to use?
Create a configuration file `ufcity-semantic.config`:
- ./
    - ufcity-semantic.jar
    - ufcity-semantic.config

Example of a `ufcity-semantic.config` file:
```
--fog-address: 172.23.0.4
--cloud-address: 172.23.0.5
--fog-port: 1883
--cloud-port: 1883
```

Note: Into the Docker environment can use the hostname instead IP.

#### Download UFCity Semantic
Download: [ufcity-fog-semantic-1.0-SNAPSHOT.jar](build%2Flibs%2Fufcity-fog-semantic-1.0-SNAPSHOT.jar)

#### Running the UFCity Semantic
`java -jar ufcity-fog-semantic-1.0-SNAPSHOT.jar`