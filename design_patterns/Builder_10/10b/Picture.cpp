#include "Multimedia.cpp"

class Picture : public Multimedia
{
public:
    Picture(std::string name, std::string type)
        : Multimedia(name, type) {}

    std::unique_ptr<Multimedia> clone() override {
        return std::make_unique<Picture>(*this);
    }

    std::string toString() override {
        return "Picture: " + name + ", " + type;
    }
};
