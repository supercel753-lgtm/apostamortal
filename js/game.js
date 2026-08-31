/* =====================================================
   LAST CHANCE
   GAME.JS
===================================================== */

"use strict";


/* =====================================================
   CONFIGURAÇÃO
===================================================== */

const GAME = {

    round: 1,

    clockSeconds: 0,

    maxRounds: 12,

    bet: 1,

    playerScore: 0,

    aliceScore: 0,

    playerChips: 100,

    health: 100,

    fear: 20,

    stability: 100,

    resistance: 0,

    trust: 20,

    aliceFear: 35,

    suspicion: 10,

    hope: 40,

    running: false,

    turn: "player",

    playerHand: [],

    aliceHand: [],

    usedCards: [],

    history: [],

    ending: null

};


/* =====================================================
   CARTAS
===================================================== */

const CARD_VALUES = {

    "4": 1,
    "5": 2,
    "6": 3,
    "7": 4,
    "Q": 5,
    "J": 6,
    "K": 7,
    "A": 8,
    "2": 9,
    "3": 10

};


/* =====================================================
   DIÁLOGOS
===================================================== */

const DIALOGUES = {

    dealer: [

        "Olha só quem finalmente decidiu aparecer.",

        "Não façam essa cara. Eu preparei tudo com carinho.",

        "Alice, você ainda confia nele?",

        "Leo, você ainda acha que sabe quem está sentado aí?",

        "Interessante... essa carta pode mudar tudo.",

        "Eu não aconselharia essa jogada. Mas também não aconselho quase nada.",

        "Vocês dois são terrivelmente previsíveis.",

        "Truco? Agora ficou interessante.",

        "Ah. Essa mão é perigosa.",

        "Eu conheço essa expressão, Alice.",

        "Leo... você percebeu o que acabou de acontecer?",

        "Não parem agora. Eu estava começando a me divertir.",

        "Vocês poderiam simplesmente conversar.",

        "Mas aí eu não teria nada para fazer.",

        "Que pena que algumas coisas chegam tarde demais."

    ],

    alice: [

        "Eu não gosto desse cara.",

        "Você parece diferente do que eu imaginava.",

        "Não sei se posso confiar em você.",

        "Por que ele fica olhando para nós desse jeito?",

        "Você conhece ele?",

        "Essa partida está ficando estranha.",

        "Não precisa aumentar a aposta.",

        "Eu só quero ir embora.",

        "Você realmente acha que isso vai terminar bem?",

        "Leo... posso te fazer uma pergunta?",

        "Tem alguma coisa que você não está me contando.",

        "Não quero continuar assim.",

        "Talvez a gente devesse parar de ouvir ele.",

        "Você também percebeu que ele está mentindo?",

        "Eu lembro de alguma coisa..."

    ],

    leo: [

        "Quem é você?",

        "Eu não lembro de ter vindo aqui.",

        "Por que você sabe meu nome?",

        "Alice, espera.",

        "Tem alguma coisa errada nessa partida.",

        "Eu não confio nesse sujeito.",

        "Vamos descobrir a verdade.",

        "Não vou deixar ele decidir por nós."

    ]

};


/* =====================================================
   ELEMENTOS HTML
===================================================== */

const $ = (id) => document.getElementById(id);


const titleScreen = $("title-screen");
const gameScreen = $("game-screen");
const endingScreen = $("ending-screen");

const startButton = $("start-button");
const restartButton = $("restart-button");

const clockElement = $("digital-clock");
const roundElement = $("round-number");

const dialogueSpeaker = $("dialogue-speaker");
const dialogueText = $("dialogue-text");

const aliceMood = $("alice-mood");

const aliceTrustValue = $("alice-trust-value");
const aliceTrustBar = $("alice-trust-bar");

const aliceFearValue = $("alice-fear-value");
const aliceFearBar = $("alice-fear-bar");

const aliceSuspicionValue = $("alice-suspicion-value");
const aliceSuspicionBar = $("alice-suspicion-bar");

const aliceHopeValue = $("alice-hope-value");
const aliceHopeBar = $("alice-hope-bar");

const aliceScore = $("alice-score");
const aliceScoreFooter = $("alice-score-footer");

const playerHealthValue = $("player-health-value");
const playerHealthBar = $("player-health-bar");

const playerFearValue = $("player-fear-value");

