#!/bin/bash

################################################################
# Generate a New Algorithm
# - Used to generate a Java package, its code files, and test files for a new algorithm to implement

# Aryan Kukreja
# Plagiarism Detector Project
################################################################

# Main function
main () {
	# Prepare for installations
	pre_setup

	# Installations
	java_setup
	tomcat_setup

	# Post-processing
	post_process
}

pre_setup () {
	# Check if script is running with elevated privileges
	if [[ $EUID -eq 0 ]]
	then
    echo -e "\e[34m> Script is running with elevated privileges"
	else
		echo -e "\e[31m> Error: Script has to run with elevate privileges. Run the script with \"sudo\"";
		exit -1
	fi
}

java_setup () {
	# Update package information
	apt update

	# Attempt to install Java 
	apt install openjdk-11-jdk;

	# Check if the installation directory exists
	if [ -d "/usr/lib/jvm/java-11-openjdk-amd64/" ]
	then
		echo -e "\e[34m> Java is installed"
	else
		echo -e "\e[31m> Error: Java installation failed. Try to run installation for \"openjdk-11-jdk\" again";
		exit -1
	fi

	# Set $JAVA_HOME variable and re-source .bashrc 
	echo "export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/" >> ~/.bashrc 
	source ~/.bashrc

	if [[ -z "${JAVA_HOME}" ]]
	then
		echo -e "\e[34m> \$JAVA_HOME has been set"
	else
		echo -e "\e[31m> Error: Unable to set \$JAVA_HOME. Please attempt to set JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/";
		exit -1;
	fi
}

tomcat_setup () {
	# Install wget
	apt install wget

	# Check if maven was successfully installed
	command_exists "wget";

	# Get Tomcat V9
	## First clone from remote Tomcat server
	echo -e "\e[34m> Attempting to get Tomcat V9.0"
	wget_output=$(wget -P /tmp https://downloads.apache.org/tomcat/tomcat-9/v9.0.52/bin/apache-tomcat-9.0.52.tar.gz)
	if [ $? -ne 0 ]
	then
		echo -e "\e[31m> Error: unable to wget Tomcat 9.0. Manually download and extract from: \"https://downloads.apache.org/tomcat/tomcat-9/v9.0.52/bin/apache-tomcat-9.0.52.tar.gz\"";
		exit -1
	else
		echo -e "\e[34m>> 1. Tomcat 9.0 retrieved"
	fi

	# Then extract to correct location
	mkdir /usr/lib/apache-tomcat-9.0.52
	tar_output=$(tar -xvzf /tmp/apache-tomcat-9.0.52.tar.gz -C /usr/lib/apache-tomcat-9.0.52)
	if [ $? -ne 0 ]
	then
		echo -e "\e[31m> Error: unable to extract Tomcat 9.0. Manually attempt to extract.";
		exit -1
	else
		echo -e "\e[34m>> 2. Tomcat 9.0 extracted and saved to \"/usr/lib/apache-tomcat-9.0.52\"."
	fi
}

post_process () {
	# Remove the temp Tomcat 9 tar.gz file
	rm -rf /tmp/apache-tomcat-9.0.52

	# Move into the root of the project
	cd ../../..

	# Attempt to run a clean install
	chmod +x ./mvnw
	mvn_install_output=$(./mvnw clean install)
	if [ $? -ne 0 ]
	then
		echo -e "\e[31m> Error: unable to install devdependencies for project. Check if \"mvnw\" file exists, and works as expected.";
		exit -1
	fi

	echo -e "\e[32m> Success! Your project is ready to run.";
}

# Utility function to check if program exists
# Source: https://raymii.org/s/snippets/Bash_Bits_Check_if_command_is_available.html
command_exists() {
    # check if command exists and fail otherwise
    command -v "$1" >/dev/null 2>&1
    if [[ $? -ne 0 ]]
		then
        echo -e "e[31m> Error: Installation for $1 failed. Attempt to install manually."
        exit -1
		else
			exists=0
    fi
}

main