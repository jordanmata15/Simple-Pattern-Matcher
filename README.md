# Simple-Pattern-Matcher

A rudimentary regular expression matcher. Uses chain of responsibility to delegate the responsibility of a match/no match to a dynamically created chain of object matchers.

### Supported characters
- Star wildcard (*) - 0 or more of any characters.
- Dot wildcard (.) - A single instance of any character.
- All other characters are treated as ordinary ASCII characters.

# Purpose

The goal of this project was to understand the appropriate use of the chain of responsibility design pattern by creating a rudimentary regular expression matcher. This project provided a real-world use case of advanced object oriented design patterns.

# Requirements

Developed on:
- JRE17
- JUnit5