const playerStabilityValue = $("player-stability-value");
const playerStabilityBar = $("player-stability-bar");

const playerChips = $("player-chips");
const playerScore = $("player-score");

const currentBet = $("current-bet");
const statusBet = $("status-bet");

const playerCardsElement = $("player-cards");

const playerPlayedCard = $("player-played-card");
const alicePlayedCard = $("alice-played-card");

const actionTruco = $("action-truco");
const actionCompassion = $("action-compassion");
const actionSuspicion = $("action-suspicion");
const actionSilence = $("action-silence");


/* =====================================================
   UTILIDADES
===================================================== */

function random(array) {

    return array[
        Math.floor(Math.random() * array.length)
    ];

}


function clamp(value, min, max) {

    return Math.max(
        min,
        Math.min(max, value)
    );

}


function changeStat(name, amount) {

    GAME[name] = clamp(
        GAME[name] + amount,
        0,
        100
    );

}


/* =====================================================
   RELÓGIO
===================================================== */

let clockInterval = null;


function startClock() {

    clearInterval(clockInterval);

    clockInterval = setInterval(() => {

        if (!GAME.running) {
            return;
        }

        GAME.clockSeconds++;

        updateClock();

        if (GAME.clockSeconds > 300) {

            finishGame(
                "TEMPO ESGOTADO",
                "O terminal encerrou a sessão antes que qualquer um de vocês encontrasse uma saída."
            );

        }

    }, 1000);

}


function updateClock() {

    const baseHour = 23;

    const minutes =
        Math.floor(GAME.clockSeconds / 60);

    const seconds =
        GAME.clockSeconds % 60;

    const minuteString =
        String(minutes).padStart(2, "0");

    const secondString =
        String(seconds).padStart(2, "0");

    clockElement.textContent =
        `${baseHour}:${minuteString}:${secondString}`;

}


/* =====================================================
   DIÁLOGO
===================================================== */

function say(speaker, text) {

    dialogueSpeaker.textContent = speaker;

    dialogueText.textContent = text;

}


function dealerTalk() {

    say(
        "DEALER",
        random(DIALOGUES.dealer)
    );

}


function aliceTalk() {

    say(
        "ALICE",
        random(DIALOGUES.alice)
    );

}


function leoTalk() {

    say(
        "LEO",
        random(DIALOGUES.leo)
    );

}


/* =====================================================
   BARALHO
===================================================== */

function createDeck() {

    return [

        "4",
        "5",
        "6",
        "7",

        "Q",
        "J",
        "K",

        "A",
        "2",
        "3"

    ];

}


function shuffle(array) {

    const result = [...array];

    for (
        let i = result.length - 1;
        i > 0;
        i--
    ) {

        const j =
            Math.floor(Math.random() * (i + 1));

        [
            result[i],
            result[j]
        ] =
        [
            result[j],
            result[i]
        ];

    }

    return result;

}


function dealHands() {

    let deck = shuffle(createDeck());

    GAME.playerHand =
        deck.slice(0, 3);

    GAME.aliceHand =
        deck.slice(3, 6);

    renderPlayerCards();

}


/* =====================================================
   CARTAS DO PLAYER
===================================================== */

function renderPlayerCards() {

    playerCardsElement.innerHTML = "";

    GAME.playerHand.forEach(
        (card, index) => {

            const button =
                document.createElement("button");

            button.className =
                "card player-card";

            button.textContent =
                card;

            button.dataset.index =
                index;

            button.addEventListener(
                "click",
                () => {

                    playCard(index);

                }
            );

            playerCardsElement.appendChild(
                button
            );

        }
    );

}


/* =====================================================
   JOGAR CARTA
===================================================== */

function playCard(index) {

    if (!GAME.running) {
        return;
    }

    if (GAME.turn !== "player") {
        return;
    }

    if (!GAME.playerHand[index]) {
        return;
    }

    const card =
        GAME.playerHand[index];

    GAME.playerHand.splice(
        index,
        1
    );

    playerPlayedCard.textContent =
        card;

    GAME.turn = "alice";

    renderPlayerCards();

    dealerCheckInstantWin();

    if (!GAME.running) {
        return;
    }

    setTimeout(
        alicePlay,
        800
    );

}


/* =====================================================
   ALICE JOGA
===================================================== */

