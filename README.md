# Size-of-Social-Network

Problem
The task is to count the size of the social network of the word LISTY in the dictionary provided.

We define two words as being friends if the edit distance between them is 1. For this problem, we will be using Levenshtein distance (http://en.wikipedia.org/wiki/Levenshtein_distance) as our edit distance.

The size of a word's social network is equal to 1 (for the word itself), plus the number of words who are friends with it, plus the number of friends each of its friends has, and so on. A word is in its own social network, so if our dictionary is simply [HI] then the size of the social network for HI is 1.

Example
With dictionary [HI HERE THERE HER HE SHE HEAR HALLOW] The size of the social network for HI is 7. We calculate this as follows:

HI is friends with HE, because they are edit distance 1 apart.

HE is friends with SHE and HER.

HER is friends with HEAR and HERE.

HERE is friends with THERE.

As before, HI is in its own social network.

1 + 1 + (1+ 1) + (1 +  1) +  1    = 7
HI  HE  SHE HER  HEAR HERE   THERE

ASSUMPTION

- If the word is present in its own social network, the total size is number of friends plus 1(For the word itself).
- If the word is not present in its own social network, the total size is number of friend.
- In case of invalid input, the program will terminate with an error message.

USAGE

- Create a Java Project in any IDE(Ex: NetBeans, Eclipse)
- Create a package(Its recommended not to use the default package)
- Create a Java file and write the code
 -Run the Java file

Example:

- Dictionary : very_small_test_dictionary.txt
- Input  : "LISTY"
- Output : The size of social network for LISTY is : 5

Increasing Efficiency of Code

- Stored list of words from the dictionary according to the word length in a HashMap
- The friends of a word are removed from the list when generated, instead of removing them after processing

