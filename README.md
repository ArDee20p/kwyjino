# kwyjino
Group compiler project for COMP 430 - CSUN Spring 2023.

Grammar:
```
variable is variable
Classname is an obj
String is a string ('a'..'z'|'A'..'Z')+ ;
INT is an int: '0'..'9'+ ;
! Converts privacy errors into warnings.
type::=`string`|`int`| classname|`var`	Types are inferred with ‘VAR’; can be STRING or INT
op::=` + `| `- `| `/ `| `*`
exp ::= INT | String | `(` op exp exp `)`|`new` classname `(` exp* `)``[` variable variable `]`
vardec ::= type variable
STMT::= `Print` exp| type variable `=` exp|   vardec `=` exp
classdef ::= `obj` classname `{` vardec* `}`
program::=[`!`] classdef* stmt*

```
## How to Compile Compiler:
 Open the project in eclipse and select to build the project. Make sure the desired starter class is selected. Options are start, lextest, or parsetest
 
## How to Run Compiler: Open the project in eclipse, place code folder with desired code in the directory eclipse checks for same folder, select run. Test files may also be used instead. Make sure the desired starter class is selected. Options are start, lextest, or parsetest
## How to Compile Compiler