function alicePlay() {

    if (!GAME.running) {
        return;
    }

    if (
        GAME.aliceHand.length === 0
    ) {

        nextRound();

        return;

    }

    const best =
        chooseAliceCard();

    const card =
        GAME.aliceHand.splice(
            best,
            1
        )[0];

    alicePlayedCard.textContent =
        card;

    resolveTrick(
        card
    );

}


/* =====================================================
   IA DA ALICE
===================================================== */

function chooseAliceCard() {

    let bestIndex = 0;

    for (
        let i = 1;
        i < GAME.aliceHand.length;
        i++
    ) {

        const current =
            CARD_VALUES[
                GAME.aliceHand[i]
            ];

        const best =
            CARD_VALUES[
                GAME.aliceHand[bestIndex]
            ];

        if (current > best) {

            bestIndex = i;

        }

    }


    /*
       Alice fica mais agressiva
       quando está desconfiada.
    */

    if (
        GAME.suspicion > 65 &&
        Math.random() < 0.6
    ) {

        let strongest =
            bestIndex;

        return strongest;

    }


    /*
       Alice às vezes joga uma carta
       mais fraca para testar Leo.
    */

    if (
        Math.random() < 0.3 &&
        GAME.aliceHand.length > 1
    ) {

        return Math.floor(
            Math.random() *
            GAME.aliceHand.length
        );

    }

    return bestIndex;

}


/* =====================================================
   RESOLVER TRUCO
===================================================== */

function resolveTrick(aliceCard) {

    const playerCard =
        playerPlayedCard.textContent;

    const playerValue =
        CARD_VALUES[playerCard];

    const aliceValue =
        CARD_VALUES[aliceCard];


    if (
        playerValue === undefined ||
        aliceValue === undefined
    ) {

        return;

    }


    if (
        playerValue >
        aliceValue
    ) {

        GAME.playerScore +=
            GAME.bet;

        say(
            "DEALER",
            "Leo levou essa. Interessante."
        );

        changeStat(
            "trust",
            3
        );

    }

    else if (
        aliceValue >
        playerValue
    ) {

        GAME.aliceScore +=
            GAME.bet;

        say(
            "DEALER",
            "Alice venceu. Você deveria ter pensado melhor."
        );

        changeStat(
            "aliceFear",
            -2
        );

    }

    else {

        say(
            "DEALER",
            "Empate. Que decepcionante."
        );

    }


    updateUI();


    setTimeout(
        nextRound,
        1000
    );

}


/* =====================================================
   PRÓXIMA RODADA
===================================================== */

function nextRound() {

    if (!GAME.running) {
        return;
    }

    GAME.round++;

    GAME.bet = 1;

    roundElement.textContent =
        GAME.round;

    currentBet.textContent =
        GAME.bet;

    statusBet.textContent =
        GAME.bet;

    playerPlayedCard.textContent = "";

    alicePlayedCard.textContent = "";


    if (
        GAME.round >
        GAME.maxRounds
    ) {

        determineEnding();

        return;

    }


    dealHands();

    GAME.turn = "player";

    dealerCheckInstantWin();

}


/* =====================================================
   TRUCO
===================================================== */

function callTruco() {

    if (!GAME.running) {
        return;
    }

    if (GAME.bet >= 12) {

        say(
            "DEALER",
            "Você realmente quer continuar aumentando?"
        );

        return;

    }


    GAME.bet *= 2;


    if (
        GAME.playerChips <
        GAME.bet
    ) {

        GAME.bet =
            GAME.playerChips;

    }


    changeStat(
        "aliceFear",
        4
    );

    changeStat(
        "suspicion",
        3
    );

    say(
        "DEALER",
        `TRUCO. A mão agora vale ${GAME.bet}.`
    );

    currentBet.textContent =
        GAME.bet;

    statusBet.textContent =
        GAME.bet;

    updateUI();


    /*
       Alice decide se aceita
       a pressão.
    */

    setTimeout(
        aliceRespondToTruco,
        900
    );

}


function aliceRespondToTruco() {

    if (!GAME.running) {
        return;
    }


    const acceptance =
        GAME.trust +
        GAME.hope -
        GAME.suspicion;


    if (
        acceptance > 40 ||
        Math.random() > 0.25
    ) {

        aliceTalk();

        return;

    }


    /*
       Alice recua.
    */

    GAME.playerScore +=
        GAME.bet;

    say(
        "ALICE",
        "Eu... aceito."
    );

    updateUI();

}


