#ifndef MAINMENU_H
#define MAINMENU_H

#include <QMainWindow>
#include "player.h"

namespace Ui {
class MainMenu;
}

class MainMenu : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainMenu(QWidget *parent = 0);
    ~MainMenu();

private slots:
    void on_newGameButton_clicked();

    void on_exitButton_clicked();

    void on_actionLoad_triggered();

    void on_loadGameButton_clicked();

    void on_actionNew_triggered();

    void on_actionQuit_triggered();

private:
    Ui::MainMenu *ui;
};

#endif // MAINMENU_H
