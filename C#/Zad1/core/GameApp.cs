using System.Data;
using System.Runtime.CompilerServices;
using System.Text.RegularExpressions;

public class GameApp
{
    List<Location> locations;
    Location currentLocation;

    // list of locations with players
    Dictionary<string, string[]> locPlayMap = new Dictionary<string, string[]>
    {
        {"Calimport", new[] {"Akara", "Charsi"}},
        {"Neverwinter", new[] {"Deckard Cain", "Gheed"}},
        {"Silverymoon", new[] {"Kashya", "Warriv"}}
    };

    public GameApp() {}

    public void Run()
    {
        initMenu();
        
        while(true) 
        {
            Console.Write("Your choice: ");
            string? choice = Console.ReadLine();
            if(choice == "1")
            {
                startGame();
                return;
            }
            else if(choice == "X")
            {
                return;
            }
            else 
            {
                Console.WriteLine("Pick valid command");
            }
        }
    }

    private void initMenu()
    {
        Console.WriteLine("Welcome to Heros game");
        Console.WriteLine("[1] Begin new game");
        Console.WriteLine("[X] Close program");
    }

    private void startGame()
    {
        Console.Clear();
        Hero hero = generateHero();

        Console.WriteLine($"{hero.HeroClass} {hero.Name} is ready for an adventure!");

        generateLocations();
        currentLocation = locations[0];

        startDialog();
    }

    private Hero generateHero()
    {
        string characterName = askForCharacterName();
        Console.WriteLine($"Hello {characterName}");
        EHeroClass heroClass = askForCharacterClass();
        return new Hero(characterName, heroClass);
    }

    private string askForCharacterName()
    {
        string? characterName;
        while(true)
        {
            Console.Write("Give character name: ");
            characterName = Console.ReadLine();
            if(String.IsNullOrEmpty(characterName))
            {
                Console.WriteLine("Your name is empty. Try again.");
                continue;
            }
            removeWhitespace(ref characterName);
            if(!isNameValid(characterName))
            {
                Console.WriteLine("Your name needs to follow the rules.");
                continue;
            }
            break;
        }
        return characterName;
    }

    private EHeroClass askForCharacterClass()
    {
        EHeroClass heroClass;
        while(true)
        {
            Console.WriteLine("Give character class (Barbarian, Paladin, Amazon): ");
            string? characterClass = Console.ReadLine();
            if(Enum.TryParse(characterClass, out heroClass) && Enum.IsDefined(typeof(EHeroClass), heroClass))
            {
                break;
            }
        }
        return heroClass;
    }

    void startDialog()
    {
        initDialogMenu(currentLocation);

        int choiceInt;
        while(true)
        {
            Console.WriteLine("Choice: ");
            string? choice = Console.ReadLine();

            if(choice == "X")
            {
                Environment.Exit(1);
            }

            if(String.IsNullOrEmpty(choice) || !int.TryParse(choice, out _))
            {
                continue;
            }

            choiceInt = Int32.Parse(choice);
            if(choiceInt < 1 || choiceInt > currentLocation.NPCList.Count)
            {
                continue;
            }
            break;
        }
        // here we have a guarantee that NPC number is valid
        talkTo(currentLocation.NPCList[choiceInt-1]);
    }

    void initDialogMenu(Location location)
    {
        Console.Clear();
        Console.WriteLine($"You are in {location.Name}. What do you want to do?");

        for(int i=0; i<location.NPCList.Count; ++i) {
            string NPCName = location.NPCList[i+1].Name;
            Console.WriteLine($"[{i+1}] Speak to {NPCName}");
        }
        Console.WriteLine($"[X] Close program");
    }

    void generateLocations()
    {
        foreach(var key in locPlayMap.Keys)
        {
            List<NonPlayerCharacter> playersList = new List<NonPlayerCharacter>();
            foreach(string playerName in locPlayMap[key])
            {
                playersList.Add(new NonPlayerCharacter(playerName));
            }
            locations.Add(new Location(key, playersList));
        }
    }

    void talkTo(NonPlayerCharacter npc)
    {

    }

    public static void removeWhitespace(ref string input)
    {
        input = Regex.Replace(input, @"\s", "");
    }

    public static bool isNameValid(string input)
    {
        // rules are -> 1. only letters, 2. size at least two
        return input.Length >= 2 && input.All(Char.IsLetter);
    }
}