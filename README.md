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

## Ledtool

### Structure Basic with permissions
```bash
root@kos:/mnt/datafs/ledtool# ls -la
total 36
drwxr-xr-x  5 kos  kos  4096 Jun 26 04:47 .
drwxr-xr-x 12 root root 4096 Apr 28  2022 ..
drwxr-xr-x  2 kos  kos  4096 Jan  1  1970 bin
-rwxr-xr-x  1 kos  kos   344 Jun 26 05:10 blue.sh
-rwxr-xr-x  1 kos  kos   346 Jun 26 05:04 green.sh
drwxr-xr-x  2 kos  kos  4096 Jan  1  1970 lib
-rwxr-xr-x  1 kos  kos   344 Jun 26 05:10 red.sh
-rwxr-xr-x  1 kos  kos   572 Jun 26 03:58 rgb.sh
drwxr-xr-x  3 kos  kos  4096 Jan  1  1970 share
root@kos:/mnt/datafs/ledtool#


root@kos:/mnt/datafs/ledtool# ls -la bin
total 11520
drwxr-xr-x 2 kos  kos      4096 Jan  1  1970 .
drwxr-xr-x 5 kos  kos      4096 Jun 26 04:47 ..
-rwsr-xr-x 1 root root    19808 Jan  1  1970 ledstriptool
-rwxr-xr-x 1 kos  kos  11437061 Jan  1  1970 rackhw-tool
-rwxr-xr-x 1 kos  kos       930 Jan  1  1970 rackhw_check_update_firmware.sh
-rwxr-xr-x 1 kos  kos    320576 Jan  1  1970 stm32flash
root@kos:/mnt/datafs/ledtool#

root@kos:/mnt/datafs/ledtool# ls -la lib
total 84
drwxr-xr-x 2 kos kos  4096 Jan  1  1970 .
drwxr-xr-x 5 kos kos  4096 Jun 26 04:47 ..
-rw-r--r-- 1 kos kos 76680 Jan  1  1970 libws2811.so
root@kos:/mnt/datafs/ledtool#

root@kos:/mnt/datafs/ledtool# ls -la share
total 12
drwxr-xr-x 3 kos kos 4096 Jan  1  1970 .
drwxr-xr-x 5 kos kos 4096 Jun 26 04:47 ..
drwxr-xr-x 2 kos kos 4096 Jan  1  1970 rackhw
root@kos:/mnt/datafs/ledtool#
```
#### Optional and testing
```bash
cd /
cd /dev
sudo chmod 666 /dev/mem
sudo chmod 666 /dev/vcio
```

### Path required
```bash
cd /mnt/datafs
mkdir ledtool
cd /mnt/datafs/ledtool
```
Or move all the folder

### Testing LedTool
```bash
su -s /bin/bash kos
cd /mnt/datafs/ledtool
./rgb.sh
```


