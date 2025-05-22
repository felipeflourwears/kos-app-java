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

### Git Bash
```bash
hp@DESKTOP-A6IQMQH MINGW64 ~
$ cd /c/Users/hp/Documents/Github/popa-rack-app

hp@DESKTOP-A6IQMQH MINGW64 ~/Documents/Github/popa-rack-app (master)
$ ./quickbuild.sh

```

### Logs
```bash
cd /mnt/datafs/logs/live
ls -lh
cat java_2025-05-22_682f4b4a
tail -f java_2025-05-22_682f4b4a


root@kos:/mnt/datafs/logs/live# ls -lh
total 212K
-rw-r--r-- 1 kos kos  34K May 22 16:43 bootlog_2025-05-22_682f4d2e
-rw-r--r-- 1 kos kos 106K May 22 16:44 java_2025-05-22_682f4b4a
-rw-r--r-- 1 kos kos  62K May 22 16:47 syslog_2025-05-22_682f5442
root@kos:/mnt/datafs/logs/live#
```

### Target 
This file can be used in KosStudio
```bash
popa-rack-app-1.0.0-SNAPSHOT.kab
```

#### Testing LED GPIO 17
```
echo 17 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio17/direction
echo 1 > /sys/class/gpio/gpio17/value  # Enciende LED
sleep 2
echo 0 > /sys/class/gpio/gpio17/value  # Apaga LED
echo 17 > /sys/class/gpio/unexport

```

### Kos Login Terminal
```bash
root@kos:/mnt/datafs/logs/live# sudo -u kos /bin/bash
kos@kos:/mnt/datafs/logs/live$ su -s /bin/bash kos
```