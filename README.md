# rpn-calculator

A simple RPN calculator implemented in Scala 3. It uses type
classes to implement available operations. Example usage is
presented in `example` package.

## Syntax
The calculator has custom syntax and can be used with dots
and postfix operators.

Examples below present various ways to define `1 2 3 + - 3 / 10 * 3 % -`.

### Dot
```scala
val dotSyntax: Int = <>.~(1).~(2).~(3).+.-.~(3)./.~(10).*.~(3).%.-
```

### Postfix
```scala
val postfixSyntax: Int = (((((<> ~ 1 ~ 2 ~ 3 +) -) ~ 3 /) ~ 10 *) ~ 3 %) -
```

### Mixed
```scala
val mixedSyntax: Int = (<> ~ 1 ~ 2 ~ 3 +).-.~(3)./.~(10).*.~(3).%.-
```
