#include "enemy.h"
#include <string>
#include <cstdlib>
#include "player.h"

using namespace std;

Enemy::Enemy(Player* player) {
    maxHP = player->getMAX_HP();
    minDamage = player->minDamage();
    maxDamage = player->maxDamage();
    this->player = player;
    weight = 0;
    setPrefix();
    setName();
    setSuffix();
    HP = maxHP;
}

Enemy::~Enemy()
{

}

void Enemy::setPrefix() {
    int level = 0;
    if (player->getLevel() > 50)
        level = 60;
    else
        level = player->getLevel();
    if ((rand() % (101 + level)) - level >= 95) {
        prefix = "Mythic";
        maxHP += getMAX_HP() / 2;
        weight += 10;
    } else if ((rand() % (101 + level)) - level >= 80) {
        prefix = "Heroic";
        maxHP += getMAX_HP() / 5;
        weight += 5;
    } else if ((rand() % (101 + level)) - level >= 50) {
        prefix = "Angry";
        maxHP += getMAX_HP() / 10;
        weight += 2;
    } else if ((rand() % (101 + level)) - level >= 0)
        prefix = "";
}

void Enemy::setName() {
    if (rand() % 101 >= 80) {
        name = "Goblin";
    } else if (rand() % 101 >= 60) {
        name = "Orc";
        maxHP += getMAX_HP() / 10;
        weight += 5;
    } else if (rand() % 101 >= 40) {
        name = "Human";
    } else if (rand() % 101 >= 20) {
        name = "Half-Elf";
    } else if (rand() % 101 >= 0) {
        name = "Demon";
        maxHP += getMAX_HP() / 5;
        weight += 10;
    }
}

void Enemy::setSuffix() {
    int level = 0;
    if (player->getLevel() > 50)
        level = 60;
    else
        level = player->getLevel();
    if ((rand() % (101 + level)) - level >= 99) {
        suffix = "Boss";
        weight += 10;
    } else if ((rand() % (101 + level)) - level >= 95) {
        suffix = "Warlord";
        weight += 5;
    } else if ((rand() % (101 + level)) - level >= 80) {
        suffix = "Spellblade";
        weight += 2;
    } else if ((rand() % (101 + level)) - level >= 50) {
        suffix = "Warrior";
        weight += 1;
    } else if ((rand() % (101 + level)) - level >= 0) {
        suffix = "";
    }
}

int Enemy::getWeight() {
    return weight;
}

bool Enemy::alive() {
    if (HP > 0)
        return true;
    else
        return false;
}

string Enemy::getName() {
    string toString = "";
    if (prefix != "")
        toString += prefix + " ";
    toString += name;
    if (suffix != "")
        toString += " " + suffix;
    return toString;
}

int Enemy::getHP() {
    return HP;
}

void Enemy::setHP(int hp) {
    HP = hp;
}

int Enemy::getMAX_HP() {
    return maxHP;
}

void Enemy::setMAX_HP(int maxHP) {
    this->maxHP = maxHP;
}

int Enemy::getMod() {
    int mod = 0;
    if(prefix == "Mythic")
        mod += 3;
    else if(prefix == "Heroic")
        mod += 2;
    else if(prefix == "Angry")
        mod += 1;

    if(suffix == "Boss")
        mod += 3;
    else  if(suffix == "Warlord")
        mod += 2;
    else if(suffix == "Spell")
        mod += 1;
    if (name == "Orc")
        mod += 1;
    else if (name == "Demon")
        mod += 3;
    mod *= player->getLevel();
    if (mod > maxDamage)
        mod = maxDamage;
    return mod;
}

int Enemy::getMinDamage() {
    return minDamage + getMod();
}

int Enemy::getMaxDamage() {
    return maxDamage + getMod();
}

Player Enemy::meleeAttack(Player actor, bool block) {
    int damage = abs((rand() % (getMaxDamage() - getMinDamage()) + getMinDamage()) * (rand() % (getMaxDamage() + getMod()))) / (player->getLevel() + (getMod() / 5));
    if (damage <= 0) {
        return meleeAttack(actor, block);
    }
    if (!actor.block(block)) {
        actor.setHP(actor.getHP() - damage);
        // TODO Print damage here
    } else {
        // TODO Print damage here
    }
    return actor;
}

Player Enemy::magicAttack(Player actor, bool dodge) {
    int damage = abs((rand() % (getMaxDamage() - getMinDamage()) + getMinDamage()) * (rand() % (getMaxDamage() + getMod()))) / (player->getLevel() + (getMod() / 5));
    if (damage <= 0) {
        return meleeAttack(actor, dodge);
    }
    if (!actor.dodge(dodge)) {
        actor.setHP(actor.getHP() - damage);
        // TODO Print damage here
    } else {
        // TODO Print damage here
    }
    return actor;
}

bool Enemy::block(Player* actor, bool& ability) {
    int blockChance = 90 - ((actor->getDex() + getMod()) / 5);
    if (blockChance < 60)
        blockChance = 60;
    if (ability || rand() % 100 > blockChance)
        return true;
    return false;
}

bool Enemy::dodge(Player* actor, bool& ability) {
    int dodgeChance = 90 - ((actor->getDex() + getMod()) / 5);
    if (dodgeChance < 60)
        dodgeChance = 60;
    if (ability || rand() % 100 > dodgeChance)
        return true;
    return false;
}
