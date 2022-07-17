# General Introduction

Functional Programming is becoming increasingly important in the industry

Scala is one of the most popular programming languages for this purpose since:

1. It fuses functional and object-oriented programming
2. It's the basis for frameworks such as Apache Spark, Kafka and Akka
3. Integrates with Java and JavaScript

## Tool Setup

The Scala setup can be made through the instructions available in tool [coursier](https://get-coursier.io/docs/cli-installation.html#native-launcher)

## Cheat Sheet

There's a Cheat Sheet available, that goes through most important topics of Scala, that can be accessed [here](https://github.com/lampepfl/progfun-wiki/blob/gh-pages/CheatSheet.md)

## Working Hard to Keep it Simple (OSCON Java 2011)

- Huge volume workloads that require horizontal scaling
- PPP - Popular Parallel Programming challenge
- Two trends:
    1. Parallel programming - execute programs faster on parallel hardward
    2. Concurrent programming - manage concurrent execution threads explicitly

- The Root of The Problem: non-determinism caused by concurrent threads accessing shared mutable state  -> non-determinism = parallelism + mutable state. Ex:

```
var x = 0
async {x = x + 1}
async {x = x * 2}

//can give 0, 1 or 2
```

- The mutable state should be avoided to achieve deterministic processing, that is, to program functionally

- **Imperative programming**: thinks in a time spectrum, that is, which cells to run first in order to achieve the desired result
- **Functionally programming**: thinks in a space spectrum, that is, which tools to bring together to achieve to desired result

- Scala is a Unifier:
    - Agile
    - Object-oriented
    - Functional
    - Safe and performant

- Scala features classes and typed arguments

- Add `.par` to array for parallel programming


## Scala Style Guide

1. Avoid casts and type tests (ex: isInstanceOf or asInstanceOf)
2. Check for proper indentation
3. Make sure lines are not too long
4. Use local values to simplify complex expressions
5. Choose meaningful Names for Methods and Values
6. Avoid unnecessary invocations of computation-intensive methods
7. Don't copy-paste code
8. Don't use semicolons
9. Remove print statements after debugging
10. Avoid return statement in functions
11. Avoid mutable local Variables