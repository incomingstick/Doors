#ifndef ATTRIBUTES_H
#define ATTRIBUTES_H

#include <QWidget>
#include <player.h>

namespace Ui {
class Attributes;
}

class Attributes : public QWidget
{
    Q_OBJECT

public:
    explicit Attributes(QWidget *parent = 0);
    ~Attributes();

    void setPlayer(Player player);

private:
    Ui::Attributes *ui;

    Player player;
};

#endif // ATTRIBUTES_H
