# TDD-Scala

## Description

Project for Scala and TDD.  The following 
class goes through each of the following topics.

## Outline

### Day 1
* Test Driven Development
* Setting up a Scala Project
  * Different Types of Tests
  * Using Matchers
  * Running Full Suites
  * Running Specific Tests
  * Code Coverage with SCoverage
  * _Lab: Setting up a project_
* Test Driven Development Example
  * Thinking in TDD
  * Refactoring
  * _Lab: Caesar Shift_
* Property Based Testing with ScalaCheck
  * Basic Principles
  * Strategies for Property Testing
  * Code Smells when using Property Tests
  * `Arbitrary`/`Gen`
  * Shrinking
  * _Lab: Caesar Shift Property Testing_

### Day 2

* Using Mocks
  * Scala Mock
  * When to use Mocks
  * When to use Functions instead
  * _Lab: Scala Mock_
* Testing Strategies
  * How to design in Scala using `object` factories
  * Designing for IO
  * Designing for Dates
  * _Lab: Determine the Age of an Object_
* Testing Legacy Code
  * Extract `interface`/`trait`
  * Parameterized Method
  * Parameterized Constructor
  * Sprout Method
  * Sprout Class
  * Wrap Class
  * Wrap Method
  * Introduce Instance Delegator
  * Passing `null`
  * Subclass and Override
  * Sensing Variables
* Transmogrify Testing 
  * Issues with real world data
  * How to obtain what went wrong?
  * Making Unit Tests from Known Production Issues
  * Creating `Abrbitrary`/`Gen` cases for our product
  * _Lab: Turn production issues into Unit Tests_
  