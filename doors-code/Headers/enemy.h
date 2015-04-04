#ifndef ENEMY_H
#define ENEMY_H
#include <vector>
#include <string>

class Item;
class Player;

using namespace std;

class Enemy
{
public:
    Enemy();
    Enemy(Player* player);
    ~Enemy();

public:
    bool alive();
    bool block(Player* actor, bool& ability);
    bool dodge(Player* actor, bool& ability);
    int getHP();
    int getMAX_HP();
    int getMod();
    string getName();
    Player magicAttack(Player actor, bool dodge);
    int getMaxDamage();
    Player meleeAttack(Player actor, bool block);
    int getMinDamage();
    void setHP(int hp);
    void setMAX_HP(int maxHP);
    void setName();
    void setPrefix();
    void setSuffix();
    int getWeight();

private:
    int HP, maxHP, minDamage, maxDamage, weight;

    string prefix, name, suffix;

    Player *player;
};

#endif // ENEMY_H
