# TypeAheadSearch
Created a Java application to replicate the backend of a Type Ahead Search System.


This is a backend of a common use application called the Type Ahead Search. 

When ever you search for anything on google you are recommended a list of words that start with the word you have typed in. These words are recommended in order of how often they have been searched for previously (Not neccesarily by you).

Some of the key features implemented in this project :

-> When a user searches for a word a list of words should be recommended to them.
-> This list can contain a dynamic number of words.
-> When a user searches for a word if it is a new word add it to our repository with count 1 else the count of the word should increase.
-> Next time as user searches for something with the same prefix the word can be recommended to the user.
-> Also include a decay so that more recent serches have higher priority
-> Make the decay factor dynamic.
-> Decay will be implemented such that every day that passes will reduce the value of every search count by a given factor. So for instance if today a word has 50 count and the decay factor is 2 tomorrow that same word will have 25count.
-> New searches will add 1 to the value of the word.
<<<<<<< HEAD


Rev 2.0 :  
  -> Added Exceptions 
  -> Added stratergies for decay
  -> Created Singleton approach for decay.
  
