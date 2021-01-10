# CapitalCitiesTrivia

A command line capital cities trivia game built entirely in Java.

Users have access to a variety of modes based on command parameters including the option of guessing the country when given the capital or the capital when given the country.

This program uses the REST Countries API: https://restcountries.eu/

## Building and Running

### Build Commands (Java required)

Simply compile and run this project on the command line from the src directory with the following commands:
* javac com/capitalCitiesTrivia/Main.java
* java com/capitalCitiesTrivia/Main [parameters]

Replace [parameters] with up to two numbers:
* Example: java com/capitalCitiesTrivia/Main 1 10

### Parameter Options

* The first number is required and selects one of 3 modes.
* The second number is optional and specifies a maximum number of questions.
* java com/capitalCitiesTrivia/Main number1 number2
  * number1 = 1: Infinite questions mode guessing the country from it's capital
  * number1 = 2: Infinite questions mode guessing the capital of a given country
  * number1 = 3: Challenge mode guessing the country from it's capital
  * number1 = 4: Challenge mode guessing the capital of a given country
  * number1 = 5: Multiple choice mode guessing the correct country from it's capital with 4 options
