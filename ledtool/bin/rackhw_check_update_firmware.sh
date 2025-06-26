#! /bin/sh

# update this with the version of the ST
TARGET_ST_VERSION="0.0.13"

# get the ST Version
CURRENT_ST_VERSION="$(rackhw-tool fwversion)"
STATUS=$?
echo "Current ST version: ${CURRENT_ST_VERSION}"

FIRMWARE_FILE="/usr/share/rackhw/rackfirmware-st.bin"

# # boot0 needs to be driven
# export 22 >  "/sys/class/gpio/export"
# echo "out" > "/sys/class/gpio/gpio22/direction"

if [ "$STATUS" != "0" ] || [ "${CURRENT_ST_VERSION}" != "${TARGET_ST_VERSION}" ]; then
    stm32flash -w "${FIRMWARE_FILE}" -v -i '19&22,,-19,,:-22&19,,-19' /dev/ttyAMA1
    PROGRAM_STATUS=$?

    if [ "$PROGRAM_STATUS" != "0" ]; then
       echo "ERROR Programming ST. Attempting again..."
       stm32flash -w "${FIRMWARE_FILE}" -v -i '19&22,,-19,,:-22&19,,-19' /dev/ttyAMA1
    fi

    FINAL_ST_VERSION="$(rackhw-tool fwversion)"
    echo "ST VERSION is now: ${FINAL_ST_VERSION}"
else 
    echo "Rack Firmware (ST) is up-to-date"
    exit 0
fi
