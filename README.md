# popa-rack-app
Java application for popa rack

## Steps

### Verify Java and Maven
```bash
java -version
mvn -v
```

### Access to KOS Package
Create a file with a Github Credencials
```bash
C:\Users\hp\.m2\settings.xml
```

#### settings.xml
```bash
<servers>
  <server>
    <id>kosdevcode</id>
    <username>USERNAME</username>
    <password>YOUR_TOKEN</password>
  </server>
</servers>

```

### Run Install
```bash
cd /c/Users/hp/Documents/Github/popa-rack-app
./quickbuild.sh
```

### Target 
This file can be used in KosStudio
```bash
popa-rack-app-1.0.0-SNAPSHOT.kab
```