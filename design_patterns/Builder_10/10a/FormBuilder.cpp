#include <iostream>

#include "ButtonsBuilder.cpp"
#include "WelcomeMessageBuilder.cpp"

#include "Form.cpp"

class FormBuilder
{
public:
    FormBuilder() {}

    void setButtonsBuilder(ButtonsBuilder* bBuilder)
    {
        buttonsBuilder = bBuilder;
    }

    void setWMBuilder(WelcomeMessageBuilder* wmBuilder)
    {
        welcomeMessageBuilder = wmBuilder;
    }

    void constructForm()
    {
        form = new Form(buttonsBuilder, welcomeMessageBuilder);
    }

    Form getForm()
    {
        if(form)
            return *form;

        return *new Form();
    }

private:
    ButtonsBuilder* buttonsBuilder;
    WelcomeMessageBuilder* welcomeMessageBuilder;

    Form* form;
};


