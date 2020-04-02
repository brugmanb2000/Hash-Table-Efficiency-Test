# Hash Table Linear/Double Hash Performence Test

This is a project for my Data Structures class where we built our Hash Tables and would run tests to determine the probe/duplicate count and overall efficiency between hashtables using Linear or Double Hashing open addressing methods. 

What the program is intended to do is to find the nearest twin primes between 95,500 - 96,000 and create a hashtable of that size. From there, depending on user input (which the usage will be shown below), it will create a Linear hashtable and Double Hash hashtable and determine the efficency of creating that size of hashtable. 

Both hashtables have full utilization. 

## Getting Started
Download the files (including word-count.txt if wanting to test the 

## User Input
After compilation, if using the command line, use "java HashTest" as your first verbage and the next three arguments will be below: 

- First Argument: "1" tests random integers, "2" tests current time on the computer, "3" tests the wordcount file
- Second Argument: Choose a load factor between 0 - 1. It expects a double so you can use any number in the middle and the hashtable will fill until that percentage has been met. 
- Third Argument (optional): Leaving blank or selecting "0" will output just the efficiency to the console/command line, but selecting 1 will also create a dump file for both Double Hash and Linear files so you can see how the tables were filled.


Ex. using "java HashTest 1 .5 1" in the command will fill the hashtables with random integers, with a load factor of .5, and with the debug value of 1, print efficiencies while also producing a dump file showing how the tables were filled. 

## Compilation
I used JavaSE 1.8 when creating this program.

## Author
Brandon Brugman
