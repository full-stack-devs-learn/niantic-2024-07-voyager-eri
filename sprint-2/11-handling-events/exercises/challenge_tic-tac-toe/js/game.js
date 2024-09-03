// players player 1,2
const player1 = {
    name: 'Player One',
    value: 'X'
}
const player2 = {
    name: 'Player Two',
    value: 'O'
}

// current
let currentPlayer = player1;


function setNextPlayer()
{
    currentPlayer = (currentPlayer == player1) ? player2 : player1;
    displayCurrentPlayer();
}

function displayCurrentPlayer()
{
    const playerNameDiv = document.getElementById("playerName");
    playerNameDiv.innerHTML = currentPlayer.name + "'s Turn";
}


function init()
{
    displayCurrentPlayer();
    markTile();
}

function markTile()
{
    const buttons = document.querySelectorAll(".game-button");

    buttons.forEach(button => {
        button.addEventListener("click", () => {
            if(button.innerHTML == "")
            {
                button.innerHTML = (currentPlayer == player1) ? player1.value : player2.value;
                setNextPlayer();
            }
        });
    });
}

// main
document.addEventListener('DOMContentLoaded', () =>
{
    init()
})
