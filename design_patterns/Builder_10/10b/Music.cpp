#include "Multimedia.cpp"

class Music : public Multimedia
{
public:
    Music(std::string name, std::string type)
        : Multimedia(name, type) {}

    std::unique_ptr<Multimedia> clone() override {
        return std::make_unique<Music>(*this);
    }

    std::string toString() override {
        return "Music: " + name + ", " + type;
    }
};