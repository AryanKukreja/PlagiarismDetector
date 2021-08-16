#!/bin/bash

################################################################
# Generate a New Algorithm
# - Used to generate a Java package, its code files, and test files for a new algorithm to implement

# Aryan Kukreja
# Plagiarism Detector Project
################################################################

# Main function
main () {
	# Check if the user entered a valid algorithm name (for Java class & package creation purposes)
	path="../java/com/abusyprogrammer/backend/algorithms/${1^}"
	test_path="../../test/java/com/abusyprogrammer/backend/algorithms"
	check_valid_name $1
	echo ""

	# Create the directory in the appropriate place
	create_dir $1
	echo ""

	# Create the Controller, Service and Input Java programs in the file
	algorithm=${1^}
	create_file "Controller"
	create_file "Service"
	create_file "Input"
	echo ""

	# Create the test file for this algorithm
	create_test

	echo ""
	echo ""
	echo -e "\e[32mSuccess! New package has been created at $path."

	echo -e "\e[33m> Reminder: Be sure to complete all the TODOs when implementing your algorithm. Reference existing implementations as a guide."
	echo -e "\e[33m> Reminder: Be sure to push testing for your code. Coverage will be tracked."
	echo -e "\e[33m> Reminder: Don't forget to comment your code."
}

# Check if the user entered a valid name
check_valid_name () {
	# If it contains non-alphanumeric characters, then exit with error
	if [[ "$1" =~ [^a-zA-Z0-9] ]]
	then
  	echo -e "\e[31mError: Invalid package name - must be alphanumeric only \"$1\""
		exit -1
	
	# Else if it starts with a number, exit with error also
	elif [[ "${1:0:1}" =~ [0-9] ]]
	then
		echo -e "\e[31mError: Invalid package name - cannot start with a number \"$1\""
		exit -1
	else
		echo -e "\e[34m> Valid algorithm name was provided"
	fi
}

# Creates a Java package for the new file
create_dir () {
	# If the folder already exists, then ask the user if they want to overwrite the folder
	if [ -d $path ] 
	then
			echo "Directory $path already exists. Do you want to reset its contents? (Y/N)" 
			read response

			if [ $response == "Y" ] || [ $response == "y" ]
			then
				mkdir -p $path
				echo -e "\e[34m> Directory created. Older content deleted"
			elif [ $response == "N" ] || [ $response == "n" ]
			then
				echo -e "\e[34m> Operation cancelled"
				exit 0
			else
				echo -e "\e[31mError: Unknown operation \"$response\""
				exit -1
			fi
	
	# Else, create the directory
	else
			mkdir -p $path
			echo -e "\e[34m> New directory created"
	fi
}

# Creates the files for the new algorithm
create_file () {
	# Identify parameters
	type=$1
	new_file="$path/$algorithm$type.java"

	# Copy the template file into the new directory
	cp "./new-algorithm-templates/Temp$type.txt" $new_file
	echo -e "\e[34m> New file \"$new_file\" created"

	temp=${algorithm,}

	# Replace all instances of "Temp" with the algorithm name
	sed -i "s/api\/Temp/api\/$temp/g" $new_file
	sed -i "s/Temp/${algorithm}/g" $new_file
	echo -e "\e[34m> Content of new file \"$new_file\" updated"
}

# Creates the file for testing the new Algorithm
create_test () {
	# Define new file location
	new_test_file="$test_path/${algorithm}ServiceTests.java"

	# Copy the template file into the new directory
	cp "./new-algorithm-templates/TempServiceTests.txt" $new_test_file
	echo -e "\e[34m> Test file \"$new_file\" cpdated"

	# Replace all instances of "Temp" with the algorithm name
	sed -i "s/Temp/${algorithm}/g" $new_test_file
	echo -e "\e[34m> Content of test file \"$new_file\" updated"
}

# Call main()
main $1
