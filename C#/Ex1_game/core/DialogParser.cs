class DialogParser
{
    Dictionary<string, string> Variables;
    public DialogParser(Hero hero)
    {
        Variables = new()
        {
            { "#HERONAME#", hero.Name }
        };
    }

    public string ParseDialog(IDialogPart IDialog) 
    {
        string result = IDialog.GetDialogText();
        foreach(var variable in Variables)
        {
            result.Replace(variable.Key, variable.Value);
        }
        return result;
    }
}