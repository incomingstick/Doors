#ifndef ITEM_H
#define ITEM_H
#include <string>
#include <cstdlib>
#include <QPixmap>

class Player;

using namespace std;

class Item
{
public:
    Item();
    Item(Player* player, int weight);
    Item(QString name);
    ~Item();

public:
    QString getRarity() const;
    QString getPrefix() const;
    QString getSuffix() const;
    QString getType() const;
    QString getName() const;
    int getStrMain() const;
    int getIntMain() const;
    int getDexMain() const;
    int getApOff() const;
    int getSpOff() const;
    int getCritOff() const;
    int getHealing() const;
    int getSellPrice() const;
    int getItemLevel() const;
    void setRarity(int weight);
    void setPrefix();
    void setName(int weight);
    void setStats();
    void setItemLevel();
    bool equals(Item obj);
    QString toString() const;
    QPixmap getPixmap() const;

private:
    QString rarity, prefix, name, suffix, type;

    int strMain = 0, intMain = 0, dexMain = 0, apOff = 0, spOff = 0,
                critOff = 0, healing = 0, sellPrice = 0, itemLevel = 0;

    Player* player;

    QPixmap image;
};

#endif // ITEM_H
