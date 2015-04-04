#ifndef ITEM_H
#define ITEM_H
#include <string>
#include <cstdlib>

class Player;

using namespace std;

class Item
{
public:
    Item();
    Item(Player* player, int weight);
    Item(string name);
    ~Item();

public:
    string getRarity();
    string getPrefix();
    string getSuffix();
    string getType();
    string getName();
    int getStrMain();
    int getIntMain();
    int getDexMain();
    int getApOff();
    int getSpOff();
    int getCritOff();
    int getHealing();
    int getSellPrice();
    int getItemLevel();
    void setRarity(int weight);
    void setPrefix();
    void setName(int weight);
    void setStats();
    void setItemLevel();
    bool equals(Item obj);
    string toString();

private:
    string rarity, prefix, name, suffix, type;

    int strMain = 0, intMain = 0, dexMain = 0, apOff = 0, spOff = 0,
                critOff = 0, healing = 0, sellPrice = 0, itemLevel = 0;

    Player* player;
};

#endif // ITEM_H
