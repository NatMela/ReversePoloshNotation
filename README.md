## ReversePoloshNotation
The description of the problem could be found here: https://en.wikipedia.org/wiki/Reverse_Polish_notation

The script takes 2 string arguments as an input: expression, written in postfix polish notation, and separator, which is used in this expression to divide "words".
```
For example: 
2 * 5 6 -
separator ' '

2,4,5,+,*
separator ','
```
In this implementation two additional operations are added (abs and max that could be written in lower or upper case).

Empty strings, which could appear after splitting by separator, are skipped without causing errors
