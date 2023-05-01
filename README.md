# COMP430-GroupLang
Group compiler project for COMP 430 - CSUN Spring 2023.

Language name: "kwyjino"

Grammar:
```
variable is variable
Classname is an obj
String is a string ('a'..'z'|'A'..'Z')+ ;
INT is an int: '0'..'9'+ ;
type::=`string`|`int`| classname|`var`	Types are inferred with ‘VAR’; can be STRING or INT
op::=` + `| `- `| `/ `| `*`
exp ::= INT | String | `(` op exp exp `)`|`[`exp variable`]`|`new` classname `(` exp* `)`

STMT::= `Print` exp| type variable `=` exp| lhs `=` exp
classdef::=`obj` classname`{`(type variable)*`}`
lhs ::= variable | `[` lhs variable `]`
program::=[`!`] classdef* stmt*

! Converts privacy errors into warnings.
```

## How to Compile Compiler

The compiler can be compiled by

## How to Run Compiler

The compiler can be run by
