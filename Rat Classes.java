
    ratRNG = rng.nextInt(31) + (ratskilled * 2);
    
    {
        if ((ratRNG >= 5) && (ratRNG < 10)) {
            // HP 1, ATK 1 == 2
            ratHP = rng.nextInt(10) + (1 * ratsKilled);
            ratATK = rng.nextInt(10) + (1 * ratsKilled) / 3;
            return "baby ";
        } else if ((ratRNG >= 10) && (ratRNG < 15)) {
            // HP 2, ATK 2 == 4
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
            return "weak ";
        } else if ((ratRNG >= 15) && (ratRNG < 20)) {
            // HP 2, ATK 3 == 5
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "small ";
        } else if ((ratRNG >= 20) && (ratRNG < 25)) {
            // HP 3, ATK 3 == 6
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "squeeking ";
        } else if ((ratRNG >= 25) && (ratRNG < 30)) {
            // HP 2, ATK 4 == 6
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "hungry ";
        } else if ((ratRNG >= 30) && (ratRNG < 35)) {
            // HP 2, ATK 5 == 7
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "homeless ";
        } else if ((ratRNG >= 35) && (ratRNG < 40)) {
            // HP 2, ATK 6 == 8
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "smelly ";
        } else if ((ratRNG >= 40) && (ratRNG < 45)) {
            // HP 5, ATK 3 == 8
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "hairy ";
        } else if ((ratRNG >= 45) && (ratRNG < 50)) {
            // HP 5, ATK 4 == 8
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "big ";
        } else if ((ratRNG >= 50) && (ratRNG < 55)) {
            // HP 4, ATK 5 == 9
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "yellow ";
        } else if ((ratRNG >= 55) && (ratRNG < 60)) {
            // HP 3, ATK 6 == 9
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "flying ";
        } else if ((ratRNG >= 60) && (ratRNG < 65)) {
            // HP 4, ATK 5 == 9
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "boxer ";
        } else if ((ratRNG >= 65) && (ratRNG < 70)) {
            // HP 4, ATK -4 == 0
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (-4 * ratsKilled) / 3;
            return "canadian ";
        } else if ((ratRNG >= 70) && (ratRNG < 75)) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "lab ";
        } else if ((ratRNG >= 75) && (ratRNG < 80)) {
            // HP 1, ATK 9 == 10
            ratHP = rng.nextInt(10) + (1 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "cannon ";
        } else if ((ratRNG >= 80) && (ratRNG < 90)) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "cyborg ";
        } else if ((ratRNG >= 85) && (ratRNG < 95)) {
            // HP 4, ATK 6 == 10
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "computer ";
        } else if ((ratRNG >= 90) && (ratRNG < 100)) {
            // HP 7, ATK 4 == 11
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "robo ";
        } else if ((ratRNG >= 95) && (ratRNG < 105)) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "five toe'd ";
        } else if ((ratRNG >= 100) && (ratRNG < 110)) {
            // HP 4, ATK 6 == 10
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "indian ";
        } else if ((ratRNG >= 105) && (ratRNG < 110)) {
            // HP 6, ATK 5 == 11
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "sushi ";
        } else if ((ratRNG >= 110) && (ratRNG < 115)) {
            // HP 3, ATK 8 == 11
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "canadian (mad) ";
        } else if ((ratRNG >= 115) && (ratRNG < 120)) {
            // HP 3, ATK 8 == 11
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "karate ";
        } else if ((ratRNG >= 120) && (ratRNG < 125)) {
            // HP 11, ATK 6 == 11 + (6/2)
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(2) * (6 * ratsKilled) / 3;
            return "wood ";
        } else if ((ratRNG >= 125) && (ratRNG < 130)) {
            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "road ";
        } else if ((ratRNG >= 130) && (ratRNG < 135)) {
            // HP 6, ATK 6 == 12
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "mullet ";
        } else if ((ratRNG >= 135) && (ratRNG < 140)) {
            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "chinese ";
        } else if ((ratRNG >= 140) && (ratRNG < 145)) {
            // HP 2, ATK 10 == 12
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "glass ";
        } else if ((ratRNG >= 145) && (ratRNG < 150)) {
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "DK ";
        } else if ((ratRNG >= 150) && (ratRNG < 155)) {
            // HP ?, ATK ? == ?
            ratHP = rng.nextInt((19) - 6) * (2 * ratsKilled);
            ratATK = rng.nextInt((19) - 6) * (2*ratsKilled);
            return "random ";
        } else if ((ratRNG >= 155) && (ratRNG < 160)) {
            // HP 7, ATK 5 == 12
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "ocean ";
        } else if ((ratRNG >= 160) && (ratRNG < 165)) {
            // HP 4, ATK 8 == 12
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "poison ";
        } else if ((ratRNG >= 165) && (ratRNG < 170)) {
            // HP 5, ATK 7  == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "gym ";
        } else if ((ratRNG >= 170) && (ratRNG < 175)) {
            // HP 10, ATK 2  == 12
            ratHP = rng.nextInt(10) + (10 * ratsKilled);
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
            return "cross country ";
        } else if ((ratRNG >= 175) && (ratRNG < 180)) {
            // HP 8, ATK 4  == 12
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "ice ";
        } else if ((ratRNG >= 180) && (ratRNG < 185)) {
            // HP 4, ATK 8  == 12
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "fire ";
        } else if ((ratRNG >= 185) && (ratRNG < 190)) {
            // HP 2, ATK 11 == 13
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (11 * ratsKilled) / 3;
            return "space ";
        } else if ((ratRNG >= 190) && (ratRNG < 195)) {
            // HP 2, ATK 1 == 3
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (1 * ratsKilled) / 3;
            return "xander ";
        } else if ((ratRNG >= 195) && (ratRNG < 200)) {
            // HP 6, ATK 7 == 13
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "russian ";
        } else if ((ratRNG >= 200) && (ratRNG < 205)) {
            // HP 7, ATK 6 == 13
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "music ";
        } else if ((ratRNG >= 205) && (ratRNG < 210)) {
            // HP 8, ATK 5 == 13
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "iron ";
        } else if ((ratRNG >= 210) && (ratRNG < 215)) {
            // HP 5, ATK 8 == 13
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "punk ";
        } else if ((ratRNG >= 215) && (ratRNG < 220)) {
            // HP 7, ATK 7 == 14
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "screaming ";
        } else if ((ratRNG >= 220) && (ratRNG < 225)) {
            // HP 15, ATK -1 == 14
            ratHP = rng.nextInt(10) + (15 * ratsKilled);
            ratATK = rng.nextInt(10) + (-1 * ratsKilled) / 3;
            return "chair ";
        } else if ((ratRNG >= 225) && (ratRNG < 230)) {
            // HP 6, ATK 8 == 14
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "officer ";
        } else if ((ratRNG >= 230) && (ratRNG < 235)) {
            // HP 4, ATK 10 == 14
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "plauge ";
        } else if ((ratRNG >= 235) && (ratRNG < 240)) {
            // HP 12, ATK 2 == 14
            ratHP = rng.nextInt(10) + (12 * ratsKilled);
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
            return "fat ";
        } else if ((ratRNG >= 240) && (ratRNG < 245)) {
            // HP 7, ATK 7 == 14
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "sir ";
        } else if ((ratRNG >= 245) && (ratRNG < 250)) {
            // HP 8, ATK 7 == 15
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "bad ";
        } else if ((ratRNG >= 250) && (ratRNG < 255)) {
            // HP 6, ATK 9 == 15
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "business ";
        } else if ((ratRNG >= 255) && (ratRNG < 260)) {
            // HP 6, ATK 9 == 15
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "man ";
        } else if ((ratRNG >= 260) && (ratRNG < 265)) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "super ";
        } else if ((ratRNG >= 265) && (ratRNG < 270)) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "philadelphia ";
        } else if ((ratRNG >= 270) && (ratRNG < 275)) {
            // HP 12, ATK 5 == 17
            ratHP = rng.nextInt(10) + (12 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "whale ";
        } else if ((ratRNG >= 275) && (ratRNG < 280)) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "royal ";
        } else if ((ratRNG >= 280) && (ratRNG < 285)) {
            // HP 3, ATK 13 == 16
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (13 * ratsKilled) / 3;
            return "radioactive ";
        } else if ((ratRNG >= 285) && (ratRNG < 290)) {
            // HP 16, ATK 0 == 16
            ratHP = rng.nextInt(10) + (16 * ratsKilled);
            ratATK = rng.nextInt(10) + (0 * ratsKilled) / 3;
            return "statue ";
        } else if ((ratRNG >= 290) && (ratRNG < 295)) {
            // HP -16, ATK 0 == -16
            ratHP = rng.nextInt(10) + (-16 * ratsKilled);
            ratATK = rng.nextInt(10) + (0 * ratsKilled) / 3;
            return "dead ";
        } else if ((ratRNG >= 295) && (ratRNG < 300)) {
            // HP 6, ATK 10 == 16
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "candy ";
        } else if ((ratRNG >= 300) && (ratRNG < 305)) {
            // HP 8, ATK 9 == 17
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "regal ";
        } else if ((ratRNG >= 305) && (ratRNG < 310)) {
            // HP 2, ATK 15 == 17
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (17 * ratsKilled) / 3;
            return "kamikaze ";
        } else if ((ratRNG >= 310) && (ratRNG < 315)) {
            // HP 9, ATK 8 == 17
            ratHP = rng.nextInt(10) + (9 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "Emeile ";
        } else if ((ratRNG >= 315) && (ratRNG < 320)) {
            // HP 9, ATK 9 == 18
            ratHP = rng.nextInt(10) + (9 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "math ";
        } else if ((ratRNG >= 320) && (ratRNG < 325)) {
            // HP 8, ATK 11 == 19
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (11 * ratsKilled) / 3;
            return "gorilla ";
        } else if ((ratRNG >= 325) && (ratRNG < 330)) {
            // HP 10, ATK 10 == 20
            ratHP = rng.nextInt(10) + (10 * ratsKilled);
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "Remy ";
        } else if ((ratRNG >= 330) && (ratRNG < 335)) {
            // HP 15, ATK 15 == 30
            ratHP = rng.nextInt(10) + (15 * ratsKilled);
            ratATK = rng.nextInt(10) + (15 * ratsKilled) / 3;
            return "Emporer ";
        } else if ((ratRNG >= 335) && (ratRNG < 340)) {
            // HP 200, ATK 20 == 220
            ratHP = rng.nextInt(10) + (200 * ratsKilled);
            ratATK = rng.nextInt(10) + (20 * ratsKilled) / 3;
            return "the last ";
         } else {// HP 3, ATK 2 == 5
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "";}
            
            

            // HP 4, ATK -4 == 0
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (-4 * ratsKilled) / 3;
            return "canadian ";
            // steal syrup button: turns

            // HP 1, ATK 1 == 2
            ratHP = rng.nextInt(10) + (1 * ratsKilled);
            ratATK = rng.nextInt(10) + (1 * ratsKilled) / 3;
            return "baby ";

            // HP 2, ATK 2 == 4
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
            return "weak ";

            // HP 2, ATK 3 == 5
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "small ";

            // HP 3, ATK 3 == 6
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "squeeking ";

            // HP 2, ATK 4 == 6
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "hungry ";

            // HP 2, ATK 5 == 7
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "homeless ";

            // HP 2, ATK 6 == 8
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "smelly ";

            // HP 5, ATK 3 == 8
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "hairy ";

            // HP 5, ATK 4 == 8
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "big ";

            // HP 4, ATK 5 == 9
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "yellow ";

            // HP 3, ATK 6 == 9
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "flying ";

            // HP 4, ATK 5 == 9
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "boxer ";

            // HP 5, ATK 4 == 9
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "banana ";

            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "lab ";

            // HP 1, ATK 9 == 10
            ratHP = rng.nextInt(10) + (1 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "cannon ";

            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "cyborg ";

            // HP 4, ATK 6 == 10
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "computer ";

            // HP 6, ATK 4 == 10
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "robo ";

            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "five toe'd ";

            // HP 4, ATK 6 == 10
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "indian ";

            // HP 6, ATK 5 == 11
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "sushi ";

            // HP 3, ATK 8 == 11
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "canadian (mad) ";

            // HP 3, ATK 8 == 11
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "karate ";

            // HP 11, ATK 6 == 11 + (6/2)
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(2) * (6 * ratsKilled) / 3;
            return "wood ";

            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "road ";

            // HP 6, ATK 6 == 12
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "mullet ";

            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "chinese ";

            // HP 2, ATK 10 == 12
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "glass ";

            // HP 6, ATK 6 == 12
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "DK ";

            // HP ?, ATK ? == ?
            ratHP = rng.nextInt((21) - 6) * (2 * ratsKilled);
            ratATK = rng.nextInt((21) - 6) * (2*ratsKilled);
            return "random ";

            // HP 7, ATK 5 == 12
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "ocean ";

            // HP 4, ATK 8 == 12
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "poison ";

            // HP 5, ATK 7  == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "gym ";

            // HP 10, ATK 2  == 12
            ratHP = rng.nextInt(10) + (10 * ratsKilled);
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
            return "cross country ";

            // HP 8, ATK 4  == 12
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "ice ";

            // HP 4, ATK 8  == 12
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "fire ";

            // HP 2, ATK 1 == 3
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (1 * ratsKilled) / 3;
            return "xander ";

            // HP 2, ATK 11 == 13
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (11 * ratsKilled) / 3;
            return "space ";

            // HP 6, ATK 7 == 13
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "russian ";

            // HP 7, ATK 6 == 13
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "music ";

            // HP 8, ATK 5 == 13
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "iron ";

            // HP 5, ATK 8 == 13
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "punk ";

            // HP 7, ATK 7 == 14
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "screaming ";

            // HP 15, ATK -1 == 14
            ratHP = rng.nextInt(10) + (15 * ratsKilled);
            ratATK = rng.nextInt(10) + (-1 * ratsKilled) / 3;
            return "chair ";

            // HP 6, ATK 8 == 14
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "officer ";

            // HP 4, ATK 10 == 14
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "plauge ";

            // HP 12, ATK 2 == 14
            ratHP = rng.nextInt(10) + (12 * ratsKilled);
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
            return "fat ";

            // HP 7, ATK 7 == 14
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "sir ";

            // HP 8, ATK 7 == 15
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "bad ";

            // HP 6, ATK 9 == 15
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "business ";

            // HP 6, ATK 9 == 15
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "man ";

            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "super ";

            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "philadelphia ";

            // HP 12, ATK 5 == 17
            ratHP = rng.nextInt(10) + (12 * ratsKilled);
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "whale ";

            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "royal ";

            // HP 3, ATK 13 == 16
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            ratATK = rng.nextInt(10) + (13 * ratsKilled) / 3;
            return "radioactive ";

            // HP 16, ATK 0 == 16
            ratHP = rng.nextInt(10) + (16 * ratsKilled);
            ratATK = rng.nextInt(10) + (0 * ratsKilled) / 3;
            return "statue ";

            // HP -16, ATK 0 == -16
            ratHP = rng.nextInt(10) + (-16 * ratsKilled);
            ratATK = rng.nextInt(10) + (0 * ratsKilled) / 3;
            return "dead ";

            // HP 6, ATK 10 == 16
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "candy ";

            // HP 8, ATK 9 == 17
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "regal ";

            // HP 2, ATK 15 == 17
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            ratATK = rng.nextInt(10) + (17 * ratsKilled) / 3;
            return "kamikaze ";

            // HP 9, ATK 8 == 17
            ratHP = rng.nextInt(10) + (9 * ratsKilled);
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "Emeile ";

            // HP 9, ATK 9 == 18
            ratHP = rng.nextInt(10) + (9 * ratsKilled);
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "math ";

            // HP 8, ATK 11 == 19
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            ratATK = rng.nextInt(10) + (11 * ratsKilled) / 3;
            return "gorilla ";

            // HP 10, ATK 10 == 20
            ratHP = rng.nextInt(10) + (10 * ratsKilled);
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "Remy ";

            // HP 15, ATK 15 == 30
            ratHP = rng.nextInt(10) + (15 * ratsKilled);
            ratATK = rng.nextInt(10) + (15 * ratsKilled) / 3;
            return "Emporer ";

            // HP 200, ATK 20 == 220
            ratHP = rng.nextInt(10) + (200 * ratsKilled);
            ratATK = rng.nextInt(10) + (20 * ratsKilled) / 3;
            return "the last ";


// Base Rat

            // HP 3, ATK 2 == 5
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "";