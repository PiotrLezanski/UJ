using System.Data;
using System.Net;
using System.Runtime.CompilerServices;
using System.Text.RegularExpressions;

public class GameApp
{
    List<Location> locations;
    Location? currentLocation;
    Hero hero;

    // list of locations with players
    readonly Dictionary<string, Player[]> locPlayMap = new Dictionary<string, Player[]>
    {
        {"Calimport", new[] {new Player("Akara", Dialogs.Dialog1), new Player("Akara", Dialogs.Dialog1)}},
        {"Neverwinter", new[] {new Player("Deckard Cain", Dialogs.Dialog1), new Player("Gheed", Dialogs.Dialog1)}},
        {"Silverymoon", new[] {new Player("Kashya", Dialogs.Dialog1), new Player("Warriv", Dialogs.Dialog1)}}
    };

    public GameApp() 
    {
        locations = new List<Location>();
    }

    public void Run()
    {
        InitMenu();
        
        while(true) 
        {
            Console.Write("Your choice: ");
            string? choice = Console.ReadLine();
            if(choice == "1")
            {
                StartGame();
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

    private void InitMenu()
    {
        Console.WriteLine("Welcome to Heros game");
        Console.WriteLine("[1] Begin new game");
        Console.WriteLine("[X] Close program");
    }

    private void StartGame()
    {
        Console.Clear();
        hero = GenerateHero();

        Console.WriteLine($"{hero.HeroClass} {hero.Name} is ready for an adventure!");

        GenerateLocations();
        currentLocation = locations[0];

        if(currentLocation == null)
        {
            return;
        }

        StartDialog();
    }

    private Hero GenerateHero()
    {
        string characterName = AskForCharacterName();
        Console.WriteLine($"Hello {characterName}");
        EHeroClass heroClass = AskForCharacterClass();
        return new Hero(characterName, heroClass);
    }

    private string AskForCharacterName()
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
            RemoveWhitespace(ref characterName);
            if(!IsNameValid(characterName))
            {
                Console.WriteLine("Your name needs to follow the rules.");
                continue;
            }
            break;
        }
        return characterName;
    }

    private EHeroClass AskForCharacterClass()
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

    void StartDialog()
    {
        InitDialogMenu(currentLocation);

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
        TalkTo(currentLocation.NPCList[choiceInt-1], new DialogParser(hero));
    }

    void InitDialogMenu(Location location)
    {
        Console.Clear();
        Console.WriteLine($"You are in {location.Name}. What do you want to do?");

        for(int i=0; i<location.NPCList.Count; ++i) {
            string NPCName = location.NPCList[i].Name;
            Console.WriteLine($"[{i+1}] Speak to {NPCName}");
        }
        Console.WriteLine($"[X] Close program");
    }

    void GenerateLocations()
    {
        foreach(var key in locPlayMap.Keys)
        {
            List<NonPlayerCharacter> playersList = new();
            foreach(Player player in locPlayMap[key])
            {
                playersList.Add(new NonPlayerCharacter(player.Name, player.Dialog));
            }
            locations.Add(new Location(key, playersList));
        }
    }

    void TalkTo(NonPlayerCharacter npc, DialogParser parser)
    {
        var dialog = npc.startTalking();

        while(true)
        {
            Console.WriteLine(parser.ParseDialog(dialog));

            if(dialog.HeroDialogParts == null)
            {
                break;
            }

            for(int i=0; i<dialog.HeroDialogParts.Count; ++i)
            {
                Console.WriteLine($"[{i+1}] {parser.ParseDialog(dialog.HeroDialogParts[i])}");
            }

            while(true)
            {
                Console.WriteLine("Choice: ");
                string? choice = Console.ReadLine();
                int choiceInt = Int32.Parse(choice);
                if(String.IsNullOrEmpty(choice) || choiceInt < 1 || choiceInt > dialog.HeroDialogParts.Count)
                {
                    continue;
                }

                // choice is valid
                Console.WriteLine(parser.ParseDialog(dialog.HeroDialogParts[choiceInt]));
            }
        }
    }

    public static void RemoveWhitespace(ref string input)
    {
        input = Regex.Replace(input, @"\s", "");
    }

    public static bool IsNameValid(string input)
    {
        // rules are -> 1. only letters, 2. size at least two
        return input.Length >= 2 && input.All(Char.IsLetter);
    }
}