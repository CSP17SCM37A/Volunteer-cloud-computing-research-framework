#!/bin/bash
NEW_UUID=chiru
adduser --quiet --disabled-password --shell /bin/bash --home /home/$NEW_UUID --force-badname --gecos "User" $NEW_UUID
echo "$NEW_UUID"
echo "$NEW_UUID:hello" | chpasswd
