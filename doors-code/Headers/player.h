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
    QString getName() const;
    void setName(QString name);
    int getStr() const;
    void setStr(int strength);
    int getInt() const;
    void setInt(int intelligence);
    int getDex() const;
    void setDex(int dexterity);
    int getAttackPower() const;
    void setAttackPower(int attackPower);
    int getSpellPower() const;
    void setSpellPower(int spellPower);
    double getCrit() const;
    void setCrit(double crit);
    int getHP() const;
    void setHP(int hp);
    int getMAX_HP();
    int getCURRENT_EXP() const;
    void setCURRENT_EXP(int CURRENT_EXP);
    int getMAX_EXP() const;
    int getLevel() const;
    void setLevel(int level);
    QList<Item*> getInventory() const;
    int minDamage();
    int maxDamage();
    QList<Item*> getEquip() const;
    void equipItem(Item item);
    Enemy meleeAttack(Enemy enemy, bool block);
    Enemy magicAttack(Enemy actor, bool dodge);
    bool block(bool& ability);
    bool dodge(bool& ability);
    void addItem(Item item);
    void setItem(int index, Item item);
    void removeItem(int index);
    int getPointsToSpend() const;
    void setPointsToSpend(int pointsToSpend);

private:
    int Strength, Intelligence, Dexterity, AttackPower, SpellPower,
                CURRENT_EXP, MAX_EXP, HP, MAX_HP, Level, pointsToSpend;
    double crit;

    QString name;
    QList<Item*> inventory;
    QList<Item*> equip;
};

#endif // PLAYER_H
