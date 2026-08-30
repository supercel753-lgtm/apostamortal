package game;

import java.util.Random;

/**
 * ================================================================
 *                       GAME DEVICE
 * ================================================================
 *
 * Máquina central de LAST CHANCE.
 *
 * Responsável por:
 *
 * - Relógio da partida
 * - Instabilidade
 * - Estado da máquina
 * - Monitoramento
 * - Eventos
 * - Sistema de recompensa
 * - Controle das fases da partida
 * - Registro das ações dos jogadores
 * - Mensagens automáticas
 * - Falhas e comportamentos estranhos
 *
 * A máquina não é necessariamente "má".
 * Ela simplesmente executa as regras para as quais foi criada.
 *
 * O problema é que ninguém sabe exatamente quais são essas
 * regras.
 *
 * ================================================================
 */

public class GameDevice {

    // ============================================================
    // CONFIGURAÇÕES
    // ============================================================

    private static final int MAX_INSTABILITY = 100;

    private static final int MAX_STRESS = 100;

    private static final int MAX_ROUNDS = 100;

    private static final int START_HOUR = 2;

    private static final int START_MINUTE = 13;

    private static final int END_HOUR = 4;

    private static final int END_MINUTE = 0;

    // ============================================================
    // ALEATORIEDADE
    // ============================================================

    private final Random random;

    // ============================================================
    // RELÓGIO
    // ============================================================

    private int hour;

    private int minute;

    private int elapsedMinutes;

    // ============================================================
    // INSTABILIDADE
    // ============================================================

    private int instability;

    private int malfunctionCount;

    private int systemWarnings;

    // ============================================================
    // MONITORAMENTO
    // ============================================================

    private int playerStress;

    private int aliceStress;

    private int playerActions;

    private int aliceActions;

    private int trucoCalls;

    private int retreats;

    // ============================================================
    // ESTADO
    // ============================================================

    private boolean active;

    private boolean locked;

    private boolean malfunctioning;

    private boolean rewardAvailable;

    private boolean rewardConfirmed;

    private boolean monitoring;

    private boolean timerRunning;

    private boolean gameComplete;

    private boolean secretMode;

    // ============================================================
    // RECOMPENSA
    // ============================================================

    private String rewardDescription;

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public GameDevice() {

        random = new Random();

        reset();
    }

    // ============================================================
    // RESET
    // ============================================================

    public void reset() {

        hour = START_HOUR;

        minute = START_MINUTE;

        elapsedMinutes = 0;

        instability = 0;

        malfunctionCount = 0;

        systemWarnings = 0;

        playerStress = 0;

        aliceStress = 0;

        playerActions = 0;

        aliceActions = 0;

        trucoCalls = 0;

        retreats = 0;

        active = true;

        locked = false;

        malfunctioning = false;

        rewardAvailable = true;

        rewardConfirmed = false;

        monitoring = true;

        timerRunning = true;

        gameComplete = false;

        secretMode = false;

        rewardDescription =
            "LIBERDADE";
    }

    // ============================================================
    // AVANÇAR RELÓGIO
    // ============================================================

    public void advanceTime(int minutes) {

        if (!timerRunning) {

            return;
        }

        if (minutes < 0) {

            return;
        }

        minute += minutes;

        elapsedMinutes += minutes;

        while (minute >= 60) {

            minute -= 60;

            hour++;
        }

        if (isTimeOver()) {

            timerRunning = false;

            gameComplete = true;

            hour = END_HOUR;

            minute = END_MINUTE;
        }

        updateInstabilityFromTime(minutes);
    }

    // ============================================================
    // INSTABILIDADE PELO TEMPO
    // ============================================================

    private void updateInstabilityFromTime(
        int minutes
    ) {

        int increase =
            Math.max(
                0,
                minutes / 5
            );

        if (increase > 0) {

            increaseInstability(
                increase
            );
        }
    }

    // ============================================================
    // VERIFICAR TEMPO
    // ============================================================

