filepath='/sdcard/Download/timestamp.txt'
now=$(date +"%T")

echo "Current time : ${now}" >"$filepath"
