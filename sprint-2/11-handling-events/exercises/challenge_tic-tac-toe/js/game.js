const player1 = {
    name: 'Player One',
    value: 'X'
}
const player2 = {
    name: 'Player Two',
    value: 'O'
}

let currentPlayer = player1;
let buttons;
let gameActive = true;

let a1Div;
let a2Div;
let a3Div;
let b1Div;
let b2Div;
let b3Div;
let c1Div;
let c2Div;
let c3Div;

function init()
{
    displayCurrentPlayer();
    markTile();
}

function displayCurrentPlayer()
{
    const playerNameDiv = document.getElementById("playerName");
    playerNameDiv.innerHTML = currentPlayer.name + "'s Turn";
}

function setNextPlayer()
{
    currentPlayer = (currentPlayer == player1) ? player2 : player1;
    displayCurrentPlayer();
}

function isWin()
{
    const a1 = a1Div.innerHTML;
    const a2 = a2Div.innerHTML;
    const a3 = a3Div.innerHTML;
    const b1 = b1Div.innerHTML;
    const b2 = b2Div.innerHTML;
    const b3 = b3Div.innerHTML;
    const c1 = c1Div.innerHTML;
    const c2 = c2Div.innerHTML;
    const c3 = c3Div.innerHTML;

    const winnableCombos = [
        [a1, a2, a3],
        [b1, b2, b3],
        [c1, c2, c3],
        [a1, b1, c1],
        [a2, b2, c2],
        [a3, b2, b3],
        [a1, b2, c3],
        [a3, b2, c1]
    ];

    for (const combo of winnableCombos) 
    {
        const set = new Set(combo);
        if ((set.size == 1) && !(set.has("")))
        {
            return true;
        }
    }
    return false;
}

function displayWinner()
{
    const playerNameDiv = document.getElementById("playerName");
    playerNameDiv.innerHTML = "WINNER: " + currentPlayer.name;
    gameActive = false;
}

function markTile()
{
    buttons.forEach(button => {
        button.addEventListener("click", () => {
            if(button.innerHTML == "" && gameActive)
            {
                button.innerHTML = (currentPlayer == player1) ? player1.value : player2.value;
                nextAction();
            }
        });
    });
}

function nextAction()
{
    if(isWin())
    {
        displayWinner();
    }
    else
    {
        setNextPlayer();
    }
}

function resetTiles()
{
    buttons.forEach(button => {
        button.innerHTML = "";
    });

    gameActive = true;
    currentPlayer = player1;
    displayCurrentPlayer();
}

document.addEventListener('DOMContentLoaded', () =>
{
    buttons = document.querySelectorAll(".game-button");

    a1Div = document.getElementById("a1");
    a2Div = document.getElementById("a2");
    a3Div = document.getElementById("a3");
    b1Div = document.getElementById("b1");
    b2Div = document.getElementById("b2");
    b3Div = document.getElementById("b3");
    c1Div = document.getElementById("c1");
    c2Div = document.getElementById("c2");
    c3Div = document.getElementById("c3");

    init()
})