    public boolean isTimeOver() {

        return
            hour > END_HOUR ||
            (
                hour == END_HOUR &&
                minute >= END_MINUTE
            );
    }

    // ============================================================
    // TEMPO RESTANTE
    // ============================================================

    public int getRemainingMinutes() {

        int current =
            (hour * 60) + minute;

        int end =
            (END_HOUR * 60) + END_MINUTE;

        return Math.max(
            0,
            end - current
        );
    }

    // ============================================================
    // FORMATO DO RELÓGIO
    // ============================================================

    public String getClock() {

        return String.format(
            "%02d:%02d",
            hour,
            minute
        );
    }

    // ============================================================
    // INSTABILIDADE
    // ============================================================

    public void increaseInstability(
        int amount
    ) {

        if (amount <= 0) {

            return;
        }

        instability += amount;

        if (
            instability >
            MAX_INSTABILITY
        ) {

            instability =
                MAX_INSTABILITY;
        }

        checkForMalfunction();
    }

    // ============================================================
    // DIMINUIR INSTABILIDADE
    // ============================================================

    public void decreaseInstability(
        int amount
    ) {

        if (amount <= 0) {

            return;
        }

        instability -= amount;

        if (instability < 0) {

            instability = 0;
        }

        if (
            instability < 70
        ) {

            malfunctioning = false;
        }
    }

    // ============================================================
    // VERIFICAR FALHAS
    // ============================================================

    private void checkForMalfunction() {

        if (instability < 40) {

            return;
        }

        int chance =
            random.nextInt(100);

        int threshold;

        if (instability >= 90) {

            threshold = 65;

        } else if (instability >= 70) {

            threshold = 40;

        } else {

            threshold = 15;
        }

        if (chance < threshold) {

            malfunction();
        }
    }

    // ============================================================
    // FALHA
    // ============================================================

    private void malfunction() {

        malfunctionCount++;

        malfunctioning = true;

        systemWarnings++;

        int type =
            random.nextInt(6);

        switch (type) {

            case 0:

                /*
                 * Relógio pode atrasar.
                 */

                minute += 1;

                if (minute >= 60) {

                    minute = 0;

                    hour++;
                }

                break;

            case 1:

                /*
                 * Relógio pode adiantar.
                 */

                minute += 2;

                if (minute >= 60) {

                    minute -= 60;

                    hour++;
                }

                break;

            case 2:

                /*
                 * Sistema entra em modo estranho.
                 */

                secretMode = true;

                break;

            case 3:

                /*
                 * Apenas aumenta o estresse.
                 */

                increaseStress(
                    true,
                    5
                );

                increaseStress(
                    false,
                    5
                );

                break;

            case 4:

                /*
                 * Sistema bloqueia momentaneamente.
                 */

                locked = true;

                break;

            case 5:

                /*
                 * Máquina volta ao normal,
                 * mas registra a falha.
                 */

                malfunctioning = false;

                break;

            default:

                break;
        }
    }

    // ============================================================
    // DESBLOQUEAR
    // ============================================================

    public void unlock() {

        locked = false;
    }

    // ============================================================
    // REGISTRAR AÇÃO DO JOGADOR
    // ============================================================

    public void registerPlayerAction() {

        playerActions++;

        increaseStress(
            true,
            1
        );
    }

    // ============================================================
    // REGISTRAR AÇÃO DA ALICE
    // ============================================================

    public void registerAliceAction() {

        aliceActions++;

        increaseStress(
            false,
            1
        );
    }

    // ============================================================
    // REGISTRAR TRUCO
    // ============================================================

    public void registerTruco(
        int value,
        boolean playerCalled
    ) {

        trucoCalls++;

        /*
         * Quanto maior o valor da aposta,
         * maior a instabilidade.
         */

        switch (value) {

            case 3:

                increaseInstability(3);

                break;

            case 6:

                increaseInstability(6);

                break;

            case 9:

                increaseInstability(10);

                break;

            case 12:

                increaseInstability(15);

                break;

            default:

                increaseInstability(1);

                break;
        }

        if (playerCalled) {

            increaseStress(
                true,
                value / 3
            );

        } else {

            increaseStress(
                false,
                value / 3
            );
        }
    }