/* =====================================================
   CONVERSAR
===================================================== */

function talkToAlice() {

    if (!GAME.running) {
        return;
    }

    changeStat(
        "trust",
        8
    );

    changeStat(
        "aliceFear",
        -5
    );

    changeStat(
        "suspicion",
        -6
    );

    changeStat(
        "hope",
        5
    );

    aliceTalk();

    updateUI();

}


/* =====================================================
   QUESTIONAR
===================================================== */

function questionDealer() {

    if (!GAME.running) {
        return;
    }

    changeStat(
        "suspicion",
        10
    );

    changeStat(
        "trust",
        -2
    );

    say(
        "LEO",
        "Você está escondendo alguma coisa."
    );


    setTimeout(
        () => {

            say(
                "DEALER",
                "Eu? Escondendo? Que acusação horrível."
            );

        },
        700
    );


    updateUI();

}


/* =====================================================
   SILÊNCIO
===================================================== */

function remainSilent() {

    if (!GAME.running) {
        return;
    }

    changeStat(
        "aliceFear",
        2
    );

    changeStat(
        "suspicion",
        2
    );

    say(
        "DEALER",
        "Silêncio. Finalmente alguém está aprendendo."
    );

    updateUI();

}


/* =====================================================
   DEALER
===================================================== */

function dealerCheckInstantWin() {

    if (!GAME.running) {
        return;
    }


    const playerWin =
        hasWinningHand(
            GAME.playerHand
        );

    const aliceWin =
        hasWinningHand(
            GAME.aliceHand
        );


    if (
        playerWin &&
        aliceWin
    ) {

        say(
            "DEALER",
            "Ah. Os dois têm uma mão excelente. Isso vai ser divertido."
        );

        return;

    }


    if (playerWin) {

        say(
            "DEALER",
            "Leo... você percebeu que acabou de receber uma mão praticamente decisiva?"
        );

        return;

    }


    if (aliceWin) {

        say(
            "DEALER",
            "Alice, não olha agora. Ou olha. Tanto faz."
        );

    }

}


function hasWinningHand(hand) {

    if (!hand || hand.length < 3) {
        return false;
    }

    /*
       Aqui usamos uma condição abstrata
       de mão muito forte para o protótipo.
    */

    const values =
        hand.map(
            card => CARD_VALUES[card]
        );

    values.sort(
        (a, b) => b - a
    );


    /*
       Três cartas consecutivas
       são consideradas mão decisiva.
    */

    if (
        values[0] === values[1] + 1 &&
        values[1] === values[2] + 1
    ) {

        return true;

    }


    /*
       Três cartas com mesmo valor
       também são mão decisiva.
    */

    if (
        values[0] === values[1] &&
        values[1] === values[2]
    ) {

        return true;

    }

    return false;

}


/* =====================================================
   UI
===================================================== */

function updateUI() {

    /*
       Alice
    */

    aliceTrustValue.textContent =
        GAME.trust;

    aliceTrustBar.style.width =
        `${GAME.trust}%`;


    aliceFearValue.textContent =
        GAME.aliceFear;

    aliceFearBar.style.width =
        `${GAME.aliceFear}%`;


    aliceSuspicionValue.textContent =
        GAME.suspicion;

    aliceSuspicionBar.style.width =
        `${GAME.suspicion}%`;


    aliceHopeValue.textContent =
        GAME.hope;

    aliceHopeBar.style.width =
        `${GAME.hope}%`;


    /*
       Leo
    */

    playerHealthValue.textContent =
        GAME.health;

    playerHealthBar.style.width =
        `${GAME.health}%`;


    playerFearValue.textContent =
        GAME.fear;


    playerStabilityValue.textContent =
        GAME.stability;

    playerStabilityBar.style.width =
        `${GAME.stability}%`;


    /*
       Pontuação
    */

    playerScore.textContent =
        GAME.playerScore;

    aliceScore.textContent =
        GAME.aliceScore;

    aliceScoreFooter.textContent =
        GAME.aliceScore;


    playerChips.textContent =
        GAME.playerChips;


    /*
       Humor da Alice
    */

    if (GAME.trust >= 70) {

        aliceMood.textContent =
            "TRUSTING";

    }

    else if (
        GAME.suspicion >= 70
    ) {

        aliceMood.textContent =
            "SUSPICIOUS";

    }

    else if (
        GAME.aliceFear >= 70
    ) {

        aliceMood.textContent =
            "PANICKED";

    }

    else {

        aliceMood.textContent =
            "NERVOUS";

    }

}


