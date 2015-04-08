#ifndef INVENTORY_H
#define INVENTORY_H

#include <QDialog>
#include <QGraphicsScene>
#include <QGraphicsPixmapItem>
#include <QLabel>
#include <QList>
#include <player.h>

namespace Ui {
class Inventory;
}

class Inventory : public QDialog
{
    Q_OBJECT

public:
    explicit Inventory(QWidget *parent = 0);
    ~Inventory();

    void setPlayer(Player player);

private slots:
    void on_pushButton_clicked();

private:
    Ui::Inventory *ui;

    Player player;

    QList<QLabel*> inv;
    QList<QLabel*> equiped;
};

#endif // INVENTORY_H
