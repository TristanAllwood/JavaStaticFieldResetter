Note this is really not a good idea in practice, and it
ignore a lot of edge cases.

Build with:

> javac -sourcepath src -d bin src/blah/*.java
> javac -sourcepath test_cp/ -d test_cp/ test_cp/test/Test.java

Run with:

> java -cp bin blah.Main

Hopefully you'll get output:

Before Changes
[1, 2, 3]
lit
After Changes
[1, 2, 999]
extinguished
After resetting
[1, 2, 3]
lit