/* =====================================================
   FINAIS
===================================================== */

function determineEnding() {

    if (!GAME.running) {
        return;
    }


    /*
       FINAL 1
       Ruim
    */

    if (
        GAME.stability < 20
    ) {

        finishGame(
            "SISTEMA INSTÁVEL",
            "O terminal encerrou a partida. Nenhuma resposta veio com o silêncio."
        );

        return;

    }


    /*
       FINAL 2
       Ruim
    */

    if (
        GAME.suspicion >= 85
    ) {

        finishGame(
            "CONFIANÇA PERDIDA",
            "Alice deixou de acreditar em você antes que vocês descobrissem a verdade."
        );

        return;

    }


    /*
       FINAL 3
       Ruim
    */

    if (
        GAME.aliceScore >
        GAME.playerScore
    ) {

        finishGame(
            "ALICE VENCEU",
            "A partida acabou. O Dealer sorriu como se já soubesse o resultado."
        );

        return;

    }


    /*
       FINAL 4
       Ruim
    */

    if (
        GAME.trust < 20
    ) {

        finishGame(
            "DOIS ESTRANHOS",
            "Vocês chegaram ao fim sem conseguir reconstruir a confiança."
        );

        return;

    }


    /*
       FINAL 5
       Bom
    */

    if (
        GAME.trust >= 70 &&
        GAME.suspicion >= 50
    ) {

        finishGame(
            "A VERDADE",
            "Leo e Alice finalmente percebem que o Dealer estava manipulando a partida."
        );

        return;

    }


    /*
       FINAL 6
       Bom
    */

    finishGame(
        "ÚLTIMA CHANCE",
        "Vocês não venceram exatamente a partida. Venceram a oportunidade de escolher o próprio caminho."
    );

}


/* =====================================================
   ENCERRAR
===================================================== */

function finishGame(title, text) {

    GAME.running = false;

    clearInterval(clockInterval);

    GAME.ending = title;

    $("ending-title").textContent =
        title;

    $("ending-text").textContent =
        text;

    gameScreen.classList.remove(
        "active"
    );

    endingScreen.classList.add(
        "active"
    );

}


/* =====================================================
   INICIAR
===================================================== */

function startGame() {

    GAME.running = true;

    GAME.round = 1;

    GAME.clockSeconds = 0;

    GAME.bet = 1;

    GAME.playerScore = 0;

    GAME.aliceScore = 0;

    GAME.playerChips = 100;

    GAME.health = 100;

    GAME.fear = 20;

    GAME.stability = 100;

    GAME.resistance = 0;

    GAME.trust = 20;

    GAME.aliceFear = 35;

    GAME.suspicion = 10;

    GAME.hope = 40;

    GAME.turn = "player";

    titleScreen.classList.remove(
        "active"
    );

    endingScreen.classList.remove(
        "active"
    );

    gameScreen.classList.add(
        "active"
    );

    roundElement.textContent =
        "1";

    currentBet.textContent =
        "1";

    statusBet.textContent =
        "1";

    dealHands();

    updateUI();

    updateClock();

    startClock();

    say(
        "DEALER",
        "Finalmente. Vocês chegaram."
    );

}


/* =====================================================
   REINICIAR
===================================================== */

function restartGame() {

    endingScreen.classList.remove(
        "active"
    );

    titleScreen.classList.add(
        "active"
    );

}


/* =====================================================
   EVENTOS
===================================================== */

startButton.addEventListener(
    "click",
    startGame
);


restartButton.addEventListener(
    "click",
    restartGame
);


actionTruco.addEventListener(
    "click",
    callTruco
);


actionCompassion.addEventListener(
    "click",
    talkToAlice
);


actionSuspicion.addEventListener(
    "click",
    questionDealer
);


actionSilence.addEventListener(
    "click",
    remainSilent
);


/* =====================================================
   INICIALIZAÇÃO
===================================================== */

updateClock();
updateUI();

console.log(
    "LAST CHANCE :: SYSTEM READY"
);
