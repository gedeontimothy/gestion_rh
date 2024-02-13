#!/bin/bash
php -S 172.20.10.4:3001 ./index.php
# php -S 192.168.148.151:3001 ./index.php
# php -S 192.168.202.151:3001 ./index.php
# php -S 127.0.0.1:3001 ./index.php

read -p "Appuyez sur Entrée pour continuer..."
php -r "file_put_contents('./datas/desktop.php', '<?php return array ();');file_put_contents('./datas/device_mobile.php', '<?php return array ();');"
echo
echo
echo "------- SERVER FERME ET REINITIALISER --------"
echo
echo
read -p "Appuyez sur Entrée pour continuer..."