#!/bin/bash

current_dir="${0%/*}"
parent_dir="${current_dir%/*}"
destination_directory="$parent_dir//app//src//main//res//raw//"

# Check if the destination directory exists
if [[ ! -d "${destination_directory}" ]]; then
    echo "Directory '${destination_directory}' does not exist => Creating it"
    mkdir -p "$destination_directory"
fi
# List of tones to match
tones=("tone1" "tone2" "tone3" "tone4")

# Loop through the tone list and copy/rename files
while read -r line; do
    # Extract the tone from the line (assuming filenames don't contain spaces)
    tone=$(echo "$line" | awk -F'_' '{print $2}')

    # Check if the tone matches one of the tones in the list
    if [[ " ${tones[@]} " =~ " $tone " ]]; then
        # Extract the filename from the line
        filename=$(echo "$line" | awk -F'/' '{print $NF}')

        # Copy the corresponding tone file to the destination directory with the new name
        cp "${current_dir}/$tone" "$destination_directory/$filename"
        echo "Copied '$tone' to '$destination_directory/$filename'"
    else
        echo "No matching tone found for '$line'"
    fi
done < ${current_dir}/tone_list.txt