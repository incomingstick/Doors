#ifndef PLAYER_H
#define PLAYER_H
#include <vector>
#include <string>
#include <cstdlib>
#include <item.h>
#include <qstring.h>

class Enemy;

using namespace std;

class Player
{
public:
    Player();
    ~Player();

public:
    bool checkLevelUp();
    void LevelUp();
    QString getName();
    void setName(QString name);
    int getStr();
    void setStr(int strength);
    int getInt();
    void setInt(int intelligence);
    int getDex();
    void setDex(int dexterity);
    int getAttackPower();
    void setAttackPower(int attackPower);
    int getSpellPower();
    void setSpellPower(int spellPower);
    double getCrit();
    void setCrit(double crit);
    int getHP();
    void setHP(int hp);
    int getMAX_HP();
    int getCURRENT_EXP();
    void setCURRENT_EXP(int CURRENT_EXP);
    int getMAX_EXP();
    int getLevel();
    void setLevel(int level);
    vector<Item> getInventory();
    int minDamage();
    int maxDamage();
    vector<Item> getEquip();
    void equipItem(Item item);
    Enemy meleeAttack(Enemy enemy, bool block);
    Enemy magicAttack(Enemy actor, bool dodge);
    bool block(bool& ability);
    bool dodge(bool& ability);
    void addItem(Item item);
    void setItem(int index, Item item);
    void removeItem(int index);
    int getPointsToSpend();
    void setPointsToSpend(int pointsToSpend);

private:
    int Strength, Intelligence, Dexterity, AttackPower, SpellPower,
                CURRENT_EXP, MAX_EXP, HP, MAX_HP, Level, pointsToSpend;
    double crit;

    QString name;
    vector<Item> inventory;
    vector<Item> equip;
};

#endif // PLAYER_H
