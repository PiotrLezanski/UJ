using System.Runtime.CompilerServices;
using System.Text.RegularExpressions;

public class GameApp
{
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
        Console.WriteLine("Witaj w grze Herosi");
        Console.WriteLine("[1] Zacznij nową grę");
        Console.WriteLine("[X] Zamknij program");
    }

    private void startGame()
    {
        Console.Clear();
        Hero hero = generateHero();
        

        Console.ReadKey();
    }

    private Hero generateHero()
    {
        string characterName = askForCharacterName();
        Console.WriteLine($"Hello {characterName}.");
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