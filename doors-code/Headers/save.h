#ifndef SAVE_H
#define SAVE_H

#include <QWidget>
#include <player.h>

namespace Ui {
class Save;
}

class Save : public QWidget
{
    Q_OBJECT

public:
    explicit Save(QWidget *parent = 0);
    ~Save();

private slots:
    void on_saveButton_clicked();

    void on_quitButton_clicked();

    void on_backButton_clicked();

private:
    Ui::Save *ui;

    Player player;

    bool newGame;

public:
    void saveButtonChange();

    void setPlayer(Player& player);

    void isNewGame(bool game);
};

#endif // SAVE_H
