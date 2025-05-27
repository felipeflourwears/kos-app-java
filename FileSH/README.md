## Create file to 
```bash
sudo nano /mnt/datafs/blink-gpio17.sh
```
### Run
```bash
sh /mnt/datafs/blink-gpio17.sh
./mnt/datafs/test/rgb.sh
```

### Raspberry PI
```bash
sudo LD_LIBRARY_PATH=../lib ./ledstriptool --gpio 18 --strip grb --count 30 --color ff0000
```

