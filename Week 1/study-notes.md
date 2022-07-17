# Week 1 - Functions & Evaluation

## Programming Paradigms

- True statements about functional programming (Quizz):
    1. Focuses on functions and immutable data
    2. Helps with the complexity of concurrency and parallelism

## Elements of Programming

Non-trivial programming languages provides:
1. primitive expressions (simple elements) (ex: `x = 2`)
2. ways to combine expressions (ex: x = `2*y`)
3. ways to abstract expressions (ex: `def sum()`)

Functional programming ~ calculator -> REPL (Read-Eval-Print-Loop) to evaluate expressions -> for Scala type `scala`

Evaluation - for non-primitive expressions:
    1. Take the leftmost operator
    2. Evaluate the operands (left to right)
    3. Apply the operator to operands

Named operators are replaced by its definition (ex: variable size will have its defined value)

The evaluation stops once it results in a value (ex: number)

```
val x = 2
val y = 3

3*x + y -> 3*2 + y -> 6 + y -> 6 + 3 -> 9
```

Functions can take parameters, for example:

```
def square(x: Double) = x*x

def sumOfSquares(x: Double, y: Double): Double = square(x) + square(y)
```

Functions can have a type (:Type after arguments). Types are Primitive Types (like Java) that are capitalized

Evaluation of Parameterized Functions:

 1. Evaluate arguments, from left to right
 2. Replace the function application by its definition (right-hand side)
 3. Replace the parameters by the actual arguments

For example:

```
sumOfSquares(3, 2+2)
sumOfSquares(3, 4)
square(3) + square(4)
3*3 + square(4)
9 + square(4)
9 + 4*4
9 + 16
25
```

This form of calculating is called Substitution model (lambda-calculus [Alonzo Church]) - evaluation focuses on reducing an expression to a value -> expressions must not have side-effects (each iteration is different) and this model is really powerful

Does every expression reduce to a value (in a finite # of steps)? No! Loop expressions won't end in a value
``` 
def loop: Int = loop
```

We can change the evaluation strategy, that is, instead of reducing all arguments to its value before the function, we can simplify the expression (unreduced arguments) and then substitute the argument values. Example:

```
sumOfSquares(x,y)
square(x) + square(y)
x*x + y*y
9 + 16
25
```

Evaluation Strategies:

1. Call-by-name: evaluates the expression using unreduced arguments. Only arguments that are used to calculate the final expression are evaluated

2. Call-by-value: evaluates the expression using reduced arguments. Evaluate each argument only once

They will both result in the same value as long as: reduced expressions consists of pure functions and the evaluation terminates

## Evaluation Strategies and Termination

The CBN and CBV are evaluation techniques to reduce an expression to a value, as long as it terminates. However, they are not equivalent and can lead to different results if the termination isn't guaranteed

If a CBV evaluation terminates then CBN terminates -> however the opposite isn't true. For example:

```
def first(x: Int, y: Int) = x
first(x, loop)
CBN -> x
CBV -> loop
```

Scala uses CBV:
1. CBV can avoid numerous extra reduction steps
2. CBV is much more predictable

You can use => to explicitly use CBN

```
def constOne(x:Int, y:=> Int) = 1
```

In Scala:
- val: evaluates the expressions immediately
- def: evaluates the expressions when needed

## Conditionals and Value Definitions

### Conditionals

Scala has `if-then-else` for choosing between two alternatives -> used for expressions not statements

```
def abs(x:Int) = if x>=0[predicate of type boolean] then x else -x
```

In Scala: ``` true false, !b for negation, b && b for conjunction and b || b for disjunction ```

Short-circuit evaluation: not always the right operand needs to be evaluated

|Expression|Result|
|:--:|:--:|
| !true  | false|
| !false  | true|
| true && e | true|
| false && e | false|
|true ll e | true|
|false ll e | e|

So for `if-then-else` statements:
```
if true then e1 else e2 -> e1
if false then e1 else e2 -> e2
```

### Value Definitions

The def form is equivalent for CBN, it's evaluated on each use

The val form is equivalent for CBV, it's evaluated at the point of the definition

If an expression doesn't terminate (ex: loop): 
```
def x = loop -> OK
val x = loop -> NOT OK
```

#### Exercise - Write and and or functions without && and ||
```
def and(x: Boolean, y: Boolean): Boolean =
    if x then y else false

def or(x: Boolean, y: Boolean): Boolean =
    if x then x else if y then y else false
```

### Example: Square roots with Newton's method

One way to create a function that calculates the square root of a number is to use successive approximations based on the Newton's method:

1. Start with a guess `y` to calculate `sqrt(x)`
2. Improve y by assuming a new guess `y' = (y + x/y)/2`

To implement this, we can think of:

```
def improve(guess: Double, x: Double) = 
    (guess + x/guess)/2

def isGoodEnough(guess: Double, x: Double): Boolean = 
    abs(guess*guess-x)/x < 0.0001

def sqrtIter(guess: Double, x: Double): Double = 
    if isGoodEnough(guess, x) then guess
        else sqrtIter(improve(guess,x), x)
```

The function `sqrtIter` is recursive, since it calls itself in its definition. The type returned by the function is mandatory for recursive functions and optional for non-recursive ones.

### Exercise

1. The isGoodEnough test is not very precise for small numbers and can lead to non-termination for very large numbers. Explain why:
> The test can be imprecise for very small numbers (ex: 1E-10) since it can lead to a smaller difference than 0.001. Additionally, if we're evaluating big numbers such as 12E31, the difference will be way higher than 0.001 and leading to the code never converging.

2. Design a different version of isGoodEnough that does not have these problems

> We can overcome the previous test limitations by using a relative percentage difference instead of an absolute one. That can be achieved by dividing the difference of guess*guess - x by x.

```
def isGoodEnough(guess: Double, x: Double): Boolean = 
    abs((guess*guess - x)/x) < 0.0001
```

3. Test with some very very small and large numbers:

- 0.001 -> 0.0316227
- 0.1e-20 -> 3.16333E-11
- 1.0e20 -> 3.1623E9
- 1.0e50 -> 3.1623E24