    // ============================================================
    // REGISTRAR FUGA
    // ============================================================

    public void registerRetreat(
        boolean player
    ) {

        retreats++;

        increaseInstability(2);

        if (player) {

            increaseStress(
                true,
                3
            );

        } else {

            increaseStress(
                false,
                3
            );
        }
    }

    // ============================================================
    // ESTRESSE
    // ============================================================

    private void increaseStress(
        boolean player,
        int amount
    ) {

        if (player) {

            playerStress += amount;

            if (
                playerStress >
                MAX_STRESS
            ) {

                playerStress =
                    MAX_STRESS;
            }

        } else {

            aliceStress += amount;

            if (
                aliceStress >
                MAX_STRESS
            ) {

                aliceStress =
                    MAX_STRESS;
            }
        }
    }

    // ============================================================
    // REDUZIR ESTRESSE
    // ============================================================

    public void reduceStress(
        boolean player,
        int amount
    ) {

        if (amount <= 0) {

            return;
        }

        if (player) {

            playerStress -= amount;

            if (playerStress < 0) {

                playerStress = 0;
            }

        } else {

            aliceStress -= amount;

            if (aliceStress < 0) {

                aliceStress = 0;
            }
        }
    }

    // ============================================================
    // MONITORAMENTO
    // ============================================================

    public String monitoringReport() {

        if (!monitoring) {

            return
                "MONITORAMENTO DESATIVADO.";
        }

        return
            "MONITORAMENTO ATIVO\n" +

            "JOGADOR: " +
            playerStress +
            "%\n" +

            "ALICE: " +
            aliceStress +
            "%\n" +

            "INSTABILIDADE: " +
            instability +
            "%";
    }

    // ============================================================
    // MENSAGEM AUTOMÁTICA
    // ============================================================

    public String automaticMessage() {

        if (gameComplete) {

            return
                "PARTIDA ENCERRADA.";
        }

        if (locked) {

            return
                "SISTEMA BLOQUEADO.";
        }

        if (instability >= 90) {

            return
                "ERRO CRÍTICO.\n" +
                "ESTABILIDADE DO SISTEMA: " +
                instability +
                "%.";
        }

        if (instability >= 70) {

            return
                "AVISO.\n" +
                "COMPORTAMENTO IRREGULAR DETECTADO.";
        }

        if (instability >= 40) {

            return
                "ATENÇÃO.\n" +
                "PEQUENAS INCONSISTÊNCIAS DETECTADAS.";
        }

        return
            "SISTEMA OPERANDO NORMALMENTE.";
    }

    // ============================================================
    // MENSAGEM ALEATÓRIA
    // ============================================================

    public String randomMessage() {

        int option =
            random.nextInt(10);

        switch (option) {

            case 0:

                return
                    "NÃO TENTE ENGANAR A MÁQUINA.";

            case 1:

                return
                    "O TEMPO CONTINUA PASSANDO.";

            case 2:

                return
                    "RESULTADO REGISTRADO.";

            case 3:

                return
                    "COMPORTAMENTO ANALISADO.";

            case 4:

                return
                    "CONTINUE.";

            case 5:

                return
                    "VOCÊ AINDA TEM ESCOLHAS.";

            case 6:

                return
                    "A PARTIDA NÃO PODE SER CANCELADA.";

            case 7:

                return
                    "RECOMPENSA DISPONÍVEL PARA O VENCEDOR.";

            case 8:

                return
                    "NÃO CONFIE EM INFORMAÇÕES NÃO VERIFICADAS.";

            default:

                return
                    "AGUARDANDO PRÓXIMA AÇÃO.";
        }
    }

