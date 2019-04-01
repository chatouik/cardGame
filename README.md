# TexasHoldemImplementation

Hello, this is my implementation of texasHoldem
To launch, just compile and run the main, the inputs are via the console. 
I chose to implement texasHoldem because I am a bit familiar with the game. I started by thinking about the main architecture to 
make future games implementable, and then creating the main objects. 
Concerning the main implementation of the game, I made it to the showdown which is the final step of a turn, I started implementing 
a filter (TexasHandFilter.java) which is supposed to take as an argument the list of players still playing the current turn,
create each player to his hand+community cards, and then pass multiple filters on the map elements to get the strongest hand. 
In case we have hands with the same strength, I wanted to make Card.java into a comparable to sort those results. 
When the showdown is done, I can add a main loop which would end when only one player has balance left
For now, it is only possible to add human players, but I talk later about how I would implement the feature.
There is also no filter to check if the user input is false
Although i did not finish the showdown, I did implement a simple file parser to store the scores, it checks if the player exists
and updates his score. The test of this feature is in TexasHoldemGameTest.java as well as some other method tests.

What I would do to improve the project or implement the missing features : 
- Adding a generic filter for user input, it would take as an argument the type of input we are waiting for (name/betValue/etc)
and do the necessary checks and throw an exception otherwise. 
- Implementation of bot difficulty: To add the bot difficulty, I wanted to calculate the hand strength(hand + community cards) 
at each step (preflop/flop/etc), then using that hand strength; i can rank the possible moves, for example, if the hand strength is 
high, we rank the moves as follows : raise(),call(),fold(). Then, depending on the bot difficulty (e/m/h), we would chose choice 1,2 or 3
Then to implement the bot, i'll add a isBot argument on the Player object, then we use isBot condition to wait for userInput or manually 
call raise(), call(), fold() from the previous bot choice.

- A good solution for a visual input, would be adding a react Layer, we can use the same architecture as the backside to create 
react components, and interacting with these components can send the user choices to the backside via json perhaps. 

- Packaging the project by using maven perhaps, that way I can add junit as a dependency and implement full unit tests more easily.