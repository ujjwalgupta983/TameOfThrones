# TameOfThrones
GeekTrust's Object Modelling Problem that determines the problem solving skills of programmer as well as the clean coding.

# Description

Initialized a gradle project using Spring initializer and install dependencies like jUnit, lombok.

## TameOfThrones.java

It is the main class of the application.

## dto

Contains KingdomDto which is used to provide domain to each object.

## models

Contains models for Kingdoms and Ruler

## services

IRulerService is an interface that contains useful properties getSubjects for Ruler. It can extended as per their requirement.
RulerService implements RulerService interface.

## utils

It contains utilities for the application like:
1. CaesarCipher class is reponsible to decrypt message of each kingdom
2. FileParser class is responsible to parse input and kingdom details
3. Mapper class maps input details to kingdomDto and maps Ruler with their emblem.

## globals

It holds the global constant variables that can be used by any class.

## resources

It contains resources such as input files under inputs directory, output files under outputs directory and other important assets under fixtures.

## Tests

The tests are present under test directory.

1. services directory for validating RulerService using RulerServiceTest
2. utils directory for validating all utilities tests/
3. Integration Test i.e, TameOfThronesTest

# Run Command

java -jar geektrust.jar <absolute_path_to_input_file>

Note: geektrust.jar is located at root. The KingdomDetails.txt file is located under src/main/resources/fixtures/