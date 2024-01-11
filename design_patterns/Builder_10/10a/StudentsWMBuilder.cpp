#include <iostream>
#include "WelcomeMessageBuilder.cpp"

class StudentsWMBuilder : public WelcomeMessageBuilder
{
    void printWM() override
    {
        std::cout << "Building Students Welcome Message" <<std::endl;
    }
};