    // ============================================================
    // RECOMPENSA
    // ============================================================

    public String getReward() {

        return rewardDescription;
    }

    public boolean isRewardAvailable() {

        return rewardAvailable;
    }

    public void confirmReward() {

        rewardConfirmed = true;
    }

    /*
     * A máquina pode começar dizendo que existe uma recompensa,
     * mas o jogo pode descobrir depois que "liberdade" não significa
     * necessariamente sair da sala.
     */

    public void changeReward(
        String newReward
    ) {

        if (
            newReward == null ||
            newReward.isBlank()
        ) {

            return;
        }

        rewardDescription =
            newReward;
    }

    // ============================================================
    // ATIVAR MODO SECRETO
    // ============================================================

    public void activateSecretMode() {

        secretMode = true;

        increaseInstability(10);
    }

    // ============================================================
    // DESATIVAR MONITORAMENTO
    // ============================================================

    public void disableMonitoring() {

        monitoring = false;
    }

    // ============================================================
    // ATIVAR MONITORAMENTO
    // ============================================================

    public void enableMonitoring() {

        monitoring = true;
    }

    // ============================================================
    // ENCERRAR PARTIDA
    // ============================================================

    public void endGame() {

        gameComplete = true;

        timerRunning = false;

        active = false;
    }

    // ============================================================
    // STATUS COMPLETO
    // ============================================================

    public String getFullStatus() {

        StringBuilder status =
            new StringBuilder();

        status.append(
            "==============================\n"
        );

        status.append(
            "       STATUS DA MÁQUINA\n"
        );

        status.append(
            "==============================\n"
        );

        status.append(
            "RELÓGIO: "
        );

        status.append(
            getClock()
        );

        status.append("\n");

        status.append(
            "TEMPO RESTANTE: "
        );

        status.append(
            getRemainingMinutes()
        );

        status.append(
            " MIN\n"
        );

        status.append(
            "INSTABILIDADE: "
        );

        status.append(
            instability
        );

        status.append(
            "%\n"
        );

        status.append(
            "ESTRESSE JOGADOR: "
        );

        status.append(
            playerStress
        );

        status.append(
            "%\n"
        );

        status.append(
            "ESTRESSE ALICE: "
        );

        status.append(
            aliceStress
        );

        status.append(
            "%\n"
        );

        status.append(
            "TRUCOS: "
        );

        status.append(
            trucoCalls
        );

        status.append(
            "\n"
        );

        status.append(
            "FALHAS: "
        );

        status.append(
            malfunctionCount
        );

        status.append(
            "\n"
        );

        status.append(
            "=============================="
        );

        return status.toString();
    }

    // ============================================================
    // GETTERS
    // ============================================================

    public int getHour() {

        return hour;
    }

    public int getMinute() {

        return minute;
    }

    public int getElapsedMinutes() {

        return elapsedMinutes;
    }

    public int getInstability() {

        return instability;
    }

    public int getMalfunctionCount() {

        return malfunctionCount;
    }

    public int getSystemWarnings() {

        return systemWarnings;
    }

    public int getPlayerStress() {

        return playerStress;
    }

    public int getAliceStress() {

        return aliceStress;
    }

    public int getPlayerActions() {

        return playerActions;
    }

    public int getAliceActions() {

        return aliceActions;
    }

    public int getTrucoCalls() {

        return trucoCalls;
    }

    public int getRetreats() {

        return retreats;
    }

    public boolean isActive() {

        return active;
    }

    public boolean isLocked() {

        return locked;
    }

    public boolean isMalfunctioning() {

        return malfunctioning;
    }

    public boolean isMonitoring() {

        return monitoring;
    }

    public boolean isTimerRunning() {

        return timerRunning;
    }

    public boolean isGameComplete() {

        return gameComplete;
    }

    public boolean isSecretMode() {

        return secretMode;
    }

    public boolean isRewardConfirmed() {

        return rewardConfirmed;
    }
}
