#pragma once

#include "WelcomeMessageBuilder.cpp"
#include "ButtonsBuilder.cpp"

class Form
{
public:
    Form() {}

    Form(ButtonsBuilder *buttonsBuilder, WelcomeMessageBuilder *welcomeMessageBuilder)
        : buttonsBuilder(buttonsBuilder), welcomeMessageBuilder(welcomeMessageBuilder) {}

    void viewForm()
    {
        welcomeMessageBuilder->printWM();
        buttonsBuilder->addButtons();
    }

private:
    WelcomeMessageBuilder* welcomeMessageBuilder;
    ButtonsBuilder* buttonsBuilder;
};