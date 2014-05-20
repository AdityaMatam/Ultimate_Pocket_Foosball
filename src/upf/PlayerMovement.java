package upf;

public class PlayerMovement
{
        int[] x1 = { 80, 136, 188, 57, 110, 159, 210, 108, 163, 136 };
        int[] x2 = x1;
        int[] y = { 125, 155, 195, 235, 291, 330, 370, 402 };
        static int vP1=0, vP2=0;

        public void player1Position(int i)
        {
                if (i < 3)
                        Game1.player1[i].setLocation(x1[i], y[2]);
                else if (i < 7)
                        Game1.player1[i].setLocation(x1[i], y[4]);
                else if (i < 9)
                        Game1.player1[i].setLocation(x1[i], y[6]);
                else
                        Game1.player1[i].setLocation(x1[i], y[7]);
        }

        public void player2Position(int i)
        {
                if (i < 3)
                        Game1.player2[i].setLocation(x2[i], y[5]);
                else if (i < 7)
                        Game1.player2[i].setLocation(x2[i], y[3]);
                else if (i < 9)
                        Game1.player2[i].setLocation(x2[i], y[1]);
                else
                        Game1.player2[i].setLocation(x2[i], y[0]);
        }

        public void p1Left()
        {
                for (int i = 0; i != 10; i++)
                {
                        x1[i] -= 3;
                        vP1=-3;
                        player1Position(i);
                }
        }

        public void p1Right()
        {
                for (int i = 0; i != 10; i++)
                {
                        x1[i] += 3;
                        vP1=3;
                        player1Position(i);
                }
        }
        public void p2Left()
        {
                for (int i = 0; i != 10; i++)
                {
                        x2[i] -= 3;
                        player2Position(i);
                }
        }

        public void p2Right()
        {
                for (int i = 0; i != 10; i++)
                {
                        x2[i] += 3;
                        player2Position(i);
                }
        }

        public void move ()
        {
                if (Game1.keys[0]&& x1[3] > 22)  {p1Left();}
                if (Game1.keys[1]&& x1[6] < 252) {p1Right();}
                if (Game1.keys[2]&& x2[3] > 22)  {p2Left();}
                if (Game1.keys[3]&& x2[6] < 252) {p2Right();}
        }


}
