# TameOfThrones
GeekTrust's Object Modelling Problem that determines the problem solving skills of programmer as well as the clean coding.

# Description

Initialized a gradle project using Spring initializer then install dependency like jUnit, lombok.
The application is fully extensible and loosely coupled.

## GeekTrust.java

It is the main java class that runs the application

## dto

Contains KingdomDTO which is used to provide domain to each object.

## models

Contains model for Kingdom it include attributes like KingdomName, emblem, Message, isRuler.

## repositories

KingdomRepo is an interface that loosely couples the application. It contains property to getKingdomDetails.
KingdomRepoImpl implements KingdomRepo to use this properties.

## services

RulerService is also an interface that contains useful properties like get decrypted message, get Subjects. It can extended as per their requirement.
RulerServiceImpl implements RulerService interface.

## utils

It contains utilities for the application like:
1. CaesarCipher class is reponsible to decrypt message of each kingdom
2. InputParser class is responsible to parse input
3. Output class is responsible to print output
4. ParseKingdomDetails class is responsible to parse details for each kingdom which is available under KingdomDetails.txt. It can be changed as per their use

## resources

it contains resources such as input files under inputs directory, output files under outputs directory and other important assets under fixtures.

## Tests

The tests are present under test directory.

# Run Command

java -jar geektrust.jar <absolute_path_to_input_file>

Note: geektrust.jar is located at root. The KingdomDetails.txt file is located under src/main/resources/fixtures/