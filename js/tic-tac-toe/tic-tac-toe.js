var round = 0;
const EMPTY = 0;
const PLAYER_X = 1;
const PLAYER_O = 2;
var houses = [EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY];

// true = player1 (x) / false = player2 (0)

function action(element, number) {

    if (!canFill(number)) return;

    addRound(element, number);

    whoWinner();

    isDraw();

}

function whoPlayerSrc() {
    return this.round % 2 == 0 ? '../assets/letter-x-32.png' : '../assets/letter-o-32.png';
}

function whoPlayer() {
    return this.round % 2 == 0 ? PLAYER_X : PLAYER_O;
}

function canFill(number) {
    return this.houses[number-1] === EMPTY;
}

function addRound(element, number) {
    this.houses[number-1] = whoPlayer();

    const image = document.createElement("img");
    image.src = whoPlayerSrc();
    
    element.appendChild(image);
    this.round++;
}

function whoWinner() {

    // horizontal
    let case1X = houses[0] === PLAYER_X & houses[1] === PLAYER_X & houses[2] === PLAYER_X;
    let case2X = houses[3] === PLAYER_X & houses[4] === PLAYER_X & houses[5] === PLAYER_X;
    let case3X = houses[6] === PLAYER_X & houses[7] === PLAYER_X & houses[8] === PLAYER_X;

    //vertical
    let case4X = houses[0] === PLAYER_X & houses[3] === PLAYER_X & houses[6] === PLAYER_X;
    let case5X = houses[1] === PLAYER_X & houses[4] === PLAYER_X & houses[7] === PLAYER_X;
    let case6X = houses[2] === PLAYER_X & houses[5] === PLAYER_X & houses[8] === PLAYER_X;

    //diagonal
    let case7X = houses[0] === PLAYER_X & houses[4] === PLAYER_X & houses[8] === PLAYER_X;
    let case8X = houses[2] === PLAYER_X & houses[4] === PLAYER_X & houses[6] === PLAYER_X;

    // horizontal
    let case1O = houses[0] === PLAYER_O & houses[1] === PLAYER_O & houses[2] === PLAYER_O;
    let case2O = houses[3] === PLAYER_O & houses[4] === PLAYER_O & houses[5] === PLAYER_O;
    let case3O = houses[6] === PLAYER_O & houses[7] === PLAYER_O & houses[8] === PLAYER_O;

    //vertical
    let case4O = houses[0] === PLAYER_O & houses[3] === PLAYER_O & houses[6] === PLAYER_O;
    let case5O = houses[1] === PLAYER_O & houses[4] === PLAYER_O & houses[7] === PLAYER_O;
    let case6O = houses[2] === PLAYER_O & houses[5] === PLAYER_O & houses[8] === PLAYER_O;

    //diagonal
    let case7O = houses[0] === PLAYER_O & houses[4] === PLAYER_O & houses[8] === PLAYER_O;
    let case8O = houses[2] === PLAYER_O & houses[4] === PLAYER_O & houses[6] === PLAYER_O;

    if(case1X | case2X | case3X | case4X | case5X | case6X | case7X | case8X) {
        winner(PLAYER_X);
    }

    if(case1O | case2O | case3O | case4O | case5O | case6O | case7O | case8O) {
        winner(PLAYER_O);
    }
}

function winner(player) {
    if(player === PLAYER_X) {
        alert("Parabéns jogador X");
        location.reload();
    }

    if(player === PLAYER_O) {
        alert("Parabéns jogador O");
        location.reload();
    }
}

function isDraw() {
    if(!(this.houses[0] === EMPTY) & !(this.houses[1] === EMPTY) & !(this.houses[2] === EMPTY) & !(this.houses[3] === EMPTY) & !(this.houses[4] === EMPTY) & !(this.houses[5] === EMPTY) & !(this.houses[6] === EMPTY) & !(this.houses[7] === EMPTY) & !(this.houses[8] === EMPTY)) {
        alert("Empate....");
        location.reload();
    }
}