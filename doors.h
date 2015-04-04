#ifndef DOORS_H
#define DOORS_H

#include <QMainWindow>
#include <player.h>

namespace Ui {
class Doors;
}

class Doors : public QMainWindow
{
    Q_OBJECT

public:
    explicit Doors(QWidget *parent = 0);
    ~Doors();

private slots:
    void on_actionSave_and_Quit_triggered();

    void on_actionSave_triggered();

    void on_actionSave_2_triggered();

    void on_actionNew_triggered();

    void on_actionStats_triggered();

private:
    Ui::Doors *ui;

    Player player;

public:
    void setPlayer(Player& player);
};

#endif // DOORS_H
